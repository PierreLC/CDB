package controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.DashboardService;

@Controller
@RequestMapping(value = "/dashboard")
public class Dashboard {
	
	private DashboardService dashboardService;

	public Dashboard(DashboardService dashboardService) {

		this.dashboardService = dashboardService;
	}

	@GetMapping
	protected ModelAndView getDashboard(ParamsControllers paramsControllers)
			throws ServletException, IOException {
		
		ModelAndView modelAndView = new ModelAndView("dashboard");

		int nbSearchedComputer = dashboardService.getNbSearchedComputer(paramsControllers.getSearch());
		
		dashboardService.setNbRows(modelAndView);
		dashboardService.setLastPage(paramsControllers, nbSearchedComputer, modelAndView);
		
		 dashboardService.setComputerDTOListPag(paramsControllers, modelAndView);
		 dashboardService.setComputerDTOSearchedListPag(paramsControllers, modelAndView);
		 dashboardService.setView(modelAndView, paramsControllers, nbSearchedComputer);
		
//		if(paramsControllers.getStep() != null && paramsControllers.getPageIterator() != null) {
//
//		String columnName = "";
//		
//		dashboardService.setComputerDTOListPag(columnName, paramsControllers.getPageIterator(), paramsControllers.getStep(), modelAndView);
//		dashboardService.setComputerDTOSearchedListPag(paramsControllers.getSearch(), paramsControllers.getColumnName(), paramsControllers.getPageIterator(), paramsControllers.getStep(), modelAndView);
//		dashboardService.setView(modelAndView, paramsControllers.getSearch(), paramsControllers.getOrderBy(), nbSearchedComputer, paramsControllers.getPageIterator(), paramsControllers.getStep());
//		
//		} else if(paramsControllers.getStep() != null && paramsControllers.getPageIterator() != null && paramsControllers.getColumnName() != null){
//		 
//			System.out.println("passage dans le cas 2");
//			
//		 dashboardService.setComputerDTOListPag(paramsControllers.getColumnName(), paramsControllers.getPageIterator(), paramsControllers.getStep(), modelAndView);
//		 dashboardService.setComputerDTOSearchedListPag(paramsControllers.getSearch(), paramsControllers.getColumnName(), paramsControllers.getPageIterator(), paramsControllers.getStep(), modelAndView);
//		 dashboardService.setView(modelAndView, paramsControllers.getSearch(), paramsControllers.getOrderBy(), nbSearchedComputer, paramsControllers.getPageIterator(), paramsControllers.getStep());
//		 
//		} else {
//			
//			 String step = "10";
//			 String pageIterator = "1";
//			 String columnName = "";
//			 
//			 dashboardService.setComputerDTOListPag(columnName, pageIterator, step, modelAndView);
//			 dashboardService.setComputerDTOSearchedListPag(paramsControllers.getSearch(), columnName, pageIterator, step, modelAndView);
//			 dashboardService.setView(modelAndView, paramsControllers.getSearch(), paramsControllers.getOrderBy(), nbSearchedComputer, pageIterator, step);
//		}
		
		return modelAndView;
	}

	@PostMapping
	protected ModelAndView postDashboard(ParamsControllers paramsControllers)
			throws ServletException, IOException {

		ModelAndView modelAndView = new ModelAndView("redirect:/dashboard");
		
		dashboardService.deleteComputerSelection(paramsControllers.getComputerSelection());

		return modelAndView;
	}
}
