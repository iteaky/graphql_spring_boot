package com.syncretis.graphql.graph;

import com.syncretis.graphql.dataloader.CityDataLoader;
import com.syncretis.graphql.dataloader.MallDataLoader;
import com.syncretis.graphql.dataloader.StreetDataLoader;
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
    private StreetDataLoader streetDataLoader;
    private CityDataLoader cityDataLoader;
    private MallDataLoader mallDataLoader;

    @Override
    public void init() throws ServletException {
        getAllCityFetcher = ServiceLocator.getObject(GetAllCityFetcher.class);
        cityFetcher = ServiceLocator.getObject(GetCityFetcher.class);
        getStreetFetcher = ServiceLocator.getObject(GetStreetFetcher.class);
        mallFetcher = ServiceLocator.getObject(GetMallFetcher.class);
        streetDataLoader = ServiceLocator.getObject(StreetDataLoader.class);
        cityDataLoader = ServiceLocator.getObject(CityDataLoader.class);
        mallDataLoader = ServiceLocator.getObject(MallDataLoader.class);
        super.init();
    }

    @SneakyThrows
    @Override
    protected GraphQLConfiguration getConfiguration() {
        CustomGraphQLContextBuilder customGraphQLContextBuilder = new CustomGraphQLContextBuilder(streetDataLoader, cityDataLoader, mallDataLoader);
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
