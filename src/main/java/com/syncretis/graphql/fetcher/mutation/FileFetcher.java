package com.syncretis.graphql.fetcher.mutation;

import com.syncretis.graphql.config.graph.GraphQLFetcher;
import com.syncretis.graphql.config.graph.GraphQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.util.Map;

@Service
@AllArgsConstructor
@GraphQLFetcher(type = GraphQLType.Mutation, name = "file")
public class FileFetcher implements DataFetcher<Long> {
    @Override
    public Long get(DataFetchingEnvironment environment) throws Exception {
        Map<String, Object> input = environment.getArgument("input");
        Part file = (Part) input.get("file");
        String mall = (String) input.get("mall");
        Long mallId = (Long) input.get("mallId");
        return 1L;
    }
}