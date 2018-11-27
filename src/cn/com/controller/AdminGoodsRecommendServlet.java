package cn.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.service.GoodsService;

/**
 * Servlet implementation class AdminGoodsRecommendServlet
 */
@WebServlet("/admin/goods_recommend")
public class AdminGoodsRecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService gService = new GoodsService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String method = request.getParameter("method");
		int typeTarget = Integer.parseInt(request.getParameter("typeTarget"));
		if(method.equals("remove")) {
			gService.deleteRecommend(id, typeTarget);
		}else {
			gService.addRecommend(id, typeTarget);
		}
		request.getRequestDispatcher("/admin/goods_list").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
