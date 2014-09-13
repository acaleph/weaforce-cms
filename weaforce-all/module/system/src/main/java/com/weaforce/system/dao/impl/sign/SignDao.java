package com.weaforce.system.dao.impl.sign;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.sign.ISignDao;
import com.weaforce.system.entity.sign.Sign;
@Repository("signDao")
public class SignDao extends GenericDao<Sign, Long> implements ISignDao {

}
