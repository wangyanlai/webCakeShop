package cn.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.model.Order;

/**
 * Servlet implementation class GoodsDeleteServlet
 */
@WebServlet("/goods_delete")
public class GoodsDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order o = (Order) request.getSession().getAttribute("order");
		int goodsid = Integer.parseInt(request.getParameter("goodsid"));
		o.delectGoods(goodsid);
		response.getWriter().print("ok");
//		request.getRequestDispatcher("/goods_cart.jsp").forward(request, response);
	}

}
