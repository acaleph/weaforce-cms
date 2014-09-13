package com.weaforce.entity.area;

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

/**
 * Address
 * 
 * @author Manfred
 * 
 */
@Entity
@Table(name = "area_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = true)
public class Address implements Serializable {
	private static final long serialVersionUID = 5459147452691196520L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "46", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "ADDRESS_ID", length = 20)
	private Long addressId;
	// Name
	@Column(name = "ADDRESS_NAME", length = 20)
	private String addressName;
	// Parent address
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_FID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADDRESS_FID")
	private Address addressParent;

	public Address() {

	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public Address getAddressParent() {
		return addressParent;
	}

	public void setAddressParent(Address addressParent) {
		this.addressParent = addressParent;
	}

}
