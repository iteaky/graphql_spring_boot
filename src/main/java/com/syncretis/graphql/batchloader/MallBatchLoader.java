package com.syncretis.graphql.batchloader;

import com.syncretis.graphql.model.dto.MallDTO;
import com.syncretis.graphql.service.MallService;
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
public class MallBatchLoader implements MappedBatchLoader<Long, MallDTO> {

    public final Executor taskExecutor;
    private final MallService mallService;

    @Override
    public CompletionStage<Map<Long, MallDTO>> load(Set<Long> keys) {
        return CompletableFuture.supplyAsync(
                () -> mallService.getAllByIds(keys), taskExecutor);
    }
}