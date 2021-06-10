package com.syncretis.graphql.fetcher;

import com.syncretis.graphql.dto.StreetDTO;
import com.syncretis.graphql.service.StreetService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class ChangeStreetOwnerFetcher implements DataFetcher<StreetDTO> {

    private final StreetService streetService;

    @Override
    public StreetDTO get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        Map<String, Object> input = dataFetchingEnvironment.getArgument("input");
        Long streetId = (Long) input.get("streetId");
        String owner = (String) input.get("owner");
        return streetService.changeOwner(streetId, owner);

    }
}



















/*
        DataLoader<Long, StreetDTO> streetDataLoader = dataFetchingEnvironment.getDataLoader("streetDataLoader");
        streetDataLoader.clear(streetId);
 */
