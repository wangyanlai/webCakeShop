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
 * Servlet implementation class UserChangePasswordServlet
 */
@WebServlet("/user_changepassword")
public class UserChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService uService = new UserService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = (String) request.getParameter("password");
		String newpassword = (String) request.getParameter("newpassword"); 
		User loginUser = (User) request.getSession().getAttribute("user");
		if(password.equals(loginUser.getPassword())) {
			loginUser.setPassword(newpassword);
			uService.updateUserPassword(loginUser);
			request.setAttribute("msg", "�����޸ĳɹ�!");
			request.getRequestDispatcher("/user_center.jsp").forward(request, response);
		}else {
			request.setAttribute("failMsg", "�����޸�ʧ�ܣ�ԭ���벻��ȷ����������!");
			request.getRequestDispatcher("/user_center.jsp").forward(request, response);
		}
	}

}
