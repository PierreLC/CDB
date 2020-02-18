package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import mapper.ComputerMapper;
import model.Computer;

public final class ComputerDAO {
	static Connection connect;
	ResultSet resultList;
	ResultSet resultFind;
	public final String ADD = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?);";
	public final String LIST_COMPUTER = "SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id;";
	public final String DELETE = "DELETE FROM computer WHERE id=?;";
	public final String UPDATE = "UPDATE computer " + "SET  name = ?, Introduced = ?"
			+ "Discontinued = ?,company_id = ? WHERE Id = ?;";
	//public final String COUNT = "SELECT COUNT(*) FROM computer;";
	//public final String GET_PAGE = "SELECT computer.id as computer_id, computer.name as computer_name, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id LIMIT ?, ?;";
	public final String GET_COMPUTER_BY_ID = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id  WHERE computer.id=?;";

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
			System.err.println("Erreur there 1" + e.getMessage());
			System.exit(-1);
		}
	}

	public List<Computer> lister() {
		List<Computer> computerList = new ArrayList<Computer>();

		try (PreparedStatement pstmList = connect.prepareStatement(LIST_COMPUTER)) {
			resultList = pstmList.executeQuery();
			while (resultList.next()) {
				Computer computer = ComputerMapper.getComputerResultSet(resultList);
				computerList.add(computer);
			}

		} catch (SQLException e) {
			System.err.println("Erreur there 2" + e.getMessage());
			System.exit(-1);
		}
		return computerList;
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

		try (PreparedStatement pstmFind = connect.prepareStatement(GET_COMPUTER_BY_ID);) {
			pstmFind.setLong(1, i);
			resultFind = pstmFind.executeQuery();
			System.out.println("requete exec");
			if (resultFind.first()) {
				computer = ComputerMapper.getComputerResultSet(resultFind);
			}

		} catch (SQLException e) {
			System.err.println("Erreur there 4" + e.getMessage());
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

			pstmUpdate.executeUpdate();
			pstmUpdate.close();
		} catch (SQLException e) {
			System.err.println("Erreur there 5" + e.getMessage());
			System.exit(-1);
		}
	}
}
