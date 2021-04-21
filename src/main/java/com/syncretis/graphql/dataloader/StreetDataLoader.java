package com.syncretis.graphql.dataloader;

import com.syncretis.graphql.batchloader.StreetBatchLoader;
import com.syncretis.graphql.dto.StreetDTO;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.springframework.stereotype.Component;

@Component
public class StreetDataLoader extends DataLoader<Long, StreetDTO> {

    public StreetDataLoader(StreetBatchLoader batchLoadFunction) {
        super(batchLoadFunction, DataLoaderOptions.newOptions().setMaxBatchSize(1000));
    }
}
