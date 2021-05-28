package com.syncretis.graphql.dataloader;

import com.syncretis.graphql.dto.CityDTO;
import com.syncretis.graphql.dto.MallDTO;
import com.syncretis.graphql.service.MallService;
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
public class MallDataLoader {

    private final MallService mallService;
    private DataLoader<Long, MallDTO> dataLoader;

    public DataLoader<Long, MallDTO> init() {
        if (dataLoader == null) {
            BatchLoader<Long, MallDTO> mallDTOBatchLoader = buildBatchLoader();
            dataLoader = buildDataLoader(mallDTOBatchLoader);
        }
        return dataLoader;
    }

    private DataLoader<Long, MallDTO> buildDataLoader(BatchLoader<Long, MallDTO> mallDTOBatchLoader) {
        return new DataLoader<Long, MallDTO>(mallDTOBatchLoader);
    }

    private BatchLoader<Long, MallDTO> buildBatchLoader() {
        return list -> CompletableFuture.supplyAsync(() ->
                mallService.getAllByIds(list).stream()
                        .sorted(Comparator.comparingInt(it -> list.indexOf(it.getId())))
                        .collect(Collectors.toList()));
    }
}
