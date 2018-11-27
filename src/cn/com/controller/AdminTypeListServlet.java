package cn.com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.model.Page;
import cn.com.model.Type;
import cn.com.service.TypeService;

/**
 * Servlet implementation class AdminTypeListServlet
 */
@WebServlet("/admin/type_list")
public class AdminTypeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TypeService tService = new TypeService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<Type> list = tService.getAll();
//		request.setAttribute("typelist", list);
//		request.getRequestDispatcher("/admin/type_list.jsp").forward(request, response);
		int pageNo = 1;
		if(request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		Page p = tService.selectTypeAll(pageNo);
		request.setAttribute("p", p);
		request.getRequestDispatcher("/admin/type_list.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}
