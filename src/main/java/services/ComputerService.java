package services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ComputerDAO;
import model.Computer;

@Service
public class ComputerService {
	
	private ComputerDAO computerDAO;

	@Autowired
	public ComputerService(ComputerDAO computerDAO) {
		
		this.computerDAO = computerDAO;
	}
	
	@Transactional
	public void add(Computer computer) throws SQLException {
		
		computerDAO.add(computer);
	}
	
	@Transactional
	public List<Computer> list(){
		
		return computerDAO.list();
	}
	
	@Transactional
	public List<Computer> listPage(int offset, int pageSize){
		
		return computerDAO.listPage(offset, pageSize);
	}
	
	@Transactional
	public void delete(int id) {
		
		computerDAO.deleteComputer(id);
	}
	
	@Transactional
	public Optional<Computer> getComputerById(int id) {
		
		return computerDAO.getComputerById(id);
	}
	
	@Transactional
	public List<Computer> getComputerByName(String search, int offset, int pageSize) {
		
		return computerDAO.getComputerByName(search, offset, pageSize);
	}
	
	@Transactional
	public void update(Computer computer) {
		
		computerDAO.update(computer);
	}
	
	@Transactional
	public int getNbRows() throws SQLException {
		
		return computerDAO.getNbRows();
	}
	
	@Transactional
	public int nbSearchedComputer(String search) {
		
		return computerDAO.nbSearchedComputer(search);
	}
	
	@Transactional
	public List<Computer> orderByName(int offset, int step){
		
		return computerDAO.orderByName(offset, step);
	}
	
	@Transactional
	public List<Computer> orderByIntroduced(int offset, int step){
		
		return computerDAO.orderByIntroduced(offset, step);
	}
	
	@Transactional
	public List<Computer> orderByDiscontinued(int offset, int step){
		
		return computerDAO.orderByDiscontinued(offset, step);
	}
	
	@Transactional
	public List<Computer> orderByCompany(int offset, int step){
		
		return computerDAO.orderByCompany(offset, step);
	}
}
