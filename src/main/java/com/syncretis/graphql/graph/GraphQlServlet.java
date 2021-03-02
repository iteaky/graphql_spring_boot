package com.syncretis.graphql.graph;

import com.syncretis.graphql.fetcher.CityFetcher;
import com.syncretis.graphql.fetcher.StreetsFetcher;
import com.syncretis.graphql.service.ServiceLocator;
import graphql.Scalars;
import graphql.kickstart.servlet.GraphQLConfiguration;
import graphql.kickstart.servlet.GraphQLHttpServlet;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
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
    private CityFetcher cityFetcher;
    private StreetsFetcher streetsFetcher;

    @Override
    public void init() throws ServletException {
        cityFetcher = ServiceLocator.getFetcher(CityFetcher.class);
        streetsFetcher = ServiceLocator.getFetcher(StreetsFetcher.class);
        super.init();
    }

    @SneakyThrows
    @Override
    protected GraphQLConfiguration getConfiguration() {
        return GraphQLConfiguration.with(createSchema()).build();
    }

    private GraphQLSchema createSchema() throws FileNotFoundException {
        File schema = ResourceUtils.getFile("classpath:schema.graphql");

        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .scalar(Scalars.GraphQLLong)
                .type("Query", builder -> builder.dataFetcher("getAllCities", cityFetcher))
                .type("City", builder -> builder.dataFetcher("streets", streetsFetcher))
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    }

}
