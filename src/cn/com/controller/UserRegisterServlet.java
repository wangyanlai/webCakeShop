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

@WebServlet("/user_register")
public class UserRegisterServlet extends HttpServlet {
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
			request.setAttribute("msg", "ע��ɹ������¼!");
			request.getRequestDispatcher("/user_login.jsp").forward(request, response);
		}else {			
			request.setAttribute("msg", "�û����������ظ�����������д!");
			request.getRequestDispatcher("/user_register.jsp").forward(request, response);
		}
	}

}
