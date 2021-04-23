package com.syncretis.graphql.resolver.mutation;

import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;

@Service
@AllArgsConstructor
public class FileSizeFetcher implements GraphQLMutationResolver {

    public String fileSize(DataFetchingEnvironment environment) throws Exception {
        DefaultGraphQLServletContext context = environment.getContext();
        Part file = context.getFileParts().iterator().next();
        long l = file.getSize() / 1000;
        return l + " kb";
    }
}
