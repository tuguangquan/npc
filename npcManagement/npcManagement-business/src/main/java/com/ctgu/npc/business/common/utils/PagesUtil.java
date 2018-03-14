package com.ctgu.npc.business.common.utils;

import java.util.ArrayList;
import java.util.List;

public class PagesUtil<T> {

	private int curPage = 1;// 当前页码

	private int totalPages;// 总页数

	private int rowCount;// 总记录数

	private int sizePage = 50000;// 页面大小

	private List<T> lists = new ArrayList<T>();
	

	/**
	 * 构造方法
	 * @param curPage 当前页码
	 * @param rowCount 记录条数
	 * @param sizePage 页面大小
	 * @param lists 当前对象列表
	 */
	public PagesUtil(int curPage, int rowCount, int sizePage, List<T> lists) {
		super();
		this.curPage = curPage;
		this.rowCount = rowCount;
		this.sizePage = sizePage;
		this.lists = lists;
	}

	public PagesUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<T> getLists() {
		return lists;
	}

	public void setLists(List<T> lists) {
		this.lists = lists;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		if (rowCount > 0) {
			int pages = rowCount / sizePage;
			if (rowCount % sizePage != 0) {
				pages += 1;
			}
			setTotalPages(pages);
		} else {
			setTotalPages(1);
		}
		this.rowCount = rowCount;
	}

	public int getSizePage() {
		return sizePage;
	}

	public void setSizePage(int sizePage) {
		this.sizePage = sizePage;
	}

}
