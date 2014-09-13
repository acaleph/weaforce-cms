package com.weaforce.core.web.page;

import java.util.List;

public class QuickPageContext<T> implements IPageContext<T> {

	private Page<T> page = new Page<T>();

	/**
	 * Initialize the page parameters from DAO operated by page util
	 * 
	 * @param list
	 *            Data list
	 * @param totalElements
	 *            The total amount of elements.
	 * @param pageSize
	 *            Page size
	 */
	public QuickPageContext(Page<T> page,List<T> content, int totalElements, int pageSize) {
		int div = 0;
		// Get the number of elements currently on this page
		if (pageSize == 0)
			pageSize = page.getPageSize();
		if (content != null) {
			page.setContent(content);
			// The number of elements currently on this page
			page.setNumberOfElements(content.size());

			div = totalElements / pageSize;
			page.setTotalPages((totalElements % pageSize == 0) ? div : div + 1);
		}
		page.setTotalElements(totalElements);
		this.page = page;

	}

	public Page<T> getPage() {
		page.setPageContext(this);
		return this.page;
	}

}
