package services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import dao.CompanyDAO;
import dao.ConnexionSQL;
import model.Company;

@Service
public class CompanyService {
//	private static volatile CompanyService instance = null;
	private static CompanyDAO companyDAO;
	private ConnexionSQL connexionSQL;

//	public final static CompanyService getInstance() {
//		if (CompanyService.instance == null) {
//			synchronized (CompanyService.class) {
//				if (CompanyService.instance == null) {
//					CompanyService.instance = new CompanyService(companyDAO);
//				}
//			}
//		}
//		return CompanyService.instance;
//	}
	
	public CompanyService(ConnexionSQL instance) {
//		CompanyService.companyDAO = CompanyDAO.getInstance();
		this.connexionSQL = instance;
	}
	
	public Company add(Company company) throws SQLException {
		return CompanyDAO.add(company);
	}
	
	public List<Company> list() throws SQLException{
		return CompanyDAO.list();
	}
	
	public Company find_by_id(int id) {
		return companyDAO.findById(id);
	}
	
	public void deleteCompany(int id) throws SQLException {
		companyDAO.deleteCompany(id);
	}
}


