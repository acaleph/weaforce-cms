package com.weaforce.cms.entity.social;

import java.io.Serializable;
import java.math.BigDecimal;

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

@Entity
@Table(name = "cms_social_car_bunker")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class CarBunker implements Serializable {
	private static final long serialVersionUID = -8448541426462655992L;

	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "183", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "BUNKER_ID", length = 20)
	private Long bunkerId;

	@Column(name = "BUNKER_PRICE", precision = 10, scale = 2)
	private BigDecimal bunkerPrice;
	@Column(name = "BUNKER_KM_START", precision = 10, scale = 2)
	private BigDecimal bunkerKmStart;
	@Column(name = "BUNKER_KM_END", precision = 10, scale = 2)
	private BigDecimal bunkerKmEnd;
	@Column(name = "BUNKER_DATE", length = 45)
	private Long bunkerDate;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "BUNKER_CAR_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_BUNKER_CAR_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Car bunkerCar;

	public CarBunker() {

	}

	public Long getBunkerId() {
		return bunkerId;
	}

	public void setBunkerId(Long bunkerId) {
		this.bunkerId = bunkerId;
	}

	public BigDecimal getBunkerPrice() {
		return bunkerPrice;
	}

	public void setBunkerPrice(BigDecimal bunkerPrice) {
		this.bunkerPrice = bunkerPrice;
	}

	public BigDecimal getBunkerKmStart() {
		return bunkerKmStart;
	}

	public void setBunkerKmStart(BigDecimal bunkerKmStart) {
		this.bunkerKmStart = bunkerKmStart;
	}

	public BigDecimal getBunkerKmEnd() {
		return bunkerKmEnd;
	}

	public void setBunkerKmEnd(BigDecimal bunkerKmEnd) {
		this.bunkerKmEnd = bunkerKmEnd;
	}

	public Long getBunkerDate() {
		return bunkerDate;
	}

	public void setBunkerDate(Long bunkerDate) {
		this.bunkerDate = bunkerDate;
	}

	public Car getBunkerCar() {
		return bunkerCar;
	}

	public void setBunkerCar(Car bunkerCar) {
		this.bunkerCar = bunkerCar;
	}

	

}
