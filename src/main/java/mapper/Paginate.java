package mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import controller.ParamsControllers;
import dto.ComputerDTO;
import model.Computer;
import services.ComputerService;
import services.EnumDisplayedPage;

@Component
public class Paginate {

	private int step;
	private int stepDefaultValue = 10;
	private String columnName;
	private String search;
	private int pageIterator;
	private int pageIteratorDefaultValue = 1;
	private double lastPage;
	private List<ComputerDTO> computerDTOSearchedList = new ArrayList<>();
	private List<ComputerDTO> computerDTOList = new ArrayList<>();
	private ComputerService computerService;
	
	public Paginate(ComputerService computerService) {

		this.computerService = computerService;
	}

	public void paginate(ParamsControllers paramsControllers, ModelAndView modelAndView) {

		setParameters(paramsControllers);
		setComputerDTOList();
		System.out.println("affichage computerDTOList dans paginate : " + computerDTOList);
		setComputerDTOSearchedList(); 
		setLastPage();
		setView(modelAndView);
	}

	public void setParameters(ParamsControllers paramsControllers) {

		setColumnName(paramsControllers);
		setSearch(paramsControllers);
		setPageIterator(paramsControllers);
		setStep(paramsControllers);
	}

	private int getOffset() {
		int avoidBlankPage = 1;
		int offset = ((pageIterator - avoidBlankPage) * step);
		
		return offset;
	}
	
	private void setColumnName(ParamsControllers paramsControllers) {

		if ((paramsControllers.getColumnName() != null) && !(paramsControllers.getColumnName().isEmpty())) {
			this.columnName = paramsControllers.getColumnName();
		} else {
			this.columnName = "";
		}
	}

	private void setSearch(ParamsControllers paramsControllers) {

		if ((paramsControllers.getSearch() != null) && !(paramsControllers.getSearch().isEmpty())) {
			this.search = paramsControllers.getSearch();
		} else {
			this.search = null;
		}
	}

	private void setPageIterator(ParamsControllers paramsControllers) {

		if (paramsControllers.getPageIterator() != null) {
			this.pageIterator = Integer.parseInt(paramsControllers.getPageIterator());
		} else {
			this.pageIterator = pageIteratorDefaultValue;
		}
	}

	private void setStep(ParamsControllers paramsControllers) {

		if (paramsControllers.getStep() != null) {
			this.step = Integer.parseInt(paramsControllers.getStep());
		} else {
			this.step = stepDefaultValue;
		}
	}

	private List<Computer> displayedPag() {

		List<Computer> listComputerPag;
		
		switch (EnumDisplayedPage.displayedPage(columnName)) {
		case COMPUTER_NAME:
			
			listComputerPag = computerService.orderByName(getOffset(), step);
			
			return listComputerPag;
			
		case INTRODUCED:
			
			listComputerPag = computerService.orderByIntroduced(getOffset(), step);

			return listComputerPag;
			
		case DISCONTINUED:
			
			listComputerPag = computerService.orderByDiscontinued(getOffset(), step);
			
			return listComputerPag;
			
		case COMPANY:
			
			listComputerPag = computerService.orderByCompany(getOffset(), step);
			
			return listComputerPag;
			
		default:

			listComputerPag = computerService.listPage(getOffset(), step);
			
			return listComputerPag;
		}
	}
	
	public void setComputerDTOSearchedList() {

		computerDTOSearchedList.clear();
		List<Computer> computerSearchedList = computerService.getComputerByName(search, getOffset(), step);

		computerSearchedList.stream()
				.forEach(computer -> computerDTOSearchedList.add(ComputerMapper.computerToComputerDTO(computer)));
	}
	
	public void setComputerDTOList() {
		
		computerDTOList.clear();
		List<Computer> computerList = displayedPag();

		computerList.stream()
				.forEach(computer -> computerDTOList.add(ComputerMapper.computerToComputerDTO(computer)));
	}
	
	public int getNbSearchedComputer(String search) {

		int nbSearchedComputer = computerService.nbSearchedComputer(search);

		return nbSearchedComputer;
	}
	
	public void setLastPage() {

		List<Computer> computerList = computerService.list();

			if (search != null) {
				lastPage = Math.ceil(getNbSearchedComputer(search) / step);
			} else {

				lastPage = (int) Math.ceil(computerList.size() / step);
			}
	}
	
	public void setView(ModelAndView modelAndView) {

		modelAndView.addObject("search", search);
		modelAndView.addObject("orderBy", columnName);
		modelAndView.addObject("nbSearchedComputer", getNbSearchedComputer(search));
		modelAndView.addObject("pageIterator", pageIterator);
		modelAndView.addObject("step", step);
		modelAndView.addObject("computerDTOSearchedList", computerDTOSearchedList);
		modelAndView.addObject("computerDTOListPag", computerDTOList);
		modelAndView.addObject("lastPage", lastPage);
	}
}