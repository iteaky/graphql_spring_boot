package com.syncretis.graphql.dataloader;

import com.syncretis.graphql.dto.MallDTO;
import com.syncretis.graphql.service.MallService;
import lombok.AllArgsConstructor;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MallDataLoader {

    private final MallService mallService;

    public DataLoader<Long, MallDTO> init() {
        BatchLoader<Long, MallDTO> mallDTOBatchLoader = buildBatchLoader();
        return buildDataLoader(mallDTOBatchLoader);
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
