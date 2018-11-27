package cn.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.service.UserService;

/**
 * Servlet implementation class AdminUserDeleteServlet
 */
@WebServlet("/admin/user_delete")
public class AdminUserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService uService = new UserService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		boolean isSuccess = uService.deleteUser(id);
		if(isSuccess) {
			request.setAttribute("msg", "用户删除成功！");
		}else {
			request.setAttribute("failMsg", "用户有下过的订单，请删除订单后再来删除用户！");
		}
		request.getRequestDispatcher("/admin/user_list").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
