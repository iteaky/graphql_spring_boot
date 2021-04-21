package com.syncretis.graphql.dataloader;

import com.syncretis.graphql.batchloader.CityBatchLoader;
import com.syncretis.graphql.dto.CityDTO;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.springframework.stereotype.Component;

@Component
public class CityDataLoader extends DataLoader<Long, CityDTO> {

    public CityDataLoader(CityBatchLoader batchLoadFunction) {
        super(batchLoadFunction, DataLoaderOptions.newOptions().setMaxBatchSize(1000));
    }
}
