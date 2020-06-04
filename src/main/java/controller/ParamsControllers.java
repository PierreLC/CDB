package controller;

public class ParamsControllers {
	private String search;
	private String orderBy;
	private String columnName;
	private int pageIterator;
	private int step;
	private String computerSelection;
	private String computerName;
	private String introduced;
	private String discontinued;
	private String companyId;
	private String id;

	public ParamsControllers(String search, String orderBy, String columnName, int pageIterator, int step, String computerSelection,
			               String computerName, String introduced, String discontinued, String companyId, String id) {
		
		this.search = search;
		this.orderBy = orderBy;
		this.columnName = columnName;
		this.pageIterator = pageIterator;
		this.step = step;
		this.computerSelection = computerSelection;
		this.computerName = computerName;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
		this.id = id;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getPageIterator() {
		return pageIterator;
	}

	public void setPageIterator(int pageIterator) {
		this.pageIterator = pageIterator;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	
	public String getComputerSelection() {
		return computerSelection;
	}

	public void setComputerSelection(String computerSelection) {
		this.computerSelection = computerSelection;
	}
	
	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}