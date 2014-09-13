package com.weaforce.system.entity.sign;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;


/**
 * 签名标签类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "sign_tag")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class SignTag  implements Serializable {
	private static final long serialVersionUID = 6728262319040054323L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "166", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "TAG_ID", length = 20)
	private Long tagId;// 标签主键
	@Column(name = "TAG_TITLE_ID", length = 20)
	private Long tagTitleId;// 管理主键
	@Column(name = "TAG_NAME", nullable = false, length = 45)
	private String tagName; // make/check/confirm/approve
	@Column(name = "TAG_USER_ID", length = 20)
	private Long tagUserId; // 指定签名人主键
	@Column(name = "TAG_ORDER", length = 4)
	private Byte tagOrder;// 签名顺序

	public SignTag() {

	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Long getTagTitleId() {
		return tagTitleId;
	}

	public void setTagTitleId(Long tagTitleId) {
		this.tagTitleId = tagTitleId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Long getTagUserId() {
		return tagUserId;
	}

	public void setTagUserId(Long tagUserId) {
		this.tagUserId = tagUserId;
	}

	public Byte getTagOrder() {
		return tagOrder;
	}

	public void setTagOrder(Byte tagOrder) {
		this.tagOrder = tagOrder;
	}

}
