package com.excilys.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.mapper.ParamsControllers;
import com.excilys.services.AddComputerService;

@Controller
@RequestMapping(value = "/addComputer")
public class AddComputer {
	
	private AddComputerService addComputerService;

	public AddComputer(AddComputerService addComputerService) {
		
		this.addComputerService = addComputerService;
	}

	@GetMapping
	protected ModelAndView getAddComputer()
			throws ServletException, IOException, SQLException {
		
		ModelAndView modelAndView = new ModelAndView();
		
		addComputerService.setCompanyDTOList(modelAndView);
		
		return modelAndView;
	}
	
	@PostMapping
	protected String postDashboard(ParamsControllers paramsControllers)
			throws ServletException, IOException, SQLException {
		
		addComputerService.addComputer(paramsControllers.getCompanyId(), paramsControllers.getComputerName(), paramsControllers.getDiscontinued(), paramsControllers.getIntroduced());

		return "redirect:/dashboard";
	}
}

