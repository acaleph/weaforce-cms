package com.weaforce.system.dao.impl.logistics;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.logistics.IProviderDao;
import com.weaforce.system.entity.logistics.Provider;

@Repository("providerDao")
public class ProviderDao extends GenericDao<Provider, Long> implements
		IProviderDao {
	private static final String QUERY_PROVIDER = " From Provider a Where a.account = ? ";
	private static final String QUERY_PROVIDER_BY_ACCOUNT = " From Provider a Where a.account = ? and a.providerAccount.accountId=? ";

	/**
	 * 根据帐套取得供应商
	 * 
	 * @param account
	 *            当前帐套
	 * @param accountId
	 *            目标帐套主键
	 * @return
	 */
	public Provider getProviderByAccount(String account, Long accountId) {
		return loadEntity(QUERY_PROVIDER_BY_ACCOUNT, account, accountId);
	}

	/**
	 * 取得供应商 list
	 * 
	 * @param account
	 *            当前帐套
	 * @param flag
	 *            是否有none选项
	 * @return
	 */
	public List<Provider> getProviderList(String account, boolean flag) {
		List<Provider> providerList = listQuery(QUERY_PROVIDER, account);
		if (flag) {
			Provider o = new Provider();
			o.setProviderCode(" ---none---");
			providerList.add(0, o);
		}
		return providerList;
	}
}
