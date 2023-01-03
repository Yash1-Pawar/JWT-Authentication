package com.jwt.demo.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.demo.model.AuthenticationResponse;
import com.jwt.demo.model.UserDTO;
import com.jwt.demo.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/everyone")
@RequiredArgsConstructor
public class JwtController {

	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping("/hello")
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok("Hello from unsecured endpoint");
	}

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDTO userDTO) {
		return ResponseEntity.ok(authenticationService.register(userDTO));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody UserDTO userDTO) {
		try {
			return ResponseEntity.ok(authenticationService.authenticate(userDTO));
		} catch (Exception e) {
			return ResponseEntity.ok(null);
		}
	}

}
