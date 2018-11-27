package cn.com.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.model.Order;
import cn.com.model.Orderitem;
import cn.com.utils.DBUtil;

public class OrderDao {
	//插入订单表
	public void insertOrder(Connection con,Order o) throws SQLException {
		QueryRunner r = new QueryRunner();
		String sql = "insert into `order`(total,amount,status,paytype,name,phone,address,datetime,user_id) values(?,?,?,?,?,?,?,?,?)";
		r.update(con, sql, o.getTotal(),o.getAmount(),o.getStatus(),o.getPaytype(),
				o.getName(),o.getPhone(),o.getAddress(),o.getDatetime(),o.getUser().getId());
	}
	//得到最后一条插入ID
	public int getLastInsertId(Connection con) throws SQLException {
		QueryRunner r = new QueryRunner();
		String sql = "select last_insert_id()";
		BigInteger big = r.query(con, sql, new ScalarHandler<BigInteger>());
		return Integer.parseInt(big.toString());
	}
	//插入表单项
	public void insertOrderitem(Connection con,Orderitem item) throws SQLException {
		QueryRunner r = new QueryRunner();
		String sql = "insert into orderitem(price,amount,goods_id,order_id) values(?,?,?,?)";
		r.update(con, sql, item.getPrice(),item.getAmount(),item.getGoods().getId(),item.getOrder().getId());
	}
	//查询某用户的全部订单
	public List<Order> selectOrderListByUserId(int userid) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from `order` where user_id = ? order by datetime desc";
		return r.query(sql, new BeanListHandler<Order>(Order.class),userid);
	}
	//查询某订单下的所有订单项
	public List<Orderitem> selectOrderitemByOrderId(Order order) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select i.id,i.price,i.amount,g.name from orderitem i,goods g where order_id = ? and i.goods_id = g.id";
		return r.query(sql, new BeanListHandler<Orderitem>(Orderitem.class),order.getId());
	}
	             //管理员查询订单数及订单表
	public int adminSelectOrderCount(int status) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		if(status == 0) {
			String sql = "select count(*) from `order`";
			return r.query(sql, new ScalarHandler<Long>()).intValue();
		}else {
			String sql = "select count(*) from `order` where status = ?";
			return r.query(sql, new ScalarHandler<Long>(),status).intValue();
		}
	}
	public List<Order> adminSelectOrderList(int status,int pageNo,int pageSize) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		if(status == 0) {
			String sql = "select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id = u.id order by datetime desc limit ?,?";
			return r.query(sql, new BeanListHandler<Order>(Order.class),(pageNo-1)*pageSize,pageSize);
		}else {
			String sql = "select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id = u.id and status = ? order by datetime desc limit ?,?";
			return r.query(sql, new BeanListHandler<Order>(Order.class),status,(pageNo-1)*pageSize,pageSize);
		}
	}
	public List<Orderitem> adminSelectOrderitemList(Order order) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select o.id,o.price,o.amount,g.name goodsname from orderitem o,goods g where o.order_id = ? and o.goods_id = g.id";
		return r.query(sql, new BeanListHandler<Orderitem>(Orderitem.class),order.getId());
	}
	  //修改订单状态
	public void updateStatus(int id,int status) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "update `order` set status = ? where id = ?";
		r.update(sql,status,id);
	}
	//删除订单项及订单
	public void adminDeleteOrder(Connection con,int id) throws SQLException {
		QueryRunner r = new QueryRunner();
		String sql = "delete from `order` where id = ? ";
		r.update(con,sql,id);
	}
	public void adminDeleteOrderitem(Connection con,int id) throws SQLException {
		QueryRunner r = new QueryRunner();
		String sql = "delete from orderitem where order_id = ? ";
		r.update(con,sql,id);
	}
}
