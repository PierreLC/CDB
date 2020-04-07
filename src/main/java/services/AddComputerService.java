package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import dto.CompanyDTO;
import dto.ComputerDTO;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import mapper.DateMapper;
import model.Company;
import model.Computer;

@Service
public class AddComputerService {

	private CompanyService companyService;
	private ComputerService computerService;
	
	@Autowired
	public AddComputerService(CompanyService companyService, ComputerService computerService) {
		
		this.companyService = companyService;
		this.computerService = computerService;
	}
	
	public void setCompanyDTOList(ModelMap modelMap) throws SQLException {
		
		List<Company> companyList = companyService.list();
		List<CompanyDTO> companyDTOList = new ArrayList<>();
		companyList.stream().forEach(company -> companyDTOList.add(CompanyMapper.companyToCompanyDTO(company)));
		
		modelMap.put("companyDTOList", companyDTOList);
	}
	
	public void addComputer(String companyId, String computerName, String introduced, String discontinued) throws SQLException {
		
		CompanyDTO companyDTO = new CompanyDTO.Builder().setIdDTO(Integer.parseInt(companyId)).build();
		
		ComputerDTO computerDTO = new ComputerDTO.Builder().setNameDTO(computerName)
												  .setIntroducedDTO(DateMapper.convertToLDT(introduced))
												  .setDiscontinuedDTO(DateMapper.convertToLDT(discontinued))
												  .setCompanyDTO(companyDTO)
												  .build();
		
		Computer computer = ComputerMapper.computerDTOToComputer(computerDTO);
		
		computerService.add(computer);
	}
}
