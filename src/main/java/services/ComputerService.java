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
	
	public Computer findById(int i) {
		return computerDAO.findById(i);
	}
	public List<Computer> findByName(String search, int offset, int pageSize) {
		return computerDAO.findByName(search, offset, pageSize);
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
	
	public List<Computer> orderByName(int offset, int pageSize){
		return computerDAO.orderByName(offset, pageSize);
	}
	
	public List<Computer> orderByIntroduced(int offset, int pageSize){
		return computerDAO.orderByIntroduced(offset, pageSize);
	}
	
	public List<Computer> orderByDiscontinued(int offset, int pageSize){
		return computerDAO.orderByDiscontinued(offset, pageSize);
	}
	
	public List<Computer> orderByCompany(int offset, int pageSize){
		return computerDAO.orderByCompany(offset, pageSize);
	}
}
