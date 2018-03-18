package com.nomad.printboard.security.config;

import com.nomad.printboard.security.CustomAuthenticationManager;
import com.nomad.printboard.security.filters.FormLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter converter;

    @Value("${oauth.resource.id}")
    private String resourceId;

    @Value("${oauth.access_token.signingkey}")
    private String signingKey;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**", "/api/v1/userjoin**", "/profile").permitAll()
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .csrf()
                .disable();

        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity
                .addFilterAfter(new FormLoginFilter("/login", authenticationManager, this.converter), CorsFilter.class);

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer configurer) throws Exception {
        configurer.resourceId(this.resourceId);
    }
}
