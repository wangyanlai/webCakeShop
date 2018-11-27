package cn.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.model.Goods;
import cn.com.service.GoodsService;
@WebServlet("/goods_detail")
public class GoodsDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService gs = new GoodsService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Goods g = gs.getGoodsDetail(id);
		request.setAttribute("g", g);
		request.getRequestDispatcher("/goods_detail.jsp").forward(request, response);
	}
}
