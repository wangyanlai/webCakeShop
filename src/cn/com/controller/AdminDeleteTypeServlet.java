package cn.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.service.TypeService;

/**
 * Servlet implementation class AdminDeleteTypeServlet
 */
@WebServlet("/admin/type_delete")
public class AdminDeleteTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TypeService tService = new TypeService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		boolean isSuccess = tService.adminDeleteType(id);
		if(isSuccess) {
			request.setAttribute("msg", "删除成功！");
		}else {
			request.setAttribute("failMsg", "该系列下有商品，不能直接删除!");
		}
		request.getRequestDispatcher("/admin/type_list").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
