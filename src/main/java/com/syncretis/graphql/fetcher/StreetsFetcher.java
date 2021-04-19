package com.syncretis.graphql.fetcher;

import com.syncretis.graphql.dto.CityDTO;
import com.syncretis.graphql.dto.StreetDTO;
import com.syncretis.graphql.service.StreetService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StreetsFetcher implements DataFetcher<List<StreetDTO>> {
    private final StreetService streetService;

    @Override
    public List<StreetDTO> get(DataFetchingEnvironment environment) {
        CityDTO source = environment.getSource();
        return streetService.findByIds(source.getStreetsIds());
    }
}
