package controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import services.DashboardService;

@Controller
public class Dashboard {
	
	private DashboardService dashboardService;
	
	public Dashboard(DashboardService dashboardService) {
		
		this.dashboardService = dashboardService;
	}

	@GetMapping("/dashboard")
	protected String getDashboard(@RequestParam(value="search", required = false) String search,
								  @RequestParam(value="orderBy", defaultValue = "default", required = false) String orderBy,
								  @RequestParam(value="columnName", defaultValue = "default", required = false) String columnName,
								  @RequestParam(value="pageIterator", defaultValue = "1", required = false) int pageIterator,
								  @RequestParam(value="step", defaultValue = "10", required = false) int step,
								  ModelMap modelMap)
			throws ServletException, IOException {

		int nbSearchedComputer = dashboardService.getNbSearchedComputer(search);

		dashboardService.setNbRows(modelMap);
		dashboardService.setLastPage(search, step, nbSearchedComputer, modelMap);
		dashboardService.setComputerDTOListPag(columnName, pageIterator, step, modelMap);
		dashboardService.setComputerDTOSearchedListPag(search, columnName, pageIterator, step, modelMap);
		dashboardService.setView(modelMap, search, orderBy, nbSearchedComputer, pageIterator, step);
		
		return "dashboard";
	}

	@PostMapping("/dashboard")
	protected String postDashboard(@RequestParam(value="selection", required = false) String computerSelection,
								 ModelMap modelMap)
			throws ServletException, IOException {
		
		dashboardService.deleteComputerSelection(computerSelection);
		
		return "redirect:/dashboard";
	}
}
