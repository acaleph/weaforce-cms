package com.weaforce.cms.dao.impl.ads;


import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.IAdsStyleDao;
import com.weaforce.cms.entity.ads.AdsStyle;
import com.weaforce.core.dao.impl.GenericDao;
@Repository("adsAdsStyleDao")
public class AdsStyleDao extends GenericDao<AdsStyle,Long> implements IAdsStyleDao {

}
