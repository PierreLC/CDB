package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import dto.ComputerDTO;
import mapper.ComputerMapper;
import model.Computer;

@Service
public class DashboardService {

	private ComputerService computerService;
	
	@Autowired
	public DashboardService(ComputerService computerService) {
		
		this.computerService = computerService;
	}
	
	public List<Computer> displayedListPag(String columnName, int pageIterator, int step) {
		
		List<Computer> computerListPag;
		int offset  = (pageIterator - 1) * step;
		
		switch (EnumDisplayedPage.displayedPage(columnName)) {
		case COMPUTER_NAME:
			return computerListPag = computerService.orderByName(offset, step);
		case INTRODUCED:
			return computerListPag = computerService.orderByIntroduced(offset, step);
		case DISCONTINUED:
			return computerListPag = computerService.orderByDiscontinued(offset, step);
		case COMPANY:
			return computerListPag = computerService.orderByCompany(offset, step);
		default:
			return computerListPag = computerService.listPage(offset, step);
		}
	}
		
	public void setLastPage(String search, int step, int nbSearchedComputer, ModelMap modelMap) {
		
		List<Computer> computerList = computerService.list();
		
		if (search != null) {
			int lastPage = (int) Math.ceil((double) nbSearchedComputer / step);
			
			modelMap.put("lastPage", lastPage);
		} else {
			int lastPage = (int) Math.ceil((double) computerList.size() / step);
			
			modelMap.put("lastPage", lastPage);
		}
	}
	
	public void setNbRows(ModelMap modelMap) {
		
		try {
			int nbRows = computerService.getNbRows();
			
			modelMap.put("nbRows", nbRows);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setComputerDTOListPag(String columnName, int pageIterator, int step, ModelMap modelMap) {
		
		List<Computer> computerListPag = displayedListPag(columnName, pageIterator, step);
		List<ComputerDTO> computerDTOListPag = new ArrayList<>();
		computerListPag.stream().forEach(computer -> computerDTOListPag.add(ComputerMapper.computerToComputerDTO(computer)));
		
		modelMap.put("computerDTOListPag", computerDTOListPag);
	}
	public void setComputerDTOSearchedListPag(String search, String columnName, int pageIterator, int step, ModelMap modelMap) {
		
		int avoidBlankPage = 1;
		int offset  = (pageIterator - avoidBlankPage) * step;
		
		List<Computer> computerSearchedList = computerService.getComputerByName(search, offset, step);
		List<ComputerDTO> computerDTOSearchedList = new ArrayList<>();
		computerSearchedList.stream().forEach(computer -> computerDTOSearchedList.add(ComputerMapper.computerToComputerDTO(computer)));
		
		modelMap.put("computerDTOSearchedList", computerDTOSearchedList);
	}
	
	public void setView(ModelMap modelMap, String search, String orderBy, int nbSearchedComputer, int pageIterator, int step) {
		
		modelMap.put("search", search);
		modelMap.put("orderBy", orderBy);
		modelMap.put("nbSearchedComputer", nbSearchedComputer);
		modelMap.put("pageIterator", pageIterator);
		modelMap.put("step", step);
	}
	
	public int getNbSearchedComputer(String search) {
		
		int nbSearchedComputer = computerService.nbSearchedComputer(search);
		
		return nbSearchedComputer;
	}
	
	public void deleteComputerSelection(String computerSelection) {
		
		List<String> computerToDelete = Arrays.asList(computerSelection.split(","));
		
		for (String computer : computerToDelete) {
			computerService.delete(Integer.parseInt(computer));
		}
	}
}
