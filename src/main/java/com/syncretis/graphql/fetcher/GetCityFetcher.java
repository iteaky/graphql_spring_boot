package com.syncretis.graphql.fetcher;

import com.syncretis.graphql.config.graph.GraphQLFetcher;
import com.syncretis.graphql.config.graph.GraphQLType;
import com.syncretis.graphql.dataloader.CityDataLoader;
import com.syncretis.graphql.dto.CityDTO;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.dataloader.BatchLoaderEnvironment;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
@AllArgsConstructor
@GraphQLFetcher(type = GraphQLType.Query, name = "getCity")
public class GetCityFetcher implements DataFetcher<CompletableFuture<CityDTO>> {

    private final CityDataLoader cityDataLoader;

    @Override
    public CompletableFuture<CityDTO> get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long cityId = dataFetchingEnvironment.getArgument("cityId");
        return cityDataLoader.load(cityId);
    }
}
