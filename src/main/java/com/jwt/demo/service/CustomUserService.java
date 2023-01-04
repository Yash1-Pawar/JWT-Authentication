package com.jwt.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jwt.demo.model.UserDTO;

@Component
public class CustomUserService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO userDTO;
		try {
			userDTO = userService.getUserByName(username);
		} catch (Exception e) {
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("User Not Found");
		}
		return userDTO;
	}

}
