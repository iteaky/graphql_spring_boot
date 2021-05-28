package com.syncretis.graphql.fetcher;

import com.syncretis.graphql.dto.CityDTO;
import com.syncretis.graphql.service.CityService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;


@Service
@AllArgsConstructor
public class GetCityFetcher implements DataFetcher<CityDTO> {

    private final CityService cityService;

    @Override
    public CityDTO get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long cityId = dataFetchingEnvironment.getArgument("cityId");
        return cityService.getById(cityId);
    }
}
