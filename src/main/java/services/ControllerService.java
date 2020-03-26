package services;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Computer;

@Service
public class ControllerService {

	private ComputerService computerService;
	
	public ControllerService(ComputerService computerService) {
		
		this.computerService = computerService;
	}
	
	public List<Computer> displayedListPag(String columnName, int pageIterator, int step) {
		
		List<Computer> computerListPag;
		int offset  = (pageIterator - 1) * step;
		
		switch (columnName) {
		case "computerName":
			return computerListPag = computerService.orderByName(offset, step);
		case "introduced":
			return computerListPag = computerService.orderByIntroduced(offset, step);
		case "discontinued":
			return computerListPag = computerService.orderByDiscontinued(offset, step);
		case "company":
			return computerListPag = computerService.orderByCompany(offset, step);
		default:
			return computerListPag = computerService.listPage(offset, step);
		}
	}
}
