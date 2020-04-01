package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

		int offset  = (pageIterator - 1) * step;
		int nbSearchedComputer = computerService.nbSearchedComputer(search);

		List<Computer> computerListPag = controllerService.displayedListPag(columnName, pageIterator, step);
		List<Computer> computerSearchedList = computerService.findByName(search, offset, step);
		
		setLastPage(search, step, nbSearchedComputer, modelMap);
		setNbRows(modelMap);
		
		modelMap.put("search", search);
		modelMap.put("orderBy", orderBy);
		modelMap.put("nbSearchedComputer", nbSearchedComputer);
		modelMap.put("computerSearchedList", computerSearchedList);
		modelMap.put("pageIterator", pageIterator);
		modelMap.put("step", step);
		modelMap.put("computerListPag", computerListPag);
		
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
}
