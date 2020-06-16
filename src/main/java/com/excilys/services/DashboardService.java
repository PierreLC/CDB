package com.excilys.services;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.controller.ParamsControllers;
import com.excilys.mapper.Paginate;

@Service
public class DashboardService {

	private ComputerService computerService;
	private Paginate page;

	@Autowired
	public DashboardService(ComputerService computerService, Paginate page) {

		this.computerService = computerService;
		this.page = page;
	}
	
	public void setPage(ParamsControllers paramsControllers, ModelAndView modelAndView) throws SQLException {
		
		page.paginate(paramsControllers, modelAndView);
	}
	
	public void deleteComputerSelection(String selection) {

		System.out.println(selection);
		List<String> computerToDelete = Arrays.asList(selection.split(","));

		for (String computer : computerToDelete) {
			computerService.delete(Long.parseLong(computer));
		}
	}
}
