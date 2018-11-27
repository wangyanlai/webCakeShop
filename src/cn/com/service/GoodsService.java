package cn.com.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.dao.GoodsDao;
import cn.com.model.Goods;
import cn.com.model.Page;

public class GoodsService {
	private GoodsDao gDao = new GoodsDao();
	//条幅商品
	public Map<String, Object> getScrollGoods(){
		Map<String, Object> map = null;
		try {
			map = gDao.getScrollGoods();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	//热销商品
	public List<Map<String, Object>> getHotGoodsList() {
		List<Map<String, Object>> list = null;
		try {
			list = gDao.getGoodsList(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//新品商品
	public List<Map<String, Object>> getNewGoodsList() {
		List<Map<String, Object>> list = null;
		try {
			list = gDao.getGoodsList(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
//	public List<Goods> selectAll(int type_id,int pageNumber,int pageSize){
//		List<Goods> list = null;
//		try {
//			list = gDao.selectAll(type_id, pageNumber, pageSize);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;		
//	} //系列商品列表页
	public Page getGoodsPage(int typeId,int pageNo) {
		Page p = new Page();
		p.setPageNo(pageNo);
		int totalCount = 0;
		try {
			totalCount = gDao.getTotalCount(typeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setPageSizeAndTotalCount(8,totalCount);
		List list = null;
		try {
			list = gDao.selectAll(typeId, pageNo, 8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setList(list);
		return p;
	}    //推荐商品列表页
	public Page getRecommendList(int reCommendType,int pageNo) {
		Page p = new Page();
		p.setPageNo(pageNo);
		int pageSize = 8;
		int reCommendTC = 0;
		try {
			reCommendTC = gDao.getRecommendTC(reCommendType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setPageSizeAndTotalCount(pageSize, reCommendTC);
		List list = null;
		try {
			list = gDao.selectRecommendAll(reCommendType, pageNo, pageSize);
			for(Goods g : (List<Goods>)list) {
				g.setScroll(gDao.isScroll(g));
				g.setHot(gDao.isHot(g));
				g.setNew(gDao.isNew(g));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setList(list);
		return p;
	}
	public Goods getGoodsDetail(int id) {
		Goods goods = null;
		try {
			goods = gDao.getGoodsDetail(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goods;
	}//关键字查商品
	public Page getSerachGoods(String keyword,int pageNo) {
		Page p = new Page();
		p.setPageNo(pageNo);
		int searchCount = 0;
		try {
			searchCount = gDao.getSearchCount(keyword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setPageSizeAndTotalCount(8, searchCount);
		List list = null;
		try {
			list = gDao.getSearchGoods(keyword, pageNo, 8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setList(list);
		return p;
	}
	public void addRecommend(int id,int type) {
		try {
			gDao.addRecommend(id, type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteRecommend(int id,int type) {
		try {
			gDao.deleteRecommend(id, type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insert(Goods g) {
		try {
			gDao.insert(g);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(Goods g) {
		try {
			gDao.update(g);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteGoods(int id) {
		try {
			gDao.deleteGoods(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}




