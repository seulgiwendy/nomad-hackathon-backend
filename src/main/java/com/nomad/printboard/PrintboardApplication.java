package com.nomad.printboard;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PrintboardApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "/home/ubuntu/app/config/printboard/prod-config.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(PrintboardApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
	}


}
