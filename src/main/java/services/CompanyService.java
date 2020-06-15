package services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import model.Company;
import repository.CompanyRepository;

@Service
public class CompanyService {
	
	private CompanyRepository companyRepository;
	
	public CompanyService(CompanyRepository companyRepository) {
		
		this.companyRepository = companyRepository;
	}

	public void add(Company company) throws SQLException {
		
		companyRepository.save(company);
	}
	
	public List<Company> list() throws SQLException{
		
		return companyRepository.findAll();
	}

	public Optional<Company> getCompanyById(long id) {
		
		return companyRepository.findById(id);
	}

	public void deleteCompany(long id) throws SQLException {
		
		companyRepository.deleteById(id);;
	}
}


