package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ComputerDAO;
import exceptions.DAOException;
import model.Computer;

@WebServlet(urlPatterns = "/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int nbRows = 0;
		try {
			nbRows = ComputerDAO.getInstance().getNbRows();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();;
		}

		request.setAttribute("nbRows", nbRows);

		List<Computer> listComputer = ComputerDAO.getInstance().list();
		request.setAttribute("listComputer", listComputer);

		response.getWriter().append("Served at: ").append(request.getContextPath());

		request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
