package com.weaforce.system.service;

import java.util.List;

import com.weaforce.core.util.PageInfo;
import com.weaforce.system.entity.finance.Bank;
import com.weaforce.system.entity.finance.BankBranch;
import com.weaforce.system.entity.finance.CostType;
import com.weaforce.system.entity.finance.Currency;
import com.weaforce.system.entity.finance.ExchangeRate;
import com.weaforce.entity.finance.Period;

public interface IFinanceService {
	public Currency getCurrency(Long currencyId);

	public void deleteCurrency(Long currencyId);

	public Currency saveCurrency(Currency o);

	/**
	 * 取得币种列表
	 * 
	 * @param isActive
	 *            活动
	 * @return
	 */

	public List<Currency> getCurrencyListByActive(String isActive);

	public PageInfo<Currency> getCurrencyPage(PageInfo<Currency> pageInfo,
			String account, String currencyCode, String currencyName);

	public ExchangeRate getExchangeRate(Long rateId);

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
	public ExchangeRate getExchangeRateByOriginTargetActive(String account,
			Long originId, Long targetId, String isActive);

	public ExchangeRate saveExchangeRate(ExchangeRate o);

	public void saveExchangeRate(List<ExchangeRate> rateList);

	public List<ExchangeRate> getExchangeRateList(String account,
			Long originId, Long targetId);

	/**
	 * 获取当前有效汇率
	 * 
	 * @param account
	 *            帐套
	 * @param rateIsActive
	 *            是否有效
	 * @return
	 */

	public List<ExchangeRate> getExchangeRateNode(String account,
			String rateIsActive);

	public PageInfo<ExchangeRate> getExchangeRatePage(
			PageInfo<ExchangeRate> pageInfo, String account, Long rateOriginId,
			Long rateTargetId);

	/**
	 * 预处理账期
	 * 
	 * @param account
	 * 
	 * @param o
	 * @param periodId
	 * @param periodYear
	 *            年度
	 * @return
	 */
	public Period preparePeriod(String account, Period o, Long periodId,
			Integer periodYear);

	/**
	 * 取得账期
	 * 
	 * @param periodId
	 * @return
	 */
	public Period getPeriod(Long periodId);

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
	public Period getPeriodByStartEnd(String account, Long periodStart,
			Long periodEnd);

	/**
	 * 删除账期
	 * 
	 * @param periodId
	 */
	public void deletePeriod(Long periodId);

	/**
	 * 保存账期
	 * 
	 * @param o
	 * @param parentId
	 * @return
	 */
	public Period savePeriod(String account, Integer periodYear, Period o,
			Long parentId);

	/**
	 * 取得年度账期
	 * 
	 * @param account
	 *            帐套
	 * @param periodYear
	 *            年度
	 * @return
	 */
	public List<Period> getPeriodListByYear(String account, Integer periodYear);

	/**
	 * 取得活动的账期
	 * 
	 * @param account
	 * @param isActive
	 * @return
	 */
	public List<Period> getPeriodListByActive(String account, String isActive);

	/**
	 * 取得年度账期JSON格式 list
	 * 
	 * @param account
	 *            帐套
	 * 
	 * @param periodYear
	 *            账期年度
	 * @return
	 */
	public String getPeriodDDLByYear(String account, Integer periodYear);

	public CostType getType(Long typeId);

	public void deleteType(Long typeId);

	public CostType saveType(CostType o);

	public List<CostType> getTypeList(String account, String isActive);

	public PageInfo<CostType> getTypePage(PageInfo<CostType> pageInfo,
			String account, String queryTypeName);

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
	public Bank prepareBank(Bank o, Long bankId, Long countryId);

	/**
	 * 保存银行
	 * 
	 * @param o
	 *            银行
	 * @param countryId
	 *            国家主键
	 * @return
	 */
	public Bank saveBank(Bank o, Long countryId);

	/**
	 * 删除银行
	 * 
	 * @param bankId
	 *            银行主键
	 */
	public void deleteBank(Long bankId);

	/**
	 * 取得银行 page
	 * 
	 * @param pageInfo
	 * @return
	 */
	public PageInfo<Bank> getBankPage(PageInfo<Bank> pageInfo);

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
			Long countryId);

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
			Long countryId);

	/**
	 * 删除银行分支
	 * 
	 * @param branchId
	 *            银行分支主键
	 */
	public void deleteBranch(Long branchId);

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
			Long bankId, Long countryId);
}
