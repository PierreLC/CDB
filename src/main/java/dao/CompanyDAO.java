package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.DaoException;
import mapper.CompanyMapper;

import model.Company;

public final class CompanyDAO {
	static Connection connect;
	ResultSet resultSetList;
	public final String ADD_COMPANY = "INSERT TO company(id, name) VALUES (?, ?)";
	public final String LIST_COMPANY = "SELECT id, name FROM company";
	public final String GET_COMPANY_BY_ID = "SELECT id, name FROM company WHERE id=?";
	private static volatile CompanyDAO instance = null;

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

	public Company add(Company company) {
		System.out.println("LOG : Creation de la company avec pour nom " + company.getName());

		try (PreparedStatement pstmAdd = connect.prepareStatement(ADD_COMPANY);) {

			pstmAdd.setLong(1, company.getId());
			pstmAdd.setString(2, company.getName());

			ResultSet result = pstmAdd.executeQuery();
			return CompanyMapper.getCompanyResultSet(result);
		} catch (SQLException e) {
			System.err.println("LOG : Erreur lors de l'ajout" + e.getMessage());
			throw new DaoException("LOG : Erreur lors de l'ajout");
		}
	}

	public List<Company> lister() throws SQLException {
		List<Company> allCompanies = new ArrayList<Company>();

		try (PreparedStatement stmt = connect.prepareStatement(LIST_COMPANY)) {
			resultSetList = stmt.executeQuery();
			while (resultSetList.next()) {
				//Company company = CompanyMapper.getCompanyResultSet(resultSetList);
				Company company = new Company(resultSetList.getLong("id"), resultSetList.getString("name"));
				allCompanies.add(company);
				System.out.println("company listée");
			}
		} catch (SQLException e) {

		}
		finally {
			resultSetList.close();
		}
		return allCompanies;
	}

	public Company find(int i) {
		System.out.println("LOG : Au moment de trouver la company avec l'id" + i);
		Company company = null;

		try {
//			System.out.println("LOG : tentative de connexion");
//			System.out.println("LOG : connexion réussie");
			PreparedStatement pstmFind = connect.prepareStatement(GET_COMPANY_BY_ID);

		System.out.println("LOG : connexion statetement");

			pstmFind.setInt(1, i);
			ResultSet resultFind = pstmFind.executeQuery();

			if (resultFind.first()) {
				company = CompanyMapper.getCompanyResultSet(resultFind);
			}
			connect.close();
		} catch (SQLException e) {
			System.err.println("Erreur there 4" + e.getMessage());
			System.exit(-1);
		}

		return company;
	}

}
