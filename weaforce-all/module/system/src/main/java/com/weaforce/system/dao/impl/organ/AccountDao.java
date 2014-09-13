package com.weaforce.system.dao.impl.organ;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.organ.IAccountDao;
import com.weaforce.system.entity.organ.Account;

@Repository("accountDao")
public class AccountDao extends GenericDao<Account, Long> implements
		IAccountDao {
	private static final String QUERY_ACCOUNT_BY_SCORE = " From Account a Where a.accountIsScore = ? ";

	public List<Account> getAccountListByScore(String isScore){
		return listQuery(QUERY_ACCOUNT_BY_SCORE,isScore);
	}
}
