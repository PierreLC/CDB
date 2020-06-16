package com.excilys.ui;
//
//import java.sql.SQLException;
//import java.util.Optional;
//import java.util.Scanner;
//
//import org.springframework.stereotype.Component;
//
//import dao.CompanyDAO;
//import dao.ComputerDAO;
//import mapper.DateMapper;
//import model.Company;
//import model.Computer;
//
//@Component
//public class MenuAction {
//
//	final ComputerDAO computerDAO;
//	final CompanyDAO companyDAO;
//
//	public Scanner sc = new Scanner(System.in);
//
//	public MenuAction(ComputerDAO computerDAO, CompanyDAO companyDAO) {
//		this.computerDAO = computerDAO;
//		this.companyDAO = companyDAO;
//	}
//
//	public void createComputer() throws SQLException {
//		Computer computer = new Computer();
//		System.out.println("Saisir le nom de l'ordinateur :");
//		computer.setName(sc.nextLine());
//		System.out.println("Saisir la date de parution du modèle (yyyy-MM-dd):");
//		computer.setIntroduced(DateMapper.convertToLDT(sc.nextLine()));
//		System.out.println("Saisir la date de fin de production : (yyyy-MM-dd)");
//		computer.setDiscontinued(DateMapper.convertToLDT(sc.nextLine()));
//		System.out.println("Saisir l'id de la marque :");
//		Company company = companyDAO.getCompanyById(sc.nextInt());
//			System.out.println("La compagnie n'existe pas, rentrez le nom de la nouvelle compagnie :");
//		// Check that the method still work, it will not set the company into the
//		// computer right now
//		companyDAO.add(company);
//		computer.setCompany(company);
//		computerDAO.add(computer);
//	}
//
//	public void updateComputer() {
//		System.out.println("Saisir l'id de l'ordinateur à modifier :\n");
//		Computer computer = computerDAO.getComputerById(sc.nextInt());
//			System.out.println("Saisir le nouvel id :\n");
//			computer = new Computer.Builder().setId(sc.nextLong()).build();
//			System.out.println("Saisir le nouveau nom :\n");
//			computer.setName(sc.next());
//			System.out.println("Saisir la nouvelle date de parution du modele :\n");
//			computer.setIntroduced(DateMapper.convertToLDT(sc.next()));
//			System.out.println("Saisir la nouvelle date de fin de production :\n");
//			computer.setDiscontinued(DateMapper.convertToLDT(sc.next()));
//			System.out.println("Saisir le nouvel id de la marque :\n");
//			Company company;
//			company = new Company.Builder().setId(sc.nextLong()).build();
//			computer.setCompany(company);
//			computerDAO.update(computer);
//	}
//
//	public void displayComputer() {
//		System.out.println("Rentrez l'id de l'ordinateur à afficher :\n");
//		System.out.println(computerDAO.getComputerById(sc.nextInt()));
//	}
//
//	public void deleteComputer() {
//		System.out.println("Rentrez l'id de l'ordinateur à supprimer :\n");
//		computerDAO.deleteComputer(sc.nextInt());
//	}
//
//	@Override
//	public void finalize() {
//		sc.close();
//	}
//
//}
