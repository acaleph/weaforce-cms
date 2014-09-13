package com.weaforce.cms.dao;

import java.util.List;

import com.weaforce.cms.entity.Article;
import com.weaforce.core.dao.IGenericDao;

public interface IArticleDao extends IGenericDao<Article, Long> {
	
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
			String parse);

	/**
	 * 根据栏目,取得文章 list
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @return
	 */

	public List<Article> getArticleListByCategory(Long categoryId);

	/**
	 * 根据栏目取得JSON格式文章分页记录
	 * 
	 * @param categoryId
	 *            栏目
	 * @param pageNumber
	 *            页码
	 * @return
	 */
	public String getArticleJSONByCategory(Long categoryId, Integer pageNumber);

	/**
	 * 取得文章 list
	 * 
	 * @param categoryId
	 *            栏目
	 * @param articleTitle
	 *            标题
	 * @param dateFrom
	 *            起始创建时间
	 * @param dateTo
	 *            终止创建时间
	 * @return
	 */
	public List<Article> getArticleList(Long categoryId, String articleTitle,
			Long dateFrom, Long dateTo);

	/**
	 * 取得相关文章 list
	 * 
	 * @param articleRelation
	 * @return
	 */
	public List<Article> getArticleListByRelation(String articleRelation);



}
