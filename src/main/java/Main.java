import java.sql.SQLException;

import dao.CompanyDAO;
import ui.MenuAction;

public class Main {

	public static void main(String[] args) throws SQLException {
		//MenuAction.getInstance().createComputer();
		System.out.println(CompanyDAO.getInstance().find(2));
}
}