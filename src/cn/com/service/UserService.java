package cn.com.service;

import java.sql.SQLException;
import java.util.List;

import cn.com.dao.UserDao;
import cn.com.model.Page;
import cn.com.model.User;

public class UserService {
	private UserDao uDao = new UserDao();
	public User selectUserById(int id) {
		User u = null;
		try {
			 u = uDao.selectById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	public boolean register(User user) {
		try {
			if(uDao.isUsernameExist(user.getUsername())) {
				return false;
			}else {
				if(uDao.isEmailExist(user.getEmail())) {
					return false;
				}else {
					uDao.addUser(user);
					return true;
				}
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public User login(String ue,String password) {
		User u = null;
		try {
			u = uDao.selectByUP(ue, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(u != null)  return u;
		try {
			u = uDao.selectByEP(ue, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(u != null)  return u;
		return null;
	}
	
	public void updateUserAddress(User user) {
		try {
			uDao.updateUserAddress(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateUserPassword(User user) {
		try {
			uDao.updateUserPassword(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Page adminSelectUserList(int pageNo) {
		Page p = new Page();
		p.setPageNo(pageNo);
		int pageSize = 7;
		int totalCount = 0;
		try {
			totalCount = uDao.adminSelectUserCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setPageSizeAndTotalCount(pageSize, totalCount);
		List list = null;
		try {
			list = uDao.adminSelectUserList(pageNo,pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setList(list);
		return p;
	}
	public boolean deleteUser(int id) {
		try {
			uDao.deleteUser(id);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
