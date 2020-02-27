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
	private static volatile CompanyDAO instance = null;
	static Connection connect;
	
	public final static String ADD_COMPANY = "INSERT INTO company(name) VALUES (?)";
	public final static String LIST_COMPANY = "SELECT id, name FROM company";
	public final String GET_COMPANY_BY_ID = "SELECT id, name FROM company WHERE id=?";

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

	public static Company add(Company company) throws SQLException {
		ResultSet resultAdd = null;
		
		try (PreparedStatement pstmAdd = connect.prepareStatement(ADD_COMPANY);) {

			pstmAdd.setString(1, company.getName());

			resultAdd = pstmAdd.executeQuery();

		} catch (SQLException e) {
			DAOException.displayError(ADD_COMPANY_LOG + e.getMessage());
		}
		return CompanyMapper.getCompanyResultSet(resultAdd);
	}

	public static List<Company> list() throws SQLException {
		List<Company> allCompanies = new ArrayList<Company>();
		ResultSet resultSetList = null;

		try (PreparedStatement stmt = connect.prepareStatement(LIST_COMPANY)) {
			resultSetList = stmt.executeQuery();
			while (resultSetList.next()) {
				Company company = CompanyMapper.getCompanyResultSet(resultSetList);
				allCompanies.add(company);
			}
		} catch (SQLException e) {
			DAOException.displayError(LIST_COMPANY_LOG+e.getMessage());
		} finally {
			resultSetList.close();
		}
		return allCompanies;
	}

	public Company find_by_id(int id) {
		System.out.println("LOG : Au moment de trouver la company avec l'id" + id);
		Company company = null;

		try {
			PreparedStatement pstmFind = connect.prepareStatement(GET_COMPANY_BY_ID);

			System.out.println("LOG : connexion statetement");

			pstmFind.setInt(1, id);
			ResultSet resultFind = pstmFind.executeQuery();

			if (resultFind.first()) {
				company = CompanyMapper.getCompanyResultSet(resultFind);
			}
			connect.close();
		} catch (SQLException e) {
			DAOException.displayError(GET_COMPANY_LOG+e.getMessage());
		}

		return company;
	}

}
