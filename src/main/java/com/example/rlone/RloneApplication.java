package com.example.rlone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = {"http://localhost:8081", "null"})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(RloneApplication.class, args);
	}

}
