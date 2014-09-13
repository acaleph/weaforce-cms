package com.weaforce.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ITemplateMobileDao;
import com.weaforce.cms.entity.TemplateMobile;
import com.weaforce.core.dao.impl.GenericDao;
@Repository("templateMobileDao")
public class TemplateMobileDao extends GenericDao<TemplateMobile, Long>
		implements ITemplateMobileDao {

}
