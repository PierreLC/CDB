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
		ComputerService.computerDAO = ComputerDAO.getInstance();
	}
	
	public void add(Computer computer) throws SQLException {
		computerDAO.add(computer);
	}
	
	public List<Computer> list(){
		return computerDAO.list();
	}
	
	public List<Computer> listPage(int offset, int step){
		return computerDAO.listPage(offset, step);
	}
	
	public void delete(int id) {
		computerDAO.deleteComputer(id);
	}
	
	public Computer find_by_id(int i) {
		return computerDAO.findById(i);
	}
	public List<Computer> find_by_name(String name, int offset, int step) {
		return computerDAO.findByName(name, offset, step);
	}
	
	public void update(Computer computer) {
		computerDAO.update(computer);
	}
	
	public int getNbRows() throws SQLException {
		return computerDAO.getNbRows();
	}
	
	public int nbSearchedComputer(String name) {
		System.out.println("Au moment du service"+computerDAO.nbSearchedComputer(name));
		return computerDAO.nbSearchedComputer(name);
	}
}
