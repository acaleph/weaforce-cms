package com.weaforce.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.IAuthorDao;
import com.weaforce.cms.entity.Author;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("authorDao")
public class AuthorDao extends GenericDao<Author, Long> implements IAuthorDao {
	private static final String QUERY_AUTHOR = " From Author a Where a.account=? ";

	/**
	 * 取得作者list
	 * 
	 * @param account
	 * @return
	 */
	public List<Author> getAuthorList(String account) {
		return listQuery(QUERY_AUTHOR, account);
	}
}
