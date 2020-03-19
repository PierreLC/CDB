package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.ComputerDAO;
import model.Company;
import model.Computer;
import services.CompanyService;

@Controller
public class AddComputer {
	
	private CompanyService companyService;
	private ComputerDAO computerDAO;

	public AddComputer(CompanyService companyService, ComputerDAO computerDAO) {
		
		this.companyService = companyService;
		this.computerDAO = computerDAO;
	}

	@GetMapping("/addComputer")
	protected void getAddComputer(ModelMap modelMap)
			throws ServletException, IOException {
		
		try {
			List<Company> companyList = companyService.list();
			modelMap.put("companyList", companyList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/addComputer")
	protected String postDashboard(@RequestParam(value="computerName", required = false) String computerName,
								   @RequestParam(value="introduced", required = false) String introduced,
								   @RequestParam(value="discontinued", required = false) String discontinued,
								   @RequestParam(value="companyId", required = false) String companyId)
			throws ServletException, IOException {
		
		Company company = companyService.find_by_id(Integer.parseInt(companyId));
		
		Computer computer = new Computer.Builder().setName(computerName)
												 .setIntroducedDate(LocalDateTime.parse(introduced))
												 .setDiscontinuedDate(LocalDateTime.parse(discontinued))
												 .setCompany(company)
												 .build();
		
		try {
			computerDAO.add(computer);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "redirect:/dashboard";
	}
}
