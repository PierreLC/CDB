package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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
		
		int offset  = (pageIterator - 1) * step;
		
		switch (EnumDisplayedPage.displayedPage(columnName)) {
		case COMPUTER_NAME:
			return computerService.orderByName(offset, step);
		case INTRODUCED:
			return computerService.orderByIntroduced(offset, step);
		case DISCONTINUED:
			return computerService.orderByDiscontinued(offset, step);
		case COMPANY:
			return computerService.orderByCompany(offset, step);
		default:
			return computerService.listPage(offset, step);
		}
	}
		
	public void setLastPage(String search, int step, int nbSearchedComputer, ModelAndView modelAndView) {
		
		List<Computer> computerList = computerService.list();
		
		if (search != null) {
			int lastPage = (int) Math.ceil((double) nbSearchedComputer / step);
			
			modelAndView.addObject("lastPage", lastPage);
		} else {
			int lastPage = (int) Math.ceil((double) computerList.size() / step);
			
			modelAndView.addObject("lastPage", lastPage);
		}
	}
	
	public void setNbRows(ModelAndView modelAndView) {
		
		try {
			int nbRows = computerService.getNbRows();
			
			modelAndView.addObject("nbRows", nbRows);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setComputerDTOListPag(String columnName, int pageIterator, int step, ModelAndView modelAndView) {
		
		List<Computer> computerListPag = displayedListPag(columnName, pageIterator, step);
		List<ComputerDTO> computerDTOListPag = new ArrayList<>();
		computerListPag.stream().forEach(computer -> computerDTOListPag.add(ComputerMapper.computerToComputerDTO(computer)));
		
		modelAndView.addObject("computerDTOListPag", computerDTOListPag);
	}
	public void setComputerDTOSearchedListPag(String search, String columnName, int pageIterator, int step, ModelAndView modelAndView) {
		
		int avoidBlankPage = 1;
		int offset  = (pageIterator - avoidBlankPage) * step;
		
		List<Computer> computerSearchedList = computerService.getComputerByName(search, offset, step);
		List<ComputerDTO> computerDTOSearchedList = new ArrayList<>();
		computerSearchedList.stream().forEach(computer -> computerDTOSearchedList.add(ComputerMapper.computerToComputerDTO(computer)));
		
		modelAndView.addObject("computerDTOSearchedList", computerDTOSearchedList);
	}
	
	public void setView(ModelAndView modelAndView, String search, String orderBy, int nbSearchedComputer, int pageIterator, int step) {
		
		modelAndView.addObject("search", search);
		modelAndView.addObject("orderBy", orderBy);
		modelAndView.addObject("nbSearchedComputer", nbSearchedComputer);
		modelAndView.addObject("pageIterator", pageIterator);
		modelAndView.addObject("step", step);
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
