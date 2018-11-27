package cn.com.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.model.Type;
import cn.com.utils.DBUtil;

public class TypeDao {
	//全系列
	public List<Type> getAll() throws SQLException {	              
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from type";
		return r.query(sql, new BeanListHandler<Type>(Type.class));
	}
	                  //某一系列
	public Type select(int id) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from type where id = ?";
		return r.query(sql, new BeanHandler<Type>(Type.class),id);
	}
	public void insertType(Type type) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "insert into type(name) values(?)";
		r.update(sql,type.getName());
	}
	//管理员获得系列列表
	public List<Type> adminGetTypeAll(int pageNo,int pageSize) throws SQLException {	              
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from type limit ?,?";
		return r.query(sql, new BeanListHandler<Type>(Type.class),(pageNo-1)*pageSize,pageSize);
	}
	public int adminSelectTypeAll() throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select count(*) from type";
		return r.query(sql, new ScalarHandler<Long>()).intValue();
	}
	public void adminUpdateType(Type t) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "update type set name = ? where id = ?";
		r.update(sql,t.getName(),t.getId());
	}
	public void adminDeleteType(int id) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "delete from type where id = ?";
		r.update(sql,id);
	}
}
