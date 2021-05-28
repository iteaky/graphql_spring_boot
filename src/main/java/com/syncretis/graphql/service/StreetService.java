package com.syncretis.graphql.service;

import com.syncretis.graphql.dto.StreetDTO;
import com.syncretis.graphql.entity.Street;
import com.syncretis.graphql.repository.StreetRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StreetService {
    private final StreetRepository streetRepository;

    @SneakyThrows
    public List<StreetDTO> findByIds(List<Long> ids) {
        return streetRepository.findAllById(ids).stream()
                .map(StreetDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public StreetDTO changeOwner(Long streetId, String owner) {
        Optional<Street> byId = streetRepository.findById(streetId);
        Street street = byId.orElseThrow(() -> new RuntimeException("No such street with id: " + streetId));
        street.setCreatedBy(owner);
        Street save = streetRepository.save(street);
        return StreetDTO.fromEntity(save);
    }
}
