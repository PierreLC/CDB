package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import model.Company;

public class CompanyMapper implements RowMapper<Company> {
	
	@Override
	public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		return mapCompany(resultSet);
	}

	public static Company mapCompany(ResultSet resultSet) throws SQLException {

		Company company = new Company.CompanyBuilder().build();

		company.setId(resultSet.getLong("id"));
		company.setName(resultSet.getString("name"));

		return company;
	}
}
