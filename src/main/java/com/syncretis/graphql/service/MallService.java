package com.syncretis.graphql.service;

import com.syncretis.graphql.dto.MallDTO;
import com.syncretis.graphql.repository.MallRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
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

    @RolesAllowed("ROLE_TEST")
    public List<MallDTO> getAllByIds(List<Long> ids) {
        return mallRepository.findAllById(ids)
                .stream()
                .map(MallDTO::fromEntity)
                .collect(Collectors.toList());
    }

}
