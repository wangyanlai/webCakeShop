package cn.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.service.OrderService;

/**
 * Servlet implementation class AdminOrderDelect
 */
@WebServlet("/admin/order_delete")
public class AdminOrderDelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService oService = new OrderService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		oService.adminDeleteOrder(id);
		request.getRequestDispatcher("/admin/order_list").forward(request, response);
	}
}
