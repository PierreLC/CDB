package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;

@Component
public class CompanyMapper implements RowMapper<Company> {
	
	@Override
	public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		Company company = new Company.Builder().build();
		
		company.setId(resultSet.getLong("company.id"));
		company.setName(resultSet.getString("company.name"));
		
		return company;
	}
	
	public static CompanyDTO companyToCompanyDTO(Company company) {
		
		return new CompanyDTO.Builder().setIdDTO(company.getId()).setNameDTO(company.getName()).build();
	}
}