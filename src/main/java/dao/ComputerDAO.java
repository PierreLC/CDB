package dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import mapper.ComputerMapper;
import model.Computer;

@Repository
public final class ComputerDAO {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	ComputerMapper computerMapper;

	private ComputerDAO(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		computerMapper = new ComputerMapper();
	}

	public void add(Computer computer) throws SQLException {

		SqlParameterSource namedParameter = new MapSqlParameterSource();
		namedParameterJdbcTemplate.query(SQLRequest.ADD.getQuery(), namedParameter, this.computerMapper);
	}

	public List<Computer> list() {

		return namedParameterJdbcTemplate.query(SQLRequest.LIST_COMPUTER.getQuery(), this.computerMapper);
	}

	public List<Computer> listPage(int startPaginate, int pageSize) {

		return namedParameterJdbcTemplate.query(SQLRequest.LIST_COMPANY.getQuery(), this.computerMapper);
	}

	public void deleteComputer(int id) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("id", id);
		namedParameterJdbcTemplate.query(SQLRequest.DELETE.getQuery(), namedParameter, this.computerMapper);
	}

	public Computer findById(int id) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("id", id);
		Computer computer = (Computer) namedParameterJdbcTemplate.query(SQLRequest.FIND_BY_ID.getQuery(), namedParameter, this.computerMapper);

		return computer;
	}

	public List<Computer> findByName(String name, int offset, int step) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("name", '%' + name + '%')
				.addValue("offset", offset).addValue("step", step);

		List<Computer> computer = (List<Computer>) namedParameterJdbcTemplate.query(SQLRequest.FIND_BY_NAME.getQuery(), namedParameter, this.computerMapper);

		return computer;
	}

	public int nbSearchedComputer(String name) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("name", name);

		List<Computer> nbSearchedComputer = namedParameterJdbcTemplate.query(SQLRequest.FIND_BY_NAME.getQuery(), namedParameter, this.computerMapper);

		return nbSearchedComputer.size();
	}

	public void update(Computer computer) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("id", computer.getId())
				.addValue("name", computer.getName()).addValue("introduced", computer.getIntroduced())
				.addValue("discontinued", computer.getDiscontinued()).addValue("company", computer.getCompany());

		namedParameterJdbcTemplate.update(SQLRequest.UPDATE.getQuery(), namedParameter);
	}

	public int getNbRows() throws SQLException {

		MapSqlParameterSource parameterMap = new MapSqlParameterSource();

		return namedParameterJdbcTemplate.queryForObject(SQLRequest.NB_ROWS.getQuery(), parameterMap, Integer.class);
	}

	public List<Computer> orderByName(int startPaginate, int pageSize) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("startPaginate", startPaginate)
																	   .addValue("pageSize", pageSize);

		return namedParameterJdbcTemplate.query(OrderByRequest.ORDER_BY_NAME.getQuery(), namedParameter, this.computerMapper);
	}

	public List<Computer> orderByIntroduced(int startPaginate, int pageSize) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("startPaginate", startPaginate)
																	   .addValue("pageSize", pageSize);

		return namedParameterJdbcTemplate.query(OrderByRequest.ORDER_BY_INTRODUCED.getQuery(), namedParameter, this.computerMapper);
	}

	public List<Computer> orderByDiscontinued(int startPaginate, int pageSize) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("startPaginate", startPaginate)
				                                                       .addValue("pageSize", pageSize);

		return namedParameterJdbcTemplate.query(OrderByRequest.ORDER_BY_DISCONTINUED.getQuery(), namedParameter,
				this.computerMapper);
	}

	public List<Computer> orderByCompany(int startPaginate, int pageSize) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("startPaginate", startPaginate)
				                                                       .addValue("pageSize", pageSize);

		return namedParameterJdbcTemplate.query(OrderByRequest.ORDER_BY_COMPANY.getQuery(), namedParameter, this.computerMapper);
	}
}
