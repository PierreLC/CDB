package dao;

public enum OrderByRequest {
	ORDER_BY_NAME("SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id "
			+"ORDER BY computer.name LIMIT :offset, :step;"),
	ORDER_BY_INTRODUCED("SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id "
			+"ORDER BY introduced LIMIT :offset, :step;"),
	ORDER_BY_DISCONTINUED("SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id "
			+"ORDER BY discontinued LIMIT :offset, :step;"),
	ORDER_BY_COMPANY("SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id "
			+"ORDER BY company.name LIMIT :offset, :step;");

	private final String query;

	OrderByRequest(String query) {
		this.query = query;
	}

	public String getQuery() {
		return this.query;
	}
}
