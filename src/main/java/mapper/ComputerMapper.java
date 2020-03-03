package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import dto.CompanyDTO;
import dto.ComputerDTO;
import model.Company;
import model.Computer;

public class ComputerMapper {

	public static Computer getComputerResultSet(ResultSet resDetailcomputer) throws SQLException {
		Computer computer;
		long computerId = (resDetailcomputer.getLong("computer.id"));
		String computerName = (resDetailcomputer.getString("computer.name"));
		LocalDateTime introduced = (resDetailcomputer.getTimestamp("computer.introduced") != null
				? resDetailcomputer.getTimestamp("computer.introduced").toLocalDateTime()
				: null);
		LocalDateTime discontinued = (resDetailcomputer.getTimestamp("discontinued") != null
				? resDetailcomputer.getTimestamp("computer.discontinued").toLocalDateTime()
				: null);
		Long companyId = (resDetailcomputer.getLong("company_id"));
		String companyName = (resDetailcomputer.getString("company.name"));

		Company company = new Company.CompanyBuilder().id(companyId).name(companyName).build();

		computer = new Computer.Builder().setId(computerId).setName(computerName).setIntroducedDate(introduced)
				.setDiscontinuedDate(discontinued).setCompany(company).build();
		return computer;
	}
//	
//	public ComputerDTO toComputerDto(Computer computer) {
//		CompanyDTO companyDTO = new CompanyDTO();
//		companyDTO.setId(computer.getCompany().getId());
//		companyDTO.setName(computer.getCompany().getName());
//
//		ComputerDTO computerDTO = new ComputerDTO(computer.getId(),computer.getName(),
//				computer.getIntroduced()==null?null:computer.getIntroduced().toString(),
//				computer.getDiscontinued()==null?null:computer.getDiscontinued().toString(),companyDTO);
//		return computerDTO;
//	}
//	
//	public static Computer convertFromComputerDtoToComputer(ComputerDto computerDto) throws ParseException {
//		Computer computer = new Computer.ComputerBuilder().setId(computerDto.getId())
//														  .setName(computerDto.getName())
//														  .setIntroduced(convertStringToLocalDateTime(computerDto.getIntroduced()))
//														  .setDiscontinued(convertStringToLocalDateTime(computerDto.getDiscontinued()))
//														  .setCompany(CompanyMapper.mapFromCompanyDtoToCompany(computerDto.getCompany()))
//														  .build();   
//		return computer;
//
}
