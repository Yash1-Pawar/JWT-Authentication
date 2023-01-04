package com.jwt.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.demo.entity.UserEntity;
import com.jwt.demo.model.UserDTO;
import com.jwt.demo.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserDTO getUserByName(String name) throws Exception {
		Optional<UserEntity> userOptional = userRepository.findByUserName(name);
		UserEntity userEntity = userOptional.orElseThrow(() -> new Exception("User Not Found"));
		UserDTO userDTO = new UserDTO(userEntity.getUserId(), userEntity.getUserName(), userEntity.getUserPassword(),
				userEntity.getRoles());
		return userDTO;
	}

	public Boolean existsByUsername(String name) {
		if (userRepository.findByUserName(name).isPresent())
			return true;
		else
			return false;
	}

	public boolean saveUser(UserDTO userDTO) {
		if (this.existsByUsername(userDTO.getUsername())) {
			System.out.println("User already exists with given username");
			return false;
		} else {
			UserEntity userEntity = UserEntity.builder().userName(userDTO.getUsername())
						.userPassword(userDTO.getPassword())
						.roles(userDTO.getRoles())
						.build();
			userRepository.save(userEntity);
			System.out.println("User Added Successfully");
			return true;
		}
	}

}
