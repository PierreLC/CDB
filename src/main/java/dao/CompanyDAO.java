package dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mapper.CompanyMapper;
import model.Company;

@Repository
public final class CompanyDAO {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private CompanyMapper companyMapper;

	private CompanyDAO(DataSource dataSource, CompanyMapper companyMapper) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.companyMapper = companyMapper;
	}

	public void add(Company company) throws SQLException {
		
		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("company", company);
		
		namedParameterJdbcTemplate.query(SQLRequest.ADD_COMPANY.getQuery(), namedParameter, this.companyMapper);
	}

	public List<Company> list() throws SQLException {

		return namedParameterJdbcTemplate.query(SQLRequest.LIST_COMPANY.getQuery(), this.companyMapper);
	}

	public Company findById(long id) {
		
		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("company.id", id);
		
		Company company = namedParameterJdbcTemplate.queryForObject(SQLRequest.GET_COMPANY_BY_ID.getQuery(), namedParameter, this.companyMapper);
		
		return company;	
	}
	
	@Transactional
	public void deleteCompany(long id) throws SQLException {
		
		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("id", id);
		
		namedParameterJdbcTemplate.update(SQLRequest.DELETE_COMPANY.getQuery(), namedParameter);
	}
}

