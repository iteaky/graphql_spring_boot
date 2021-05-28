package com.syncretis.graphql.dataloader;

import com.syncretis.graphql.dto.StreetDTO;
import com.syncretis.graphql.service.StreetService;
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
public class StreetDataLoader {

    private final StreetService streetService;
    private DataLoader<Long, StreetDTO> dataLoader;

    public DataLoader<Long, StreetDTO> init() {
        if (dataLoader == null) {
            BatchLoader<Long, StreetDTO> streetDTOBatchLoader = buildBatchLoader();
            dataLoader = buildDataLoader(streetDTOBatchLoader);
        }
        return dataLoader;
    }

    private DataLoader<Long, StreetDTO> buildDataLoader(BatchLoader<Long, StreetDTO> streetDTOBatchLoader) {
        return new DataLoader<Long, StreetDTO>(streetDTOBatchLoader);
    }

    private BatchLoader<Long, StreetDTO> buildBatchLoader() {
        return list -> CompletableFuture.supplyAsync(() ->
                streetService.findByIds(list).stream()
                        .sorted(Comparator.comparingInt(it -> list.indexOf(it.getId())))
                        .collect(Collectors.toList()));
    }
}
