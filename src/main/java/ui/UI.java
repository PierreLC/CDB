package ui;

import java.util.Scanner;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dao.CompanyDAO;
import dao.ComputerDAO;

public class UI {
	private static Scanner scanner = new Scanner(System.in);

	public UI() {
		actions();
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
			try {
				answer = scanner.nextLine();
				System.out.println(answer);
				choice = Integer.parseInt(answer);
			} catch (ParseException e) {
				System.err.println("Erreur happened :" + e.getMessage());
				scanner.close();
				System.exit(-1);
			}
		} while (choice == -1 || (choice < entreeBasse) || (choice > entreeHaute));

		return choice;
	}

	public static void actions() {
		int choice;

		choice = menu();

		try {
			switch (MenuEntry.entry(choice)) {

			case LISTCOMPUTERS:
				System.out.println(ComputerDAO.getInstance().list());
				break;
			case PAGECOMPUTERS:
				System.out.println("lance pagination depuis menu");
				MenuAction.getInstance().computerPaginate();
			case DELETECOMPUTER:
				MenuAction.getInstance().deleteComputer();
				break;
			case UPDATECOMPUTER:
				MenuAction.getInstance().updateComputer();
				break;
			case ADDCOMPUTER:
				MenuAction.getInstance().createComputer();
				break;
			case DISPLAYCOMPANIES:
				System.out.println(CompanyDAO.getInstance().lister());
				break;
			case DISPLAYDETAILS:
				MenuAction.getInstance().displayComputer();
				break;
			default:
				break;
			}
//		}   catch (ServiceException e1) {
//			System.err.println(e1.getMessage());	
		}	catch (Exception e3) {
			System.err.println("Erreur inattendue" + e3.getMessage());
			System.exit(-1);
		}

	}
}
