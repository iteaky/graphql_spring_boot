package com.syncretis.graphql.config.graph;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface GraphQLFetcher {

    GraphQLType type();

    String name();

    FetcherDefinition[] definitions() default {};

    @Target({})
    @Retention(RetentionPolicy.RUNTIME)
    @interface FetcherDefinition {
        GraphQLType type();

        String name();

    }
}