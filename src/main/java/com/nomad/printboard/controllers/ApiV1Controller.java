package com.nomad.printboard.controllers;

import com.nomad.printboard.security.JwtParsingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class ApiV1Controller {

    @Autowired
    private Environment environment;

    @Autowired
    private JwtParsingUtils jwtParsingUtils;

    @GetMapping("/profile")
    public String getProfile() {
        return Arrays.stream(environment.getActiveProfiles()).findFirst().orElse("no profile found!");
    }

    @GetMapping("/userinfo")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getUsername(OAuth2Authentication authentication) {
        return jwtParsingUtils.getLoggedInMember(authentication).getName();
    }
}
