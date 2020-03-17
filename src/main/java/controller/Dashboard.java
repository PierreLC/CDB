package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
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

import exceptions.DAOException;
import model.Computer;
import services.ComputerService;

@WebServlet(urlPatterns = "/dashboard")
@Controller
public class Dashboard extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ComputerService computerService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	// GetMapping + param url
	@GetMapping
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageSize = 10;
		int pageIterator = 1;
		int nbRows = 0;

		try {
			nbRows = computerService.getNbRows();
			System.out.println("nbrows vaut dans le servlet " + nbRows);
		} catch (SQLException e) {
			DAOException.displayError(e.getMessage());
		}

		try {

			if (request.getParameter("pageIterator") != null) {
				String s = request.getParameter("pageIterator");
				try {
					pageIterator = Integer.parseInt(s);
				} catch (NumberFormatException e) {
					this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request,
							response);
				}
			}

			if (request.getParameter("step") != null) {
				String s = request.getParameter("step");
				try {
					pageSize = Integer.parseInt(s);
				} catch (NumberFormatException e) {
					this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request,
							response);
				}
			}

		} catch (Exception e) {
			System.exit(0);
		}

		String orderBy = (request.getParameter("columnName") != null) ? request.getParameter("columnName") : "";

		List<Computer> computerListPag;
		List<Computer> computerSearchedList;

		switch (orderBy) {
		case "computerName":
			computerListPag = computerService.orderByName((pageIterator - 1) * pageSize, pageSize);
			break;
		case "introduced":
			computerListPag = computerService.orderByIntroduced((pageIterator - 1) * pageSize, pageSize);
			break;
		case "discontinued":
			computerListPag = computerService.orderByDiscontinued((pageIterator - 1) * pageSize, pageSize);
			break;
		case "company":
			computerListPag = computerService.orderByCompany((pageIterator - 1) * pageSize, pageSize);
			break;
		default:
			computerListPag = computerService.listPage((pageIterator - 1) * pageSize, pageSize);
		}
		List<Computer> computerList = computerService.list();

		String search = request.getParameter("search");

		int nbSearchedComputer = computerService.nbSearchedComputer(search);

		computerSearchedList = computerService.findByName(search, (pageIterator - 1) * pageSize, pageSize);

		if (search != null) {
			int lastPage = (int) Math.ceil((double) nbSearchedComputer / pageSize);
			request.setAttribute("lastPage", lastPage);
		} else {
			int lastPage = (int) Math.ceil((double) computerList.size() / pageSize);
			request.setAttribute("lastPage", lastPage);
		}

		request.setAttribute("orderBy", orderBy);
		request.setAttribute("search", search);
		request.setAttribute("nbSearchedComputer", nbSearchedComputer);
		request.setAttribute("computerSearchedList", computerSearchedList);
		request.setAttribute("nbRows", nbRows);
		request.setAttribute("pageIterator", pageIterator);
		request.setAttribute("step", pageSize);
		request.setAttribute("computerList", computerList);
		request.setAttribute("computerListPag", computerListPag);
		request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	@PostMapping
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String computerSelection = request.getParameter("selection");
		List<String> computerToDelete = Arrays.asList(computerSelection.split(","));
		for (String s : computerToDelete) {
			computerService.delete(Integer.parseInt(s));
		}
		doGet(request, response);
	}
}
