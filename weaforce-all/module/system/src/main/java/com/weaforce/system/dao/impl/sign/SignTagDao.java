package com.weaforce.system.dao.impl.sign;


import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.sign.ISignTagDao;
import com.weaforce.system.entity.sign.SignTag;
@Repository("signTagDao")
public class SignTagDao extends GenericDao<SignTag, Long> implements
		ISignTagDao {

}
