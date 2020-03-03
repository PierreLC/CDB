package servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.Computer;
import services.ComputerService;

@WebServlet(urlPatterns="/editComputer")
public class UpdateComputer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static ComputerService service = ComputerService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		Computer computer = ComputerService.getInstance().find_by_id(Integer.parseInt(request.getParameter("computerID")));
		String newName = computer;
		LocalDateTime introduced = computer.getIntroduced();
		LocalDateTime discontinued = computer.getDiscontinued();
		Company company = computer.getCompany();
	}
}
