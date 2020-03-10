package dao;

public enum OrderByRequest {
	ORDER_BY_NAME("SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id "
			+"ORDER BY computer.name LIMIT ?, ?;"),
	ORDER_BY_INTRODUCED("SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id "
			+"ORDER BY introduced LIMIT ?, ?;"),
	ORDER_BY_DISCONTINUED("SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id "
			+"ORDER BY discontinued LIMIT ?, ?;"),
	ORDER_BY_COMPANY("SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id "
			+"ORDER BY company.name LIMIT ?, ?;");

	private final String query;

	OrderByRequest(String query) {
		this.query = query;
	}

	public String getQuery() {
		return this.query;
	}
}
