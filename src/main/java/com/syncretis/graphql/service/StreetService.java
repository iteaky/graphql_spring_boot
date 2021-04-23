package com.syncretis.graphql.service;

import com.syncretis.graphql.model.dto.StreetDTO;
import com.syncretis.graphql.repository.StreetRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class StreetService {
    private final StreetRepository streetRepository;

    @SneakyThrows
    public Map<Long, StreetDTO> getAllByIds(Collection<Long> ids) {
        log.info(getClass().getSimpleName() + " was Called");
        return streetRepository.findAllById(ids).stream()
                .map(StreetDTO::fromEntity)
                .collect(Collectors.toMap(StreetDTO::getId, val -> val));
    }
}
