package com.weaforce.system.dao.organ;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.organ.Account;

public interface IAccountDao extends IGenericDao<Account, Long> {
	public List<Account> getAccountListByScore(String isScore);
}
