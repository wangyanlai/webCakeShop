package cn.com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.model.Goods;
import cn.com.model.Page;
import cn.com.model.Type;
import cn.com.service.GoodsService;
import cn.com.service.TypeService;

/**
 * Servlet implementation class GoodsListServlet
 */
@WebServlet("/goods_list")
public class GoodsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService gs = new GoodsService();
	TypeService ts = new TypeService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int typeId = 0;
		if(request.getParameter("id")!=null) {
			typeId = Integer.parseInt(request.getParameter("id"));
		}
		int pageNo = 1;
		if(request.getParameter("pageNo")!=null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		Type t = null;
		if(typeId != 0) {
			t = ts.select(typeId);
		}
		Page p = gs.getGoodsPage(typeId, pageNo);
		request.setAttribute("p", p);
		request.setAttribute("id", typeId);
		request.setAttribute("t", t);
		request.getRequestDispatcher("/goods_list.jsp").forward(request, response);
	}
}
