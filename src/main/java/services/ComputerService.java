package services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Computer;
import repository.ComputerRepository;

@Service
public class ComputerService {
	
	private ComputerRepository computerRepository;

	@Autowired
	public ComputerService(ComputerRepository computerRepository) {
		this.computerRepository = computerRepository;
	}
	
	@Transactional
	public void add(Computer computer) throws SQLException {
		
		computerRepository.save(computer);
	}
	
	@Transactional
	public List<Computer> list(){
		
		return computerRepository.findAll();
	}
	
//	@Transactional
//	public List<Computer> listPage(int offset, int pageSize){
//		
//		return computerDAO.listPage(offset, pageSize);
//	}
	
	@Transactional
	public void delete(long id) {
		
		computerRepository.deleteById(id);
	}
	
	@Transactional
	public Optional<Computer> getComputerById(long id) {
		
		return computerRepository.findById(id);
	}
	
//	@Transactional
//	public List<Computer> getComputerByName(String search, int offset, int pageSize) {
//		
//		return computerDAO.getComputerByName(search, offset, pageSize);
//	}
	
	@Transactional
	public void update(Computer computer) {
		
		computerRepository.save(computer);
	}
	
	@Transactional
	public long getNbRows() throws SQLException {
		
		return computerRepository.count();
	}
	
//	@Transactional
//	public int nbSearchedComputer(String search) {
//		
//		//Regarder le example matcher
//		return computerRepository.count();
//	}
	
//	@Transactional
//	public List<Computer> orderByName(int offset, int step){
//		
//		return computerRepository.findAll(columnName);
//	}
	
//	@Transactional
//	public List<Computer> orderByIntroduced(int offset, int step){
//		
//		return computerDAO.orderByIntroduced(offset, step);
//	}
//	
//	@Transactional
//	public List<Computer> orderByDiscontinued(int offset, int step){
//		
//		return computerDAO.orderByDiscontinued(offset, step);
//	}
//	
//	@Transactional
//	public List<Computer> orderByCompany(int offset, int step){
//		
//		return computerDAO.orderByCompany(offset, step);
//	}
}
