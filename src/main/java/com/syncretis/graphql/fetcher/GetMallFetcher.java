package com.syncretis.graphql.fetcher;

import com.syncretis.graphql.dto.CityDTO;
import com.syncretis.graphql.dto.MallDTO;
import com.syncretis.graphql.dto.StreetDTO;
import com.syncretis.graphql.repository.CityRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
@AllArgsConstructor
public class GetMallFetcher implements DataFetcher<CompletableFuture<List<MallDTO>>> {

    @Override
    public CompletableFuture<List<MallDTO>> get(DataFetchingEnvironment environment) {
        StreetDTO source = environment.getSource();
        DataLoader<Long, MallDTO> mallDataLoader = environment.getDataLoader("mallDataLoader");
        return mallDataLoader.loadMany(source.getMallIds());
    }
}
