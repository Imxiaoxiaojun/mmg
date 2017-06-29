package com.mmg.common;

import java.util.List;

/**
 * Created by yj on 2017/6/16.
 */
public class Page<T> {
	private Integer curPage = 1;
	private Integer pageSize = 10;
	private List<T> pageList;
	private Integer startRow;
	private Integer count;


	public Page(Integer count, Integer curPage, Integer pageSize) {
		if (curPage != null)
			this.curPage = curPage;
		if (pageSize != null)
			this.pageSize = pageSize;
		this.count = count;
	}

	public int getCurPage() {
		return this.curPage;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public Integer getStartRow() {
		return (this.curPage - 1) * this.pageSize;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<T> getPageList() {
		return this.pageList;
	}
}
