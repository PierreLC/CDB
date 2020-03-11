package ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.CompanyDAO;
import dao.ComputerDAO;

@Component
public class UI {
	private static Scanner scanner = new Scanner(System.in);
	
	private static ComputerDAO computerDAO;
	private static MenuAction menuAction;
	private static CompanyDAO companyDAO;

	@Autowired
	public UI(ComputerDAO computerInstance, MenuAction menuInstance, CompanyDAO companyInstance) {
		this.computerDAO = computerInstance;
		this.menuAction = menuInstance;
		this.companyDAO = companyInstance;
	}

	public static int menu() {

		System.out.println("\n******************************Menu***************************************************");
		System.out.println("\n                       1 - Afficher la liste des ordinateurs                         ");
		System.out.println("\n                       2 - Afficher la liste des ordinateurs avec pagination         ");
		System.out.println("\n                       3 - Supprimer un ordinateur                                   ");
		System.out.println("\n                       4 - Mettre à jour un ordinateur                               ");
		System.out.println("\n                       5 - Ajouter un ordinateur                                     ");
		System.out.println("\n                       6 - Afficher toute les marques d'ordinateur                   ");
		System.out.println("\n                       7 - Afficher les détails d'un ordi à partir de son id         ");
		System.out.println("\n                       8 - Quitter                                                   ");
		System.out.println("\n******************************Fin****************************************************");
		return scannerMenu(1, 8);
	}

	public static int scannerMenu(int entreeBasse, int entreeHaute) {
		String answer;
		int choice = -1;

		do {
			System.out.println("Rentrer votre choix d actions :");
			answer = scanner.nextLine();
			System.out.println(answer);
			choice = Integer.parseInt(answer); 
		} while (choice == -1 || (choice < entreeBasse) || (choice > entreeHaute));

		return choice;
	}

	public static void actions() {
		int choice;

		choice = menu();

		try {
			switch (MenuEntry.entry(choice)) {

			case LISTCOMPUTERS:
				System.out.println(computerDAO.list());
				break;
			case PAGECOMPUTERS:
				Pagination.computerPaginate();
			case DELETECOMPUTER:
				menuAction.deleteComputer();
				break;
			case UPDATECOMPUTER:
				menuAction.updateComputer();
				break;
			case ADDCOMPUTER:
				menuAction.createComputer();
				break;
			case DISPLAYCOMPANIES:
				System.out.println(companyDAO.list());
				break;
			case DISPLAYDETAILS:
				menuAction.displayComputer();
				break;
			default:
				break;
			}
		}	catch (Exception e3) {
			System.err.println("Erreur inattendue" + e3.getMessage());
			System.exit(-1);
		}

	}
}
