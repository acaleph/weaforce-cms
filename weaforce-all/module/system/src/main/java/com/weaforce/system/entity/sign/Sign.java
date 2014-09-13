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
 * 签名记录类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "sign_sign")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Sign  implements Serializable {
	private static final long serialVersionUID = -4995069367609179831L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "167", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "SIGN_ID", length = 20)
	private Long signId; // 签名主键
	@Column(name = "SIGN_TAG_ID", length = 20)
	private Long signTagId;// 签名标签主键
	@Column(name = "SIGN_FORM_ID", length = 20)
	private Long signFormId; // 签名表单主键
	@Column(name = "SIGN_STATE", length = 1)
	private String signState; // 签名状态:没有执行(0)/签名(1)/拒绝(2)

	public Sign() {
		signState = "0";
	}

	public Long getSignId() {
		return signId;
	}

	public void setSignId(Long signId) {
		this.signId = signId;
	}

	public Long getSignTagId() {
		return signTagId;
	}

	public void setSignTagId(Long signTagId) {
		this.signTagId = signTagId;
	}

	public Long getSignFormId() {
		return signFormId;
	}

	public void setSignFormId(Long signFormId) {
		this.signFormId = signFormId;
	}

	public String getSignState() {
		return signState;
	}

	public void setSignState(String signState) {
		this.signState = signState;
	}

}
