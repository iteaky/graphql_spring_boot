package com.syncretis.graphql.config.graph;

import com.syncretis.graphql.config.graph.exceptions.CustomExecutionStrategyProvider;
import graphql.Scalars;
import graphql.kickstart.execution.GraphQLQueryInvoker;
import graphql.kickstart.servlet.GraphQLConfiguration;
import graphql.kickstart.servlet.GraphQLHttpServlet;
import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "GraphQL", urlPatterns = {"/graphql"}, loadOnStartup = 1)
@MultipartConfig
public class GraphQLServlet extends GraphQLHttpServlet {

    @Autowired
    private CustomGraphQLContextBuilder contextBuilder;

    @Autowired
    private CustomExecutionStrategyProvider provider;


    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        super.init(config);
    }

    @SneakyThrows
    @Override
    protected GraphQLConfiguration getConfiguration() {

        GraphQLQueryInvoker queryInvoker = GraphQLQueryInvoker.newBuilder()
                .withExecutionStrategyProvider(provider)
                .build();

        return GraphQLConfiguration
                .with(createSchema())
                .with(queryInvoker)
                .with(contextBuilder)
                .build();
    }

    private GraphQLSchema createSchema() throws FileNotFoundException {

        File file = ResourceUtils.getFile("classpath:schema_query.graphql");
        File upload = ResourceUtils.getFile("classpath:schema_mutation.graphql");
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(file);
        typeDefinitionRegistry.merge(schemaParser.parse(upload));

        RuntimeWiring runtimeWiring = buildRuntimeWiring();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

    }

    private RuntimeWiring buildRuntimeWiring() {
        List<ServiceLocator.FetcherData> list = ServiceLocator.getFetchers();
        Map<GraphQLType, List<ServiceLocator.FetcherData>> fetchersByType = list.stream()
                .collect(Collectors.groupingBy(ServiceLocator.FetcherData::getType));

        RuntimeWiring.Builder builder = RuntimeWiring.newRuntimeWiring()
                .scalar(Scalars.GraphQLLong)
                .scalar(ApolloScalars.Upload)
                .scalar(ApolloScalars.Upload);

        for (Map.Entry<GraphQLType, List<ServiceLocator.FetcherData>> entry : fetchersByType.entrySet()) {
            TypeRuntimeWiring.Builder typeBuilder = TypeRuntimeWiring.newTypeWiring(entry.getKey().name());
            entry.getValue()
                    .forEach(it -> typeBuilder.dataFetcher(it.getName(), it.getDataFetcher()));
            builder.type(typeBuilder.build());
        }
        return builder.build();
    }
}

