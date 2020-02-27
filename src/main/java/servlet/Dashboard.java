package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.DAOException;
import services.ComputerService;

@WebServlet(urlPatterns = "/dashboard")
public class Dashboard extends HttpServlet {
	private ComputerService computerService;
	private static final long serialVersionUID = 1L;
	int nbRows = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			nbRows = computerService.getNbRows();
		} catch (SQLException e) {
			DAOException.displayError(e.getMessage());
		}

//		List<Computer> listComputer = ComputerService.getInstance().list();
//		request.setAttribute("listComputer", listComputer);

		response.getWriter().append("Served at: ").append(request.getContextPath());

		request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
//		request.setAttribute("nbRows", nbRows);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
