package com.weaforce.core.util;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象. 包含当前页数据及分页信息如总记录数.
 */
public class PageInfo<T> implements Serializable {
	private static final long serialVersionUID = 6518122942384814159L;
	private int curPage;
	private int totalCount;
	private int pageSize;
	private List<T> data;
	private static int ROWS_PER_PAGE = 15;

	public PageInfo() {
		curPage = 1;
		totalCount = 0;
		pageSize = ROWS_PER_PAGE;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	/*
	 * 取得总记录数
	 */

	public int getTotalCount() {
		return totalCount;
	}

	/*
	 * 设置总记录数
	 */

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/*
	 * 取得总页数.
	 */
	public int getTotalPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int rows) {
		if (rows > 0) {
			pageSize = rows;
		} else {
			pageSize = ROWS_PER_PAGE;
		}
	}

	public int getDefaultRowsPerPage() {
		return ROWS_PER_PAGE;
	}

	/*
	 * 取得当前页中的结果集
	 */

	public Object getResult() {
		return data;
	}

	/*
	 * 设置当前页中的结果集
	 */

	public void setResult(List<T> data) {
		this.data = data;
	}

	/**
	 * 获取任一页第一条数据在数据集的位置.
	 * 
	 * @param pageNo
	 *            从1开始的页号
	 * @param pageSize
	 *            每页记录条数
	 * @return 该页第一条数据
	 */
	public int getStartOfPage() {
		return (curPage - 1) * pageSize;
	}

	public static void main(String[] args) {
	}

}
