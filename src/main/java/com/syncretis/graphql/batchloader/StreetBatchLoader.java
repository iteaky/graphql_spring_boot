package com.syncretis.graphql.batchloader;

import com.syncretis.graphql.model.dto.StreetDTO;
import com.syncretis.graphql.service.StreetService;
import lombok.AllArgsConstructor;
import org.dataloader.MappedBatchLoader;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

@Service
@AllArgsConstructor
public class StreetBatchLoader implements MappedBatchLoader<Long, StreetDTO> {

    public final Executor taskExecutor;
    private final StreetService streetService;

    @Override
    public CompletionStage<Map<Long, StreetDTO>> load(Set<Long> keys) {
        return CompletableFuture.supplyAsync(
                () -> streetService.getAllByIds(keys), taskExecutor);    }
}