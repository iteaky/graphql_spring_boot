package com.syncretis.graphql.config;

import org.dataloader.DataLoaderRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AppConfig {

    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
        return new TomcatSecurityConfiguration();
    }

    @Bean
    public DataLoaderRegistry dataLoaderRegistry() {
        return new DataLoaderRegistry();
    }
}
