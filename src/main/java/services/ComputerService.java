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
	
	public List<Computer> listPage(int startPaginate, int pageSize){
		return computerDAO.listPage(startPaginate, pageSize);
	}
	
	public void delete(int id) {
		computerDAO.deleteComputer(id);
	}
	
	public Computer find_by_id(int i) {
		return computerDAO.findById(i);
	}
	public List<Computer> find_by_name(String name, int startPaginate, int pageSize) {
		return computerDAO.findByName(name, startPaginate, pageSize);
	}
	
	public void update(Computer computer) {
		computerDAO.update(computer);
	}
	
	public int getNbRows() throws SQLException {
		System.out.println("au niveau du service rows vaut "+computerDAO.getNbRows());
		return computerDAO.getNbRows();
	}
	
	public int nbSearchedComputer(String name) {
		return computerDAO.nbSearchedComputer(name);
	}
	
	public List<Computer> orderByName(int startPaginate, int pageSize){
		return computerDAO.orderByName(startPaginate, pageSize);
	}
	
	public List<Computer> orderByIntroduced(int startPaginate, int pageSize){
		return computerDAO.orderByIntroduced(startPaginate, pageSize);
	}
	
	public List<Computer> orderByDiscontinued(int startPaginate, int pageSize){
		return computerDAO.orderByDiscontinued(startPaginate, pageSize);
	}
	
	public List<Computer> orderByCompany(int startPaginate, int pageSize){
		return computerDAO.orderByCompany(startPaginate, pageSize);
	}
}
