package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Company;

public class CompanyMapper {

	public static Company getCompanyResultSet(ResultSet resDetailCompany) throws SQLException {
		
		Company company = null;
	
		try {
			long companyID = resDetailCompany.getLong("company.id");
			String companyName = resDetailCompany.getString("company.name");
			company = new Company.CompanyBuilder().id(companyID).name(companyName).build();
		} catch (SQLException e) {
			System.out.println(e.getMessage());;
		}
		return company;
	}
}
