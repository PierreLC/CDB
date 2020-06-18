package com.excilys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "role")
public class Role {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "role")
	private String roleName;

	public Role(Builder builder) {
		this.id = builder.id;
		this.roleName = builder.roleName;
	}
	
	public Role() {
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String role) {
		this.roleName = role;
	}
	
	public static class Builder {
		private long id;
		private String roleName;
		
		public Builder setId(long id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.roleName = name;
			return this;
		}
		
		public Role build() {
			return new Role(this);
		}
	}
}
