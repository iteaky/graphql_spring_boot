package com.syncretis.graphql.service;

import com.syncretis.graphql.dto.StreetDTO;
import com.syncretis.graphql.repository.StreetRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StreetService {
    private final StreetRepository streetRepository;

    @SneakyThrows
    public List<StreetDTO> findByIds(List<Long> ids) {
        Thread.sleep(10000);
        return streetRepository.findAllById(ids).stream()
                .map(StreetDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
