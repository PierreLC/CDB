package services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CompanyDAO;
import model.Company;

@Service
public class CompanyService {
	
	private CompanyDAO companyDAO;
	
	@Autowired
	public CompanyService(CompanyDAO companyDAO) {
		
		this.companyDAO = companyDAO;
	}
	
	public void add(Company company) throws SQLException {
		
		companyDAO.add(company);
	}
	
	public List<Company> list() throws SQLException{
		
		return companyDAO.list();
	}
	
	public Company getCompanyById(int id) {
		
		return companyDAO.getCompanyById(id);
	}
	
	public void deleteCompany(int id) throws SQLException {
		
		companyDAO.deleteCompany(id);
	}
}


