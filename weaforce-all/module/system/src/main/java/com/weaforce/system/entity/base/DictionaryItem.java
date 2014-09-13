package com.weaforce.system.entity.base;

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
@Table(name = "base_dictionary_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class DictionaryItem extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -3571289268824795658L;

	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "5", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "ITEM_ID", length = 20)
	private Long itemId;
	@Column(name = "ITEM_NAME", length = 45)
	private String itemName;
	@Column(name = "ITEM_ANNEXA", length = 75)
	private String itemAnnexa;
	@Column(name = "ITEM_ANNEXB", length = 75)
	private String itemAnnexb;
	@Column(name = "ITEM_ANNEXC", length = 75)
	private String itemAnnexc;
	@Column(name = "ITEM_ANNEXD", length = 75)
	private String itemAnnexd;
	@Column(name = "ITEM_ANNEXE", length = 75)
	private String itemAnnexe;
	@Column(name = "ITEM_CLIENT_ID", length = 10)
	private Long itemClientId;
	@Column(name = "ITEM_MODULE_ID", length = 20)
	private Long itemModuleId;
	@Column(name = "ITEM_IS_ACTIVE", length = 1)
	private String itemIsActive;
	// 字典
	@Column(name = "ITEM_DICTIONARY_ID", length = 20)
	private Long itemDictionaryId;
	@Column(name = "ITEM_GROUP_ORDER", length = 10)
	private Integer itemGroupOrder;
	@Column(name = "ITEM_ORGAN_ID", length = 20)
	private Long itemOrganId;
	@Column(name = "ITEM_REFE_ID", length = 10)
	private String itemRefeId;
	@Column(name = "ITEM_MODULE_ATTACHMENT", length = 1)
	private String itemModuleAttachment;

	public DictionaryItem() {

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

	public String getItemAnnexa() {
		return itemAnnexa;
	}

	public void setItemAnnexa(String itemAnnexa) {
		this.itemAnnexa = itemAnnexa;
	}

	public String getItemAnnexb() {
		return itemAnnexb;
	}

	public void setItemAnnexb(String itemAnnexb) {
		this.itemAnnexb = itemAnnexb;
	}

	public String getItemAnnexc() {
		return itemAnnexc;
	}

	public void setItemAnnexc(String itemAnnexc) {
		this.itemAnnexc = itemAnnexc;
	}

	public String getItemAnnexd() {
		return itemAnnexd;
	}

	public void setItemAnnexd(String itemAnnexd) {
		this.itemAnnexd = itemAnnexd;
	}

	public String getItemAnnexe() {
		return itemAnnexe;
	}

	public void setItemAnnexe(String itemAnnexe) {
		this.itemAnnexe = itemAnnexe;
	}

	public Long getItemClientId() {
		return itemClientId;
	}

	public void setItemClientId(Long itemClientId) {
		this.itemClientId = itemClientId;
	}

	public Long getItemModuleId() {
		return itemModuleId;
	}

	public void setItemModuleId(Long itemModuleId) {
		this.itemModuleId = itemModuleId;
	}

	public String getItemIsActive() {
		return itemIsActive;
	}

	public void setItemIsActive(String itemIsActive) {
		this.itemIsActive = itemIsActive;
	}

	public Long getItemDictionaryId() {
		return itemDictionaryId;
	}

	public void setItemDictionaryId(Long itemDictionaryId) {
		this.itemDictionaryId = itemDictionaryId;
	}

	public Integer getItemGroupOrder() {
		return itemGroupOrder;
	}

	public void setItemGroupOrder(Integer itemGroupOrder) {
		this.itemGroupOrder = itemGroupOrder;
	}

	public Long getItemOrganId() {
		return itemOrganId;
	}

	public void setItemOrganId(Long itemOrganId) {
		this.itemOrganId = itemOrganId;
	}

	public String getItemRefeId() {
		return itemRefeId;
	}

	public void setItemRefeId(String itemRefeId) {
		this.itemRefeId = itemRefeId;
	}

	public String getItemModuleAttachment() {
		return itemModuleAttachment;
	}

	public void setItemModuleAttachment(String itemModuleAttachment) {
		this.itemModuleAttachment = itemModuleAttachment;
	}

}
