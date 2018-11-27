package cn.com.model;

import java.util.List;

//��ҳ����ģ��
public class Page {
	private int pageNo;  //��ǰҳ��
	private int pageSize;    //ÿҳ��Ŀ
	private int totalCount;  //����Ŀ
	private int pageCount;   //��ҳ��
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
