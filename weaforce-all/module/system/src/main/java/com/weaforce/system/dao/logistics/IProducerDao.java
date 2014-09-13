package com.weaforce.system.dao.logistics;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.logistics.Producer;

public interface IProducerDao extends IGenericDao<Producer, Long> {
	/**
	 * 取得生产商 list
	 * 
	 * @param account
	 *            帐套
	 * @param flag
	 *            是否有none选项
	 * @return
	 */
	public List<Producer> getProducerList(String account, boolean flag);

	/**
	 * 根据帐套取得生产商
	 * 
	 * @param account
	 *            当前帐套
	 * @param accountId
	 *            目标帐套主键
	 * @return
	 */
	public Producer getProducerByAccount(String account, Long accountId);
}
