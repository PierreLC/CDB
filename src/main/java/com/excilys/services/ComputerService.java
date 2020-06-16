package com.excilys.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.excilys.model.Computer;
import com.excilys.repository.ComputerRepository;

@Service
public class ComputerService {
	
	@Autowired
	private ComputerRepository computerRepository;

	public void add(Computer computer) throws SQLException {
		
		computerRepository.save(computer);
	}

	public List<Computer> list(){
		
		return computerRepository.findAll();
	}

	public Page<Computer> listPage(int offset, int pageSize){
		
		Page<Computer> computersPag = computerRepository.findAll(PageRequest.of(offset, pageSize));
		
		return computersPag;
	}

	public void delete(long id) {
		
		computerRepository.deleteById(id);
	}

	public Optional<Computer> getComputerById(long id) {
		
		return computerRepository.findById(id);
	}

	public Page<Computer> getComputerByName(String search, int offset, int pageSize) {
		
		return computerRepository.findByNameContaining(search, PageRequest.of(offset, pageSize));
	}

	public void update(Computer computer) {
		
		computerRepository.save(computer);
	}

	public long getNbRows() throws SQLException {
		
		return computerRepository.count();
	}

	public long nbSearchedComputer(String search) {
		
		return computerRepository.countByNameContaining(search);
	}

	public Page<Computer> orderByName(int offset, int step){
		
		Page<Computer> computersByName = computerRepository.findAll(PageRequest.of(offset, step, Sort.by(Sort.Direction.ASC, "name")));
		
		return computersByName;
	}

	public Page<Computer> orderByIntroduced(int offset, int step){
		
		Page<Computer> computersByIntroduced = computerRepository.findAll(PageRequest.of(offset, step, Sort.by(Sort.Direction.ASC, "introduced")));
		
		return computersByIntroduced;
	}

	public Page<Computer> orderByDiscontinued(int offset, int step){
		
		Page<Computer> computersByDiscontinued = computerRepository.findAll(PageRequest.of(offset, step, Sort.by(Sort.Direction.ASC, "discontinued")));
		
		return computersByDiscontinued;
	}

	public Page<Computer> orderByCompany(int offset, int step){
		
		Page<Computer> computersByCompany = computerRepository.findAll(PageRequest.of(offset, step, Sort.by(Sort.Direction.ASC, "company")));
		
		return computersByCompany;
	}
	
//	public void deleteComputersById(String computerSelection) {
//		
//		List<String> computerToDelete = Arrays.asList(computerSelection.split(","));
//		
//		computerRepository.deleteComputerById(computerToDelete);
//	}
}
