package com.syncretis.graphql.fetcher.mutation;

import com.syncretis.graphql.config.graph.GraphQLFetcher;
import com.syncretis.graphql.config.graph.GraphQLType;
import com.syncretis.graphql.dto.StreetDTO;
import com.syncretis.graphql.entity.Street;
import com.syncretis.graphql.repository.StreetRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@GraphQLFetcher(type = GraphQLType.Mutation, name = "changeStreetName")
public class ChangeStreetNameFetcher implements DataFetcher<StreetDTO> {

    private final StreetRepository streetRepository;

    @Override
    public StreetDTO get(DataFetchingEnvironment environment) throws Exception {
        Map<String, Object> input = environment.getArgument("input");
        Long id = (Long) input.get("id");
        String newName = (String) input.get("name");
        Optional<Street> streetToUpdate = streetRepository.findById(id);
        return streetToUpdate.map(it -> {
            it.setName(newName);
            return streetRepository.save(it);
        })
                .map(StreetDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("No Street with id: " + id));
    }
}
