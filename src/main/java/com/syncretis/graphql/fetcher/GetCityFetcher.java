package com.syncretis.graphql.fetcher;

import com.syncretis.graphql.dto.CityDTO;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
@AllArgsConstructor
public class GetCityFetcher implements DataFetcher<CompletableFuture<CityDTO>> {

    @Override
    public CompletableFuture<CityDTO> get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long cityId = dataFetchingEnvironment.getArgument("cityId");
        DataLoader<Long, CityDTO> cityDataLoader = dataFetchingEnvironment.getDataLoader("cityDataLoader");
        return cityDataLoader.load(cityId);
    }
}
