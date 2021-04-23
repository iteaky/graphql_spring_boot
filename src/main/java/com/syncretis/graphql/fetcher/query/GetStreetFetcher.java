package com.syncretis.graphql.fetcher.query;

import com.syncretis.graphql.config.graph.GraphQLFetcher;
import com.syncretis.graphql.config.graph.GraphQLType;
import com.syncretis.graphql.dataloader.StreetDataLoader;
import com.syncretis.graphql.dto.CityDTO;
import com.syncretis.graphql.dto.StreetDTO;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@GraphQLFetcher(type = GraphQLType.City, name = "streets")
public class GetStreetFetcher implements DataFetcher<CompletableFuture<List<StreetDTO>>> {

    private final StreetDataLoader streetDataLoader;

    @Override
    public CompletableFuture<List<StreetDTO>> get(DataFetchingEnvironment environment) {
        CityDTO source = environment.getSource();
        return streetDataLoader.loadMany(source.getStreetsIds());
    }
}