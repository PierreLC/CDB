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
public class EditComputer {
	
	private CompanyService companyService;
	private ComputerService computerService;
	
	public EditComputer(CompanyService companyService, ComputerService computerService) {
		this.computerService = computerService;
		this.companyService = companyService;
	}
	
	@GetMapping("/editComputer")
	protected void getUpdate(@RequestParam(value="id", required = false) String id,
						     ModelMap modelMap) 
		throws ServletException, IOException, SQLException {

			List<Company> companyList = companyService.list();
			Computer computer = getComputerById(id);
			
			setView(modelMap, computer, companyList);
	}
	
	@PostMapping("/editComputer")
	protected String postUpdate(@RequestParam(value="computerId", required = false) String id,
								@RequestParam(value="computerName", required = false) String computerName,
						        @RequestParam(value="introduced", required = false) String introduced,
						        @RequestParam(value="discontinued", required = false) String discontinued,
						        @RequestParam(value="companyId", required = false) String companyId,
						        ModelMap modelMap)
	throws ServletException, IOException{
		
		updateComputer(companyId, computerName, introduced, discontinued, id);
		
		return "redirect:/dashboard";
	}
	
	public void updateComputer(String companyId, String computerName, String introduced, String discontinued, String id) {
		
		Company company = companyService.findById(Integer.parseInt(companyId));
		
		Computer computer = new Computer.Builder().setName(computerName)
				 						.setIntroducedDate(DateUtils.convertToLDT(introduced))
				 						.setDiscontinuedDate(DateUtils.convertToLDT(discontinued))
				 						.setCompany(company)
				 						.build();
		
		computer.setId(Integer.parseInt(id));
		
		computerService.update(computer);
	}
	
	public void setView(ModelMap modelMap, Computer computer, List<Company> companyList) {
		
		modelMap.put("companyList", companyList);
		modelMap.put("computerId", computer.getId());
		modelMap.put("computerName", computer.getName());
		modelMap.put("introduced", computer.getIntroduced());
		modelMap.put("discontinued", computer.getDiscontinued());
		modelMap.put("company", computer.getCompany().getId());
	}
	
	public Computer getComputerById(String id) {
		
		Computer computer = computerService.findById(Integer.parseInt(id));
		
		return computer;
	}
}
