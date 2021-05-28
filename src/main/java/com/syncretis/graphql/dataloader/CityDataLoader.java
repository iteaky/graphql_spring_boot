package com.syncretis.graphql.dataloader;

import com.syncretis.graphql.dto.CityDTO;
import com.syncretis.graphql.service.CityService;
import lombok.AllArgsConstructor;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class CityDataLoader {

    private final CityService cityService;

    public DataLoader<Long, CityDTO> init(){
        BatchLoader<Long, CityDTO> cityDTOBatchLoader = buildBatchLoader();
        return buildDataLoader(cityDTOBatchLoader);
    }

    private DataLoader<Long, CityDTO> buildDataLoader(BatchLoader<Long, CityDTO> cityDTOBatchLoader) {
        return new DataLoader<Long, CityDTO>(cityDTOBatchLoader);
    }

    private BatchLoader<Long, CityDTO> buildBatchLoader() {
        return list -> CompletableFuture.supplyAsync(() ->
                cityService.getAllByIds(list));
    }
}
