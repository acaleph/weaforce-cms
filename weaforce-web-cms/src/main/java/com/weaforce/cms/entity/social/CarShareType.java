package com.weaforce.cms.entity.social;

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

@Entity
@Table(name = "cms_social_car_share_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class CarShareType implements Serializable{
	private static final long serialVersionUID = -4878357165672864865L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "180", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "TYPE_ID", length = 20)
	private Long typeId;
	@Column(name = "TYPE_NAME", length = 45)
	private String typeName;

	public CarShareType() {

	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
