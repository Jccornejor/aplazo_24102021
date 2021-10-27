package com.jccornejor.exam.aplazo.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ServerConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(EndpointRequest.to(ShutdownEndpoint.class))
                .hasRole("ACTUATOR_ADMIN")
                .antMatchers("/", "/public/**", "/actuator/health", "/console/**").permitAll()
                .and()
                .httpBasic();
    }
}
