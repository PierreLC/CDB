package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import services.AddComputerService;

@Controller
public class AddComputer {
	
	private AddComputerService addComputerService;

	public AddComputer(AddComputerService addComputerService) {
		
		this.addComputerService = addComputerService;
	}

	@GetMapping("/addComputer")
	protected void getAddComputer(ModelMap modelMap)
			throws ServletException, IOException, SQLException {
		
		addComputerService.setCompanyDTOList(modelMap);
	}
	
	@PostMapping("/addComputer")
	protected String postDashboard(@RequestParam(value="computerName", required = false) String computerName,
								   @RequestParam(value="introduced", required = false) String introduced,
								   @RequestParam(value="discontinued", required = false) String discontinued,
								   @RequestParam(value="companyId", required = false) String companyId)
			throws ServletException, IOException, SQLException {
		
		addComputerService.addComputer(companyId, computerName, introduced, discontinued);

		return "redirect:/dashboard";
	}
}
