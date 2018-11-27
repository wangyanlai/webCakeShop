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
 * Servlet implementation class AdminUserEditPasswordServlet
 */
@WebServlet("/admin/admin_edit")
public class AdminUserEditPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService uService = new UserService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loginUser = (User) request.getSession().getAttribute("user");
		String password = (String) request.getParameter("password");
		String newpassword = (String) request.getParameter("newpassword");
		if(password.equals(loginUser.getPassword())) {
			loginUser.setPassword(newpassword);
			uService.updateUserPassword(loginUser);
			request.setAttribute("msg", "密码修改成功!");
			request.getSession().removeAttribute("user");
			request.getRequestDispatcher("/admin/admin_login.jsp").forward(request, response);
		}else {
			request.setAttribute("failMsg", "密码修改失败，原密码不正确，请再想想!");
			request.getRequestDispatcher("/admin/admin_edit.jsp").forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
