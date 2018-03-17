package com.nomad.printboard;

import com.nomad.printboard.domain.Member;
import com.nomad.printboard.domain.repositories.MemberRepository;
import com.nomad.printboard.security.MemberRoles;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PrintboardApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "classpath:application-set1.yml,"
            + "classpath:application-set2.yml,"
            + "/home/ubuntu/app/config/printboard/prod-config.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(PrintboardApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
	}

	@Bean
    CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder, MemberRepository repository) {

	    return args -> {
            Member member = Member.builder()
                    .name("정휘준")
                    .memberId("fuck@that.shit")
                    .password(passwordEncoder.encode("1234"))
                    .roles(MemberRoles.USER)
                    .build();

            repository.save(member);
        };
    }
}
