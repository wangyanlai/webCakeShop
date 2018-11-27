package cn.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.model.Goods;
import cn.com.model.Order;
import cn.com.service.GoodsService;

/**
 * Servlet implementation class GoodsLessenServlet
 */
@WebServlet("/goods_lessen")
public class GoodsLessenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order o = (Order) request.getSession().getAttribute("order");
		int goodsid = Integer.parseInt(request.getParameter("goodsid"));
		o.lessenGoods(goodsid);
		response.getWriter().print("ok");
	}

}
