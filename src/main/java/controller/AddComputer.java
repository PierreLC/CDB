package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import dao.ComputerDAO;
import model.Company;
import model.Computer;
import services.CompanyService;

@WebServlet(urlPatterns = "/addComputer")
@Controller
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerDAO computerDAO;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
    	SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
	}

//Possibilité d'utiliser cette méthode une fois spring mvc implémenté avec l'@RestController
//	public AddComputer(CompanyService serviceInstance, ComputerDAO daoInstance) {
//		this.companyService = serviceInstance;
//		this.computerDAO = daoInstance;
//
//	}

	@GetMapping
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			List<Company> companyList = companyService.list();
			request.setAttribute("companyList", companyList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/views/addComputer.jsp").forward(request,response);
	}
	
	@PostMapping
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String computerName = request.getParameter("computerName");
		LocalDate introduced = LocalDate.parse(request.getParameter("introduced"));
		LocalDate discontinued = LocalDate.parse(request.getParameter("discontinued"));
		int companyId = Integer.parseInt(request.getParameter("companyId"));
		
		Company company = companyService.find_by_id(companyId);
		
		Computer computer = new Computer.Builder().setName(computerName)
												 .setIntroducedDate(introduced.atTime(LocalTime.MIDNIGHT))
												 .setDiscontinuedDate(discontinued.atTime(LocalTime.MIDNIGHT))
												 .setCompany(company)
												 .build();
		
		try {
			computerDAO.add(computer);
			response.sendRedirect(request.getContextPath()+"/dashboard");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
