package cn.com.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.com.dao.OrderDao;
import cn.com.model.Order;
import cn.com.model.Orderitem;
import cn.com.model.Page;
import cn.com.utils.DBUtil;

public class OrderService {
	OrderDao oDao = new OrderDao();
	public void insertOrder(Order order) {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			oDao.insertOrder(con, order);
			int id = oDao.getLastInsertId(con);
			order.setId(id);
			for(Orderitem item : order.getItemMap().values()){
				oDao.insertOrderitem(con, item);
			}			
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(con!=null)
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public List<Order> slectOrderListByUserId(int userid) {
		List<Order> list = null;
		try {
			list =  oDao.selectOrderListByUserId(userid);
			for(Order o : list) {
				List<Orderitem> item = oDao.selectOrderitemByOrderId(o);
				o.setItemList(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public Page adminSelectOrderList(int status,int pageNo) {
		Page p = new Page();
		p.setPageNo(pageNo);
		int pageSize = 10;
		int orderCount = 0;
		try {
			orderCount = oDao.adminSelectOrderCount(status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setPageSizeAndTotalCount(pageSize,orderCount );
		List list = null;
		try {
			list = oDao.adminSelectOrderList(status, pageNo, pageSize);
			for(Order or : (List<Order>)list) {
				List<Orderitem> itemlist = oDao.adminSelectOrderitemList(or);
				or.setItemList(itemlist);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setList(list);
		return p;
	}
	public void updateStatus(int id,int status) {
		try {
			oDao.updateStatus(id, status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void adminDeleteOrder(int id) {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			oDao.adminDeleteOrderitem(con, id);
			oDao.adminDeleteOrder(con, id);
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(con!=null)
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
