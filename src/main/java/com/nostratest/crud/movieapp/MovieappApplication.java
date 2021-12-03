package com.nostratest.crud.movieapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MovieappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieappApplication.class, args);
	}

}
