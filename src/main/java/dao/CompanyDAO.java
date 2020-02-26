package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.DAOException;
import mapper.CompanyMapper;

import model.Company;

public final class CompanyDAO {
	static Connection connect;
	ResultSet resultSetList;
	ResultSet result;
	public final String ADD_COMPANY = "INSERT INTO company(name) VALUES (?)";
	public final String LIST_COMPANY = "SELECT id, name FROM company";
	public final String GET_COMPANY_BY_ID = "SELECT id, name FROM company WHERE id=?";
	private static volatile CompanyDAO instance = null;
	
	private final static String ADD_COMPANY_LOG = "Erreur lors de l'ajout : échec de la connexion à la base de donnée";
	private final static String LIST_COMPANY_LOG = "Erreur au moment de lister les marques : échec de la connexion à la base de donnée";
	private final static String GET_COMPANY_LOG = "Erreur au moment d'obtenir la marque : échec de la connexion à la base de donnée";

	private CompanyDAO() {
		CompanyDAO.connect = ConnexionSQL.getInstance().connect();
	}

	public final static CompanyDAO getInstance() {
		if (CompanyDAO.instance == null) {
			synchronized (CompanyDAO.class) {
				if (CompanyDAO.instance == null) {
					CompanyDAO.instance = new CompanyDAO();
				}
			}
		}
		return CompanyDAO.instance;
	}

	public Company add(Company company) throws SQLException {
		System.out.println("LOG : Creation de la company avec pour nom " + company.getName());

		try (PreparedStatement pstmAdd = connect.prepareStatement(ADD_COMPANY);) {

			pstmAdd.setString(1, company.getName());

			result = pstmAdd.executeQuery();
			
		} catch (SQLException e) {
			DAOException.displayError(ADD_COMPANY_LOG);	
		}
		
		return CompanyMapper.getCompanyResultSet(result);
	}

	public List<Company> lister() throws SQLException {
		List<Company> allCompanies = new ArrayList<Company>();

		try (PreparedStatement stmt = connect.prepareStatement(LIST_COMPANY)) {
			resultSetList = stmt.executeQuery();
			while (resultSetList.next()) {
				Company company =  CompanyMapper.getCompanyResultSet(resultSetList);
				allCompanies.add(company);
			}
		} catch (SQLException e) {
			DAOException.displayError(LIST_COMPANY_LOG);
		} finally {
			resultSetList.close();
		}
		return allCompanies;
	}

	public Company find(int i) {
		System.out.println("LOG : Au moment de trouver la company avec l'id" + i);
		Company company = null;

		try {
			PreparedStatement pstmFind = connect.prepareStatement(GET_COMPANY_BY_ID);

			System.out.println("LOG : connexion statetement");

			pstmFind.setInt(1, i);
			ResultSet resultFind = pstmFind.executeQuery();

			if (resultFind.first()) {
				company = CompanyMapper.getCompanyResultSet(resultFind);
			}
			connect.close();
		} catch (SQLException e) {
			DAOException.displayError(GET_COMPANY_LOG);
		}

		return company;
	}

}
