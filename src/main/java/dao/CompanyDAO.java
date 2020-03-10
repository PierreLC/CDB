package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import exceptions.DAOException;
import mapper.CompanyMapper;

import model.Company;

@Repository
public final class CompanyDAO {
	private static volatile CompanyDAO instance = null;
	static Connection connect;

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
		
		try (PreparedStatement pstmAdd = connect.prepareStatement(SQLRequest.ADD_COMPANY.getQuery());) {

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

		try (PreparedStatement stmt = connect.prepareStatement(SQLRequest.LIST_COMPANY.getQuery());) {
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

	public Company findById(int id) {
		Company company = null;

		try (PreparedStatement pstmFind = connect.prepareStatement(SQLRequest.GET_COMPANY_BY_ID.getQuery());) {

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
	
	public void deleteCompany(int id) throws SQLException {
		
		try (PreparedStatement pstmDeleteComputer = connect.prepareStatement(SQLRequest.DELETE_COMPUTER_BY_COMPANY_ID.getQuery());
			 PreparedStatement pstmDeleteCompany = connect.prepareStatement(SQLRequest.DELETE_COMPANY.getQuery());) {
			connect.setAutoCommit(false);
			pstmDeleteComputer.setLong(1, id);
			pstmDeleteCompany.setLong(1, id);
			
			pstmDeleteComputer.executeUpdate();
			pstmDeleteCompany.executeUpdate();
			connect.commit();
			connect.setAutoCommit(true);
			
		} catch (SQLException e) {
			//TODO don't show message to users
			System.out.println(e.getMessage());
			
		}
	}

}
