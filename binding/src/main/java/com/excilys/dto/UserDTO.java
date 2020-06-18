package com.excilys.dto;

public class UserDTO {

	private String userNameDTO;
	private String passwordDTO;
	private RoleDTO roleDTO;
	
	public UserDTO() {
		
	}
	
	private UserDTO(Builder builder) {
		this.userNameDTO = builder.userNameDTO;
		this.passwordDTO = builder.passwordDTO;
		this.roleDTO = builder.roleDTO;
	}
	
	public String getName() {
		return userNameDTO;
	}

	public void setName(String nameDTO) {
		this.userNameDTO = nameDTO;
	}
	
	public String getPassword() {
		return passwordDTO;
	}

	public void setPassword(String passwordDTO) {
		this.passwordDTO = passwordDTO;
	}

	public RoleDTO getRole() {
		return roleDTO;
	}

	public void setRole(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}
	
	public static class Builder {
		private String userNameDTO;
		private String passwordDTO;
		private RoleDTO roleDTO;

		public Builder setUserName(String userNameDTO) {
			this.userNameDTO = userNameDTO;
			return this;
		}
	
		public Builder setPassword(String passwordDTO) {
			this.passwordDTO = passwordDTO;
			return this;
		}

		public Builder setRole(RoleDTO roleDTO) {
			this.roleDTO = roleDTO;
			return this;
		}
		
		public UserDTO build() {
			return new UserDTO(this);
		}
	}
}
