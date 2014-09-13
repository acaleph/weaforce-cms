package com.weaforce.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.weaforce.core.util.DateUtil;
import com.weaforce.core.util.PageInfo;
import com.weaforce.core.util.StringUtil;
import com.weaforce.system.dao.area.ICountryDao;
import com.weaforce.system.dao.finance.IBankDao;
import com.weaforce.system.dao.finance.IBranchDao;
import com.weaforce.system.dao.finance.ICurrencyDao;
import com.weaforce.system.dao.finance.IExchangeRateDao;
import com.weaforce.system.dao.finance.IPeriodDao;
import com.weaforce.system.dao.finance.ITypeDao;
import com.weaforce.system.entity.finance.Bank;
import com.weaforce.system.entity.finance.BankBranch;
import com.weaforce.system.entity.finance.CostType;
import com.weaforce.system.entity.finance.Currency;
import com.weaforce.system.entity.finance.ExchangeRate;
import com.weaforce.entity.finance.Period;
import com.weaforce.system.service.IFinanceService;

@Service("financeService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FinanceService implements IFinanceService {
	@Autowired
	@Qualifier("countryDao")
	private ICountryDao countryDao;
	@Autowired
	@Qualifier("exchangeRateDao")
	private IExchangeRateDao exchangeRateDao;
	@Autowired
	@Qualifier("currencyDao")
	private ICurrencyDao currencyDao;
	@Autowired
	@Qualifier("periodDao")
	private IPeriodDao periodDao;
	@Autowired
	@Qualifier("costTypeDao")
	private ITypeDao typeDao;
	@Autowired
	@Qualifier("bankDao")
	private IBankDao bankDao;
	@Autowired
	@Qualifier("bankBranchDao")
	private IBranchDao branchDao;

	@Transactional(readOnly = true)
	public Currency getCurrency(Long currencyId) {
		return currencyDao.loadEntity(currencyId);
	}

	public void deleteCurrency(Long currencyId) {
		currencyDao.delete(currencyId);
	}

	public Currency saveCurrency(Currency o) {
		return currencyDao.save(o);
	}

	/**
	 * 取得币种列表
	 * 
	 * @param isActive
	 *            活动
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Currency> getCurrencyListByActive(String isActive) {
		return currencyDao.getCurrencyListByActive(isActive);
	}

	@Transactional(readOnly = true)
	public PageInfo<Currency> getCurrencyPage(PageInfo<Currency> pageInfo,
			String account, String currencyCode, String currencyName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Currency a ");
		if (StringUtil.isNotEmpty(currencyCode)) {
			sb.append(" Where a.currencyCode like '%" + currencyCode + "%' ");
			if (StringUtil.isNotEmpty(currencyName))
				sb.append(" And a.currencyName like '%" + currencyName + "%' ");
		} else {
			if (StringUtil.isNotEmpty(currencyName)) {
				sb.append(" Where a.currencyName like '%" + currencyName
						+ "%' ");
			}
		}
		pageInfo = currencyDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.currencyCode ");
		return pageInfo;
	}

	@Transactional(readOnly = true)
	public ExchangeRate getExchangeRate(Long rateId) {
		return exchangeRateDao.loadEntity(rateId);
	}

	/**
	 * 取得当前有效的汇率
	 * 
	 * @param account
	 *            帐套
	 * @param originId
	 *            原货币
	 * @param targetId
	 *            目标货货
	 * @param isActive
	 *            有效
	 * @return
	 */
	@Transactional(readOnly = true)
	public ExchangeRate getExchangeRateByOriginTargetActive(String account,
			Long originId, Long targetId, String isActive) {
		return exchangeRateDao.getExchangeRateByOriginTargetActive(account,
				originId, targetId, isActive);
	}

	public ExchangeRate saveExchangeRate(ExchangeRate o) {
		return exchangeRateDao.save(o);
	}

	public void saveExchangeRate(List<ExchangeRate> rateList) {
		exchangeRateDao.save(rateList);
	}

	@Transactional(readOnly = true)
	public List<ExchangeRate> getExchangeRateList(String account,
			Long originId, Long targetId) {
		return exchangeRateDao.getExchangeRateList(account, originId, targetId);
	}

	@Transactional(readOnly = true)
	public List<ExchangeRate> getExchangeRateNode(String account,
			String isActive) {
		List<ExchangeRate> list = exchangeRateDao.getExchangeRateList(account,
				isActive);
		for (ExchangeRate o : list)
			o.setRateName(o.getRateOriginCurrency().getCurrencyCode() + "-"
					+ o.getRateTargetCurrency().getCurrencyCode() + "  1:"
					+ o.getRateValue().toString());
		return list;
	}

	@Transactional(readOnly = true)
	public PageInfo<ExchangeRate> getExchangeRatePage(
			PageInfo<ExchangeRate> pageInfo, String account, Long rateOriginId,
			Long rateTargetId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From ExchangeRate a Where a.account= '" + account + "' ");
		if (rateOriginId != null && rateOriginId > 0) {
			sb.append(" And a.rateOriginCurrency.currencyId = " + rateOriginId);
		}
		if (rateTargetId != null && rateTargetId > 0) {
			sb.append(" And a.rateTargetCurrency.currencyId = " + rateTargetId);
		}
		pageInfo = exchangeRateDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.rateDate ");
		return pageInfo;
	}

	/**
	 * 预处理账期
	 * 
	 * @param account
	 * @param o
	 * @param periodId
	 * @param periodYear
	 *            年度
	 * @return
	 */
	@Transactional(readOnly = true)
	public Period preparePeriod(String account, Period o, Long periodId,
			Integer periodYear) {
		if (periodId == null) {
			o = new Period();
		} else {
			o = periodDao.loadEntity(periodId);
		}
		return o;
	}

	@Transactional(readOnly = true)
	public Period getPeriod(Long periodId) {
		return periodDao.loadEntity(periodId);
	}

	/**
	 * 根据起始和结束时间确定账期
	 * 
	 * @param account
	 *            帐套
	 * @param periodStart
	 *            起始时间
	 * @param periodEnd
	 *            结束时间
	 * @return
	 */
	@Transactional(readOnly = true)
	public Period getPeriodByStartEnd(String account, Long periodStart,
			Long periodEnd) {
		return periodDao.getPeriodByStartEnd(account, periodStart, periodEnd);
	}

	/**
	 * 删除账期
	 * 
	 * @param periodId
	 */
	public void deletePeriod(Long periodId) {
		periodDao.delete(periodId);
	}

	/**
	 * 保存账期
	 * 
	 * @param o
	 * @param parentId
	 * @return
	 */
	public Period savePeriod(String account, Integer periodYear, Period o,
			Long parentId) {
		if (o.getPeriodStart() < o.getPeriodEnd()) {
			List<Period> periodList = getPeriodListByYear(account, periodYear);
			if (parentId != null)
				o.setPeriodParent(periodDao.loadEntity(parentId));
			int size = periodList.size();
			if (size > 0) {
				if (DateUtil.getUTCDate(o.getPeriodStartDate())
						- com.weaforce.core.util.Global.INTERVAL_DAY == periodList
						.get(size - 1).getPeriodEnd())
					o = periodDao.save(o);
			} else {
				o = periodDao.save(o);
			}
		}
		return o;
	}

	/**
	 * 取得年度账期
	 * 
	 * @param account
	 *            帐套
	 * @param periodYear
	 *            年度
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Period> getPeriodListByYear(String account, Integer periodYear) {
		return periodDao.getPeriodListByYear(account, periodYear);
	}

	/**
	 * 取得活动的账期
	 * 
	 * @param account
	 * @param isActive
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Period> getPeriodListByActive(String account, String isActive) {
		return periodDao.getPeriodListByActive(account, isActive);
	}

	/**
	 * 取得年度账期JSON格式 list
	 * 
	 * @param account
	 *            帐套
	 * @param periodYear
	 *            账期年度
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getPeriodDDLByYear(String account, Integer periodYear) {
		StringBuffer sb = new StringBuffer();
		List<Period> periodList = periodDao.getPeriodListByYear(account,
				periodYear);
		for (Period o : periodList) {
			if (sb.length() == 0)
				sb.append("[{\"value\":\"" + o.getPeriodId()
						+ "\",\"caption\":\"" + o.getPeriodName() + "\"}");
			else
				sb.append(",{\"value\":\"" + o.getPeriodId()
						+ "\",\"caption\":\"" + o.getPeriodName() + "\"}");
		}
		sb.append("]");
		return sb.toString();
	}

	@Transactional(readOnly = true)
	public CostType getType(Long typeId) {
		return typeDao.loadEntity(typeId);
	}

	public void deleteType(Long typeId) {
		typeDao.delete(typeId);
	}

	public CostType saveType(CostType o) {
		return typeDao.save(o);
	}

	@Transactional(readOnly = true)
	public List<CostType> getTypeList(String account, String isActive) {
		return typeDao.getTypePage(account, isActive);
	}

	@Transactional(readOnly = true)
	public PageInfo<CostType> getTypePage(PageInfo<CostType> pageInfo,
			String account, String queryTypeName) {

		StringBuffer sb = new StringBuffer();
		sb.append(" From CostType a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(queryTypeName)) {
			sb.append(" And a.typeName like " + "'%" + queryTypeName + "%'");
		}
		pageInfo = typeDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.typeName ");
		return pageInfo;

	}

	/**
	 * 预处理银行
	 * 
	 * @param o
	 *            银行
	 * @param bankId
	 *            银行主键
	 * @param countryId
	 *            国家主键
	 * @return
	 */
	public Bank prepareBank(Bank o, Long bankId, Long countryId) {
		if (bankId == null) {
			o = new Bank();
			if (countryId != null)
				o.setBankCountry(countryDao.loadEntity(countryId));
		} else
			o = bankDao.loadEntity(bankId);
		return o;
	}

	/**
	 * 保存银行
	 * 
	 * @param o
	 *            银行
	 * @param countryId
	 *            国家主键
	 * @return
	 */
	public Bank saveBank(Bank o, Long countryId) {
		if (countryId == null)
			o.setBankCountry(null);
		else
			o.setBankCountry(countryDao.loadEntity(countryId));
		return bankDao.save(o);
	}

	/**
	 * 删除银行
	 * 
	 * @param bankId
	 *            银行主键
	 */
	public void deleteBank(Long bankId) {
		if (bankId != null)
			bankDao.delete(bankId);
	}

	/**
	 * 取得银行 page
	 * 
	 * @param pageInfo
	 * @return
	 */
	public PageInfo<Bank> getBankPage(PageInfo<Bank> pageInfo) {
		StringBuffer sb = new StringBuffer(" From Bank a ");
		pageInfo = bankDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.bankName ");
		return pageInfo;
	}

	/**
	 * 预处理银行分支
	 * 
	 * @param o
	 *            银行分支
	 * @param branchId
	 *            银行分支主键
	 * @param bankId
	 *            银行主键
	 * @param countryId
	 *            国家主键
	 * @return
	 */
	public BankBranch prepareBranch(BankBranch o, Long branchId, Long bankId,
			Long countryId) {
		if (branchId == null) {
			if (bankId != null)
				o.setBranchBank(bankDao.loadEntity(bankId));
			if (countryId != null)
				o.setBranchCountry(countryDao.loadEntity(countryId));
		} else
			o = branchDao.loadEntity(branchId);

		return o;
	}

	/**
	 * 保存银行分支
	 * 
	 * @param o
	 *            银行分支
	 * @param branchId
	 *            银行分支主键
	 * @param bankId
	 *            银行主键
	 * @param countryId
	 *            国家主键
	 * @return
	 */
	public BankBranch saveBranch(BankBranch o, Long branchId, Long bankId,
			Long countryId) {
		if (bankId == null)
			o.setBranchBank(null);
		else
			o.setBranchBank(bankDao.loadEntity(bankId));
		if (countryId == null)
			o.setBranchCountry(null);
		else
			o.setBranchCountry(countryDao.loadEntity(countryId));
		return branchDao.save(o);
	}

	/**
	 * 删除银行分支
	 * 
	 * @param branchId
	 *            银行分支主键
	 */
	public void deleteBranch(Long branchId) {
		branchDao.delete(branchId);
	}

	/**
	 * 银行分支 page
	 * 
	 * @param bankId
	 *            银行主键
	 * @param countryId
	 *            国家主键
	 * @param pageInfo
	 * @return
	 */
	public PageInfo<BankBranch> getBranchPage(PageInfo<BankBranch> pageInfo,
			Long bankId, Long countryId) {
		StringBuffer sb = new StringBuffer(" From BankBranch a ");
		pageInfo = branchDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.branchShortName ");
		return pageInfo;
	}
}
