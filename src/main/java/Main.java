import java.sql.SQLException;
import java.util.List;

import dao.ComputerDAO;
import model.Computer;
import ui.MenuAction;

public class Main {

	public static void main(String[] args) throws SQLException {

// List<Computer> computers = ComputerDAO.getInstance().list();
// for(Computer c : computers) {
//	 System.out.println(c);

	 List<Computer> computers = ComputerDAO.getInstance().listPage(0, 20);
	 for(Computer c : computers) {
		 System.out.println(c);
	}
	}
}
