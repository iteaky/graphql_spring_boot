package com.syncretis.graphql.config.graph.exceptions;

import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.ExecutionStrategy;
import graphql.kickstart.execution.config.ExecutionStrategyProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomExecutionStrategyProvider implements ExecutionStrategyProvider {

    private final AsyncExecutionStrategy asyncExecutionStrategy;

    public CustomExecutionStrategyProvider(GraphqlExceptionHandler exceptionHandler) {
        this.asyncExecutionStrategy = new AsyncExecutionStrategy(exceptionHandler);
    }

    @Override
    public ExecutionStrategy getQueryExecutionStrategy() {
        return asyncExecutionStrategy;
    }

    @Override
    public ExecutionStrategy getMutationExecutionStrategy() {
        return asyncExecutionStrategy;
    }

    @Override
    public ExecutionStrategy getSubscriptionExecutionStrategy() {
        return asyncExecutionStrategy;
    }
}