package services;

import java.sql.SQLException;
import java.util.List;

import dao.ComputerDAO;
import model.Computer;

public class ComputerService {
	private static volatile ComputerService instance = null;
	private static ComputerDAO computerDAO;

	public final static ComputerService getInstance() {
		if (ComputerService.instance == null) {
			synchronized (ComputerService.class) {
				if (ComputerService.instance == null) {
					ComputerService.instance = new ComputerService(computerDAO);
				}
			}
		}
		return ComputerService.instance;
	}
	
	public ComputerService(ComputerDAO computerDAO) {
		ComputerService.computerDAO = computerDAO;
	}
	
	public Computer add(Computer computer) throws SQLException {
		return computerDAO.add(computer);
	}
	
	public List<Computer> list(){
		return computerDAO.list();
	}
	
	public List<Computer> listPage(){
		return computerDAO.listPage(0, 20);
	}
	
	public void delete(Computer computer) {
		computerDAO.deleteComputer(computer);
	}
	
	public Computer find_by_id(int i) {
		return computerDAO.find_by_id(i);
	}
	
	public void update(Computer computer) {
		computerDAO.update(computer);
	}
	
	public int getNbRows() throws SQLException {
		return computerDAO.getNbRows();
	}
}
