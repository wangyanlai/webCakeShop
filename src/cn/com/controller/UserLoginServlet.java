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
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/user_login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService uService = new UserService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ue = request.getParameter("ue");
		String password = request.getParameter("password");
		User user = uService.login(ue, password);
		if(user == null) {
			request.setAttribute("failMsg","用户名、邮箱或密码不正确，请重新登录!");
			request.getRequestDispatcher("/user_login.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/user_center.jsp").forward(request, response);
		}
	}
}
