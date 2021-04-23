package com.syncretis.graphql.batchloader;

import com.syncretis.graphql.model.dto.CityDTO;
import com.syncretis.graphql.service.CityService;
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
public class CityBatchLoader implements MappedBatchLoader<Long, CityDTO> {

    public final Executor taskExecutor;
    private final CityService cityService;

    @Override
    public CompletionStage<Map<Long, CityDTO>> load(Set<Long> keys) {
        return CompletableFuture.supplyAsync(
                () -> cityService.getAllByIds(keys), taskExecutor);
    }
}