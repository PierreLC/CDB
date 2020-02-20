package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import exceptions.DaoException;
import mapper.ComputerMapper;
import model.Computer;

public final class ComputerDAO {
	static Connection connect;
	ResultSet resultList;
	ResultSet resultFind;
	ResultSet resultRows;
	public final String ADD = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?);";
	public final String LIST_COMPUTER = "SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id;";
	public final String DELETE = "DELETE FROM computer WHERE id=?;";
	public final String UPDATE = "UPDATE computer SET  name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE Id = ?;";
	public final String LIST_PAGE = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id LIMIT ?, ?;";
	public final String DISPLAY_COMPUTER = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id  WHERE computer.id=?;";
	public final String NB_ROWS = "SELECT COUNT(*) as \"Rows\" FROM computer;";
	
	public final String ADD_LOG ="Erreur lors de l'ajout : échec de la connexion à la base de donnée";
	public final String LIST_LOG =" Erreur lors de l'affichage des ordinateur : échec de la connexion à la base de donnée";
	public final String DELETE_LOG ="Erreur lors de la suppression : échec de la connexion à la base de donnée";
	public final String UPDATE_LOG ="Erreur lors de la mise à jour de l'ordinateur : échec de la connexion à la base de donnée";
	public final String DISPLAY_LOG ="Erreur lors de l'affichage de l'ordinateur : échec de la connexion à la base de donnée";
	public final String ROWS_LOG ="Erreur au moment de compter les lignes : échec lors de la connexion à la base de donnée";
	public final String LIST_PAGE_LOG ="Erreur lors de l'affichage des pages : échec lors de la connexion à la base de donnée";
	
	private static volatile ComputerDAO instance = null;

	private ComputerDAO() {
		ComputerDAO.connect = ConnexionSQL.getInstance().connect();
	}

	public final static ComputerDAO getInstance() {
		if (ComputerDAO.instance == null) {

			synchronized (ComputerDAO.class) {
				if (ComputerDAO.instance == null) {
					ComputerDAO.instance = new ComputerDAO();
				}
			}
		}
		return ComputerDAO.instance;
	}

	public void add(Computer computer) {
		try (PreparedStatement pstmAdd = connect.prepareStatement(ADD)) {

			pstmAdd.setString(1, computer.getName());
			pstmAdd.setTimestamp(2, computer.getIntroduced() != null ? Timestamp.valueOf(computer.getIntroduced()):null);
			pstmAdd.setTimestamp(3, computer.getDiscontinued() != null ? Timestamp.valueOf(computer.getDiscontinued()):null);
			System.out.println(computer);
			pstmAdd.setLong(4, computer.getCompany().getId());

			pstmAdd.executeUpdate();
		} catch (SQLException e) {
			DaoException.displayError(ADD_LOG);
			System.exit(-1);
		}
	}

	public List<Computer> list() {
		List<Computer> computerList = new ArrayList<Computer>();

		try (PreparedStatement pstmList = connect.prepareStatement(LIST_COMPUTER)) {
			resultList = pstmList.executeQuery();
			while (resultList.next()) {
				Computer computer = ComputerMapper.getComputerResultSet(resultList);
				computerList.add(computer);
			}

		} catch (SQLException e) {
			DaoException.displayError(LIST_LOG);
			System.exit(-1);
		}
		return computerList;
	}
	
	public int getNbRows() throws SQLException {
		int nbRows = -1;
		
		try (PreparedStatement pstmRows = connect.prepareStatement(NB_ROWS);) {
			resultRows = pstmRows.executeQuery();
			
			if (resultRows.first()) {
				nbRows = resultRows.getInt("Rows");
			}
		}catch (SQLException e) {
			DaoException.displayError(ROWS_LOG);
		}
		return nbRows;
	}
	
	public List<Computer> listPage(int startPaginate, int pageSize) {
		System.out.println("passe dans la DAO");
		
		List<Computer> compPagList = new ArrayList<Computer>();

		try (PreparedStatement pstmPagList = connect.prepareStatement(LIST_PAGE)) {
			pstmPagList.setInt(1, startPaginate);
			pstmPagList.setInt(2, pageSize);
			resultList = pstmPagList.executeQuery();
			while (resultList.next()) {
				Computer computer = ComputerMapper.getComputerResultSet(resultList);
				compPagList.add(computer);
			}

		} catch (SQLException e) {
			DaoException.displayError(LIST_PAGE_LOG);
			System.exit(-1);
		}
		return compPagList;
	}

	public void deleteComputer(Computer computer) {
		try (PreparedStatement pstmDelete = connect.prepareStatement(DELETE)) {

			pstmDelete.setLong(1, computer.getId());
			pstmDelete.execute();
		} catch (SQLException e) {
			System.err.println("Erreur there 3" + e.getMessage());
			System.exit(-1);
		}
	}

	public Computer find(int i) {
		Computer computer = null;

		try (PreparedStatement pstmFind = connect.prepareStatement(DISPLAY_COMPUTER);) {
			pstmFind.setLong(1, i);
			resultFind = pstmFind.executeQuery();
			System.out.println("requete exec");
			if (resultFind.first()) {
				computer = ComputerMapper.getComputerResultSet(resultFind);
			}

		} catch (SQLException e) {
			DaoException.displayError(DISPLAY_LOG);
			System.exit(-1);
		}

		return computer;
	}

	public void update(Computer computer) {

		try (PreparedStatement pstmUpdate = connect.prepareStatement(UPDATE);) {
			
			pstmUpdate.setString(1, computer.getName());
			pstmUpdate.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()));
			pstmUpdate.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()));
 			pstmUpdate.setLong(4, computer.getCompany().getId());
			pstmUpdate.setLong(5, computer.getId());


			pstmUpdate.executeUpdate();
			pstmUpdate.close();
		} catch (SQLException e) {
			DaoException.displayError(UPDATE_LOG);
			System.exit(-1);
		}
	}
	

}
