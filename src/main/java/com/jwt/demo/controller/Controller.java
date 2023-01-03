package com.jwt.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evryone")
public class Controller {

	@GetMapping("/login")
	public String login() {
		return "Login success";
	}

	@GetMapping("/register")
	public String register() {
		return "Register success";
	}

}
