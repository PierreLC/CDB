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

		dashboardService.setNbRows(modelAndView);
		dashboardService.setPage(paramsControllers, modelAndView);
		
		return modelAndView;
	}

	@PostMapping
	protected ModelAndView postDashboard(ParamsControllers paramsControllers)
			throws ServletException, IOException {

		ModelAndView modelAndView = new ModelAndView("redirect:/dashboard");
		
		//Faire un méthode pour valider la valeur de la selection
		dashboardService.deleteComputerSelection(paramsControllers.getComputerSelection());

		return modelAndView;
	}
}
