package dao;

public enum SQLRequest {
	ADD("INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (:name, :introduced, :discontinued, :company.id);"),
	LIST_COMPUTER("SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id;"),
	DELETE("DELETE FROM computer WHERE id= :id;"),
	UPDATE("UPDATE computer SET  name = :name, introduced = :introduced, discontinued = :discontinued, company_id = :company.id WHERE id = :computer.id;"),
	LIST_PAGE("SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id LIMIT :offset, :pageSize;"),
	FIND_BY_ID("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id  WHERE id= :computer.id;"),
	FIND_BY_NAME_PAG("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id,"
			+"company.name FROM computer LEFT JOIN company ON company_id = company.id  WHERE computer.name LIKE :search LIMIT :offset, :step;"),
	FIND_BY_NAME("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
			+"FROM computer LEFT JOIN company ON company_id = company.id  WHERE computer.name LIKE :search;"),
	NB_ROWS("SELECT COUNT(*) AS RECORDS FROM computer;"),
	
	ADD_COMPANY("INSERT INTO company(name) VALUES (:company.name);"),
	LIST_COMPANY("SELECT id, name FROM company;"),
	GET_COMPANY_BY_ID("SELECT id, name FROM company WHERE id = :company.id;"),
	DELETE_COMPUTER_BY_COMPANY_ID("DELETE computer.id, computer.name, computer.introduced, computer.discontinued "
								+"computer_id, company.name FROM computer LEFT JOIN company ON company_id = company.id "
								+"WHERE name = company.name;"),
	DELETE_COMPANY("DELETE id, name FROM company WHERE id = :company.id;");

	private String query;

	SQLRequest(String query) {
		this.query = query;
	}

	public String getQuery() {
		return this.query;
	}
}
