package cn.com.service;

import java.sql.SQLException;
import java.util.List;

import cn.com.dao.TypeDao;
import cn.com.model.Page;
import cn.com.model.Type;

public class TypeService {
	private TypeDao td = new TypeDao();
	public List<Type> getAll() {
		List<Type> list  = null;
		try {
			list = td.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public Page selectTypeAll(int pageNo) {
		Page p = new Page();
		p.setPageNo(pageNo);
		int pageSize = 8;
		int typeCount = 0;
		try {
			typeCount = td.adminSelectTypeAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setPageSizeAndTotalCount(pageSize, typeCount);
		List list = null;
		try {
			list = td.adminGetTypeAll(pageNo, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setList(list);
		return p;
	}
	public Type select(int id) {
		Type t = null;
		try {
			t = td.select(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	public void insertType(Type type) {
		try {
			td.insertType(type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void adminUpdateType(Type t) {
		try {
			td.adminUpdateType(t);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean adminDeleteType(int id) {
		try {
			td.adminDeleteType(id);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
