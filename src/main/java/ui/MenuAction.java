package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import dao.CompanyDAO;
import dao.ComputerDAO;
import model.Company;
import model.Computer;

public class MenuAction {

	public Scanner sc = new Scanner(System.in);
	private static final int PAGE_SIZE = 20;

	private static volatile MenuAction instance = null;

	public final static MenuAction getInstance() {
		if (MenuAction.instance == null) {
			synchronized (MenuAction.class) {
				if (MenuAction.instance == null) {
					MenuAction.instance = new MenuAction();
				}
			}
		}
		return MenuAction.instance;
	}

	private static LocalDateTime ConvertToLDT(String date) {
		if (date.isEmpty()) {
			return null;
		}
		date = date + " 00:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime datetime = LocalDateTime.parse(date, formatter);
		return datetime;
	}

	public void createComputer() throws SQLException {
		Computer computer = new Computer();
		System.out.println("Saisir le nom de l'ordinateur :");
		computer.setName(sc.nextLine());
		System.out.println("Saisir la date de parution du modèle (yyyy-MM-dd):");
		computer.setIntroduced(ConvertToLDT(sc.nextLine()));
		System.out.println("Saisir la date de fin de production : (yyyy-MM-dd)");
		computer.setDiscontinued(ConvertToLDT(sc.nextLine()));
		System.out.println("Saisir l'id de la marque :");
		Company company = CompanyDAO.getInstance().find(sc.nextInt());
//		if (company == null) {
//			System.out.println("Erreur la compagnie n'existe pas");
//			throw new ServiceException"Erreur la compagnie n'existe pas");
//			System.out.println("La compagnie n'existe pas, rentrez le nom de la nouvelle compagnie :");
//			company = new Company.CompanyBuilder().name(sc.next()).build();
//		}
//		company = CompanyDAO.getInstance().add(company);
		computer.setCompany(company);
		ComputerDAO.getInstance().add(computer);
	}

	public void updateComputer() {
		System.out.println("Saisir l'id de l'ordinateur à modifier :\n");
		Computer computer = ComputerDAO.getInstance().find(sc.nextInt());
		System.out.println("Saisir le nouvel id :\n");
		computer = new Computer.ComputerBuilder().initializeWithId(sc.nextLong()).build();
		System.out.println("Saisir le nouveau nom :\n");
		computer.setName(sc.next());
		System.out.println("Saisir la nouvelle date de parution du modele :\n");
		computer.setIntroduced(ConvertToLDT(sc.next()));
		System.out.println("Saisir la nouvelle date de fin de production :\n");
		computer.setDiscontinued(ConvertToLDT(sc.next()));
		System.out.println("Saisir le nouvel id de la marque :\n");
		Company company = new Company.CompanyBuilder().id(sc.nextLong()).build();
		computer.setCompany(company);
		ComputerDAO.getInstance().update(computer);
	}

	public void displayComputer() {
		System.out.println("Rentrez l'id de l'ordinateur à afficher :\n");
		System.out.println(ComputerDAO.getInstance().find(sc.nextInt()));
	}

	public void deleteComputer() {
		System.out.println("Rentrez l'id de l'ordinateur à supprimer :\n");
		Computer computer = ComputerDAO.getInstance().find(sc.nextInt());
		ComputerDAO.getInstance().deleteComputer(computer);
	}
	
	public void computerPaginate() throws SQLException, IOException {
		System.out.println("passe dans le menu action");
		int nbRows = ComputerDAO.getInstance().getNbRows();
		int currentRow = 0;
		
		do {
			List<Computer> listPage = ComputerDAO.getInstance().listPage(currentRow, PAGE_SIZE);
			for (Computer c: listPage) {
				System.out.println(c);
			}
			System.out.println("[Entrée] pour changer de page");
			System.in.read();
			currentRow += 20;
		}while (currentRow < nbRows);
	}
	
	@Override
	public void finalize() {
		sc.close();
	}

}
