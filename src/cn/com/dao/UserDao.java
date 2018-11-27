package cn.com.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.model.User;
import cn.com.utils.DBUtil;

public class UserDao {
	public void addUser(User user) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "insert into user(username,email,password,name,phone,address,isadmin,isvalidate) values(?,?,?,?,?,?,?,?)";
		r.update(sql, user.getUsername(),user.getEmail(),user.getPassword(),user.getName(),user.getPhone(),user.getAddress(),user.isIsadmin(),user.isIsvalidate());
	}
	//注册验证
	public boolean isUsernameExist(String username) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from user where username = ?";
		User u =  r.query(sql, new BeanHandler<User>(User.class),username);
		if(u == null) {
			return false;
		}else {
			return true;
		}
	}
	public boolean isEmailExist(String email) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from user where email = ?";
		User u =  r.query(sql, new BeanHandler<User>(User.class),email);
		if(u == null) {
			return false;
		}else {
			return true;
		}
	}
	//登录验证
	public User selectByUP(String username,String password) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from user where username = ? and password = ?";
		return r.query(sql, new BeanHandler<User>(User.class),username,password);
	}
	public User selectByEP(String email,String password) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from user where email = ? and password = ?";
		return r.query(sql, new BeanHandler<User>(User.class),email,password);
	}
	public User selectById(int id) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from user where id = ?";
		return r.query(sql, new BeanHandler<User>(User.class),id);
	}
	//更新地址信息
	public void updateUserAddress(User user) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "update user set name = ?, phone = ?,address = ? where id = ? ";
		r.update(sql, user.getName(),user.getPhone(),user.getAddress(),user.getId());
	}
	public void updateUserPassword(User user) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "update user set password = ? where id = ? ";
		r.update(sql, user.getPassword(),user.getId());
	}
	//查询用户列表
	public int adminSelectUserCount() throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select count(*) from user";
		return r.query(sql, new ScalarHandler<Long>()).intValue();
	}
	public List<User> adminSelectUserList(int pageNo,int pageSize) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from user limit ?,?";
		return r.query(sql, new BeanListHandler<User>(User.class),(pageNo-1)*pageSize,pageSize);
	}
	    //删除用户
	public void deleteUser(int id) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "delete from user where id = ?";
		r.update(sql,id);
	}
}
