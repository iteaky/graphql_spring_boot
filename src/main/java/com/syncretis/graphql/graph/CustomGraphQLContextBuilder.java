package com.syncretis.graphql.graph;

import com.syncretis.graphql.dataloader.CityDataLoader;
import com.syncretis.graphql.dataloader.MallDataLoader;
import com.syncretis.graphql.dataloader.StreetDataLoader;
import com.syncretis.graphql.service.StreetService;
import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {

    public GraphQLContext build() {
        return new DefaultGraphQLContext();
    }

    public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return DefaultGraphQLServletContext.createServletContext()
                .with(httpServletRequest)
                .with(httpServletResponse)
                .with(buildDataLoaderRegistry())
                .build();
    }

    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        return DefaultGraphQLWebSocketContext.createWebSocketContext()
                .with(session)
                .with(handshakeRequest)
                .with(buildDataLoaderRegistry())
                .build();
    }

    private DataLoaderRegistry buildDataLoaderRegistry() {
        DataLoaderRegistry registry = new DataLoaderRegistry();
        registry.register("streetDataLoader", StreetDataLoader.streetDTODataLoader);
        registry.register("cityDataLoader", CityDataLoader.cityDTODataLoader);
        registry.register("mallDataLoader", MallDataLoader.mallDTODataLoader);
        return registry;
    }
}