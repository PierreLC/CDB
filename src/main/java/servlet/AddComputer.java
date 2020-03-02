package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.Computer;
import services.CompanyService;
import services.ComputerService;

@WebServlet(urlPatterns = "/addComputer")
public class AddComputer {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			List<Company> companyList = CompanyService.getInstance().list();
			request.setAttribute("companyList", companyList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/views/addComputer.jsp").forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String computerName = request.getParameter("computerName");
		LocalDateTime introduced = LocalDateTime.parse(request.getParameter("introduced"));
		LocalDateTime discontinued = LocalDateTime.parse(request.getParameter("discontinued"));
		int companyId = Integer.parseInt("companyId");
		
		Company company = CompanyService.getInstance().find_by_id(companyId);
		
		Computer computer = new Computer.Builder().initializeName(computerName)
												 .initializeIntroducedDate(introduced)
												 .initializeDiscontinuedDate(discontinued)
												 .initializeCompany(company)
												 .build();
		
		try {
			ComputerService.getInstance().add(computer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}
}
