package cn.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.model.Page;
import cn.com.service.GoodsService;

/**
 * Servlet implementation class RecommendGoodsListServlet
 */
@WebServlet("/goods_recommend")
public class GoodsRecommendListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService gs = new GoodsService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int recommendType = Integer.parseInt(request.getParameter("type"));
		int pageNo = 1;
		if(request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		Page p = gs.getRecommendList(recommendType, pageNo);
		request.setAttribute("p", p);
		request.setAttribute("type", recommendType);
		request.getRequestDispatcher("/goods_recommend.jsp").forward(request, response);
	}
}
