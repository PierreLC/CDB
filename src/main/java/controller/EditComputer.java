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

import model.Company;
import model.Computer;
import services.CompanyService;
import services.ComputerService;

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
		throws ServletException, IOException {
		
		List<Company> companyList;
		try {
			companyList = companyService.list();
			modelMap.put("companyList", companyList);
			
			Computer computer = computerService.findById(Integer.parseInt(id));
			
			modelMap.put("computerId", computer.getId());
			modelMap.put("computerName", computer.getName());
			modelMap.put("introduced", computer.getIntroduced());
			modelMap.put("discontinued", computer.getDiscontinued());
			modelMap.put("company", computer.getCompany());
			
		} catch (SQLException e) {
			e.getMessage();
		}
	}
	
	@PostMapping("/editComputer")
	protected String postUpdate(@RequestParam(value="computerId", required = false) String computerName,
						 @RequestParam(value="introduced", required = false) String introduced,
						 @RequestParam(value="discontinued", required = false) String discontinued,
						 @RequestParam(value="companyId", required = false) String companyId,
						 ModelMap modelMap)
	throws ServletException, IOException{
		
		int id = Integer.parseInt(companyId);
		
		Company company = companyService.find_by_id(id);
		
		Computer computer = new Computer.Builder().setName(computerName)
				 .setIntroducedDate(LocalDateTime.parse(introduced))
				 .setDiscontinuedDate(LocalDateTime.parse(discontinued))
				 .setCompany(company)
				 .build();
		
		computerService.update(computer);
		
		return "redirect:/dashbord";
	}
}
