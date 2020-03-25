package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import model.Company;
import model.Computer;

public class ComputerMapper implements RowMapper<Computer> {
	
	@Override
	public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		Company company = new Company.Builder().build();;
		
		company.setId(resultSet.getLong("company_id"));
		company.setName(resultSet.getString("company.name"));
		
		Computer computer = new Computer.Builder().build();
		
		computer.setId(resultSet.getLong("computer.id"));
		computer.setName(resultSet.getString("computer.name"));
		
		LocalDateTime introduced = resultSet.getTimestamp("introduced") != null ? resultSet.getTimestamp("introduced").toLocalDateTime() : null;
		computer.setIntroduced(introduced);
		
		LocalDateTime discontinued = resultSet.getTimestamp("discontinued") != null ? resultSet.getTimestamp("discontinued").toLocalDateTime() : null;
		computer.setDiscontinued(discontinued);
		
		computer.setCompany(company);
		
		return computer;
	}
}
