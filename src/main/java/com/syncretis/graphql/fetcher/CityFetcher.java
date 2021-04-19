package com.syncretis.graphql.fetcher;

import com.syncretis.graphql.dto.CityDTO;
import com.syncretis.graphql.entity.City;
import com.syncretis.graphql.service.CityService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityFetcher implements DataFetcher<List<CityDTO>> {
    private final CityService cityService;

    @Override
    public List<CityDTO> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return cityService.getAllCities();
    }
}
