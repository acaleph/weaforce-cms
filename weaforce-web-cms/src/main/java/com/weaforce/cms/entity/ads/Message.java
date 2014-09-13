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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.util.DateUtil;

/**
 * 短信管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_ads_message")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class Message implements Serializable {
	private static final long serialVersionUID = -2912599661887813917L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "118", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "MESSAGE_ID", length = 20)
	private Long messageId;
	// 标题
	@Column(name = "MESSAGE_TITLE", length = 90)
	private String messageTitle;
	// 手机
	@Column(name = "MESSAGE_MOBILE", length = 20)
	private String messageMobile;
	// 电话
	@Column(name = "MESSAGE_PHONE", length = 20)
	private String messagePhone;
	// 创建时间
	@Column(name = "CREATE_TIME", length = 20)
	private Long createTime;
	// 备注:广告商家信息确认后备注
	@Column(name = "MESSAGE_REMARK")
	private String messageRemark;
	// 完成
	@Column(name = "MESSAGE_IS_OK", length = 1)
	private String messageIsOk;
	// 计费完成
	@Column(name = "MESSAGE_IS_PAY", length = 1)
	private String messageIsPay;
	// 广告商家
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "MESSAGE_ADS_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_MESSAGE_ADS_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Ads messageAds;

	private String date;

	public Message() {
		messageIsOk = "0";
		messageIsPay ="0";
		createTime = System.currentTimeMillis();
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageMobile() {
		return messageMobile;
	}

	public void setMessageMobile(String messageMobile) {
		this.messageMobile = messageMobile;
	}

	public String getMessagePhone() {
		return messagePhone;
	}

	public void setMessagePhone(String messagePhone) {
		this.messagePhone = messagePhone;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}


	public String getMessageRemark() {
		return messageRemark;
	}

	public void setMessageRemark(String messageRemark) {
		this.messageRemark = messageRemark;
	}

	public String getMessageIsOk() {
		return messageIsOk;
	}

	public void setMessageIsOk(String messageIsOk) {
		this.messageIsOk = messageIsOk;
	}

	public String getMessageIsPay() {
		return messageIsPay;
	}

	public void setMessageIsPay(String messageIsPay) {
		this.messageIsPay = messageIsPay;
	}

	public Ads getMessageAds() {
		return messageAds;
	}

	public void setMessageAds(Ads messageAds) {
		this.messageAds = messageAds;
	}

	/**
	 * 返回W3C"长"格式日期,如2008-08-08 08:08:08
	 * 
	 * @return
	 */
	public String getDate() {
		if (createTime == null || createTime == 0) {
			date = DateUtil
					.completeFormat(new Date(System.currentTimeMillis()));
		} else {
			date = DateUtil.completeFormat(new Date(createTime));
		}
		return date;
	}
}
