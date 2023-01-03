package com.jwt.demo.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "JWTUserTable", uniqueConstraints = { @UniqueConstraint(columnNames = "userName") })
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Nonnull
	private String userName;

	@Nonnull
	private String userPassword;

	private String roles;

	public UserEntity(String username, String password, String roles) {
		this.userName = username;
		this.roles = roles;
		this.userPassword = password;
	}

}
