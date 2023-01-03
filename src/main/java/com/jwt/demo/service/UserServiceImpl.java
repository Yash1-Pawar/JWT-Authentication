package com.jwt.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.demo.entity.UserEntity;
import com.jwt.demo.model.UserDTO;
import com.jwt.demo.repo.UserRepository;

@Service
public class UserServiceImpl {

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

	public void addCustomer(UserDTO userDTO) {
		if (this.existsByUsername(userDTO.getUsername())) {
			System.out.println("User already exists with given username");
		} else {
			userRepository.save(new UserEntity(userDTO.getUsername(), userDTO.getPassword(), userDTO.getRoles()));
			System.out.println("User Added Successfully");
		}
	}

	public List<UserDTO> getAllUser() {
		List<UserEntity> userEntities = userRepository.findAll();
		List<UserDTO> userDTOs = new ArrayList<>();
		for (UserEntity e : userEntities) {
			userDTOs.add(new UserDTO(e.getUserId(), e.getUserName(), e.getUserPassword(), e.getRoles()));
		}
		return userDTOs;
	}

}
