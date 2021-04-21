package com.syncretis.graphql.graph;

import com.syncretis.graphql.fetcher.GetAllCityFetcher;
import com.syncretis.graphql.fetcher.GetCityFetcher;
import com.syncretis.graphql.fetcher.GetMallFetcher;
import com.syncretis.graphql.fetcher.GetStreetFetcher;
import com.syncretis.graphql.service.ServiceLocator;
import graphql.Scalars;
import graphql.kickstart.servlet.GraphQLConfiguration;
import graphql.kickstart.servlet.GraphQLHttpServlet;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.File;
import java.io.FileNotFoundException;

@WebServlet(name = "HelloServlet", urlPatterns = {"/graphql"}, loadOnStartup = 1)
public class GraphQlServlet extends GraphQLHttpServlet {
    private GetAllCityFetcher getAllCityFetcher;
    private GetStreetFetcher getStreetFetcher;
    private GetCityFetcher cityFetcher;
    private GetMallFetcher mallFetcher;

    @Override
    public void init() throws ServletException {
        getAllCityFetcher = ServiceLocator.getFetcher(GetAllCityFetcher.class);
        cityFetcher = ServiceLocator.getFetcher(GetCityFetcher.class);
        getStreetFetcher = ServiceLocator.getFetcher(GetStreetFetcher.class);
        mallFetcher = ServiceLocator.getFetcher(GetMallFetcher.class);
        super.init();
    }

    @SneakyThrows
    @Override
    protected GraphQLConfiguration getConfiguration() {
        CustomGraphQLContextBuilder customGraphQLContextBuilder = new CustomGraphQLContextBuilder();
        return GraphQLConfiguration
                .with(createSchema())
                .with(customGraphQLContextBuilder)
                .build();
    }

    private GraphQLSchema createSchema() throws FileNotFoundException {
        File schema = ResourceUtils.getFile("classpath:schema.graphql");

        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .scalar(Scalars.GraphQLLong)
                .type("Query", builder -> builder
                        .dataFetcher("getAllCities", getAllCityFetcher)
                        .dataFetcher("getCity", cityFetcher))
                .type("City", builder -> builder.dataFetcher("streets", getStreetFetcher))
                .type("Street", builder -> builder.dataFetcher("malls", mallFetcher))
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    }

}
