package com.syncretis.graphql.fetcher.subscription;

import com.syncretis.graphql.dto.StreetDTO;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SubscriptionFetcher implements DataFetcher<Publisher<StreetDTO>> {

    @Override
    public Publisher<StreetDTO> get(DataFetchingEnvironment environment) throws Exception {
        Long id = environment.getArgument("id");
        return subscriber -> System.out.println("Create Subscription for id: " + id);
    }
}
