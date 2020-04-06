package services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ComputerDAO;
import model.Computer;

@Service
public class ComputerService {
	
	private ComputerDAO computerDAO;

	@Autowired
	public ComputerService(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}
	
	public void add(Computer computer) throws SQLException {
		computerDAO.add(computer);
	}
	
	public List<Computer> list(){
		return computerDAO.list();
	}
	
	public List<Computer> listPage(int offset, int pageSize){
		return computerDAO.listPage(offset, pageSize);
	}
	
	public void delete(int id) {
		computerDAO.deleteComputer(id);
	}
	
	public Computer getComputerById(int id) {
		return computerDAO.getComputerById(id);
	}
	public List<Computer> getComputerByName(String search, int offset, int pageSize) {
		return computerDAO.getComputerByName(search, offset, pageSize);
	}
	
	public void update(Computer computer) {
		computerDAO.update(computer);
	}
	
	public int getNbRows() throws SQLException {
		return computerDAO.getNbRows();
	}
	
	public int nbSearchedComputer(String search) {
		return computerDAO.nbSearchedComputer(search);
	}
	
	public List<Computer> orderByName(int offset, int step){
		return computerDAO.orderByName(offset, step);
	}
	
	public List<Computer> orderByIntroduced(int offset, int step){
		return computerDAO.orderByIntroduced(offset, step);
	}
	
	public List<Computer> orderByDiscontinued(int offset, int step){
		return computerDAO.orderByDiscontinued(offset, step);
	}
	
	public List<Computer> orderByCompany(int offset, int step){
		return computerDAO.orderByCompany(offset, step);
	}
}
