package services;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import controller.ParamsControllers;
import mapper.Paginate;

@Service
public class DashboardService {

	private ComputerService computerService;
	private Paginate page;

	@Autowired
	public DashboardService(ComputerService computerService, Paginate page) {

		this.computerService = computerService;
		this.page = page;
	}

	public void setNbRows(ModelAndView modelAndView) {

		try {
			long nbRows = computerService.getNbRows();

			modelAndView.addObject("nbRows", nbRows);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void setPage(ParamsControllers paramsControllers, ModelAndView modelAndView) {
		
		page.paginate(paramsControllers, modelAndView);
	}
	
	public void deleteComputerSelection(String computerSelection) {

		List<String> computerToDelete = Arrays.asList(computerSelection.split(","));

		for (String computer : computerToDelete) {
			computerService.delete(Integer.parseInt(computer));
		}
	}
}
