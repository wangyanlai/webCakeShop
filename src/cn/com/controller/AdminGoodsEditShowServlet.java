package cn.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.model.Goods;
import cn.com.service.GoodsService;

/**
 * Servlet implementation class dminGoodsEditShowServlet
 */
@WebServlet("/admin/goods_editshow")
public class AdminGoodsEditShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService gService = new GoodsService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Goods goods = gService.getGoodsDetail(id);
		request.setAttribute("g", goods);
		request.getRequestDispatcher("/admin/goods_edit.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
