package com.weaforce.system.entity.help;

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

@Entity
@Table(name = "base_help")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class Help extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 5038947018639262617L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "38", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "HELP_ID", length = 20)
	private Long helpId;
	@Column(name = "HELP_MODULE_ID", length = 20)
	private Long helpModuleId;
	@Column(name = "HELP_ITEM_ID", length = 20)
	private Long helpItemId;
	@Column(name = "HELP_TITLE", length = 180)
	private String helpTitle;
	@Column(name = "HELP_PICTURE", length = 45)
	private String helpPicture;
	@Column(name = "HELP_INFO")
	private String helpInfo;

	public Help() {

	}

	public Long getHelpId() {
		return helpId;
	}

	public void setHelpId(Long helpId) {
		this.helpId = helpId;
	}

	public Long getHelpModuleId() {
		return helpModuleId;
	}

	public void setHelpModuleId(Long helpModuleId) {
		this.helpModuleId = helpModuleId;
	}

	public Long getHelpItemId() {
		return helpItemId;
	}

	public void setHelpItemId(Long helpItemId) {
		this.helpItemId = helpItemId;
	}

	public String getHelpTitle() {
		return helpTitle;
	}

	public void setHelpTitle(String helpTitle) {
		this.helpTitle = helpTitle;
	}

	public String getHelpPicture() {
		return helpPicture;
	}

	public void setHelpPicture(String helpPicture) {
		this.helpPicture = helpPicture;
	}

	public String getHelpInfo() {
		return helpInfo;
	}

	public void setHelpInfo(String helpInfo) {
		this.helpInfo = helpInfo;
	}

}
