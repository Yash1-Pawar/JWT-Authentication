package com.jwt.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.demo.config.JwtService;
import com.jwt.demo.model.AuthenticationResponse;
import com.jwt.demo.model.UserDTO;
import com.jwt.demo.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(UserDTO userDTO) {
		userDTO.setRoles("USER");
		userService.saveUser(userDTO);
		var user = User.builder().username(userDTO.getUsername())
						.password(passwordEncoder.encode(userDTO.getPassword()))
						.roles("USER")
						.build();
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	public AuthenticationResponse authenticate(UserDTO userDTO) throws Exception {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
		var user = userService.getUserByName(userDTO.getUsername());
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}
}
