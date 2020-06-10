package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.CompanyDTO;
import dto.ComputerDTO;
import services.EditComputerService;

@Controller
@RequestMapping(value = "/editComputer")
public class EditComputer {
	
	private EditComputerService editComputerService;
	
	public EditComputer(EditComputerService editComputerService) {

		this.editComputerService = editComputerService;
	}
	
	@GetMapping
	protected ModelAndView getUpdate(ParamsControllers paramsControllers) 
		throws ServletException, IOException, SQLException {
		
		ModelAndView modelAndView = new ModelAndView();
		
		List<CompanyDTO> companyDTOList = editComputerService.getCompanyDTOList();
		Optional<ComputerDTO> computerDTO = editComputerService.getComputerDTO(Integer.parseInt(paramsControllers.getId()));
		
		if(computerDTO.isPresent()) {
			editComputerService.setView(modelAndView, computerDTO.get(), companyDTOList);
		}
		
		return modelAndView;
	}
	
	@PostMapping
	protected ModelAndView postUpdate(ParamsControllers paramsControllers)
	throws ServletException, IOException{
		
		ModelAndView modelAndView = new ModelAndView("redirect:/dashboard");
		
		editComputerService.updateComputer(paramsControllers.getCompanyId(), paramsControllers.getComputerName(), paramsControllers.getIntroduced(), paramsControllers.getDiscontinued(), paramsControllers.getId());
		
		return modelAndView;
	}
}
