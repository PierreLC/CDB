package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import mapper.ComputerMapper;
import model.Computer;

@Repository
public class ComputerDAO {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private ComputerMapper computerMapper;

	
	private ComputerDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ComputerMapper computerMapper) {
		
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.computerMapper = computerMapper;
	}

	public void add(Computer computer) throws SQLException {
		
		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("name", computer.getName())
																		.addValue("introduced", computer.getIntroduced())
																		.addValue("discontinued", computer.getDiscontinued())
																		.addValue("company.id", computer.getCompany().getId());
		
		namedParameterJdbcTemplate.update(SQLRequest.ADD.getQuery(), namedParameter);
	}

	public List<Computer> list() {

		return namedParameterJdbcTemplate.query(SQLRequest.LIST_COMPUTER.getQuery(), this.computerMapper);
	}

	public List<Computer> listPage(int offset, int pageSize) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("offset", offset)
																	   .addValue("pageSize", pageSize);
		
		return namedParameterJdbcTemplate.query(SQLRequest.LIST_PAGE.getQuery(), namedParameter, this.computerMapper);
	}

	public void deleteComputer(int id) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("id", id);
		
		namedParameterJdbcTemplate.update(SQLRequest.DELETE.getQuery(), namedParameter);
	}

	public Optional<Computer> getComputerById(int id) {
		
		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("computer.id", id);
		
		Computer computer = namedParameterJdbcTemplate.queryForObject(SQLRequest.FIND_BY_ID.getQuery(), namedParameter, this.computerMapper);

		return Optional.of(computer);
	}

	public List<Computer> getComputerByName(String search, int offset, int pageSize) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("search", '%' + search + '%')
																	   .addValue("offset", offset)
																	   .addValue("step", pageSize);

		List<Computer> computer = namedParameterJdbcTemplate.query(SQLRequest.FIND_BY_NAME.getQuery(), namedParameter, this.computerMapper);

		return computer;
	}

	public int nbSearchedComputer(String search) {

		MapSqlParameterSource mapParameter = new MapSqlParameterSource().addValue("search", '%' + search + '%');

		return namedParameterJdbcTemplate.queryForObject(SQLRequest.NB_SEARCHED_COMPUTER.getQuery(), mapParameter, Integer.class);
	}

	public void update(Computer computer) {
		
		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("computer.id", computer.getId())
																	   .addValue("name", computer.getName())
																	   .addValue("introduced", computer.getIntroduced())
																	   .addValue("discontinued", computer.getDiscontinued())
																	   .addValue("company.id", computer.getCompany().getId());
		
		namedParameterJdbcTemplate.update(SQLRequest.UPDATE.getQuery(), namedParameter);
	}

	public int getNbRows() throws SQLException {

		MapSqlParameterSource mapParameter = new MapSqlParameterSource();
		
		return namedParameterJdbcTemplate.queryForObject(SQLRequest.NB_ROWS.getQuery(), mapParameter, Integer.class);
	}

	public List<Computer> orderByName(int offset, int step) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("offset", offset)
																	   .addValue("step", step);

		return namedParameterJdbcTemplate.query(OrderByRequest.ORDER_BY_NAME.getQuery(), namedParameter, this.computerMapper);
	}

	public List<Computer> orderByIntroduced(int offset, int step) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("offset", offset)
																	   .addValue("step", step);

		return namedParameterJdbcTemplate.query(OrderByRequest.ORDER_BY_INTRODUCED.getQuery(), namedParameter, this.computerMapper);
	}

	public List<Computer> orderByDiscontinued(int offset, int step) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("offset", offset)
				                                                       .addValue("step", step);

		return namedParameterJdbcTemplate.query(OrderByRequest.ORDER_BY_DISCONTINUED.getQuery(), namedParameter, this.computerMapper);
	}

	public List<Computer> orderByCompany(int offset, int step) {

		SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("offset", offset)
				                                                       .addValue("step", step);

		return namedParameterJdbcTemplate.query(OrderByRequest.ORDER_BY_COMPANY.getQuery(), namedParameter, this.computerMapper);
	}
}
