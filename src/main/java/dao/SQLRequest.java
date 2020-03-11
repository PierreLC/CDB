package dao;

public enum SQLRequest {
	ADD("INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?);"),
	LIST_COMPUTER("SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id;"),
	DELETE("DELETE FROM computer WHERE id=?;"),
	UPDATE("UPDATE computer SET  name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE Id = ?;"),
	LIST_PAGE("SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id LIMIT ?, ?;"),
	FIND_BY_ID("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id  WHERE computer.id=?;"),
	FIND_BY_NAME_PAG("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id,"
			+"company.name FROM computer LEFT JOIN company ON company_id = company.id  WHERE computer.name LIKE ? LIMIT ?, ?;"),
	FIND_BY_NAME("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id  WHERE computer.name LIKE ?;"),
	NB_ROWS("SELECT COUNT(*) as \"Rows\" FROM computer;"),
	
	ADD_COMPANY("INSERT INTO company(name) VALUES (?);"),
	LIST_COMPANY("SELECT id, name FROM company;"),
	GET_COMPANY_BY_ID("SELECT id, name FROM company WHERE id = ?;"),
	DELETE_COMPUTER_BY_COMPANY_ID("DELETE computer.id, computer.name, computer.introduced, computer.discontinued "
								+"computer_id, company.name FROM computer LEFT JOIN company ON company_id = company.id "
								+"WHERE company.name = ?;"),
	DELETE_COMPANY("DELETE id, name FROM company WHERE id = ?;");

	private final String query;

	SQLRequest(String query) {
		this.query = query;
	}

	public String getQuery() {
		return this.query;
	}
}
