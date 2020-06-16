package com.excilys.dto;

import java.time.LocalDateTime;

public class ComputerDTO {
	
	private long idDTO;
	private String nameDTO;
	private LocalDateTime introducedDTO;
	private LocalDateTime discontinuedDTO;
	private CompanyDTO companyDTO;

	public ComputerDTO(Builder builder) {
		this.idDTO = builder.idDTO;
		this.nameDTO = builder.nameDTO;
		this.introducedDTO = builder.introducedDTO;
		this.discontinuedDTO = builder.discontinuedDTO;
		this.companyDTO = builder.companyDTO;
	}

	public long getIdDTO() {
		return idDTO;
	}

	public void setIdDTO(long idDTO) {
		this.idDTO = idDTO;
	}

	public String getNameDTO() {
		return nameDTO;
	}

	public void setNameDTO(String nameDTO) {
		this.nameDTO = nameDTO;
	}

	public LocalDateTime getDiscontinuedDTO() {
		return discontinuedDTO;
	}

	public void setDiscontinuedDTO(LocalDateTime discontinuedDTO) {
		this.discontinuedDTO = discontinuedDTO;
	}

	public LocalDateTime getIntroducedDTO() {
		return introducedDTO;
	}

	public void setIntroducedDTO(LocalDateTime introducedDTO) {
		this.introducedDTO = introducedDTO;
	}

	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}

	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}

	public static class Builder {
		private long idDTO;
		private String nameDTO;
		private LocalDateTime introducedDTO;
		private LocalDateTime discontinuedDTO;
		private CompanyDTO companyDTO;

		public Builder setIdDTO(long idDTO) {
			this.idDTO = idDTO;
			return this;
		}

		public Builder setNameDTO(String nameDTO) {
			this.nameDTO = nameDTO;
			return this;
		}

		public Builder setIntroducedDTO(LocalDateTime introducedDTO) {
			this.introducedDTO = introducedDTO;
			return this;
		}

		public Builder setDiscontinuedDTO(LocalDateTime discontinuedDTO) {
			this.discontinuedDTO = discontinuedDTO;
			return this;
		}

		public Builder setCompanyDTO(CompanyDTO companyDTO) {
			this.companyDTO = companyDTO;
			return this;
		}

		public ComputerDTO build() {
			return new ComputerDTO(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyDTO == null) ? 0 : companyDTO.hashCode());
		result = prime * result + ((discontinuedDTO == null) ? 0 : discontinuedDTO.hashCode());
		result = prime * result + (int) (idDTO ^ (idDTO >>> 32));
		result = prime * result + ((introducedDTO == null) ? 0 : introducedDTO.hashCode());
		result = prime * result + ((nameDTO == null) ? 0 : nameDTO.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerDTO other = (ComputerDTO) obj;
		if (companyDTO == null) {
			if (other.companyDTO != null)
				return false;
		} else if (!companyDTO.equals(other.companyDTO))
			return false;
		if (discontinuedDTO == null) {
			if (other.discontinuedDTO != null)
				return false;
		} else if (!discontinuedDTO.equals(other.discontinuedDTO))
			return false;
		if (idDTO != other.idDTO)
			return false;
		if (introducedDTO == null) {
			if (other.introducedDTO != null)
				return false;
		} else if (!introducedDTO.equals(other.introducedDTO))
			return false;
		if (nameDTO == null) {
			if (other.nameDTO != null)
				return false;
		} else if (!nameDTO.equals(other.nameDTO))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id =" + idDTO + ", name =" + nameDTO + ", introduced date =" + introducedDTO
				+ ", discontinued date =" + discontinuedDTO + "CompanyDTO =" + companyDTO + "]";
	}
}
