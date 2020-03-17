package dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import mapper.CompanyMapper;
import model.Company;

@Repository
public final class CompanyDAO {
	private DataSource dataSource;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	CompanyMapper companyMapper;
	

	private CompanyDAO(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.dataSource = dataSource;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		companyMapper = new CompanyMapper();
	}

	public void add(Company company) throws SQLException {
		SqlParameterSource namedParameter = new MapSqlParameterSource();
		namedParameterJdbcTemplate.query(SQLRequest.ADD_COMPANY.getQuery(), namedParameter, this.companyMapper);
	}

	public List<Company> list() throws SQLException {
		return namedParameterJdbcTemplate.query(SQLRequest.LIST_COMPANY.getQuery(), new CompanyMapper());
	}

	public Company findById(long id) {
		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("id", id);
		Company company = (Company) namedParameterJdbcTemplate.query(SQLRequest.GET_COMPANY_BY_ID.getQuery(), namedParameter, this.companyMapper);
		return company;
	}
	
	public void deleteCompany(long id) throws SQLException {
		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("id", id);
		namedParameterJdbcTemplate.query(SQLRequest.DELETE_COMPANY.getQuery(), namedParameter, this.companyMapper);
	}
}
