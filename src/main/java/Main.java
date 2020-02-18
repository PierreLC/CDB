import java.sql.SQLException;
import java.util.List;

import dao.CompanyDAO;
import dao.ComputerDAO;
import model.Company;
import model.Company.CompanyBuilder;
import model.Computer;
import ui.MenuAction;

public class Main {

	public static void main(String[] args) throws SQLException {

// List<Computer> computers = ComputerDAO.getInstance().lister();
// for(Computer c : computers) {
//	 System.out.println(c);
		
		MenuAction.getInstance().updateComputer();
 }
 
		
}
