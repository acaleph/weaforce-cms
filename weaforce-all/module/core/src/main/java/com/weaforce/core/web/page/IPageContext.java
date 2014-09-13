package com.weaforce.core.web.page;

/**
 * 分页上下文环境。用于计算Page。
 * 
 */
public interface IPageContext<T> {

	/**
	 * Get the Page instance.
	 * 
	 * @param currentPage
	 *            Current page number
	 * @return
	 */
	public Page<T> getPage();

}
