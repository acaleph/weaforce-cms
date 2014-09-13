package com.weaforce.cms.dao;

import java.util.List;

import com.weaforce.cms.entity.Author;
import com.weaforce.core.dao.IGenericDao;

public interface IAuthorDao extends IGenericDao<Author, Long> {
	/**
	 * 取得作者list
	 * 
	 * @param account
	 * @return
	 */
	public List<Author> getAuthorList(String account);
}
