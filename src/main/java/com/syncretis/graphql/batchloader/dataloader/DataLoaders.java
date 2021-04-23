package com.syncretis.graphql.batchloader.dataloader;

import com.syncretis.graphql.batchloader.CityBatchLoader;
import com.syncretis.graphql.batchloader.MallBatchLoader;
import com.syncretis.graphql.batchloader.StreetBatchLoader;
import com.syncretis.graphql.model.dto.CityDTO;
import com.syncretis.graphql.model.dto.MallDTO;
import com.syncretis.graphql.model.dto.StreetDTO;
import org.dataloader.DataLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoaders {

    @Bean
    DataLoader<Long, CityDTO> cityDataLoader(CityBatchLoader batchLoader) {
        return DataLoader.newMappedDataLoader(batchLoader);
    }

    @Bean
    DataLoader<Long, StreetDTO> streetDataLoader(StreetBatchLoader batchLoader) {
        return DataLoader.newMappedDataLoader(batchLoader);
    }

    @Bean
    DataLoader<Long, MallDTO> mallDataLoader(MallBatchLoader batchLoader) {
        return DataLoader.newMappedDataLoader(batchLoader);
    }
}
