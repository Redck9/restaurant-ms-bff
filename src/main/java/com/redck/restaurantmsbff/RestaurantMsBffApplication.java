package com.redck.restaurantmsbff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

//implements CommandLineRunner
@SpringBootApplication
@EnableScheduling
//@EntityScan("com.redck.restaurantmsbff.domain")
//@EnableJpaRepositories(basePackages = "com.redck.restaurantmsbff.repository")
@ComponentScan(basePackages = {"com.redck.restaurantmsbff.*"})
public class RestaurantMsBffApplication{

	public static void main(String[] args) {
		SpringApplication.run(RestaurantMsBffApplication.class, args);
	}

}
