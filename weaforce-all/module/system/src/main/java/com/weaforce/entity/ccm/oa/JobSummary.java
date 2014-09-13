package com.weaforce.entity.ccm.oa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.entity.admin.User;
import com.weaforce.core.util.DateUtil;

/**
 * 工作总结管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "fo_job_summary")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class JobSummary extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -9029775946037386423L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "23", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "SUMMARY_ID", length = 20)
	private Long summaryId;
	// 标题
	@Column(name = "SUMMARY_TITLE", length = 180)
	private String summaryTitle;
	// 日期
	@Column(name = "SUMMARY_DATE", length = 20)
	private Long summaryDate;
	// 内容
	@Column(name = "SUMMARY_CONTENT")
	private String summaryContent;
	// 所属员工
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "SUMMARY_USER_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_JOB_SUMMARY_USER_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private User summaryUser;
	@Transient
	private String summaryDateDate;

	public JobSummary() {

	}

	public Long getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(Long summaryId) {
		this.summaryId = summaryId;
	}

	public String getSummaryTitle() {
		return summaryTitle;
	}

	public void setSummaryTitle(String summaryTitle) {
		this.summaryTitle = summaryTitle;
	}

	public Long getSummaryDate() {
		return summaryDate;
	}

	public void setSummaryDate(Long summaryDate) {
		this.summaryDate = summaryDate;
	}

	public String getSummaryContent() {
		return summaryContent;
	}

	public void setSummaryContent(String summaryContent) {
		this.summaryContent = summaryContent;
	}

	public User getSummaryUser() {
		return summaryUser;
	}

	public void setSummaryUser(User summaryUser) {
		this.summaryUser = summaryUser;
	}

	/**
	 * 以W3C格式显示日期
	 * 
	 * @return
	 */
	public String getSummaryDateDate() {
		if (summaryDate != null)
			summaryDateDate = DateUtil.defaultFormat(new Date(summaryDate));
		return summaryDateDate;
	}

	/**
	 * 以UTC格式保存日期
	 * 
	 * @param summaryDateDate
	 */
	public void setSummaryDateDate(String summaryDateDate) {
		if (StringUtils.isNotEmpty(summaryDateDate))
			summaryDate = DateUtil.getUTCDate(summaryDateDate);
		this.summaryDateDate = summaryDateDate;
	}

}
