package com.weaforce.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import com.weaforce.core.util.PageInfo;
import com.weaforce.core.web.page.Page;

public interface IGenericDao<T, PK extends Serializable> {
	/**
	 * 保存实体
	 * 
	 * @param entity
	 * @return
	 */
	public T save(T entity);

	/**
	 * 批量保存实体
	 * 
	 * @param entities
	 */
	public void save(List<T> entities);

	/**
	 * 删除对象,包括批量删除
	 * 
	 * @param name
	 *            实体属性值
	 * @param value
	 *            实体属性名称
	 * @return
	 */
	public int delete(String name, Serializable value);

	/**
	 * 直接删除实体
	 * 
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * 批量删除实体
	 * 
	 * @param entities
	 */
	public void delete(List<T> entities);

	/**
	 * 根据主键,删除实体
	 * 
	 * @param id
	 */

	public void delete(PK id);

	/**
	 * 加载实体
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public T getEntity(final PK id);

	/**
	 * 应用代理加载实体
	 * 
	 * @param id
	 *            主键
	 * @return
	 */

	public T loadEntity(final PK id);

	/**
	 * 根据HQL语句获取实体实例
	 * 
	 * @param name
	 * @param value
	 * @return
	 */

	public T loadEntity(String name, Object value);

	/**
	 * 根据形如"From PO As a Where a.***=?"执行普通查询查询语句
	 * 
	 * @param hql
	 * @param args
	 * @return
	 */

	public T loadEntity(String hql, Object... args);

	public List<T> listQuery(String hql, Object... args);

	/**
	 * 按条件取得记录 list
	 * 
	 * @param hql
	 *            Hibernate SQL
	 * @param start
	 *            起始记录
	 * @param rowNum
	 *            记录条数
	 * @param args
	 *            检索参数
	 * @return
	 */
	public List<T> listQueryPage(String hql, int start, int rowNum,
			Object... args);

	/**
	 * 为Query添加distinct transformer.
	 * 
	 * @param query
	 * @return
	 */

	public List<T> listQueryDistinct(String hql, Object... args);

	public PageInfo<T> listQuery(PageInfo<T> pageInfo, String chql,
			String qhql, Object... args);

	/**
	 * Get page of data elements
	 * 
	 * @param page
	 *            Page POJO
	 * 
	 * @param chql
	 *            Counter SQL
	 * @param qhql
	 *            Query SQL
	 * @param currentPage
	 *            Current page number
	 * @param pageSize
	 *            Page size
	 * @param args
	 *            Query parameters
	 * @return
	 */
	public void getPage(Page<T> page, String chql, String qhql, Object... args);

	/**
	 * 执行查询
	 * 
	 * @param nhql
	 * @param start
	 *            超始记录
	 * @param rowNum
	 *            记录数量
	 * @param args
	 *            检索参数
	 * @return
	 */
	public List<T> listNamedQuery(String nhql, int start, int rowNum,
			Object... args);

	public List<T> listQuery(Criterion... criterion);

	/**
	 * 执行统计查询
	 * 
	 * @param hql
	 * @param args
	 *            检索参数
	 * @return
	 */
	public Number executeStat(String hql, Object... args);

	/**
	 * 初始对象
	 * 
	 * @param object
	 *            对象
	 */
	public void initObject(Object object);

	/**
	 * 批量初始对象
	 * 
	 * @param list
	 *            对象list
	 */

	public void initObjects(List<T> list);

	/**
	 * 批量初始对象
	 * 
	 * @param set
	 *            对象set
	 */

	public void initObjects(Set<T> set);

}
