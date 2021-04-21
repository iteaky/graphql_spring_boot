package com.syncretis.graphql.batchloader;

import com.syncretis.graphql.dto.MallDTO;
import com.syncretis.graphql.service.MallService;
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
public class MallBatchLoader implements BatchLoader<Long, MallDTO> {

    private final MallService mallService;

    @Override
    public CompletionStage<List<MallDTO>> load(List<Long> list) {
        List<MallDTO> byIds = mallService.getAllByIds(list).stream()
                .sorted(Comparator.comparingInt(entity ->
                        list.indexOf(entity.getId())))
                .collect(Collectors.toList());

        return CompletableFuture.supplyAsync(() -> byIds);
    }
}