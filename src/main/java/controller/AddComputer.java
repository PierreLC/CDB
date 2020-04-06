package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.CompanyDTO;
import dto.ComputerDTO;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
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
		
		setCompanyDTOList(modelMap);
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
		
		CompanyDTO companyDTO = new CompanyDTO.Builder().setIdDTO(Integer.parseInt(companyId)).build();
		
		ComputerDTO computerDTO = new ComputerDTO.Builder().setNameDTO(computerName)
												  .setIntroducedDTO(DateUtils.convertToLDT(introduced))
												  .setDiscontinuedDTO(DateUtils.convertToLDT(discontinued))
												  .setCompanyDTO(companyDTO)
												  .build();
		
		Computer computer = ComputerMapper.computerDTOToComputer(computerDTO);
		
		computerService.add(computer);
	}
	
	public void setCompanyDTOList(ModelMap modelMap) throws SQLException {
		
		List<Company> companyList = companyService.list();
		List<CompanyDTO> companyDTOList = new ArrayList<>();
		companyList.stream().forEach(company -> companyDTOList.add(CompanyMapper.companyToCompanyDTO(company)));
		
		modelMap.put("companyDTOList", companyDTOList);
	}
}
