package ui;

import java.sql.SQLException;


import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import dao.CompanyDAO;
import dao.ComputerDAO;

public class MenuActionTest {	
	
//	@InjectMocks
//	MenuAction actionTest;
//	
//	@Mock
//	ComputerDAO computerDAO;
//	
//	@Mock
//	CompanyDAO companyDAO;
//	
//	@Before
//	public void init() {
//		MockitoAnnotations.initMocks(this);
//	}

//	public void createComputer() throws SQLException {
//		Computer computer = new Computer();
//		System.out.println("Saisir le nom de l'ordinateur :");
//		computer.setName(sc.nextLine());
//		System.out.println("Saisir la date de parution du modèle (yyyy-MM-dd):");
//		computer.setIntroduced(ConvertToLDT(sc.nextLine()));
//		System.out.println("Saisir la date de fin de production : (yyyy-MM-dd)");
//		computer.setDiscontinued(ConvertToLDT(sc.nextLine()));
//		System.out.println("Saisir l'id de la marque :");
//		Company company = companyDAO.find(sc.nextInt());
//		if (company == null) {
//			System.out.println("La compagnie n'existe pas, rentrez le nom de la nouvelle compagnie :");
//			company = new Company.CompanyBuilder().name(sc.nextLine()).build();
//		}
//		company = companyDAO.add(company);
//		computer.setCompany(company);
//		computerDAO.add(computer);
//	}
	
//	@Test
//	public void createComputer_should_linked_to_existing_company_if_exists() throws SQLException {
//		Mockito.when(companyDAO.find(ArgumentMatchers.anyInt())).thenReturn(null);
//		Mockito.doNothing().when(computerDAO).add(ArgumentMatchers.any());
//		actionTest.createComputer();
//		Mockito.verify(computerDAO).add(ArgumentMatchers.any());
//		Mockito.verifyNoMoreInteractions(companyDAO);
//	}
}
