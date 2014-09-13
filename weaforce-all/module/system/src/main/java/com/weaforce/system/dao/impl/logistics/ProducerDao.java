package com.weaforce.system.dao.impl.logistics;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.logistics.IProducerDao;
import com.weaforce.system.entity.logistics.Producer;

@Repository("producerDao")
public class ProducerDao extends GenericDao<Producer, Long> implements
		IProducerDao {
	private static final String QUERY_PRODUCER = " From Producer a Where a.account=? ";
	private static final String QUERY_PRODUCER_BY_ACCOUNT = " From Producer a Where a.account = ? and a.producerAccount.accountId=? ";

	/**
	 * 取得生产商 list
	 * 
	 * @param account
	 *            帐套
	 * @param flag
	 *            是否有none选项
	 * @return
	 */
	public List<Producer> getProducerList(String account, boolean flag) {
		List<Producer> producerList = listQuery(QUERY_PRODUCER, account);
		if (flag) {
			Producer o = new Producer();
			o.setProducerCode(" ---none--- ");
			producerList.add(0, o);
		}
		return producerList;
	}

	/**
	 * 根据帐套取得生产商
	 * 
	 * @param account
	 *            当前帐套
	 * @param accountId
	 *            目标帐套主键
	 * @return
	 */
	public Producer getProducerByAccount(String account, Long accountId) {
		return loadEntity(QUERY_PRODUCER_BY_ACCOUNT, account, accountId);
	}
}
