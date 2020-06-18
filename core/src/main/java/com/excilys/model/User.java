package com.excilys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@Column(name = "username")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;

	private User() {
		
	}
	
	private User(Builder builder) {
		this.userName = builder.userName;
		this.password = builder.password;
		this.role = builder.role;
	}
	
	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public static class Builder {
		private String userName;
		private String password;
		private Role role;

		public Builder setUserName(String userName) {
			this.userName = userName;
			return this;
		}
	
		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder setRole(Role role) {
			this.role = role;
			return this;
		}
		
		public User build() {
			return new User(this);
		}
	}
}
