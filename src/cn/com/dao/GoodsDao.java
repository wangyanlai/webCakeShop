package cn.com.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.com.model.Goods;
import cn.com.model.Recommend;
import cn.com.utils.DBUtil;

public class GoodsDao {
	//热销、新品、                                         推荐类型
	public  List<Map<String, Object>> getGoodsList(int recommendTpye) throws SQLException {
		QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
		String sql = "select g.id,g.name,g.cover,g.price,t.name typename from recommend r,goods g,type t where type = ? and goods_id = g.id and g.type_id = t.id limit 6";
		return qr.query(sql, new MapListHandler(),recommendTpye);
	}
	//条幅商品	
	public Map<String, Object> getScrollGoods() throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select g.id,g.name,g.cover,g.price from recommend r,goods g where type = 1 and r.goods_id = g.id ";
		return r.query(sql, new MapHandler());
	}
	                                  //XX系列
	public List<Goods> selectAll(int type_id,int pageNo,int pageSize) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		if(type_id == 0) {
			String sql="select * from goods limit ?,?";
			return r.query(sql, new BeanListHandler<Goods>(Goods.class),(pageNo-1)*pageSize,pageSize);
		}else {
			String sql="select * from goods where type_id = ? limit ?,?";
			return r.query(sql, new BeanListHandler<Goods>(Goods.class),type_id,(pageNo-1)*pageSize,pageSize);
		}
	}
	                            //通过系列得总数目
	public int getTotalCount(int typeId) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "";
		if(typeId == 0) {
			sql = "select count(*) from goods";
			return r.query(sql, new ScalarHandler<Long>()).intValue();
		}else{
			sql = "select count(*) from goods where type_id = ?";
			return r.query(sql, new ScalarHandler<Long>(),typeId).intValue();
		}
	}
	                           //得到分页推荐商品
	public List<Goods> selectRecommendAll(int reCommendType,int pageNo,int pageSize) throws SQLException{
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		if(reCommendType == 0) {
			String sql = "select g.id,g.name,g.cover,g.image1,g.image2,g.price,g.intro,g.stock,t.name typename from goods g,type t where g.type_id = t.id order by id limit ?, ?";
			return r.query(sql, new BeanListHandler<Goods>(Goods.class),(pageNo-1)*pageSize,pageSize);
		}else {
			String sql = "select g.id,g.name,g.cover,g.image1,g.image2,g.price,g.intro,g.stock,t.name typename from goods g,recommend r,type t where r.type = ? and r.goods_id = g.id and g.type_id = t.id order by id limit ?, ?";
			return r.query(sql, new BeanListHandler<Goods>(Goods.class),reCommendType,(pageNo-1)*pageSize,pageSize);	
		}		 
	}
	public int getRecommendTC(int reCommendType) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		if(reCommendType == 0) {
			String sql = "select count(*) from goods";
			return r.query(sql, new ScalarHandler<Long>()).intValue();
		}else {
			String sql = "select count(*) from recommend where type = ?";
			return r.query(sql, new ScalarHandler<Long>(),reCommendType).intValue();
		}
	}                        //得到商品详情
	public Goods getGoodsDetail(int id) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select g.id,g.name,g.cover,g.image1,g.image2,g.price,g.intro,g.stock,t.id typeid,t.name typename from goods g,type t where g.id = ? and g.type_id = t.id";
		return r.query(sql, new BeanHandler<Goods>(Goods.class),id);	
	}                         //模糊查询商品
	public List<Goods> getSearchGoods(String keyword,int pageNo,int pageSize) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from goods where name like ? limit ?,?";
		return r.query(sql, new BeanListHandler<Goods>(Goods.class),"%"+keyword+"%",(pageNo-1)*pageSize,pageSize);
	}
	public int getSearchCount(String keyword) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select count(*) from goods where name like ?";
		return r.query(sql, new ScalarHandler<Long>(),"%"+keyword+"%").intValue();
	}	
	       //查询是否为推荐商品
	private boolean isReCommendGoods(Goods goods,int type) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from recommend where type = ? and goods_id = ?";
		Recommend rc = r.query(sql, new BeanHandler<Recommend>(Recommend.class),type,goods.getId());
		if(rc == null) return false;
		else return true;
	}
	public boolean isScroll(Goods goods) throws SQLException {
		return isReCommendGoods(goods, 1);
	} 
	public boolean isHot(Goods goods) throws SQLException {
		return isReCommendGoods(goods, 2);
	}
	public boolean isNew(Goods goods) throws SQLException {
		return isReCommendGoods(goods, 3);
	}
	//推荐商品添加或删除
	public void addRecommend(int id,int type) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "insert into recommend(type,goods_id) values(?,?)";
		r.update(sql,type,id);
	}
	public void deleteRecommend(int id,int type) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "delete from recommend where type = ? and goods_id = ?";
		r.update(sql,type,id);
	}
	//插入新的商品
	public void insert(Goods g) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "insert into goods(name,cover,image1,image2,price,intro,stock,type_id) values(?,?,?,?,?,?,?,?)";
		r.update(sql, g.getName(),g.getCover(),g.getImage1(),g.getImage2(),g.getPrice(),g.getIntro(),g.getStock(),g.getType().getId());
	}
	public void update(Goods g) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "update goods set name = ?,cover = ?,image1 = ?,image2 = ?,price = ?,intro = ?,stock = ?,type_id = ? where id= ?";
		r.update(sql, g.getName(),g.getCover(),g.getImage1(),g.getImage2(),g.getPrice(),g.getIntro(),g.getStock(),g.getType().getId(),g.getId());
	}
	public void deleteGoods(int id) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "delete from goods where id = ?";
		r.update(sql,id);
	}
}



