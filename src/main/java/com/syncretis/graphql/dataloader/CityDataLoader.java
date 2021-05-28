package com.syncretis.graphql.dataloader;

import com.syncretis.graphql.dto.CityDTO;
import com.syncretis.graphql.service.CityService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CityDataLoader {

    private final CityService cityService;
    private DataLoader<Long, CityDTO> dataLoader;

    public DataLoader<Long, CityDTO> init() {
        if (dataLoader == null) {
            BatchLoader<Long, CityDTO> cityDTOBatchLoader = buildBatchLoader();
            dataLoader = buildDataLoader(cityDTOBatchLoader);
        }
        return dataLoader;
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
