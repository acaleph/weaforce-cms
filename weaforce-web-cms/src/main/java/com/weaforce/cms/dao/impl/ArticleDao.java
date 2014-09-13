package com.weaforce.cms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.IArticleDao;
import com.weaforce.cms.entity.Article;
import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.core.util.StringUtil;

@Repository("articleDao")
public class ArticleDao extends GenericDao<Article, Long> implements
		IArticleDao {
	private static final String QUERY_ARTICLE_BY_CATEGORY_PARSE = " From Article a Where a.articleCategory.categoryId=? And a.articleIsParse=? Order by a.createTime desc ";
	private static final String QUERY_ARTICLE_BY_CATEGORY = " From Article a Where a.articleCategory.categoryId=? Order by a.createTime desc ";
	private static final String QUERY_ARTICLE = " From Article a Where a.articleCategory.categoryId=? ";
	
	//FullTextSession fullTextSession = Search.getFullTextSession(getSession());
	
	
	//public void setFullTextSession(final FullTextSession fullTextSession) {
	//	this.fullTextSession = fullTextSession;
	//}

	/**
	 * 取得栏目相关,已经parse的文章list
	 * 
	 * @param categoryId
	 *            栏目
	 * @param parse
	 *            是否发布
	 * @return
	 */
	public List<Article> getArticleListByCategoryParse(Long categoryId,
			String parse) {
		return listQuery(QUERY_ARTICLE_BY_CATEGORY_PARSE, categoryId, parse);
	}

	/**
	 * 根据栏目,取得文章 list
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @return
	 */

	public List<Article> getArticleListByCategory(Long categoryId) {
		return listQuery(QUERY_ARTICLE_BY_CATEGORY, categoryId);
	}

	/**
	 * 根据栏目取得JSON格式文章分页记录
	 * 
	 * @param categoryId
	 *            栏目
	 * @param pageNumber
	 *            页码
	 * @return
	 */
	public String getArticleJSONByCategory(Long categoryId, Integer pageNumber) {
		StringBuffer sb = new StringBuffer();
		StringBuffer sbPage = new StringBuffer("[");
		/* 初始化页参数 */
		// 每页的记录数
		Integer pageSize = 14;
		// 页数
		Integer pageCount = 0;
		// 所有记录数
		Integer totalCount = executeStat(
				" Select Count(*) " + QUERY_ARTICLE_BY_CATEGORY, categoryId)
				.intValue();
		if (totalCount % pageSize == 0)
			pageCount = totalCount / pageSize;
		else
			pageCount = totalCount / pageSize + 1;
		/* 初始化页参数 */
		List<Article> articleList = listQueryPage(QUERY_ARTICLE_BY_CATEGORY,
				pageSize * (pageNumber - 1), pageSize, categoryId);
		for (Article o : articleList) {
			if (sb.length() == 0)
				sb.append("[");
			else
				sb.append(",");
			sb.append("{\"articleId\":\"" + o.getArticleId()
					+ "\",\"articleTitle\":\"" + o.getArticleTitle()
					+ "\",\"articleAuthor\":\""
					+ o.getArticleAuthor().getAuthorName()
					+ "\",\"articleFrom\":\""
					+ o.getArticleFrom().getFromName() + "\",\"articleUrl\":\""
					+ o.getArticleUrl() + "\",\"createTime\":\"" + o.getDate()
					+ "\"}");
		}
		if (sb.length() > 0)
			sb.append("]");
		else
			sb.append("[{}]");
		sbPage.append("{\"pageNumber\":\"" + pageNumber + "\",\"pageCount\":\""
				+ pageCount + "\",\"totalCount\":\"" + totalCount
				+ "\",\"page\":" + sb.toString() + "}]");
		return sbPage.toString();
	}

	/**
	 * 取得文章 list
	 * 
	 * @param account
	 *            帐套
	 * @param articleTitle
	 *            标题
	 * @param dateFrom
	 *            起始创建时间
	 * @param dateTo
	 *            终止创建时间
	 * @return
	 */
	public List<Article> getArticleList(Long categoryId, String articleTitle,
			Long dateFrom, Long dateTo) {
		// 如果没有检索条件,直接返回
		if (articleTitle == null && dateFrom == null && dateTo == null)
			return new ArrayList<Article>();
		String queryArticle = QUERY_ARTICLE;
		if (StringUtil.isNotEmpty(articleTitle))
			queryArticle = queryArticle + " And a.articleTitle like '%"
					+ articleTitle + "%' ";
		if (dateFrom != null && dateTo != null)
			queryArticle = queryArticle + " And a.createTime >='" + dateFrom
					+ "' And a.createTime <='" + dateTo + "'";
		return listQuery(queryArticle, categoryId);
	}

	/**
	 * 取得相关文章 list
	 * 
	 * @param articleRelation
	 * @return
	 */
	public List<Article> getArticleListByRelation(String articleRelation) {
		if (StringUtil.isNotEmpty(articleRelation))
			return listQuery("From Article a Where a.articleId in ("
					+ articleRelation + " ) ");
		else
			return new ArrayList<Article>();
	}

}
