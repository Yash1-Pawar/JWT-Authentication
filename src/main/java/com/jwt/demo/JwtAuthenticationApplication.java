package com.jwt.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtAuthenticationApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(JwtAuthenticationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Username & password are using from Data Base");
		System.out.println("userName: yash, Password: yash109");
		System.out.println("Authorization: "+ "Basic eWFzaDp5YXNoMTA5");
	}

}
