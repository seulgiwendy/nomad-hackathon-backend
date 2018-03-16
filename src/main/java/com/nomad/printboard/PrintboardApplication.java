package com.nomad.printboard;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PrintboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrintboardApplication.class, args);
	}

	@Bean
    CommandLineRunner commandLineRunner() {
	    return args -> {
	      System.out.println("something changed! please build me!");
        };
    }
}
