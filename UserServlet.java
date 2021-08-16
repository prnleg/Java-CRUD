package net.java.usermanage.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.usermanage.UserDAO;
import net.java.usermanage.model.User;


@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
    
    public UserServlet() { this.userDAO = new UserDAO(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException { this.doGet(request, response); }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String action = request.getServletPath();
		
		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			try {insertUser(request, response);} 
			catch (SQLException e) {e.printStackTrace();}
			break;
		case "/delete":
			try {deleteUser(request, response);} 
			catch (SQLException e) {e.printStackTrace();}
			break;
		case "/edit":
			try {showEditForm(request, response);} 
			catch (SQLException e) {e.printStackTrace();}
			break;
		case "/update":
			try {updateUser(request, response);} 
			catch (SQLException e) {e.printStackTrace();}
			break;
		default:
			try {listUser(request, response);} 
			catch (SQLException e) {e.printStackTrace();}
			break;
		}
	}
	
	
	private void listUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		List<User> listUser = userDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
	
		
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);
	}
	
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String email = request.getParameter("email");
		String work = request.getParameter("work");
		
		User user = new User(id, name, birth, email, work);
		userDAO.updateUser(user);
		response.sendRedirect("list");
	}

	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("list");
	}
	
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String email = request.getParameter("email");
		String work = request.getParameter("work");
		User newUser = new User(name, birth, email, work);
		userDAO.insertUser(newUser);
		response.sendRedirect("list");
	}
}
