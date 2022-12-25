package com.jwt.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.jwt.demo.entity.UserEntity;

@Component
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	Optional<UserEntity> findByUserName(String name);

}
