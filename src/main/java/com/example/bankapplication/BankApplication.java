package com.example.bankapplication;

import com.example.bankapplication.repository.BankApplicationRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BankApplicationRepo repository) {
		return (args) -> {
			repository.initialize();
		};
	}
}
