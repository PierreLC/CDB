package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.ComputerDTO;
import mapper.ComputerMapper;
import model.Computer;
import services.ComputerService;
import services.ControllerService;

@Controller
public class Dashboard {
	
	private ComputerService computerService;
	private ControllerService controllerService;
	
	public Dashboard(ComputerService computerService, ControllerService controllerService) {
		
		this.controllerService = controllerService;
		this.computerService = computerService;
	}

	@GetMapping(value = "/dashboard")
	protected String getDashboard(@RequestParam(value="search", required = false) String search,
								  @RequestParam(value="orderBy", defaultValue = "default", required = false) String orderBy,
								  @RequestParam(value="columnName", defaultValue = "default", required = false) String columnName,
								  @RequestParam(value="pageIterator", defaultValue = "1", required = false) int pageIterator,
								  @RequestParam(value="step", defaultValue = "10", required = false) int step,
								  ModelMap modelMap)
			throws ServletException, IOException {

		int nbSearchedComputer = computerService.nbSearchedComputer(search);

		setNbRows(modelMap);
		setLastPage(search, step, nbSearchedComputer, modelMap);
		setComputerDTOListPag(columnName, pageIterator, step, modelMap);
		setComputerDTOSearchedListPag(search, columnName, pageIterator, step, modelMap);
		setView(modelMap, search, orderBy, nbSearchedComputer, pageIterator, step);
		
		return "dashboard";
	}

	@PostMapping(value="/dashboard")
	protected String postDashboard(@RequestParam(value="selection", required = false) String computerSelection,
								 ModelMap modelMap)
			throws ServletException, IOException {

		List<String> computerToDelete = Arrays.asList(computerSelection.split(","));
		
		for (String s : computerToDelete) {
			computerService.delete(Integer.parseInt(s));
		}
		
		return "redirect:/dashboard";
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
		
		List<Computer> computerListPag = controllerService.displayedListPag(columnName, pageIterator, step);
		List<ComputerDTO> computerDTOListPag = new ArrayList<>();
		computerListPag.stream().forEach(computer -> computerDTOListPag.add(ComputerMapper.computerToComputerDTO(computer)));
		
		modelMap.put("computerDTOListPag", computerDTOListPag);
	}
	public void setComputerDTOSearchedListPag(String search, String columnName, int pageIterator, int step, ModelMap modelMap) {
		
		int offset  = (pageIterator - 1) * step;
		
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
}
