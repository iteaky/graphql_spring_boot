package com.syncretis.graphql.service;

import com.syncretis.graphql.dto.MallDTO;
import com.syncretis.graphql.repository.MallRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MallService {
    private final MallRepository mallRepository;

    public List<MallDTO> getAll() {
        return mallRepository.findAll()
                .stream()
                .map(MallDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public List<MallDTO> getAllByIds(List<Long> ids) {
        if(!ids.isEmpty()) Thread.sleep(4000L);
        return mallRepository.findAllById(ids)
                .stream()
                .map(MallDTO::fromEntity)
                .collect(Collectors.toList());
    }

}
