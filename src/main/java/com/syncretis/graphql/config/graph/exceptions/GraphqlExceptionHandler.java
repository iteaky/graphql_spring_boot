package com.syncretis.graphql.config.graph.exceptions;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import graphql.language.SourceLocation;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.stream.Stream;

@Component
@NoArgsConstructor
@Slf4j
public class GraphqlExceptionHandler implements DataFetcherExceptionHandler {

    @Override
    public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters handlerParameters) {

        Throwable exception = handlerParameters.getException();

        String message = exception.getMessage();

        StackTraceElement stackTraceElement = Stream.of(exception.getStackTrace())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("WTF?"));

        int lineNumber = stackTraceElement.getLineNumber();
        String className = stackTraceElement.getFileName();
        String methodName = stackTraceElement.getMethodName();

        if (message == null) {
            message = exception.toString();
        }

        log.error(message, exception);

        GraphQLError error = GraphqlErrorBuilder
                .newError()
                .message(message)
                .location(new SourceLocation(lineNumber, 0))
                .extensions(new HashMap<String, Object>() {{
                    put("Exception in class: ", className);
                    put("Exception in methodName: ", methodName + "()");
                    put("Exception on line: ", lineNumber);
                }})
                .build();

        return DataFetcherExceptionHandlerResult
                .newResult()
                .error(error)
                .build();
    }

}