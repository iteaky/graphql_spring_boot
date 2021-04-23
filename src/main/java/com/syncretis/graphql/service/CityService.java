package com.syncretis.graphql.service;

import com.syncretis.graphql.model.dto.CityDTO;
import com.syncretis.graphql.repository.CityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CityService {
    private final CityRepository cityRepository;

    public List<CityDTO> getAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(CityDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Map<Long, CityDTO> getAllByIds(Collection<Long> ids) {
        log.info(getClass().getSimpleName() + " was Called");
        return cityRepository.findAllById(ids)
                .stream()
                .map(CityDTO::fromEntity)
                .collect(Collectors.toMap(CityDTO::getId, val -> val));
    }

}
