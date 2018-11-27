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
 * Servlet implementation class UserChangeAddressServlet
 */
@WebServlet("/user_changeaddress")
public class UserChangeAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService uService = new UserService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		User loginUser = (User)request.getSession().getAttribute("user");
		try {
			BeanUtils.copyProperties(loginUser, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		uService.updateUserAddress(loginUser);
		request.setAttribute("msg", "信息更新成功!");
		request.getRequestDispatcher("/user_center.jsp").forward(request, response);
		
//		String name = (String) request.getParameter("name");
//		String phone = (String) request.getParameter("phone");
//		String address = (String) request.getParameter("address");
//		User loginUser = (User)request.getSession().getAttribute("user");
//		loginUser.setName(name);
//		loginUser.setPhone(phone);
//		loginUser.setAddress(address);
//		uService.updateUserAddress(loginUser);
//		request.setAttribute("msg", "信息更新成功!");
//		request.getRequestDispatcher("/user_center.jsp").forward(request, response);
	}
}
