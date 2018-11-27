package cn.com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.model.Order;
import cn.com.model.User;
import cn.com.service.OrderService;

/**
 * Servlet implementation class OrderListServlet
 */
@WebServlet("/order_list")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService oService = new OrderService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User) request.getSession().getAttribute("user");
		List<Order> list = oService.slectOrderListByUserId(u.getId());
		request.setAttribute("orderlist", list);
		request.getRequestDispatcher("/order_list.jsp").forward(request, response);
	}
}
