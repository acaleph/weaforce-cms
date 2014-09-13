package com.weaforce.cms.dao.impl.ads;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.IProductDao;
import com.weaforce.cms.entity.ads.Product;
import com.weaforce.core.dao.impl.GenericDao;
@Repository("adsProductDao")
public class ProductDao extends GenericDao<Product, Long> implements
		IProductDao {

}
