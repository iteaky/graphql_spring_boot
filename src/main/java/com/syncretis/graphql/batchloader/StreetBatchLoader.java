package com.syncretis.graphql.batchloader;

import com.syncretis.graphql.dto.StreetDTO;
import com.syncretis.graphql.service.StreetService;
import lombok.AllArgsConstructor;
import org.dataloader.BatchLoader;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StreetBatchLoader implements BatchLoader<Long, StreetDTO> {

    public final Executor taskExecutor;
    private final StreetService streetService;

    @Override
    public CompletionStage<List<StreetDTO>> load(List<Long> list) {
        List<StreetDTO> byIds = streetService.getAllByIds(list).stream()
                .sorted(Comparator.comparingInt(entity ->
                        list.indexOf(entity.getId())))
                .collect(Collectors.toList());

        return CompletableFuture.supplyAsync(() -> byIds, taskExecutor);
    }
}