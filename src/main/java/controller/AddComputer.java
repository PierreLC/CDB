package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Company;
import model.Computer;
import services.CompanyService;
import services.ComputerService;
import utils.DateUtils;

@Controller
public class AddComputer {
	
	private CompanyService companyService;
	private ComputerService computerService;

	public AddComputer(CompanyService companyService, ComputerService computerService) {
		
		this.companyService = companyService;
		this.computerService = computerService;
	}

	@GetMapping("/addComputer")
	protected void getAddComputer(ModelMap modelMap)
			throws ServletException, IOException, SQLException {
		
			List<Company> companyList = companyService.list();
			modelMap.put("companyList", companyList);
	}
	
	@PostMapping("/addComputer")
	protected String postDashboard(@RequestParam(value="computerName", required = false) String computerName,
								   @RequestParam(value="introduced", required = false) String introduced,
								   @RequestParam(value="discontinued", required = false) String discontinued,
								   @RequestParam(value="companyId", required = false) String companyId)
			throws ServletException, IOException, SQLException {
		
		addComputer(companyId, computerName, introduced, discontinued);

		return "redirect:/dashboard";
	}
	
	public void addComputer(String companyId, String computerName, String introduced, String discontinued) throws SQLException {
		
		Company company = companyService.findById(Integer.parseInt(companyId));
		
		Computer computer = new Computer.Builder().setName(computerName)
												  .setIntroducedDate(DateUtils.convertToLDT(introduced))
												  .setDiscontinuedDate(DateUtils.convertToLDT(discontinued))
												  .setCompany(company)
												  .build();
		
		computerService.add(computer);
	}
}
