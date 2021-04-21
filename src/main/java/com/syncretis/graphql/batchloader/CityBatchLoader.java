package com.syncretis.graphql.batchloader;

import com.syncretis.graphql.dto.CityDTO;
import com.syncretis.graphql.service.CityService;
import lombok.AllArgsConstructor;
import org.dataloader.BatchLoader;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityBatchLoader implements BatchLoader<Long, CityDTO> {

    private final CityService cityService;

    @Override
    public CompletionStage<List<CityDTO>> load(List<Long> list) {
        List<CityDTO> byIds = cityService.getAllByIds(list).stream()
                .sorted(Comparator.comparingInt(entity ->
                        list.indexOf(entity.getId())))
                .collect(Collectors.toList());

        return CompletableFuture.supplyAsync(() -> byIds);
    }
}