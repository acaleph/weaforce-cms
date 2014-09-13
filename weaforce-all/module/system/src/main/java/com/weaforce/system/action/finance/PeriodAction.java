package com.weaforce.system.action.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.core.util.DateUtil;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.entity.finance.Period;
import com.weaforce.system.service.IFinanceService;

@ParentPackage("default")
@Namespace("/system/finance")
public class PeriodAction extends AbstractCrudAction<Period> {

	private static final long serialVersionUID = 5633618377187303310L;
	@Autowired
	@Qualifier("financeService")
	private IFinanceService financeService;

	private Period period;
	private Long periodId;
	private Long parentId;

	private List<Period> periodList = new ArrayList<Period>();

	private Integer periodYear;

	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}

	
	protected void prepareModel() throws Exception {
		period = financeService.preparePeriod(getAdmin().getAccount(),period, periodId, periodYear);
	}

	/**
	 * 自动选择父账期,periodList已经按照起始时间排序
	 * 
	 */
	
	public String input() throws Exception {
		if (periodId == null) {
			periodList = financeService.getPeriodListByYear(getAdmin()
					.getAccount(), periodYear);
			int size = periodList.size();
			if (size == 0) {
				period.setPeriodParent(null);
				period.setPeriodYear(periodYear);
				periodList = financeService.getPeriodListByYear(getAdmin()
						.getAccount(), periodYear - 1);
				size = periodList.size();
				Period p = new Period();
				if (size > 0) {
					p = periodList.get(size - 1);
					period.setPeriodParent(p);
					// 以UTC格式,设置起始日期
					period.setPeriodStart(p.getPeriodEnd()
							+ com.weaforce.core.util.Global.INTERVAL_DAY);
					// 以W3C格式,显示起始日期
					period.setPeriodStartDate(DateUtil.defaultFormat(new Date(
							period.getPeriodStart())));
				}

			} else {
				// 引入上一账期结束日期作为当前账期的起始日期
				Period o = periodList.get(size - 1);
				if (periodId == null) {
					period.setPeriodParent(o);
					// 以UTC格式,设置起始日期
					period.setPeriodStart(o.getPeriodEnd()
							+ com.weaforce.core.util.Global.INTERVAL_DAY);
					// 以W3C格式,显示起始日期
					period.setPeriodStartDate(DateUtil.defaultFormat(new Date(
							period.getPeriodStart())));
				}
			}
		}
		return INPUT;
	}

	/**
	 * 父账期
	 * 
	 * @param parentId
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 保存账期
	 */
	
	public String save() throws Exception {
		period = financeService.savePeriod(getAdmin().getAccount(), periodYear,
				period, parentId);
		return list();
	}

	/**
	 * 删除账期,只允许删除父账期为null的账期
	 */
	
	public String delete() throws Exception {
		if (periodId != null) {
			period = financeService.getPeriod(periodId);
			if (period.getPeriodParent() == null)
				financeService.deletePeriod(periodId);
		}
		return list();
	}

	
	public String list() throws Exception {
		if (periodYear == null)
			periodYear = DateUtil.getYear(new Date());
		periodList = financeService.getPeriodListByYear(
				getAdmin().getAccount(), periodYear);
		return SUCCESS;
	}

	
	/**
	 * 取得年度帐期JSON格式 list
	 * @return
	 * @throws Exception
	 */
	public String getPeriodDDLByYear() throws Exception{
		return StrutsUtil.renderJSON(financeService.getPeriodDDLByYear(getAdmin().getAccount(),periodYear));
	}

	/**
	 * 账期年度
	 * 
	 * @param periodYear
	 */

	public void setPeriodYear(Integer periodYear) {
		this.periodYear = periodYear;
	}

	public List<Period> getPeriodList() {
		return periodList;
	}

	public Integer getPeriodYear() {
		return periodYear;
	}


	public Period getModel() {
		return period;
	}

}
