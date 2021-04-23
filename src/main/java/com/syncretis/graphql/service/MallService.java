package com.syncretis.graphql.service;

import com.syncretis.graphql.model.dto.MallDTO;
import com.syncretis.graphql.repository.MallRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class MallService {
    private final MallRepository mallRepository;

    @RolesAllowed("ROLE_TEST")
    public Map<Long, MallDTO> getAllByIds(Collection<Long> ids) {
        log.info(getClass().getSimpleName() + " was Called");
        return mallRepository.findAllById(ids)
                .stream()
                .map(MallDTO::fromEntity)
                .collect(Collectors.toMap(MallDTO::getId, val -> val));
    }

}
