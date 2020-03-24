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

@Controller
public class Dashboard {
	
	private ComputerService computerService;
	
	public Dashboard(ComputerService computerService) {
		
		this.computerService = computerService;
	}

	@GetMapping(value="/dashboard")
	protected String getDashboard(@RequestParam(value="search", required = false) String search,
								  @RequestParam(value="orderBy", required = false) String orderBy,
								  @RequestParam(value="columnName", required = false) String columnName,
								  @RequestParam(value="pageIterator", defaultValue="1", required = false) int pageIterator,
								  @RequestParam(value="pageSize", defaultValue="10", required = false) int pageSize,
								  ModelMap modelMap)
			throws ServletException, IOException {

		int nbRows = 0;
		int offset  = (pageIterator - 1) * pageSize;

		List<Computer> computerListPag = computerService.listPage((pageIterator - 1) * pageSize, pageSize);
		List<Computer> computerSearchedList;
		
		try {
			nbRows = computerService.getNbRows();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		switch (orderBy) {
		case "computerName":
			computerListPag = computerService.orderByName(offset, pageSize);
			break;
		case "introduced":
			computerListPag = computerService.orderByIntroduced(offset, pageSize);
			break;
		case "discontinued":
			computerListPag = computerService.orderByDiscontinued(offset, pageSize);
			break;
		case "company":
			computerListPag = computerService.orderByCompany(offset, pageSize);
			break;
		default:
			computerListPag = computerService.listPage(offset, pageSize);
		}
		
		List<Computer> computerList = computerService.list();

		int nbSearchedComputer = computerService.nbSearchedComputer(search);
		
		computerSearchedList = computerService.findByName(search, offset, pageSize);

		if (search != null) {
			int lastPage = (int) Math.ceil((double) nbSearchedComputer / pageSize);
			
			modelMap.put("lastPage", lastPage);
		} else {
			int lastPage = (int) Math.ceil((double) computerList.size() / pageSize);
			
			modelMap.put("lastPage", lastPage);
		}

		modelMap.put("search", search);
		modelMap.put("orderBy", orderBy);
		modelMap.put("nbSearchedComputer", nbSearchedComputer);
		modelMap.put("computerSearchedList", computerSearchedList);
		modelMap.put("nbRows", nbRows);
		modelMap.put("pageIterator", pageIterator);
		modelMap.put("pageSize", pageSize);
		modelMap.put("computerList", computerList);
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
		
		return "/dashboard";
	}
}
