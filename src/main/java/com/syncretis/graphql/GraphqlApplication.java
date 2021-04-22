package com.syncretis.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@ServletComponentScan
@SpringBootApplication
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class GraphqlApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GraphqlApplication.class);
    }
}
