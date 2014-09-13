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

import com.weaforce.core.entity.PrimaryEntity;

/**
 * 签名设计管理类:同一个表单,针对不同部门,可能存在多个签名模板
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "sign_title")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class SignTitle extends PrimaryEntity implements Serializable{
	private static final long serialVersionUID = 7747644727421603284L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "165", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "TITLE_ID", length = 20)
	private Long titleId;
	//模块
	@Column(name = "TITLE_MODULE_ID", length = 20)
	private Long titleModuleId;
	//名称
	@Column(name = "TITLE_NAME", nullable = false, length = 45)
	private String titleName;

	public SignTitle() {

	}

	public Long getTitleId() {
		return titleId;
	}

	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}

	public Long getTitleModuleId() {
		return titleModuleId;
	}

	public void setTitleModuleId(Long titleModuleId) {
		this.titleModuleId = titleModuleId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

}
