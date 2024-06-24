package com.coordinates.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.coordinates", "com.coordinates.persistence" })
@EnableJpaRepositories("com.coordinates.persistence")
@ComponentScan("com.coordiantes.services")
@EntityScan("com.coordinates.model")
public class XyincApplication {

	public static void main(String[] args) {
		SpringApplication.run(XyincApplication.class, args);
	}

}
