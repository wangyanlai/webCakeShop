package cn.com.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.com.model.Order;
import cn.com.model.User;
import cn.com.service.OrderService;

/**
 * Servlet implementation class OrderConfirmServlet
 */
@WebServlet("/order_confirm")
public class OrderConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService oService = new OrderService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order o = (Order) request.getSession().getAttribute("order");
		try {
			BeanUtils.copyProperties(o, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		o.setDatetime(new Date());
		o.setStatus(2);
		o.setUser((User) request.getSession().getAttribute("user"));
		oService.insertOrder(o);
		request.getSession().removeAttribute("order");
		request.setAttribute("msg", "订单支付成功!");
		request.getRequestDispatcher("/order_success.jsp").forward(request, response);
	}

}
