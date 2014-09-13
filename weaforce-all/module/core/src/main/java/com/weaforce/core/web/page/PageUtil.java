package com.weaforce.core.web.page;

import java.util.List;

import org.springframework.util.StringUtils;

public class PageUtil {

	// private static String PAGE =
	// "<li><a href=\"?page=${NUMBER}\">${NUMBER}</a></li>";
	private static final String PAGE_ACTIVATED = "<li class=\"active\"><a href=\"#\">${NUMBER}</a></li>";
	private static final String PAGE_DISABLED_PREFIX = "<li class=\"disabled\"><a href=\"#\">&lt;&lt;</a></li><li class=\"disabled\"><a href=\"#\">&lt;</a></li>";
	private static final String PAGE_DISABLED_SUFFIX = "<li class=\"disabled\"><a href=\"#\">&gt;</a></li><li class=\"disabled\"><a href=\"#\">&gt;&gt;</a></li>";
	private static final int PAGINATION_SIZE = 7;

	/**
	 * Get the data page
	 * 
	 * @param totalElements
	 *            Total amount
	 * @param currentPage
	 *            Current page
	 * @param pageSize
	 *            The number of elements in every page
	 * @param elements
	 *            Data list currently on this page
	 * @return
	 */
	public static <T> Page<T> getPage(Page<T> page, int totalElements,
			int currentPage, int pageSize, List<T> elements) {
		IPageContext<T> pageContext = new QuickPageContext<T>(page, elements,
				totalElements, pageSize);
		page = pageContext.getPage();
		int begin = getBegin(currentPage);
		int end = getEnd(begin, currentPage, page.getTotalPages());
		page.hasPreviousPage((begin > 1 ? true : false));
		page.hasNextPage(end < page.getTotalPages() ? true : false);
		page.setPagination(getPagination(page, begin, end, currentPage));
		return page;
	}

	/**
	 * Generated the pagination HTML text
	 * 
	 * @param <T>
	 * 
	 * @return
	 */
	public static <T> String getPagination(Page<T> page, int begin, int end,
			int currentPage) {
		StringBuffer sb = new StringBuffer("<ul>");
		// First page and previous page
		if (page.hasPreviousPage()) {
			sb.append("<li><a href=\"?page=1" + page.getSearchParams()
					+ "\">&lt;&lt;</a></li><li><a href=\"?page="
					+ (currentPage - 1) + page.getSearchParams()
					+ "\">&lt;</a></li>");

		} else {
			sb.append(PAGE_DISABLED_PREFIX);
		}
		// Page numbers
		for (int i = begin; i < end + 1; i++) {
			if (i == currentPage)
				sb.append(StringUtils.replace(PAGE_ACTIVATED, "${NUMBER}",
						String.valueOf(currentPage)));
			else
				sb.append(StringUtils.replace(page.getHref(), "${NUMBER}",
						String.valueOf(i)));

		}
		// Last page and next page
		if (page.hasNextPage()) {
			sb.append("<li><a href=\"?page=" + (currentPage + 1)
					+ page.getSearchParams()
					+ "\">&gt;</a></li><li><a href=\"?page="
					+ page.getTotalPages() + page.getSearchParams()
					+ "\">&gt;&gt;</a></li>");
		} else {
			sb.append(PAGE_DISABLED_SUFFIX);
		}
		sb.append("</ul>");
		return sb.toString();
	}

	/**
	 * Get the begin page number refer to the current page number
	 * 
	 * @param currentPage
	 *            Current page number
	 * @return
	 */
	public static int getBegin(int currentPage) {
		return Math.max(1, currentPage - PAGINATION_SIZE / 2);
	}

	/**
	 * 
	 * @param begin
	 *            Begin of page number
	 * @param currentPage
	 *            Current page number
	 * @param totalCount
	 *            Total count of pages
	 * @return
	 */
	public static int getEnd(int begin, int currentPage, int totalPages) {
		return Math.min(begin + (PAGINATION_SIZE - 1), totalPages);
	}

	public static <T> void main(String args[]) {
		System.out.println("begin: " + getBegin(1));
		System.out.println("end: " + getEnd(1, 1, 2));
		Page<T> page = new Page<T>();
		System.out.println(getPagination(page, 1, 2, 1));
	}
}
