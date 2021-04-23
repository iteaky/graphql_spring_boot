package com.syncretis.graphql.resolver.mutation;

import com.syncretis.graphql.model.input.FileWithMall;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FileFetcher implements GraphQLMutationResolver {

    public String file(FileWithMall input, DataFetchingEnvironment environment) {
        DefaultGraphQLServletContext context = environment.getContext();
        List<Part> fileParts = context.getFileParts();
        String mall = input.getMall();
        Long mallId = input.getMallId();
        return getClass().getSimpleName() + " was called, with parameters: \n mall:" + mall + "\n mallId: " + mallId + "\n with " + fileParts.size() + " files";
    }
}