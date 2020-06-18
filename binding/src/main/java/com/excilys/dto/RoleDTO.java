package com.excilys.dto;

public class RoleDTO {

	private long idDTO;
	private String roleNameDTO;
	
	public RoleDTO() {
		
	}
	
	public RoleDTO(Builder builder) {
		this.idDTO = builder.idDTO;
		this.roleNameDTO = builder.roleNameDTO;
	}
	
	public long getId() {
		return idDTO;
	}

	public void setId(long idDTO) {
		this.idDTO = idDTO;
	}

	public String getRoleName() {
		return roleNameDTO;
	}

	public void setRoleName(String roleNameDTO) {
		this.roleNameDTO = roleNameDTO;
	}
	
	public static class Builder {
		private long idDTO;
		private String roleNameDTO;
		
		public Builder setId(long idDTO) {
			this.idDTO = idDTO;
			return this;
		}

		public Builder setRoleName(String nameDTO) {
			this.roleNameDTO = nameDTO;
			return this;
		}
		
		public RoleDTO build() {
			return new RoleDTO(this);
		}
	}
}
