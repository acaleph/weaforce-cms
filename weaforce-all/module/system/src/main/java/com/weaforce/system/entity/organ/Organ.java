package com.weaforce.system.entity.organ;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;

@Entity
@Table(name = "admin_organ")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class Organ extends PrimaryEntity implements Serializable {

	private static final long serialVersionUID = -1668942368155997424L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "43", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "ORGAN_ID", length = 20)
	private Long organId;
	@Column(name = "ORGAN_ADDRESS", length = 255)
	private String organAddress;
	@Column(name = "ORGAN_ADDRESS_MAIL", length = 90)
	private String organAddressMail;
	@Column(name = "ORGAN_ADDRESS_REGISTRY", length = 255)
	private String organAddressRegistry;
	@Column(name = "ORGAN_BANK", length = 255)
	private String organBank;
	@Column(name = "ORGAN_BANK_ACCOUNT", length = 45)
	private String organBankAccount;
	@Column(name = "ORGAN_CODE", length = 10)
	private String organCode;
	@Column(name = "ORGAN_EMAIL", length = 45)
	private String organEmail;
	@Column(name = "ORGAN_FAX", length = 45)
	private String organFax;
	@Column(name = "ORGAN_FULL_NAME", length = 255)
	private String organFullName;
	@Column(name = "ORGAN_MANAGER", length = 45)
	private String organManager;
	@Column(name = "ORGAN_MANAGER_CELLPHONE", length = 45)
	private String organManagerCellphone;
	@Column(name = "ORGAN_MANAGER_EMAIL", length = 45)
	private String organManagerEmail;
	@Column(name = "ORGAN_MANAGER_PHONE", length = 45)
	private String organManagerPhone;
	@Column(name = "ORGAN_NAME", length = 16)
	private String organName;
	@Column(name = "ORGAN_PHONE", length = 45)
	private String organPhone;
	@Column(name = "ORGAN_POSTCODE", length = 10)
	private String organPostcode;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGAN_FID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ORGAN_FID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Organ organParent;
	@OneToMany(mappedBy = "organParent", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@org.hibernate.annotations.OrderBy(clause = "ORGAN_NAME asc")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Organ> organChild = new ArrayList<Organ>();
	@Transient
	private String organFname;

	public Organ() {

	}

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public String getOrganAddress() {
		return organAddress;
	}

	public void setOrganAddress(String organAddress) {
		this.organAddress = organAddress;
	}

	public String getOrganAddressMail() {
		return organAddressMail;
	}

	public void setOrganAddressMail(String organAddressMail) {
		this.organAddressMail = organAddressMail;
	}

	public String getOrganAddressRegistry() {
		return organAddressRegistry;
	}

	public void setOrganAddressRegistry(String organAddressRegistry) {
		this.organAddressRegistry = organAddressRegistry;
	}

	public String getOrganBank() {
		return organBank;
	}

	public void setOrganBank(String organBank) {
		this.organBank = organBank;
	}

	public String getOrganBankAccount() {
		return organBankAccount;
	}

	public void setOrganBankAccount(String organBankAccount) {
		this.organBankAccount = organBankAccount;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getOrganEmail() {
		return organEmail;
	}

	public void setOrganEmail(String organEmail) {
		this.organEmail = organEmail;
	}

	public String getOrganFax() {
		return organFax;
	}

	public void setOrganFax(String organFax) {
		this.organFax = organFax;
	}

	public String getOrganFullName() {
		return organFullName;
	}

	public void setOrganFullName(String organFullName) {
		this.organFullName = organFullName;
	}

	public String getOrganManager() {
		return organManager;
	}

	public void setOrganManager(String organManager) {
		this.organManager = organManager;
	}

	public String getOrganManagerCellphone() {
		return organManagerCellphone;
	}

	public void setOrganManagerCellphone(String organManagerCellphone) {
		this.organManagerCellphone = organManagerCellphone;
	}

	public String getOrganManagerEmail() {
		return organManagerEmail;
	}

	public void setOrganManagerEmail(String organManagerEmail) {
		this.organManagerEmail = organManagerEmail;
	}

	public String getOrganManagerPhone() {
		return organManagerPhone;
	}

	public void setOrganManagerPhone(String organManagerPhone) {
		this.organManagerPhone = organManagerPhone;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getOrganPhone() {
		return organPhone;
	}

	public void setOrganPhone(String organPhone) {
		this.organPhone = organPhone;
	}

	public String getOrganPostcode() {
		return organPostcode;
	}

	public void setOrganPostcode(String organPostcode) {
		this.organPostcode = organPostcode;
	}

	public String getOrganFname() {
		return organFname;
	}

	public void setOrganFname(String organFname) {
		this.organFname = organFname;
	}

	public Organ getOrganParent() {
		return organParent;
	}

	public void setOrganParent(Organ organParent) {
		this.organParent = organParent;
	}

	public List<Organ> getOrganChild() {
		return organChild;
	}

	public void setOrganChild(List<Organ> organChild) {
		this.organChild = organChild;
	}

	public void addOrganChild(Organ organ) {
		if (organ == null)
			throw new IllegalArgumentException("Null child organ!");
		if (organ.getOrganParent() != null)
			organ.getOrganParent().getOrganChild().remove(organ);
		organ.setOrganParent(this.organParent);
		this.organChild.add(organ);
	}

	public void removeOrganChild(Organ organ) {
		if (organ == null)
			throw new IllegalArgumentException("Null child organ!");
		organ.setOrganParent(null);
		this.organChild.remove(organ);
	}
}
