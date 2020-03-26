package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import model.Company;

public class CompanyMapper implements RowMapper<Company> {
	
	@Override
	public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		Company company = new Company.Builder().build();
		
		company.setId(resultSet.getLong("company_id"));
		company.setName(resultSet.getString("company.name"));
		
		return company;
	}
}
