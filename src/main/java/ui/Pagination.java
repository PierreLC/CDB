package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.ComputerDAO;
import model.Computer;

public class Pagination {
	private static final int PAGE_SIZE = 20;
	private static ComputerDAO computerDAO;
	
	public static void computerPaginate() throws SQLException, IOException {
		System.out.println("passe dans le menu action");
		int nbRows = computerDAO.getNbRows();
		int currentRow = 0;
		
		do {
			List<Computer> listPage = computerDAO.listPage(currentRow, PAGE_SIZE);
			for (Computer c: listPage) {
				System.out.println(c);
			}
			System.out.println("[Entrée] pour changer de page");
			System.in.read();
			currentRow += 20;
		}while (currentRow < nbRows);
	}
}
