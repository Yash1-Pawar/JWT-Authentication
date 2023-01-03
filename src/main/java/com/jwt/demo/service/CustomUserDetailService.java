package com.jwt.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.demo.model.UserDTO;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO userDTO;
		try {
			userDTO = userServiceImpl.getUserByName(username);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new UsernameNotFoundException(username);
		}
		return userDTO;
	}

}
