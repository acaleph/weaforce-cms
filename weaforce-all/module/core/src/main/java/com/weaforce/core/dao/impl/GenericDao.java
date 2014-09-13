package com.weaforce.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.core.util.PageInfo;
import com.weaforce.core.web.page.Page;
import com.weaforce.core.web.page.PageUtil;

@SuppressWarnings("unchecked")
public abstract class GenericDao<T, PK extends Serializable> implements
		IGenericDao<T, PK> {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	private Class<T> entityClass;

	public GenericDao() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * 保存实体
	 * 
	 * @param entity
	 * @return
	 */
	public T save(T entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}

	/**
	 * 批量保存实体
	 * 
	 * @param entities
	 */
	public void save(List<T> entities) {
		if (entities != null && entities.size() > 0) {
			for (T o : entities) {
				save(o);
			}
		}
	}

	/**
	 * 根据查询条件删除对象,包括批量删除
	 * 
	 * @param value
	 *            实体属性值
	 * @param name
	 *            实体属性名称
	 * @return
	 */
	public int delete(String name, Serializable value) {
		StringBuffer hql = new StringBuffer(" DELETE FROM ");
		hql.append(entityClass.getName());
		hql.append(" AS o WHERE o." + name + " = " + value);
		Query q = getSession().createQuery(hql.toString());
		return q.executeUpdate();
	}

	/**
	 * 直接删除实体
	 * 
	 * @param entity
	 */
	public void delete(T entity) {
		getSession().delete(entity);
	}

	/**
	 * 根据主键,删除实体
	 * 
	 * @param id
	 */
	public void delete(PK id) {
		Assert.notNull(id);
		getSession().delete(loadEntity(id));
	}

	/**
	 * 批量删除实体
	 * 
	 * @param entities
	 */
	public void delete(List<T> entities) {
		if (entities != null && entities.size() > 0) {
			for (T o : entities) {
				delete(o);
			}
		}
	}

	/**
	 * 加载实体
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public T getEntity(final PK id) {
		return (T) getSession().get(getEntityClass(), id);
	}

	/**
	 * 应用代理加载实体
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public T loadEntity(final PK id) {
		return (T) getSession().load(getEntityClass(), id);
	}

	/**
	 * 根据实体属性获取实体
	 * 
	 * @author acaleph8@yahoo.com.cn
	 */

	public T loadEntity(String name, Object value) {
		Assert.hasText(name);
		return (T) createCriteria(Restrictions.eq(name, value)).uniqueResult();
	}

	/**
	 * 根据HQL语句获取实体实例
	 * 
	 * @param name
	 * @param value
	 * @return
	 */

	public T loadEntity(String hql, Object... args) {
		Assert.hasText(hql);
		Query q = getSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			q.setParameter(i, args[i]);
		}
		return (T) q.uniqueResult();
	}

	/**
	 * 根据形如"From PO As a Where a.***=?"执行普通查询查询语句
	 * 
	 * @param hql
	 * @param args
	 * @return
	 */

	public List<T> listQuery(String hql, Object... args) {
		Assert.hasText(hql);
		Query q = getSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			q.setParameter(i, args[i]);
		}
		return q.list();
	}

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
			Object... args) {
		Query q = getSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			q.setParameter(i, args[i]);
		}
		if (start > 0) {
			q.setFirstResult(start);
		}
		if (rowNum > 0) {
			q.setMaxResults(rowNum);
		}
		return q.list();
	}

	/**
	 * 为Query添加distinct transformer.
	 * 
	 * @param query
	 * @return
	 */
	public List<T> listQueryDistinct(String hql, Object... args) {
		Query q = getSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			q.setParameter(i, args[i]);
		}
		q.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return q.list();
	}

	/**
	 * 获取数据页
	 */
	public PageInfo<T> listQuery(PageInfo<T> pageInfo, String chql,
			String qhql, Object... args) {
		if (pageInfo == null)
			pageInfo = new PageInfo<T>();
		pageInfo.setTotalCount(executeStat(chql, args).intValue());
		// Get the page list
		List<T> list = listQueryPage(qhql, pageInfo.getStartOfPage(),
				pageInfo.getPageSize(), args);
		pageInfo.setResult(list);
		return pageInfo;
	}

	public void getPage(Page<T> page, String chql, String qhql, Object... args) {
		int currentPage = page.getCurrentPage();
		int pageSize = page.getPageSize();
		int totalElements = executeStat(chql, args).intValue();
		page.setTotalElements(totalElements);
		List<T> elements = listQueryPage(qhql, (currentPage - 1) * pageSize,
				pageSize, args);
		page = PageUtil.getPage(page, totalElements, currentPage, pageSize,
				elements);
	}

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
			Object... args) {
		// 根据形如"GET_OBJECT_LIST = From PO As a Where
		// a.***=?"执行普通的查询语句，查询集数可定制
		Query q = getSession().getNamedQuery(nhql);
		for (int i = 0; i < args.length; i++) {
			q.setParameter(i, args[i]);
		}
		if (start > 0) {
			q.setFirstResult(start);
		}
		if (rowNum > 0) {
			q.setMaxResults(rowNum);
		}
		return q.list();
	}

	public List<T> listQuery(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getEntityClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	/**
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
	 */
	public Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString);
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}

	/**
	 * 执行统计查询
	 * 
	 * @param hql
	 * @param args
	 *            检索参数
	 * @return
	 */
	public Number executeStat(String hql, Object... args) {
		// 执行统计查询语句
		return (Number) uniqueResultQuery(hql, args);
	}

	public Number executeNamedStat(String nhql, Object... args) {
		// 执行统计查询语句
		return (Number) uniqueResultNamedQuery(nhql, args);
	}

	public Object uniqueResultQuery(String hql, Object... args) {
		// 执行返回单一结果的查询语句
		Query q = getSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			q.setParameter(i, args[i]);
		}
		q.setMaxResults(1);
		return q.uniqueResult();
	}

	public Object uniqueResultNamedQuery(String nhql, Object... args) {
		// 执行返回单一结果的查询语句
		Query q = getSession().getNamedQuery(nhql);
		for (int i = 0; i < args.length; i++) {
			q.setParameter(i, args[i]);
		}
		q.setMaxResults(1);
		return q.uniqueResult();
	}

	/**
	 * 根据Criterion条件创建Criteria,后续可进行更多处理,辅助函数.
	 */
	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 初始化对象. 使用load()方法得到的仅是Proxy对象, 在传到View层前需要进行初始化. initObject(user)
	 * ,初始化User的直接属性，但不会初始化延迟加载的关联集合和属性.
	 * initObject(user.getRoles())，初始化User的直接属性和关联集合.
	 * initObject(user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
	 */
	public void initObject(Object object) {
		Hibernate.initialize(object);
	}

	/**
	 * 批量初始化对象.
	 * 
	 * @see #initObject(Object)
	 */
	public void initObjects(List<T> list) {
		for (Object object : list)
			Hibernate.initialize(object);
	}

	/**
	 * 批量初始对象
	 * 
	 * @param set
	 *            对象set
	 */
	public void initObjects(Set<T> set) {
		for (Object object : set)
			Hibernate.initialize(object);
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

}
