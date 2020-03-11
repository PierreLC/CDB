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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import model.Company;
import model.Computer;
import services.CompanyService;
import services.ComputerService;

@WebServlet(urlPatterns="/editComputer")
@Controller
public class UpdateComputer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CompanyService companyService;
	private ComputerService computerService;
	
	@Autowired
	public UpdateComputer(CompanyService companyInstance, ComputerService computerInstance) {
		this.computerService = computerInstance;
		this.companyService = companyInstance;
	}
	
	@GetMapping
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		List<Company> companyList;
		try {
			companyList = companyService.list();
			request.setAttribute("companyList", companyList);
			Computer computer = computerService.find_by_id(Integer.parseInt(request.getParameter("id")));
			
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
	
	@PostMapping
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		String computerName = request.getParameter("computerName");
		LocalDate introduced = LocalDate.parse(request.getParameter("introduced"));
		LocalDate discontinued = LocalDate.parse(request.getParameter("discontinued"));
		int companyId = Integer.parseInt(request.getParameter("companyId"));
		
		Company company = companyService.find_by_id(companyId);
		
		boolean testCompanyId = (request.getParameter("companyId").equals("none"));
		
		if(!testCompanyId) {
			company = companyService.find_by_id(companyId);
		}
		
		Computer computer = new Computer.Builder().setName(computerName)
				 .setIntroducedDate(introduced.atTime(LocalTime.MIDNIGHT))
				 .setDiscontinuedDate(discontinued.atTime(LocalTime.MIDNIGHT))
				 .setCompany(company)
				 .build();
		
		computerService.update(computer);
		
		response.sendRedirect(request.getContextPath()+"/dashboard");
	}
}
