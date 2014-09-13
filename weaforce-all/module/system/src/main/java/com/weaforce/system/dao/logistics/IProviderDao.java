package com.weaforce.system.dao.logistics;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.logistics.Provider;

public interface IProviderDao extends IGenericDao<Provider, Long> {
	/**
	 * 根据帐套取得供应商
	 * 
	 * @param account
	 *            当前帐套
	 * @param accountId
	 *            目标帐套主键
	 * @return
	 */
	public Provider getProviderByAccount(String account, Long accountId);

	/**
	 * 取得供应商 list
	 * 
	 * @param account
	 *            当前帐套
	 * @param flag 是否有none选项
	 * @return
	 */
	public List<Provider> getProviderList(String account, boolean flag);

}
