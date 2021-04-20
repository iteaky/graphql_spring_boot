package com.syncretis.graphql.dataloader;

import com.syncretis.graphql.dto.StreetDTO;
import com.syncretis.graphql.service.StreetService;
import lombok.AllArgsConstructor;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class StreetDataLoader {

    private final StreetService streetService;

    public static DataLoader<Long, StreetDTO> streetDTODataLoader;


    @PostConstruct
    private void init(){
        BatchLoader<Long, StreetDTO> streetDTOBatchLoader = buildBatchLoader();
        this.streetDTODataLoader = buildDataLoader(streetDTOBatchLoader);
    }

    private DataLoader<Long, StreetDTO> buildDataLoader(BatchLoader<Long, StreetDTO> streetDTOBatchLoader) {
        return new DataLoader<Long, StreetDTO>(streetDTOBatchLoader);
    }

    private BatchLoader<Long, StreetDTO> buildBatchLoader() {
        return list -> CompletableFuture.supplyAsync(() ->
                streetService.findByIds(list));
    }
}
