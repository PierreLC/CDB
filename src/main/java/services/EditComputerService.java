package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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
		
		System.out.println(id);
		
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
	
	public void setView(ModelAndView modelAndView, ComputerDTO computerDTO, List<CompanyDTO> companyDTOList) {
		
		modelAndView.addObject("companyDTOList", companyDTOList);
		modelAndView.addObject("computerDTOId", computerDTO.getIdDTO());
		modelAndView.addObject("computerDTOName", computerDTO.getNameDTO());
		modelAndView.addObject("introducedDTO", computerDTO.getIntroducedDTO());
		modelAndView.addObject("discontinuedDTO", computerDTO.getDiscontinuedDTO());
		modelAndView.addObject("companyDTO", computerDTO.getCompanyDTO().getIdDTO());
	}
	
	public Optional<ComputerDTO> getComputerDTO(int id) {
		
		Optional<Computer> computer = computerService.getComputerById(id);
		Optional<ComputerDTO> computerDTO = computer.map(ComputerMapper::computerToComputerDTO);
		
		return computerDTO;
	}
	
	public List<CompanyDTO> getCompanyDTOList() throws SQLException {
		
		List<Company> companyList = companyService.list();
		List<CompanyDTO> companyDTOList = new ArrayList<>();
		companyList.parallelStream().forEach(company -> companyDTOList.add(CompanyMapper.companyToCompanyDTO(company)));
		
		return companyDTOList;
	}
}
