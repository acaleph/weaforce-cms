package com.weaforce.cms.entity.ads;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.util.DateUtil;

/**
 * 团购订单实体类:必需先登录
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_ads_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class Order implements Serializable {
	private static final long serialVersionUID = 4973677939997232252L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "162", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "ORDER_ID", length = 20)
	private Long orderId;
	// 日期
	@Column(name = "CREATE_TIME", length = 20)
	private Long createTime;
	// 数量
	@Column(name = "ORDER_COUNT", length = 3)
	private Byte orderCount;
	// 注册
	@Column(name = "ORDER_REGISTRY_ID", length = 20, nullable = true)
	private Long orderRegistryId;
	// 送货地址
	@Column(name = "ORDER_ADDRESS", length = 255)
	private String orderAddress;
	// 交易
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_DEAL_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_CMS_ADS_ORDER_DEAL_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Deal orderDeal;

	@Transient
	private String createTimeDate;

	public Order() {
		createTime = System.currentTimeMillis();
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Byte getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Byte orderCount) {
		this.orderCount = orderCount;
	}

	public Long getOrderRegistryId() {
		return orderRegistryId;
	}

	public void setOrderRegistryId(Long orderRegistryId) {
		this.orderRegistryId = orderRegistryId;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public Deal getOrderDeal() {
		return orderDeal;
	}

	public void setOrderDeal(Deal orderDeal) {
		this.orderDeal = orderDeal;
	}

	/**
	 * 完整显示用户订货时间
	 * 
	 * @return
	 */
	public String getCreateTimeDate() {
		if (createTime != null)
			createTimeDate = DateUtil.completeFormat(new Date(createTime));
		return createTimeDate;
	}

	public void setCreateTimeDate(String createTimeDate) {
		this.createTimeDate = createTimeDate;
	}

}
