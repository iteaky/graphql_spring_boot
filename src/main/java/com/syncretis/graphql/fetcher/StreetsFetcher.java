package com.syncretis.graphql.fetcher;

import com.syncretis.graphql.dto.CityDTO;
import com.syncretis.graphql.dto.StreetDTO;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class StreetsFetcher implements DataFetcher<CompletableFuture<List<StreetDTO>>> {

    @Override
    public CompletableFuture<List<StreetDTO>> get(DataFetchingEnvironment environment) {
        CityDTO source = environment.getSource();
        DataLoader<Long, StreetDTO> streetDataLoader = environment.getDataLoader("streetDataLoader");
        return streetDataLoader.loadMany(source.getStreetsIds());
    }
}