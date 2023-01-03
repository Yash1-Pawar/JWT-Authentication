package com.jwt.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.demo.model.UserDTO;
import com.jwt.demo.service.UserServiceImpl;

@RestController
@RequestMapping("/secured")
public class SecuredController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/addUser")
	public void addUser(@RequestBody UserDTO userDTO) {
		String plainPassword = userDTO.getPassword();
		String encodedPassword = this.passwordEncoder.encode(plainPassword);
		userDTO.setUserPassword(encodedPassword);
		userDTO.setRoles("ROLE_user");
		userServiceImpl.addCustomer(userDTO);
	}
	
	
	@GetMapping("/getAllUsers")
	public List<UserDTO> getAllUsers() {
		return userServiceImpl.getAllUser();
	}
	
}
