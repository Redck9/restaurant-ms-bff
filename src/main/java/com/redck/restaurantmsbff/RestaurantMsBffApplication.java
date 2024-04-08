package com.redck.restaurantmsbff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//implements CommandLineRunner
@SpringBootApplication
@EntityScan("com.redck.restaurantmsbff.domain")
@EnableJpaRepositories(basePackages = "com.redck.restaurantmsbff.repository")
public class RestaurantMsBffApplication{

	public static void main(String[] args) {
		SpringApplication.run(RestaurantMsBffApplication.class, args);
	}

}
