package com.jwt.demo.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/jwt")
@RequiredArgsConstructor
public class JwtController {

	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping("/hello")
	public ResponseEntity<String> sayHello() {
		System.out.println("Hello from unsecured endpoint");
		return ResponseEntity.ok("Hello from unsecured endpoint");
	}

	@PostMapping("/registerUser")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDTO userDTO) {
		System.out.println("Registering User: "+ userDTO);
		try {
			AuthenticationResponse authenticationResponse= authenticationService.register(userDTO);
			return ResponseEntity.ok(authenticationResponse);
		} catch (Exception e) {
			return new ResponseEntity<AuthenticationResponse>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/getToken")
	public ResponseEntity<AuthenticationResponse> getToken(@RequestBody UserDTO userDTO) {
		try {
			AuthenticationResponse authenticationResponse= authenticationService.authenticate(userDTO);
			return ResponseEntity.ok(authenticationResponse);
		} catch (Exception e) {
			return new ResponseEntity<AuthenticationResponse>(HttpStatus.BAD_REQUEST);
		}
	}

}
