package com.syncretis.graphql.resolver.mutation;

import com.syncretis.graphql.model.dto.StreetDTO;
import com.syncretis.graphql.model.entity.Street;
import com.syncretis.graphql.model.input.ChangeStreetNameInput;
import com.syncretis.graphql.repository.StreetRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ChangeStreetNameFetcher implements GraphQLMutationResolver {

    private final StreetRepository streetRepository;
    private final DataLoader<Long, StreetDTO> streetDataLoader;

    public StreetDTO changeStreetName(ChangeStreetNameInput input) {
        Long id = input.getId();
        String newName = input.getName();
        Optional<Street> streetToUpdate = streetRepository.findById(id);
        return streetToUpdate.map(it -> {
            streetDataLoader.clear(id);
            it.setName(newName);
            return streetRepository.save(it);
        })
                .map(StreetDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("No Street with id: " + id));
    }
}
