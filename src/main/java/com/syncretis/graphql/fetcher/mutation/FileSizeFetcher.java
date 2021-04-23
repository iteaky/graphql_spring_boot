package com.syncretis.graphql.fetcher.mutation;

import com.syncretis.graphql.config.graph.GraphQLFetcher;
import com.syncretis.graphql.config.graph.GraphQLType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;

@Service
@AllArgsConstructor
@GraphQLFetcher(type = GraphQLType.Mutation, name = "fileSize")
public class FileSizeFetcher implements DataFetcher<String> {
    @Override
    public String get(DataFetchingEnvironment environment) throws Exception {
        Part file = environment.getArgument("file");
        long l = file.getSize() / 1000;
        return l + " kb";
    }
}
