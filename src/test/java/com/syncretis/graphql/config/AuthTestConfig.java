package com.syncretis.graphql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import java.util.List;

@Configuration
public class AuthTestConfig {

    @Bean
    @Primary
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
        return new TestSecurityConfiguration();
    }


    @Bean
    public AnonymousAuthenticationFilter anonymousAuthenticationFilter() {
        return new AnonymousAuthenticationFilter(
                "TEST",
                new User("test", "test", getRoleList()),
                getRoleList());
    }

    private List<GrantedAuthority> getRoleList() {
        return AuthorityUtils
                .createAuthorityList("ROLE_TEST");
    }

}
