package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import controller.ParamsControllers;
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
	private int id;
	private int idDefaultValue;
	
	@Autowired
	public EditComputerService(ComputerService computerService, CompanyService companyService) {
		
		this.computerService = computerService;
		this.companyService = companyService;
	}
	
	public void updateComputer(ParamsControllers paramsControllers) {
		
		setId(paramsControllers);
		
		CompanyDTO companyDTO = new CompanyDTO.Builder().build();
		companyDTO.setIdDTO(Integer.parseInt(paramsControllers.getCompanyId()));
		
		ComputerDTO computerDTO = new ComputerDTO.Builder().setNameDTO(paramsControllers.getComputerName())
				 						.setIntroducedDTO(DateMapper.convertToLDT(paramsControllers.getIntroduced()))
				 						.setDiscontinuedDTO(DateMapper.convertToLDT(paramsControllers.getDiscontinued()))
				 						.setCompanyDTO(companyDTO)
				 						.build();
		
		computerDTO.setIdDTO(id);
		
		Computer computer = ComputerMapper.computerDTOToComputer(computerDTO);
		
		computerService.update(computer);
	}
	
	public void setView(ModelAndView modelAndView, ComputerDTO computerDTO) throws SQLException {
		
		modelAndView.addObject("companyDTOList", getCompanyDTOList());
		modelAndView.addObject("computerDTOId", computerDTO.getIdDTO());
		modelAndView.addObject("computerDTOName", computerDTO.getNameDTO());
		modelAndView.addObject("introducedDTO", computerDTO.getIntroducedDTO());
		modelAndView.addObject("discontinuedDTO", computerDTO.getDiscontinuedDTO());
	}
	
	public Optional<ComputerDTO> getComputerDTO(ParamsControllers paramsControllers) {
		
		setId(paramsControllers);
		
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
	
	private void setId(ParamsControllers paramsControllers) {
		
		if (paramsControllers.getId() != null) {
			this.id = Integer.parseInt(paramsControllers.getId());
		} else {
			this.id = idDefaultValue;
		}
	}
}
