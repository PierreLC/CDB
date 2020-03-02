package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.DAOException;
import model.Computer;
import services.ComputerService;

@WebServlet(urlPatterns = "/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int pageIterator = 1;
	private int nbRows = 0;
	private int step = 10;
	ComputerService computerService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			nbRows = ComputerService.getInstance().getNbRows();
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
					step = Integer.parseInt(s);
				} catch (NumberFormatException e) {
					this.getServletContext().getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(request,
							response);
				}
			}

		} catch (Exception e) {
			// TODO not all exceptions
		}

		List<Computer> computerList = ComputerService.getInstance().list();

		List<Computer> computerListPag = ComputerService.getInstance().listPage((pageIterator - 1) * step, step);

		int lastPage = (int) Math.ceil((double) computerList.size() / step);

//		request.setAttribute("addSuccess", request.getParameter("addSuccess"));
		request.setAttribute("nbRows", nbRows);
		request.setAttribute("pageIterator", pageIterator);
		request.setAttribute("step", step);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("computerList", computerList);
		request.setAttribute("computerListPag", computerListPag);
		request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
