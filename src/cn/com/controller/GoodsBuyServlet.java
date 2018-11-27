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
 * Servlet implementation class GoodsBuyServlet
 */
@WebServlet("/goods_buy")
public class GoodsBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService gService = new GoodsService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order o = null;
		if(request.getSession().getAttribute("order") != null) {
			o = (Order) request.getSession().getAttribute("order");
		}else {
			o = new Order();
			request.getSession().setAttribute("order", o);
		}
		int id = Integer.parseInt(request.getParameter("goodsid"));
		Goods g = gService.getGoodsDetail(id);
		if(g.getStock()>0) {
			o.addGoods(g);	
			response.getWriter().print("ok");
		}else {
			response.getWriter().print("fail");
		}

	}

}
