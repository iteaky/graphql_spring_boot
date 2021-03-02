package com.syncretis.graphql.fetcher;

import com.syncretis.graphql.dto.CityDTO;
import com.syncretis.graphql.dto.StreetDTO;
import com.syncretis.graphql.repository.StreetRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StreetsFetcher implements DataFetcher<List<StreetDTO>> {
    private final StreetRepository streetRepository;

    @Override
    public List<StreetDTO> get(DataFetchingEnvironment environment) {
        CityDTO source = environment.getSource();
        return streetRepository.findAllById(source.getStreetsIds()).stream()
                .map(StreetDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
