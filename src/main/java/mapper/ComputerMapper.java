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
		
		Company company = new Company.Builder().build();;
		
		company.setId(resultSet.getLong("id"));
		company.setName(resultSet.getString("name"));
		
		Computer computer = new Computer.Builder().build();
		
		computer.setId(resultSet.getLong("id"));
		computer.setName(resultSet.getString("name"));
		System.out.println(resultSet.getTimestamp("introduced"));
		computer.setIntroduced(DateUtils.convertToLDT(resultSet.getTimestamp("introduced").toString()));
		computer.setDiscontinued(DateUtils.convertToLDT(resultSet.getTimestamp("discontinued").toString()));
		computer.setCompany(company);
		
		return computer;
		
		
	}
	
	//Vérifier intérêt de cette méthode avant suppression
	public static Computer mapComputer(ResultSet resultSet) throws SQLException {
		
		Company company = new Company.Builder().build();
		
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
