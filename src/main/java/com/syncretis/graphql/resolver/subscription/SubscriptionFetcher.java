package com.syncretis.graphql.resolver.subscription;

import com.syncretis.graphql.model.dto.StreetDTO;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SubscriptionFetcher implements GraphQLSubscriptionResolver {

    public StreetDTO watchStreet(Long id) {
        return null; //TODO: Implement Publisher<StreetDTO>
    }
}
