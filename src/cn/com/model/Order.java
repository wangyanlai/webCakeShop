package cn.com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.utils.PriceUtil;

public class Order {
	private int id;
	private float total;  //商品总价
	private int amount;   //商品总数 
	private int status;   //订单状态 1、未付款 2、已付款 3、已发货 4、已完成
	private int paytype;  //支付方式 1、微信 2、支付宝 3、货到付款
	private String name;
	private String phone;
	private String address;
	private Date datetime; //下单时间
	private User user;
	private Map<Integer,Orderitem> itemMap = new HashMap<Integer,Orderitem>();
	private List<Orderitem> itemList = new ArrayList<Orderitem>();
	public void setUserName(String username) {
		user = new User();
		user.setUsername(username);
	}
	public void addGoods(Goods g) {
		Orderitem item = null;
		if(itemMap.containsKey(g.getId())) {
			item = itemMap.get(g.getId());
			item.setAmount((item.getAmount()+1));
		}else {
			item = new Orderitem(g.getPrice(),1,g,this);
			itemMap.put(g.getId(), item);
		}
		amount++;
		total = PriceUtil.add(total,item.getPrice());
	}
	public void lessenGoods(int goodsid) {
		if(itemMap.containsKey(goodsid)) {
			Orderitem item = itemMap.get(goodsid);
			item.setAmount(item.getAmount()-1);
			amount--;
			total = PriceUtil.subtract(total, item.getPrice());
			if(item.getAmount()<=0) {
				itemMap.remove(goodsid);
			}
		}
	}
	public void delectGoods(int goodsid) {
		if(itemMap.containsKey(goodsid)) {
			Orderitem item = itemMap.get(goodsid);
			amount = amount-item.getAmount();
			total = PriceUtil.subtract(total, (item.getPrice())*(item.getAmount()));
			itemMap.remove(goodsid);			
		}
	}
	public Order() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPaytype() {
		return paytype;
	}
	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Map<Integer, Orderitem> getItemMap() {
		return itemMap;
	}
	public void setItemMap(Map<Integer, Orderitem> itemMap) {
		this.itemMap = itemMap;
	}
	public List<Orderitem> getItemList() {
		return itemList;
	}
	public void setItemList(List<Orderitem> itemList) {
		this.itemList = itemList;
	}
}
