package com.weaforce.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.IArticleMobileDao;
import com.weaforce.cms.entity.ArticleMobile;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("articleMobileDao")
public class ArticleMobileDao extends GenericDao<ArticleMobile, Long> implements
		IArticleMobileDao {

}
