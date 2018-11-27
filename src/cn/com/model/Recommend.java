package cn.com.model;

public class Recommend {
	private int id;
	private String type; //1.条幅 2.热销 3.新品
	private Goods goods;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Recommend() {
		super();
	}
	public Recommend(int id, String type, Goods goods) {
		super();
		this.id = id;
		this.type = type;
		this.goods = goods;
	}
}
