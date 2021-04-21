package com.syncretis.graphql.config.graph;

import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {

    private List<DataLoader> dataLoaders;
    private DataLoaderRegistry dataLoaderRegistry;

    public GraphQLContext build() {
        return new DefaultGraphQLContext();
    }

    public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return DefaultGraphQLServletContext.createServletContext()
                .with(httpServletRequest)
                .with(httpServletResponse)
                .with(dataLoaderRegistry())
                .build();
    }

    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        return DefaultGraphQLWebSocketContext.createWebSocketContext()
                .with(session)
                .with(handshakeRequest)
                .with(dataLoaderRegistry())
                .build();
    }

    private DataLoaderRegistry dataLoaderRegistry() {
        if (dataLoaderRegistry.getDataLoaders().isEmpty()) {
            dataLoaders.forEach(it -> dataLoaderRegistry.register(it.getClass().getSimpleName(), it));

        }
        return dataLoaderRegistry;
    }
}