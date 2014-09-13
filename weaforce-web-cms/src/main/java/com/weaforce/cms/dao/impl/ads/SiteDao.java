package com.weaforce.cms.dao.impl.ads;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.ISiteDao;
import com.weaforce.cms.entity.ads.CategorySite;
import com.weaforce.core.dao.impl.GenericDao;
@Repository("adsSiteDao")
public class SiteDao extends GenericDao<CategorySite, Long> implements ISiteDao {

}
