package com.weaforce.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.IContentDao;
import com.weaforce.cms.entity.ArticleContent;
import com.weaforce.core.dao.impl.GenericDao;
@Repository("articleContentDao")
public class ContentDao extends GenericDao<ArticleContent, Long> implements
		IContentDao {

}
