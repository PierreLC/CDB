package services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Repository.CompanyRepository;
import model.Company;

@Service
public class CompanyService {
	
	private CompanyRepository companyRepository;
	
	@Autowired
	public CompanyService(CompanyRepository companyRepository) {
		
		this.companyRepository = companyRepository;
	}
	
	@Transactional
	public void add(Company company) throws SQLException {
		
		companyRepository.save(company);
	}
	
	@Transactional
	public List<Company> list() throws SQLException{
		
		return companyRepository.findAll();
	}
	
	@Transactional
	public Optional<Company> getCompanyById(long id) {
		
		return companyRepository.findById(id);
	}
	
	@Transactional
	public void deleteCompany(long id) throws SQLException {
		
		companyRepository.deleteById(id);;
	}
}


