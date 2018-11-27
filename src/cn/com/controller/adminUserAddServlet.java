package cn.com.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.com.model.User;
import cn.com.service.UserService;

/**
 * Servlet implementation class adminUserAddServlet
 */
@WebServlet("/admin/user_add")
public class adminUserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService uService = new UserService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = new User();
		try {
			BeanUtils.copyProperties(u, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(uService.register(u)) {
			request.setAttribute("msg", "用户注册成功");
			request.getRequestDispatcher("/admin/user_list").forward(request, response);
		}else {			
			request.setAttribute("failMsg", "用户名或邮箱重复，请重新填写!");
			request.setAttribute("u", u);
			request.getRequestDispatcher("/admin/user_add.jsp").forward(request, response);
		}
	}

}
