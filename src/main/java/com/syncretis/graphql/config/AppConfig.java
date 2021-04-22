package com.syncretis.graphql.config;

import org.dataloader.DataLoaderRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.Executor;

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

    @Bean
    public Executor taskExecutor(TaskDecorator taskDecorator) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setTaskDecorator(taskDecorator);
        executor.initialize();
        return executor;
    }

    @Bean
    public TaskDecorator taskDecorator() {
        return runnable -> {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            final SecurityContext securityContext = SecurityContextHolder.getContext();
            return () -> {
                try {
                    RequestContextHolder.setRequestAttributes(requestAttributes);
                    SecurityContextHolder.setContext(securityContext);
                    runnable.run();
                } finally {
                    RequestContextHolder.resetRequestAttributes();
                }
            };
        };
    }
}
