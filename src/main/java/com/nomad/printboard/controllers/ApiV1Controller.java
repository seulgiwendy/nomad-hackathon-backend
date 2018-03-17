package com.nomad.printboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class ApiV1Controller {

    @Autowired
    private Environment environment;

    @GetMapping("/profile")
    public String getProfile() {
        return Arrays.stream(environment.getActiveProfiles()).findFirst().orElse("no profile found!");
    }
}
