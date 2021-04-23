package com.syncretis.graphql.resolver.query;

import com.syncretis.graphql.model.dto.CityDTO;
import com.syncretis.graphql.service.CityService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CitiesResolver implements GraphQLQueryResolver {

    private final CityService cityService;

    public List<CityDTO> cities() {
        return cityService.getAllCities();
    }
}
