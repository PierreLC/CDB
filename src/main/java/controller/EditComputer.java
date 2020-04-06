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
			List<CompanyDTO> companyDTOList = new ArrayList<>();
			companyList.stream().forEach(company -> companyDTOList.add(CompanyMapper.companyToCompanyDTO(company)));
			
			Computer computer = computerService.getComputerById(Integer.parseInt(id));
			ComputerDTO computerDTO = ComputerMapper.computerToComputerDTO(computer);
			
			setView(modelMap, computerDTO, companyDTOList);
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
		
		CompanyDTO companyDTO = new CompanyDTO.Builder().build();
		companyDTO.setIdDTO(Integer.parseInt(companyId));
		
		ComputerDTO computerDTO = new ComputerDTO.Builder().setNameDTO(computerName)
				 						.setIntroducedDTO(DateUtils.convertToLDT(introduced))
				 						.setDiscontinuedDTO(DateUtils.convertToLDT(discontinued))
				 						.setCompanyDTO(companyDTO)
				 						.build();
		
		computerDTO.setIdDTO(Integer.parseInt(id));
		
		Computer computer = ComputerMapper.computerDTOToComputer(computerDTO);
		
		computerService.update(computer);
	}
	
	public void setView(ModelMap modelMap, ComputerDTO computerDTO, List<CompanyDTO> companyDTOList) {
		
		modelMap.put("companyDTOList", companyDTOList);
		modelMap.put("computerDTOId", computerDTO.getIdDTO());
		modelMap.put("computerDTOName", computerDTO.getNameDTO());
		modelMap.put("introducedDTO", computerDTO.getIntroducedDTO());
		modelMap.put("discontinuedDTO", computerDTO.getDiscontinuedDTO());
		modelMap.put("companyDTO", computerDTO.getCompanyDTO().getIdDTO());
	}
}
