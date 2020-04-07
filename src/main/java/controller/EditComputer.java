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

import dto.CompanyDTO;
import dto.ComputerDTO;
import services.EditComputerService;

@Controller
public class EditComputer {
	
	private EditComputerService editComputerService;
	
	public EditComputer(EditComputerService editComputerService) {

		this.editComputerService = editComputerService;
	}
	
	@GetMapping("/editComputer")
	protected void getUpdate(@RequestParam(value="id", required = false) String id,
						     ModelMap modelMap) 
		throws ServletException, IOException, SQLException {
		
		List<CompanyDTO> companyDTOList = editComputerService.getCompanyDTOList();
		ComputerDTO computerDTO = editComputerService.getComputerDTO(Integer.parseInt(id));
			
		editComputerService.setView(modelMap, computerDTO, companyDTOList);
	}
	
	@PostMapping("/editComputer")
	protected String postUpdate(@RequestParam(value="computerId", required = false) String id,
								@RequestParam(value="computerName", required = false) String computerName,
						        @RequestParam(value="introduced", required = false) String introduced,
						        @RequestParam(value="discontinued", required = false) String discontinued,
						        @RequestParam(value="companyId", required = false) String companyId,
						        ModelMap modelMap)
	throws ServletException, IOException{
		
		editComputerService.updateComputer(companyId, computerName, introduced, discontinued, id);
		
		return "redirect:/dashboard";
	}
}
