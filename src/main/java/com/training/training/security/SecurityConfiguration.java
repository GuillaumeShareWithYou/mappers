package com.training.training.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.SessionManagementFilter;

import javax.servlet.Filter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public CORSFilter corsFilter() {
        return new CORSFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(corsFilter(), SessionManagementFilter.class)
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("**").permitAll();
    }

}
