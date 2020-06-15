package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import dto.CompanyDTO;
import dto.ComputerDTO;
import model.Company;
import model.Computer;

@Component
public class ComputerMapper implements RowMapper<Computer> {

	public ComputerMapper() {
		super();
	}

	@Override
	public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		Company company = new Company.Builder().build();

		company.setId(resultSet.getLong("company_id"));
		company.setName(resultSet.getString("company.name"));

		Computer computer = new Computer.Builder().build();

		computer.setId(resultSet.getLong("computer.id"));
		computer.setName(resultSet.getString("computer.name"));

		LocalDateTime introduced = resultSet.getTimestamp("introduced") != null
				? resultSet.getTimestamp("introduced").toLocalDateTime()
				: null;
		computer.setIntroduced(introduced);

		LocalDateTime discontinued = resultSet.getTimestamp("discontinued") != null
				? resultSet.getTimestamp("discontinued").toLocalDateTime()
				: null;
		computer.setDiscontinued(discontinued);

		computer.setCompany(company);

		return computer;
	}

	public static ComputerDTO computerToComputerDTO(Computer computer) {

		if (computer.getCompany() != null) {
			
			CompanyDTO companyDTO = new CompanyDTO.Builder().setIdDTO(computer.getCompany().getId())
					.setNameDTO(computer.getCompany().getName()).build();

			ComputerDTO computerDTO = new ComputerDTO.Builder().setIdDTO(computer.getId())
					.setNameDTO(computer.getName()).setIntroducedDTO(computer.getIntroduced())
					.setDiscontinuedDTO(computer.getDiscontinued()).setCompanyDTO(companyDTO).build();
			
			return computerDTO;
		} else {
			
			ComputerDTO computerDTO = new ComputerDTO.Builder().setIdDTO(computer.getId())
					.setNameDTO(computer.getName()).setIntroducedDTO(computer.getIntroduced())
					.setDiscontinuedDTO(computer.getDiscontinued()).build();
			
			return computerDTO;
		}
	}

	public static Computer computerDTOToComputer(ComputerDTO computerDTO) {

		Company company = new Company.Builder().setId(computerDTO.getCompanyDTO().getIdDTO())
				.setName(computerDTO.getCompanyDTO().getNameDTO()).build();

		Computer computer = new Computer.Builder().setId(computerDTO.getIdDTO()).setName(computerDTO.getNameDTO())
				.setIntroduced(computerDTO.getIntroducedDTO()).setDiscontinued(computerDTO.getDiscontinuedDTO())
				.setCompany(company).build();

		return computer;
	}
}
