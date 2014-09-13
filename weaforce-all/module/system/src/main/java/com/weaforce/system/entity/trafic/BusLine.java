package com.weaforce.system.entity.trafic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.entity.area.City;
import com.weaforce.entity.admin.User;

@Entity
@Table(name = "geo_bus_line")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class BusLine implements Serializable {
	private static final long serialVersionUID = -5124030558102727828L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "190", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "LINE_ID", length = 20)
	private Long lineId;
	// Line name
	@Column(name = "LINE_NAME", length = 10)
	private String lineName;
	// Line description
	@Column(name = "LINE_DESCRIPTION", length = 90)
	private String lineDescription;
	// Line city from
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "LINE_CITY_FROM_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_LINE_CITY_FROM_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private City lineCityFrom;
	// Line city to
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "LINE_CITY_TO_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_LINE_CITY_TO_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private City lineCityTo;
	// Line buses
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "busLine", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Bus> lineBus = new ArrayList<Bus>();

	public BusLine() {

	}

	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getLineDescription() {
		return lineDescription;
	}

	public void setLineDescription(String lineDescription) {
		this.lineDescription = lineDescription;
	}

	public City getLineCityFrom() {
		return lineCityFrom;
	}

	public void setLineCityFrom(City lineCityFrom) {
		this.lineCityFrom = lineCityFrom;
	}

	public City getLineCityTo() {
		return lineCityTo;
	}

	public void setLineCityTo(City lineCityTo) {
		this.lineCityTo = lineCityTo;
	}

	public List<Bus> getLineBus() {
		return lineBus;
	}

	public void setLineBus(List<Bus> lineBus) {
		this.lineBus = lineBus;
	}
}
