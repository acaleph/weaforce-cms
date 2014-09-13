package com.weaforce.entity.ccm;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.entity.PrimaryEntity;

/**
 * 评分策略项目定义类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "co_strategy_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class StrategyItem extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -6704841047330807700L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "50", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "ITEM_ID", length = 20)
	private Long itemId;
	@Column(name = "ITEM_NAME", length = 45)
	private String itemName;
	@Column(name = "ITEM_IS_ACTIVE", length = 1)
	private String itemIsActive;
	@Column(name = "ITEM_IS_SYSTEM", length = 1)
	private String itemIsSystem;
	@Column(name = "ITEM_DESCRIPTION", length = 255)
	private String itemDescription;
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "detailItem", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<StrategyDetail> itemDetail = new ArrayList<StrategyDetail>();

	public StrategyItem() {
		itemIsActive = "1";
		itemIsSystem ="0";
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemIsActive() {
		return itemIsActive;
	}

	public void setItemIsActive(String itemIsActive) {
		this.itemIsActive = itemIsActive;
	}

	public String getItemIsSystem() {
		return itemIsSystem;
	}

	public void setItemIsSystem(String itemIsSystem) {
		this.itemIsSystem = itemIsSystem;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public List<StrategyDetail> getItemDetail() {
		return itemDetail;
	}

	public void setItemDetail(List<StrategyDetail> itemDetail) {
		this.itemDetail = itemDetail;
	}

}
