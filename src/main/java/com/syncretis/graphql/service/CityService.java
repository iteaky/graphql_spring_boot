package com.syncretis.graphql.service;

import com.syncretis.graphql.dto.CityDTO;
import com.syncretis.graphql.entity.City;
import com.syncretis.graphql.entity.Street;
import com.syncretis.graphql.repository.CityRepository;
import com.syncretis.graphql.repository.StreetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<CityDTO> getAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(CityDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<CityDTO> getAllByIds(List<Long> ids) {
        return cityRepository.findAllById(ids)
                .stream()
                .map(CityDTO::fromEntity)
                .collect(Collectors.toList());
    }

}
