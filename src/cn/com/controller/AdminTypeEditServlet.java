package cn.com.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.com.model.Type;
import cn.com.service.TypeService;
import cn.com.utils.DBUtil;

/**
 * Servlet implementation class AdminTypeEditServlet
 */
@WebServlet("/admin/type_edit")
public class AdminTypeEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TypeService tService = new TypeService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Type t = new Type();
		try {
			BeanUtils.copyProperties(t, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tService.adminUpdateType(t);
		request.getRequestDispatcher("/admin/type_list").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
