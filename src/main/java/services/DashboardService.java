package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import controller.ParamsControllers;
import dto.ComputerDTO;
import mapper.ComputerMapper;
import model.Computer;

@Service
public class DashboardService {

	private ComputerService computerService;

	@Autowired
	public DashboardService(ComputerService computerService) {

		this.computerService = computerService;
	}

	public List<Computer> displayedListPag(ParamsControllers paramsControllers) {

		List<Computer> listComputerPag;
		
		String step = "10";
		
		int offsetLimit = 1;
		
		System.out.println(step);
		System.out.println(paramsControllers.getPageIterator());
		
		int offset = (Integer.parseInt(paramsControllers.getPageIterator()) - offsetLimit) * Integer.parseInt(step);

		switch (EnumDisplayedPage.displayedPage(paramsControllers.getColumnName())) {
		case COMPUTER_NAME:
			listComputerPag = computerService.orderByName(offset, Integer.parseInt(paramsControllers.getStep()));
			return listComputerPag;
		case INTRODUCED:
			listComputerPag = computerService.orderByIntroduced(offset, Integer.parseInt(paramsControllers.getStep()));
			return listComputerPag;
		case DISCONTINUED:
			listComputerPag = computerService.orderByDiscontinued(offset, Integer.parseInt(paramsControllers.getStep()));
			return listComputerPag;
		case COMPANY:
			listComputerPag = computerService.orderByCompany(offset, Integer.parseInt(paramsControllers.getStep()));
			return listComputerPag;
		default:
			listComputerPag = computerService.listPage(offset, Integer.parseInt(paramsControllers.getStep()));
			return listComputerPag;
		}
	}

	public void setLastPage(ParamsControllers paramsControllers, int nbSearchedComputer, ModelAndView modelAndView) {

		List<Computer> computerList = computerService.list();

		if (paramsControllers.getStep() != null) {
			if (paramsControllers.getSearch() != null) {
				int lastPage = (int) Math.ceil((double) nbSearchedComputer / Integer.parseInt(paramsControllers.getStep()));

				modelAndView.addObject("lastPage", lastPage);
			} else {

				int lastPage = (int) Math.ceil(computerList.size() / Integer.parseInt(paramsControllers.getStep()));

				modelAndView.addObject("lastPage", lastPage);
			}
		} else {	
			String step = "10";
			if (paramsControllers.getSearch() != null) {
				int lastPage = (int) Math.ceil((double) nbSearchedComputer / Integer.parseInt(step));

				modelAndView.addObject("lastPage", lastPage);
			} else {

				double lastPage = Math.ceil(computerList.size() / Integer.parseInt(step));

				modelAndView.addObject("lastPage", lastPage);
			}

		}
	}

	public void setNbRows(ModelAndView modelAndView) {

		try {
			int nbRows = computerService.getNbRows();

			modelAndView.addObject("nbRows", nbRows);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setComputerDTOListPag(ParamsControllers paramsControllers, ModelAndView modelAndView) {
	
		List<Computer> computerListPag = displayedListPag(paramsControllers);
		List<ComputerDTO> computerDTOListPag = new ArrayList<>();
		computerListPag.stream()
				.forEach(computer -> computerDTOListPag.add(ComputerMapper.computerToComputerDTO(computer)));

		modelAndView.addObject("computerDTOListPag", computerDTOListPag);
	}

	public void setComputerDTOSearchedListPag(ParamsControllers paramsControllers, ModelAndView modelAndView) {

		int avoidBlankPage = 1;
		int offset = (Integer.parseInt(paramsControllers.getPageIterator()) - avoidBlankPage) * Integer.parseInt(paramsControllers.getStep());

		List<Computer> computerSearchedList = computerService.getComputerByName(paramsControllers.getSearch(), offset, paramsControllers.getStep());
		List<ComputerDTO> computerDTOSearchedList = new ArrayList<>();
		computerSearchedList.stream()
				.forEach(computer -> computerDTOSearchedList.add(ComputerMapper.computerToComputerDTO(computer)));

		modelAndView.addObject("computerDTOSearchedList", computerDTOSearchedList);
	}

	public void setView(ModelAndView modelAndView, ParamsControllers paramsControllers, int nbSearchedComputer) {

		modelAndView.addObject("search", paramsControllers.getSearch());
		modelAndView.addObject("orderBy", paramsControllers.getOrderBy());
		modelAndView.addObject("nbSearchedComputer", nbSearchedComputer);
		modelAndView.addObject("pageIterator", paramsControllers.getPageIterator());
		modelAndView.addObject("step", paramsControllers.getStep());
	}

	public int getNbSearchedComputer(String search) {

		int nbSearchedComputer = computerService.nbSearchedComputer(search);

		return nbSearchedComputer;
	}

	public void deleteComputerSelection(String computerSelection) {

		List<String> computerToDelete = Arrays.asList(computerSelection.split(","));

		for (String computer : computerToDelete) {
			computerService.delete(Integer.parseInt(computer));
		}
	}
}
