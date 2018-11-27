package cn.com.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.model.Page;
import cn.com.service.GoodsService;

/**
 * Servlet implementation class GoodsSearchServlet
 */
@WebServlet("/goods_search")
public class GoodsSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService gService= new GoodsService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = (String) request.getParameter("keyword");
		int pageNo = 1;
		if(request.getParameter("pageNo")!=null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		Page p = gService.getSerachGoods(keyword, pageNo);
		System.out.println(keyword);
		request.setAttribute("p", p);	
		request.setAttribute("keyword", URLEncoder.encode(keyword, "utf-8"));
		request.getRequestDispatcher("/goods_search.jsp").forward(request, response);
	}

}
