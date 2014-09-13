package com.weaforce.system.entity.logistics;

import java.io.Serializable;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.system.entity.organ.Account;

/**
 * 生产厂家
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "logistics_producer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Producer extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 3317203603358632158L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "74", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "PRODUCER_ID", length = 20)
	private Long producerId;
	// 代码
	@Column(name = "PRODUCER_CODE", length = 10)
	private String producerCode;
	// 简称
	@Column(name = "PRODUCER_SHORT_NAME", length = 20)
	private String producerShortName;
	// 月结天数
	@Column(name = "PRODUCER_FINANCE_DAY", length = 10)
	private Integer producerFinanceDay;
	// 帐套
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCER_ACCOUNT_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_PRODUCER_ACCOUNT_ID")
	private Account producerAccount;

	public Producer() {

	}

	public Long getProducerId() {
		return producerId;
	}

	public void setProducerId(Long producerId) {
		this.producerId = producerId;
	}

	public String getProducerCode() {
		return producerCode;
	}

	public void setProducerCode(String producerCode) {
		this.producerCode = producerCode;
	}

	

	public String getProducerShortName() {
		return producerShortName;
	}

	public void setProducerShortName(String producerShortName) {
		this.producerShortName = producerShortName;
	}

	public Integer getProducerFinanceDay() {
		return producerFinanceDay;
	}

	public void setProducerFinanceDay(Integer producerFinanceDay) {
		this.producerFinanceDay = producerFinanceDay;
	}

	public Account getProducerAccount() {
		return producerAccount;
	}

	public void setProducerAccount(Account producerAccount) {
		this.producerAccount = producerAccount;
	}

}
