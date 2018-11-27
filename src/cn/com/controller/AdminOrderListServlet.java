package cn.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.model.Page;
import cn.com.service.OrderService;

/**
 * Servlet implementation class AdminGoodsListServlet
 */
@WebServlet("/admin/order_list")
public class AdminOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService oService= new OrderService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNo = 1;
		if(request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		int status = 0;
		if(request.getParameter("status") != null) {
			status = Integer.parseInt(request.getParameter("status"));
		}
		request.setAttribute("status", status);
		Page p = oService.adminSelectOrderList(status, pageNo);
		request.setAttribute("p", p);
		request.getRequestDispatcher("/admin/admin_orderlist.jsp").forward(request, response);
	}
}
