package com.syncretis.graphql.fetcher;

import com.syncretis.graphql.config.graph.GraphQLFetcher;
import com.syncretis.graphql.config.graph.GraphQLType;
import com.syncretis.graphql.dto.CityDTO;
import com.syncretis.graphql.service.CityService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@GraphQLFetcher(type = GraphQLType.Query, name = "getAllCities")
public class GetAllCityFetcher implements DataFetcher<List<CityDTO>> {

    private final CityService cityService;

    @Override
    public List<CityDTO> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return cityService.getAllCities();
    }
}
