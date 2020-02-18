package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Company;

public class CompanyMapper {

	public static Company getCompanyResultSet(ResultSet resDetailCompany) throws SQLException {

		Company company = null;
		
		if (resDetailCompany.next()) {
			long companyId = (resDetailCompany.getLong("company.id"));
			String companyName = (resDetailCompany.getString("computer.name"));

		 company = new Company.CompanyBuilder().id(companyId).name(companyName)
					.build();
		}

		return company;

	}
}
