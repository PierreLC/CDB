package com.excilys.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.controller.ParamsControllers;
import com.excilys.dto.ComputerDTO;
import com.excilys.model.Computer;
import com.excilys.services.ComputerService;
import com.excilys.services.EnumDisplayedPage;

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

	public void paginate(ParamsControllers paramsControllers, ModelAndView modelAndView) throws SQLException {

		setParameters(paramsControllers);
		setDisplayedList();
		setLastPage();
		setNbComputer(search, modelAndView);
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
		int offset = pageIterator - avoidBlankPage;
		
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

		Page<Computer> listComputerPag;
		List<Computer> listDisplayedComputer;
		
		switch (EnumDisplayedPage.displayedPage(columnName)) {
		case COMPUTER_NAME:
			
			listComputerPag = computerService.orderByName(getOffset(), step);
			listDisplayedComputer = listComputerPag.getContent();
			
			return listDisplayedComputer;
			
		case INTRODUCED:
			
			listComputerPag = computerService.orderByIntroduced(getOffset(), step);
			listDisplayedComputer = listComputerPag.getContent();

			return listDisplayedComputer;
			
		case DISCONTINUED:
			
			listComputerPag = computerService.orderByDiscontinued(getOffset(), step);
			listDisplayedComputer = listComputerPag.getContent();
			
			return listDisplayedComputer;
			
		case COMPANY:
			
			listComputerPag = computerService.orderByCompany(getOffset(), step);
			listDisplayedComputer = listComputerPag.getContent();
			
			return listDisplayedComputer;
			
		default:
			
			listComputerPag = computerService.listPage(getOffset(), step);
			listDisplayedComputer = listComputerPag.getContent();
			
			return listDisplayedComputer;
		}
	}
	
	public void setComputerDTOSearchedList() {

		computerDTOSearchedList.clear();
		Page<Computer> computerSearchedPage = computerService.getComputerByName(search, getOffset(), step);
		List<Computer> computerSearchedList = computerSearchedPage.getContent();
		
		computerSearchedList.stream()
				.forEach(computer -> computerDTOSearchedList.add(ComputerMapper.computerToComputerDTO(computer)));
	}
	
	public void setComputerDTOList() {
		
		computerDTOList.clear();
		List<Computer> computerList = displayedPag();

		computerList.stream()
				.forEach(computer -> computerDTOList.add(ComputerMapper.computerToComputerDTO(computer)));
	}
	
	public long getNbSearchedComputer(String search) {

		long nbSearchedComputer = computerService.nbSearchedComputer(search);

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
	
	public void setDisplayedList() {
		
		if(search != null) {
			setComputerDTOSearchedList();
		} else {
			setComputerDTOList();
		}
	}
	
	public void setNbComputer(String search, ModelAndView modelAndView) throws SQLException {
	
		if(search != null) {
			long nbSearchedComputer = computerService.nbSearchedComputer(search);
			
			modelAndView.addObject("nbSearchedComputer", nbSearchedComputer);
		} else {
			long nbRows = computerService.getNbRows();
			
			modelAndView.addObject("nbRows", nbRows);
		}
	}
	
	public void setView(ModelAndView modelAndView) {

		modelAndView.addObject("search", search);
		modelAndView.addObject("orderBy", columnName);
		modelAndView.addObject("pageIterator", pageIterator);
		modelAndView.addObject("step", step);
		modelAndView.addObject("computerDTOSearchedList", computerDTOSearchedList);
		modelAndView.addObject("computerDTOListPag", computerDTOList);
		modelAndView.addObject("lastPage", lastPage);
	}
}