package com.nomad.printboard.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity security) throws Exception {

        security
                .csrf()
                .disable();

        security
                .headers()
                .frameOptions()
                .disable();

        security
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers( "/h2-console/**")
                .permitAll();

    }
}
