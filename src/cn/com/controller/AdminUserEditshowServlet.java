package cn.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.model.User;
import cn.com.service.UserService;

/**
 * Servlet implementation class AdminUserEditshowServlet
 */
@WebServlet("/admin/user_editshow")
public class AdminUserEditshowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService uService = new UserService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User u = uService.selectUserById(id);
		request.setAttribute("u", u);
		request.getRequestDispatcher("/admin/user_edit.jsp").forward(request, response);
	}
}
