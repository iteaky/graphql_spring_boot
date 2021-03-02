package com.syncretis.graphql.service;

import com.syncretis.graphql.entity.City;
import com.syncretis.graphql.entity.Street;
import com.syncretis.graphql.repository.CityRepository;
import com.syncretis.graphql.repository.StreetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

}
