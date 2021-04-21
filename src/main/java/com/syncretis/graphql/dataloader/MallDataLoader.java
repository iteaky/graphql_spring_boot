package com.syncretis.graphql.dataloader;

import com.syncretis.graphql.batchloader.MallBatchLoader;
import com.syncretis.graphql.dto.MallDTO;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.springframework.stereotype.Component;

@Component
public class MallDataLoader extends DataLoader<Long, MallDTO> {

    public MallDataLoader(MallBatchLoader batchLoadFunction) {
        super(batchLoadFunction, DataLoaderOptions.newOptions().setMaxBatchSize(1000));
    }
}
