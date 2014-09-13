package com.weaforce.system.dao.impl.sign;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.sign.ISignTitleDao;
import com.weaforce.system.entity.sign.SignTitle;
@Repository("signTitleDao")
public class SignTitleDao extends GenericDao<SignTitle, Long> implements
		ISignTitleDao {

}
