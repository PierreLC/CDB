package dto;

public class CompanyDTO {

	private long idDTO;
	private String nameDTO;

	public CompanyDTO(Builder builder) {
		this.idDTO = builder.idDTO;
		this.nameDTO = builder.nameDTO;
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

	public static class Builder {
		private long idDTO;
		private String nameDTO;
		
		public Builder() {
		}
		
		public Builder setIdDTO(long idDTO) {
			this.idDTO = idDTO;
			return this;
		}
		
		public Builder setNameDTO(String nameDTO) {
			this.nameDTO = nameDTO;
			return this;
		}
		
		public CompanyDTO build() {
			return new CompanyDTO(this);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idDTO ^ (idDTO >>> 32));
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
		CompanyDTO other = (CompanyDTO) obj;
		if (idDTO != other.idDTO)
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
		return "CompanyDTO [id=" + idDTO + ", name=" + nameDTO + "]";
	}
}
