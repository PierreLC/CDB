package services;

import java.sql.SQLException;
import java.util.List;

import dao.CompanyDAO;
import model.Company;

public class CompanyService {
	private static volatile CompanyService instance = null;
	private static CompanyDAO companyDAO;

	public final static CompanyService getInstance() {
		if (CompanyService.instance == null) {
			synchronized (CompanyService.class) {
				if (CompanyService.instance == null) {
					CompanyService.instance = new CompanyService(companyDAO);
				}
			}
		}
		return CompanyService.instance;
	}
	
	public CompanyService(CompanyDAO companyDAO) {
		CompanyService.companyDAO = CompanyDAO.getInstance();
	}
	
	public Company add(Company company) throws SQLException {
		return CompanyDAO.add(company);
	}
	
	public List<Company> list() throws SQLException{
		return CompanyDAO.list();
	}
	
	public Company find_by_id(int id) {
		return companyDAO.find_by_id(id);
	}
}
