package ui;

import java.util.Scanner;

import dao.CompanyDAO;
import dao.ComputerDAO;

public class UI {
	private static Scanner scanner = new Scanner(System.in);

	public UI() {
		actions();
	}

	public static int menu() {

		System.out.println("\n******************************Menu***************************************************");
		System.out.println("\n                       1 - Afficher la liste des ordinateur                          ");
		System.out.println("\n                       2 - Supprimer un ordinateur                                   ");
		System.out.println("\n                       3 - Mettre à jour un ordinateur                               ");
		System.out.println("\n                       4 - Ajouter un ordinateur                                     ");
		System.out.println("\n                       5 - Afficher toute les marques d'ordinateur                   ");
		System.out.println("\n                       6 - Afficher les détails d'un ordi à partir de son id         ");
		System.out.println("\n                       7 - Quitter                                                   ");
		System.out.println("\n******************************Fin****************************************************");
		return scannerMenu(1, 7);
	}

	public static int scannerMenu(int entreeBasse, int entreeHaute) {
		String answer;
		int choix = -1;

		do {
			System.out.println("Rentrer votre choix d actions :");
			try {
				answer = scanner.nextLine();
				choix = Integer.parseInt(answer);
			} catch (Exception e) {
				System.err.println("Erreur happened :" + e.getMessage());
				scanner.close();
				System.exit(-1);
			}
		} while (choix == -1 || (choix < entreeBasse) || (choix > entreeHaute));

		return choix;
	}

	public static void actions() {
		int choice;

		choice = menu();

		try {
			switch (MenuEntry.entry(choice)) {

			case DISPLAYCOMPUTERS:
				System.out.println(ComputerDAO.getInstance().lister());
				break;
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
