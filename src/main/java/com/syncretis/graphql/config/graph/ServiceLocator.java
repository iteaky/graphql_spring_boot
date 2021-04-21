package com.syncretis.graphql.config.graph;

import graphql.schema.DataFetcher;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceLocator implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static List<FetcherData> getFetchers() {
        return applicationContext.getBeansWithAnnotation(GraphQLFetcher.class)
                .entrySet()
                .stream()
                .flatMap(entry -> fetchData(entry.getKey(), entry.getValue()).stream())
                .collect(Collectors.toList());
    }

    private static List<FetcherData> fetchData(String key, Object value) {
        GraphQLFetcher annotation = applicationContext.findAnnotationOnBean(key, GraphQLFetcher.class);
        List<FetcherData> result = new ArrayList<>();
        result.add(new FetcherData(annotation.type(), annotation.name(), (DataFetcher) value));
        if (annotation.definitions().length > 0) {
            Arrays.stream(annotation.definitions()).forEach(it ->
                    result.add(new FetcherData(it.type(), it.name(), (DataFetcher) value)));
        }
        return result;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static <T> T getFetcher(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    @AllArgsConstructor
    @Data
    public static class FetcherData {
        private GraphQLType type;
        private String name;
        private DataFetcher dataFetcher;
    }

}