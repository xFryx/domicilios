package com.Backendpw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.Backendpw.repositorio.DomiciliarioRepository;

@SpringBootApplication //(exclude = {SecurityAutoConfiguration.class })
public class BackendApplication {
	private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
	public CommandLineRunner LoadData(DomiciliarioRepository dRepository) {
		return (args)->{
						log.info("hola mundo");
			
		};
	}
}
