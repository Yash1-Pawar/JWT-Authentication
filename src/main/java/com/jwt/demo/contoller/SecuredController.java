package com.jwt.demo.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class SecuredController {

	@GetMapping("/helloAdmin")
	public ResponseEntity<String> sayHelloAdmin() {
		System.out.println("Hello from secured endpoint");
		return ResponseEntity.ok("Hello Admin from secured endpoint");
	}

	@GetMapping("/helloUser")
	public ResponseEntity<String> sayHelloUser() {
		System.out.println("Hello from secured endpoint");
		return ResponseEntity.ok("Hello User from secured endpoint");
	}

	@GetMapping("/hello")
	public ResponseEntity<String> sayHello() {
		System.out.println("Hello from secured endpoint");
		return ResponseEntity.ok("Hello from secured endpoint");
	}

	@GetMapping("/helloPre")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> sayHelloPre() {
		System.out.println("Hello from secured endpoint");
		return ResponseEntity.ok("Hello from Pre secured endpoint");
	}

	@GetMapping("/helloAny")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<String> sayHelloAnyPre() {
		System.out.println("Hello from secured endpoint");
		return ResponseEntity.ok("Hello from Pre secured endpoint");
	}

}
