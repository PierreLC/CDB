package com.excilys.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.CompanyMapper;
import com.excilys.mapper.ComputerMapper;
import com.excilys.mapper.DateMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;

@Service
public class AddComputerService {

	private CompanyService companyService;
	private ComputerService computerService;
	
	@Autowired
	public AddComputerService(CompanyService companyService, ComputerService computerService) {
		
		this.companyService = companyService;
		this.computerService = computerService;
	}
	
	public void setCompanyDTOList(ModelAndView modelAndView) throws SQLException {
		
		List<Company> companyList = companyService.list();
		List<CompanyDTO> companyDTOList = new ArrayList<>();
		companyList.stream().forEach(company -> companyDTOList.add(CompanyMapper.companyToCompanyDTO(company)));
		
		modelAndView.addObject("companyDTOList", companyDTOList);
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
