package ui;

import java.sql.SQLException;

import java.util.Scanner;

import dao.CompanyDAO;
import dao.ComputerDAO;
import model.Company;
import model.Computer;
import utils.DateUtils;

public class MenuAction {
	
	final ComputerDAO computerDAO;
	final CompanyDAO companyDAO;

	public Scanner sc = new Scanner(System.in);

	private static volatile MenuAction instance = null;

	public final static MenuAction getInstance() {
		if (MenuAction.instance == null) {
			synchronized (MenuAction.class) {
				if (MenuAction.instance == null) {
					MenuAction.instance = new MenuAction(ComputerDAO.getInstance(), CompanyDAO.getInstance());
				}
			}
		}
		return MenuAction.instance;
	}
	
	public MenuAction(ComputerDAO computerDAO, CompanyDAO companyDAO) {
		this.computerDAO = computerDAO;
		this.companyDAO = companyDAO;
	}

	public void createComputer() throws SQLException {
		Computer computer = new Computer();
		System.out.println("Saisir le nom de l'ordinateur :");
		computer.setName(sc.nextLine());
		System.out.println("Saisir la date de parution du modèle (yyyy-MM-dd):");
		computer.setIntroduced(DateUtils.convertToLDT(sc.nextLine()));
		System.out.println("Saisir la date de fin de production : (yyyy-MM-dd)");
		computer.setDiscontinued(DateUtils.convertToLDT(sc.nextLine()));
		System.out.println("Saisir l'id de la marque :");
		Company company = companyDAO.find(sc.nextInt());
		if (company == null) {
			System.out.println("La compagnie n'existe pas, rentrez le nom de la nouvelle compagnie :");
			company = new Company.CompanyBuilder().name(sc.nextLine()).build();
		}
		company = companyDAO.add(company);
		computer.setCompany(company);
		computerDAO.add(computer);
	}

	public void updateComputer() {
		System.out.println("Saisir l'id de l'ordinateur à modifier :\n");
		Computer computer = ComputerDAO.getInstance().find(sc.nextInt());
		System.out.println("Saisir le nouvel id :\n");
		computer = new Computer.ComputerBuilder().initializeWithId(sc.nextLong()).build();
		System.out.println("Saisir le nouveau nom :\n");
		computer.setName(sc.next());
		System.out.println("Saisir la nouvelle date de parution du modele :\n");
		computer.setIntroduced(DateUtils.convertToLDT(sc.next()));
		System.out.println("Saisir la nouvelle date de fin de production :\n");
		computer.setDiscontinued(DateUtils.convertToLDT(sc.next()));
		System.out.println("Saisir le nouvel id de la marque :\n");
		Company company = new Company.CompanyBuilder().id(sc.nextLong()).build();
		computer.setCompany(company);
		computerDAO.update(computer);
	}

	public void displayComputer() {
		System.out.println("Rentrez l'id de l'ordinateur à afficher :\n");
		System.out.println(computerDAO.find(sc.nextInt()));
	}

	public void deleteComputer() {
		System.out.println("Rentrez l'id de l'ordinateur à supprimer :\n");
		Computer computer = computerDAO.find(sc.nextInt());
		computerDAO.deleteComputer(computer);
	}
	
	
	@Override
	public void finalize() {
		sc.close();
	}

}
