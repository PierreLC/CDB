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

import model.Company;
import model.Computer;
import services.CompanyService;
import services.ComputerService;

@WebServlet(urlPatterns="/editComputer")
public class UpdateComputer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static ComputerService service = ComputerService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		List<Company> companyList;
		try {
			companyList = CompanyService.getInstance().list();
			request.setAttribute("companyList", companyList);
			Computer computer = service.find_by_id(Integer.parseInt(request.getParameter("id")));
			
			request.setAttribute("computerId", computer.getId());
			request.setAttribute("computerName", computer.getName());
			request.setAttribute("introduced", computer.getIntroduced());
			request.setAttribute("discontinued", computer.getDiscontinued());
			request.setAttribute("company", computer.getCompany());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request,response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		String computerName = request.getParameter("computerName");
		LocalDate introduced = LocalDate.parse(request.getParameter("introduced"));
		LocalDate discontinued = LocalDate.parse(request.getParameter("discontinued"));
		int companyId = Integer.parseInt(request.getParameter("companyId"));
		
		Company company = CompanyService.getInstance().find_by_id(companyId);
		
		boolean testCompanyId = (request.getParameter("companyId").equals("none"));
		
		if(!testCompanyId) {
			company = CompanyService.getInstance().find_by_id(companyId);
		}
		
		Computer computer = new Computer.Builder().setName(computerName)
				 .setIntroducedDate(introduced.atTime(LocalTime.MIDNIGHT))
				 .setDiscontinuedDate(discontinued.atTime(LocalTime.MIDNIGHT))
				 .setCompany(company)
				 .build();
		
		service.update(computer);
		
		response.sendRedirect(request.getContextPath()+"/dashboard");
	}
}
