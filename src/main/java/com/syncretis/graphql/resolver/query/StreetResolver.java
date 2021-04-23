package com.syncretis.graphql.resolver.query;

import com.syncretis.graphql.model.dto.CityDTO;
import com.syncretis.graphql.model.dto.StreetDTO;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class StreetResolver implements GraphQLResolver<CityDTO> {

    private final DataLoader<Long, StreetDTO> streetDataLoader;

    public CompletableFuture<List<StreetDTO>> streets(CityDTO city) {
        return streetDataLoader.loadMany(city.getStreetsIds());
    }
}