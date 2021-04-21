package com.syncretis.graphql.fetcher;

import com.syncretis.graphql.config.graph.GraphQLFetcher;
import com.syncretis.graphql.config.graph.GraphQLType;
import com.syncretis.graphql.dataloader.MallDataLoader;
import com.syncretis.graphql.dto.MallDTO;
import com.syncretis.graphql.dto.StreetDTO;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
@AllArgsConstructor
@GraphQLFetcher(type = GraphQLType.Street, name = "malls")
public class GetMallFetcher implements DataFetcher<CompletableFuture<List<MallDTO>>> {

    private final MallDataLoader mallDataLoader;

    @Override
    public CompletableFuture<List<MallDTO>> get(DataFetchingEnvironment environment) {
        StreetDTO source = environment.getSource();
        return mallDataLoader.loadMany(source.getMallIds());
    }
}
