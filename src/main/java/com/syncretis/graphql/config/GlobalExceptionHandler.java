package com.syncretis.graphql.config;

import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ThrowableGraphQLError handle(Exception e) {
        return new ThrowableGraphQLError(e);
    }
}
