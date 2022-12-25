package com.jwt.demo.model;

public class UserDTO {

	private int userId;
	private String userName;
	private String userPassword;
	private String roles;

	public UserDTO() {
		super();
	}

	public UserDTO(int userId, String userName, String userPassword) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public UserDTO(int userId, String userName, String userPassword, String roles) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.roles = roles;
	}

	public UserDTO(String userName2, String encode) {
		this.userName = userName2;
		this.userPassword = encode;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + "]";
	}

}
