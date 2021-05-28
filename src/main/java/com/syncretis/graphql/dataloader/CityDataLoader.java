package com.syncretis.graphql.dataloader;

import com.syncretis.graphql.dto.CityDTO;
import com.syncretis.graphql.service.CityService;
import lombok.AllArgsConstructor;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CityDataLoader {

    private final CityService cityService;

    public DataLoader<Long, CityDTO> init() {
        BatchLoader<Long, CityDTO> cityDTOBatchLoader = buildBatchLoader();
        return buildDataLoader(cityDTOBatchLoader);
    }

    private DataLoader<Long, CityDTO> buildDataLoader(BatchLoader<Long, CityDTO> cityDTOBatchLoader) {
        return new DataLoader<Long, CityDTO>(cityDTOBatchLoader);
    }

    private BatchLoader<Long, CityDTO> buildBatchLoader() {
        return list -> CompletableFuture.supplyAsync(() ->
                cityService.getAllByIds(list).stream()
                        .sorted(Comparator.comparingInt(it -> list.indexOf(it.getId())))
                        .collect(Collectors.toList()));
    }
}
