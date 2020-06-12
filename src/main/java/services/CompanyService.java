package services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CompanyDAO;
import model.Company;

@Service
public class CompanyService {
	
	private CompanyDAO companyDAO;
	
	@Autowired
	public CompanyService(CompanyDAO companyDAO) {
		
		this.companyDAO = companyDAO;
	}
	
	@Transactional
	public void add(Company company) throws SQLException {
		
		companyDAO.add(company);
	}
	
	@Transactional
	public List<Company> list() throws SQLException{
		
		return companyDAO.list();
	}
	
	@Transactional
	public Optional<Company> getCompanyById(int id) {
		
		return companyDAO.getCompanyById(id);
	}
	
	@Transactional
	public void deleteCompany(int id) throws SQLException {
		
		companyDAO.deleteCompany(id);
	}
}


