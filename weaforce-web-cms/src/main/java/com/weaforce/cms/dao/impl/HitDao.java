package com.weaforce.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.IHitDao;
import com.weaforce.cms.entity.ArticleHit;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("hitDao")
public class HitDao extends GenericDao<ArticleHit, Long> implements IHitDao {

}
