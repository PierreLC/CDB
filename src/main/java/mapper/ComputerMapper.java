package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

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

			Company company = new Company.CompanyBuilder().id(companyId).name(companyName)
					.build();

			computer = new Computer.Builder().initializeId(computerId).initializeName(computerName)
					.initializeIntroducedDate(introduced).initializeDiscontinuedDate(discontinued)
					.initializeCompany(company).build();
			return computer;

	}
}
