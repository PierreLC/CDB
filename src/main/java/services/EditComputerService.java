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
public class EditComputerService {

	private ComputerService computerService;
	private CompanyService companyService;
	
	@Autowired
	public EditComputerService(ComputerService computerService, CompanyService companyService) {
		
		this.computerService = computerService;
		this.companyService = companyService;
	}
	
	public void updateComputer(String companyId, String computerName, String introduced, String discontinued, String id) {
		
		CompanyDTO companyDTO = new CompanyDTO.Builder().build();
		companyDTO.setIdDTO(Integer.parseInt(companyId));
		
		ComputerDTO computerDTO = new ComputerDTO.Builder().setNameDTO(computerName)
				 						.setIntroducedDTO(DateMapper.convertToLDT(introduced))
				 						.setDiscontinuedDTO(DateMapper.convertToLDT(discontinued))
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
	
	public ComputerDTO getComputerDTO(int id) {
		
		Computer computer = computerService.getComputerById(id);
		ComputerDTO computerDTO = ComputerMapper.computerToComputerDTO(computer);
		
		return computerDTO;
	}
	
	public List<CompanyDTO> getCompanyDTOList() throws SQLException {
		
		List<Company> companyList = companyService.list();
		List<CompanyDTO> companyDTOList = new ArrayList<>();
		companyList.parallelStream().forEach(company -> companyDTOList.add(CompanyMapper.companyToCompanyDTO(company)));
		
		return companyDTOList;
	}
}
