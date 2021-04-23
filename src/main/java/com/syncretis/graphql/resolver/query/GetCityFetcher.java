package com.syncretis.graphql.resolver.query;

import com.syncretis.graphql.model.dto.CityDTO;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
@AllArgsConstructor
public class GetCityFetcher implements GraphQLQueryResolver {

    private final DataLoader<Long, CityDTO> cityDataLoader;

    public CompletableFuture<CityDTO> city(Long cityId) {
        return cityDataLoader.load(cityId);
    }
}
