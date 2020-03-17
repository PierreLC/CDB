package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import model.Company;
import model.Computer;
import utils.DateUtils;

public class ComputerMapper implements RowMapper<Computer> {
	
	@Override
	public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		return mapComputer(resultSet);
	}
	
	public static Computer mapComputer(ResultSet resultSet) throws SQLException {
		
		Company company = new Company.CompanyBuilder().build();
		
		company.setId(resultSet.getLong("id"));
		company.setName(resultSet.getString("name"));
		
		Computer computer = new Computer.Builder().build();
		
		computer.setId(resultSet.getLong("id"));
		computer.setName(resultSet.getString("name"));
		computer.setIntroduced(DateUtils.convertToLDT(resultSet.getTimestamp("introduced").toString()));
		computer.setDiscontinued(DateUtils.convertToLDT(resultSet.getTimestamp("discontinued").toString()));
		computer.setCompany(company);
		
		return computer;
	}
}
