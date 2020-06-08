package mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import controller.ParamsControllers;
import dto.ComputerDTO;
import model.Computer;
import services.ComputerService;
import services.EnumDisplayedPage;

public class Paginate {

	private int step;
	private String columnName; 
	private String search;
	private int nbComputerSearched;
	private int pageIterator;
	private List<Computer> computerList = new ArrayList();
	private List<ComputerDTO> computerDTOList = new ArrayList();
	private ComputerService computerService;
	private ComputerMapper computerMapper;
	
	public Paginate(ComputerService computerService, ComputerMapper computerMapper) {
		
		this.computerService = computerService;
		this.computerMapper = computerMapper;
	}
	
	public void paginate(ParamsControllers paramsControllers, ModelAndView modelAndView) {
		
		
	}
	
	public void setParameters(ParamsControllers paramsControllers) {
		
	}
	
	private void setColumnName(ParamsControllers paramsControllers) {
		
		if((paramsControllers != null) && !(paramsControllers.getColumnName().isEmpty())) {
			this.columnName = paramsControllers.getColumnName();
		} else {
			this.columnName = null;
		}
	}
	
	private void setSearch(ParamsControllers paramsControllers) {
		
		if((paramsControllers != null) && !(paramsControllers.getSearch().isEmpty())) {
			this.search = paramsControllers.getSearch();
		} else {
			this.search = null;
		}
	}
	
	private void setPageIterator(ParamsControllers paramsControllers) {
		
		if(paramsControllers.getPageIterator() != null) {
			this.pageIterator = Integer.parseInt(paramsControllers.getPageIterator());
		} else {
			
		}
	}
	
	private void setStep(ParamsControllers paramsControllers) {
		
		if(paramsControllers.getStep() != null) {
			this.step = Integer.parseInt(paramsControllers.getStep());
		}
	}
	
	private List<Computer> setLastPage(ParamsControllers paramsControllers) {
		
	List<Computer> listComputerPag;
		
		int offsetLimit = 1;
		int offset = (Integer.parseInt(paramsControllers.getPageIterator()) - offsetLimit) * Integer.parseInt(paramsControllers.getStep());

		switch (EnumDisplayedPage.displayedPage(columnName)) {
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
}