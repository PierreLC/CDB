package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ComputerDAO;
import model.Company;
import model.Computer;
import services.CompanyService;

@WebServlet(urlPatterns = "/addComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		LocalDate introduced = LocalDate.parse(request.getParameter("introduced"));
		LocalDate discontinued = LocalDate.parse(request.getParameter("discontinued"));
		int companyId = Integer.parseInt(request.getParameter("companyId"));
		
		Company company = CompanyService.getInstance().find_by_id(companyId);
		
		Computer computer = new Computer.Builder().setName(computerName)
												 .setIntroducedDate(introduced.atTime(LocalTime.MIDNIGHT))
												 .setDiscontinuedDate(discontinued.atTime(LocalTime.MIDNIGHT))
												 .setCompany(company)
												 .build();
		
		try {
			ComputerDAO.getInstance().add(computer);
//			ComputerService.getInstance().add(computer);
			response.sendRedirect(request.getContextPath()+"/dashboard");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
