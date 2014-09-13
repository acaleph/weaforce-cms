package com.weaforce.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ICategoryMobileDao;
import com.weaforce.cms.entity.CategoryMobile;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("categoryMobileDao")
public class CategoryMobileDao extends GenericDao<CategoryMobile, Long>
		implements ICategoryMobileDao {

}
