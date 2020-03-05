package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import exceptions.DAOException;
import mapper.ComputerMapper;
import model.Computer;

public final class ComputerDAO {
	private static volatile ComputerDAO instance = null;
	static Connection connect;

	public final String ADD = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?);";
	public final String LIST_COMPUTER = "SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id;";
	public final String DELETE = "DELETE FROM computer WHERE id=?;";
	public final String UPDATE = "UPDATE computer SET  name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE Id = ?;";
	public final String LIST_PAGE = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id LIMIT ?, ?;";
	public final String FIND_BY_ID = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id  WHERE computer.id=?;";
	public final String FIND_BY_NAME = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id  WHERE computer.name LIKE ?;";
	public final String NB_ROWS = "SELECT COUNT(*) as \"Rows\" FROM computer;";

	public final String ADD_LOG = "Erreur lors de l'ajout : échec de la connexion à la base de donnée";
	public final String LIST_LOG = " Erreur lors de l'affichage des ordinateur : échec de la connexion à la base de donnée";
	public final String DELETE_LOG = "Erreur lors de la suppression : échec de la connexion à la base de donnée";
	public final String UPDATE_LOG = "Erreur lors de la mise à jour de l'ordinateur : échec de la connexion à la base de donnée";
	public final String DISPLAY_LOG = "Erreur lors de l'affichage de l'ordinateur : échec de la connexion à la base de donnée";
	public final String ROWS_LOG = "Erreur au moment de compter les lignes : échec lors de la connexion à la base de donnée";
	public final String LIST_PAGE_LOG = "Erreur lors de l'affichage des pages : échec lors de la connexion à la base de donnée";

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

	public void add(Computer computer) throws SQLException {

		try (PreparedStatement pstmAdd = connect.prepareStatement(ADD)) {
			pstmAdd.setString(1, computer.getName());
			pstmAdd.setTimestamp(2,
					computer.getIntroduced() != null ? Timestamp.valueOf(computer.getIntroduced()) : null);
			pstmAdd.setTimestamp(3,
					computer.getDiscontinued() != null ? Timestamp.valueOf(computer.getDiscontinued()) : null);
			pstmAdd.setLong(4, computer.getCompany().getId());

			 pstmAdd.executeUpdate();

		} catch (SQLException e) {
			DAOException.displayError(ADD_LOG + e.getMessage());
		}
	}

	public List<Computer> list() {
		ResultSet resultList;
		List<Computer> computerList = new ArrayList<Computer>();

		try (PreparedStatement pstmList = connect.prepareStatement(LIST_COMPUTER)) {
			resultList = pstmList.executeQuery();
			while (resultList.next()) {
				Computer computer = ComputerMapper.getComputerResultSet(resultList);
				computerList.add(computer);
			}

		} catch (SQLException e) {
			DAOException.displayError(LIST_LOG + e.getMessage());
		}
		return computerList;
	}

	public List<Computer> listPage(int startPaginate, int pageSize) {
		ResultSet resultList;

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
			DAOException.displayError(LIST_PAGE_LOG + e.getMessage());
		}
		return compPagList;
	}

	public void deleteComputer(int id) {
		try (PreparedStatement pstmDelete = connect.prepareStatement(DELETE)) {

			pstmDelete.setLong(1, id);
			pstmDelete.execute();

		} catch (SQLException e) {
			System.err.println("Erreur there 3" + e.getMessage());
		}
	}

	public Computer find_by_id(int id) {
		Computer computer = null;
		ResultSet resultFindId;

		try (PreparedStatement pstmFind = connect.prepareStatement(FIND_BY_ID);) {
			pstmFind.setLong(1, id);
			resultFindId = pstmFind.executeQuery();
			if (resultFindId.first()) {
				computer = ComputerMapper.getComputerResultSet(resultFindId);
			}

		} catch (SQLException e) {
			DAOException.displayError(DISPLAY_LOG + e.getMessage());
		}

		return computer;
	}

	public List<Computer> find_by_name(String name) {
		Computer computer;
		ResultSet resultFindName;
		List<Computer> computerSearched = new ArrayList<Computer>();

		try (PreparedStatement pstmFind = connect.prepareStatement(FIND_BY_NAME);) {
			pstmFind.setString(1, "%"+name+"%");
			resultFindName = pstmFind.executeQuery();
			while (resultFindName.next()) {
				computer = ComputerMapper.getComputerResultSet(resultFindName);
				computerSearched.add(computer);
			}

		} catch (SQLException e) {
			DAOException.displayError(DISPLAY_LOG + e.getMessage());
		}

		return computerSearched;
	}
	public void update(Computer computer) {

		try (PreparedStatement pstmUpdate = connect.prepareStatement(UPDATE);) {

			pstmUpdate.setString(1, computer.getName());
			pstmUpdate.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()));
			pstmUpdate.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()));
			pstmUpdate.setLong(4, computer.getCompany().getId());
			pstmUpdate.setLong(5, computer.getId());

			pstmUpdate.executeUpdate();
		} catch (SQLException e) {
			DAOException.displayError(UPDATE_LOG + e.getMessage());
		}
	}

	public int getNbRows() throws SQLException {
		int nbRows = -1;

		try (PreparedStatement pstmRows = connect.prepareStatement(NB_ROWS);) {
			try (ResultSet resultRows = pstmRows.executeQuery()) {
				if (resultRows.first()) {
					nbRows = resultRows.getInt("Rows");
				}
			}
		} catch (SQLException e) {
			DAOException.displayError(ROWS_LOG + e.getMessage());
		}
		return nbRows;
	}

}
