package cn.com.model;

import java.util.List;

//分页数据模型
public class Page {
	private int pageNo;  //当前页数
	private int pageSize;    //每页数目
	private int totalCount;  //总数目
	private int pageCount;   //总页数
	private List<Object> list;
	
	public void setPageSizeAndTotalCount(int pageSize,int totalCount) {
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		pageCount = (int)Math.ceil((double)totalCount/pageSize);
	}
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	
}
