package com.weaforce.system.entity.base;

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

@Entity
@Table(name = "base_person")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class Person extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -2162815609417906779L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "61", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "PERSON_ID", length = 20)
	private Long personId;
	@Column(name = "PERSON_IDENTITY", length = 45)
	private String personIdentity;
	@Column(name = "PERSON_NAME", length = 45)
	private String personName;
	@Column(name = "PERSON_SEX", length = 1)
	private String personSex;
	@Column(name = "PERSON_IS_ACTIVE", length = 1)
	private String personIsActive;

	public Person() {

	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getPersonIdentity() {
		return personIdentity;
	}

	public void setPersonIdentity(String personIdentity) {
		this.personIdentity = personIdentity;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonSex() {
		return personSex;
	}

	public void setPersonSex(String personSex) {
		this.personSex = personSex;
	}

	public String getPersonIsActive() {
		return personIsActive;
	}

	public void setPersonIsActive(String personIsActive) {
		this.personIsActive = personIsActive;
	}


}
