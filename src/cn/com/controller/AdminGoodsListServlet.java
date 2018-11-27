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
 * Servlet implementation class AdminGoodsListServlet
 */
@WebServlet("/admin/goods_list")
public class AdminGoodsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService gService = new GoodsService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNo = 1;
		if(request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		int reCommendType = 0;
		if(request.getParameter("type") != null) {
			reCommendType = Integer.parseInt(request.getParameter("type"));
		}
		Page p = gService.getRecommendList(reCommendType, pageNo);
		request.setAttribute("type", reCommendType);
		request.setAttribute("p", p);
		request.getRequestDispatcher("/admin/goods_list.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
