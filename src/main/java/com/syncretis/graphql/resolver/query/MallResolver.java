package com.syncretis.graphql.resolver.query;

import com.syncretis.graphql.model.dto.MallDTO;
import com.syncretis.graphql.model.dto.StreetDTO;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
@AllArgsConstructor
public class MallResolver implements GraphQLResolver<StreetDTO> {

    private final DataLoader<Long, MallDTO> mallDataLoader;

    public CompletableFuture<List<MallDTO>> malls(StreetDTO streetDTO) {
        return mallDataLoader.loadMany(streetDTO.getMallIds());
    }
}
