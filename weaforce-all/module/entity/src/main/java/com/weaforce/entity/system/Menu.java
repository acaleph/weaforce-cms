package com.weaforce.entity.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

@Entity
@Table(name = "admin_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Menu implements Serializable {
	private static final long serialVersionUID = -5401583940412691656L;

	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "4", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "MENU_ID", length = 20)
	private Long menuId;
	// 组排序
	@Column(name = "MENU_GROUP_ORDER", length = 10)
	private int menuGroupOrder;
	// 中文名称
	@Column(name = "MENU_NAME_CN", length = 45)
	private String menuNameCn;
	// 图片
	@Column(name = "MENU_PIC", length = 45)
	private String menuPic;
	// URL
	@Column(name = "MENU_URL", length = 50)
	private String menuUrl;
	// 描述
	@Column(name = "MENU_DESCRIPTION", length = 255)
	private String menuDescription;
	// 父菜单
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MENU_FID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_MENU_FID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Menu menuParent;
	// 子菜单
	@OneToMany(mappedBy = "menuParent", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@org.hibernate.annotations.OrderBy(clause = "MENU_GROUP_ORDER asc")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Menu> menuChild = new ArrayList<Menu>();
	// Role
	@ManyToMany(mappedBy = "roleMenu", targetEntity = Role.class, fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Role> menuRole;

	public Menu() {
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuNameCn() {
		return menuNameCn;
	}

	public void setMenuNameCn(String menuNameCn) {
		this.menuNameCn = menuNameCn;
	}

	public String getMenuPic() {
		return this.menuPic;
	}

	public void setMenuPic(String menuPic) {
		this.menuPic = menuPic;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public int getMenuGroupOrder() {
		return menuGroupOrder;
	}

	public void setMenuGroupOrder(int menuGroupOrder) {
		this.menuGroupOrder = menuGroupOrder;
	}

	public String getMenuDescription() {
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}

	public Menu getMenuParent() {
		return menuParent;
	}

	public void setMenuParent(Menu menuParent) {
		this.menuParent = menuParent;
	}

	public List<Menu> getMenuChild() {
		return menuChild;
	}

	public void setMenuChild(List<Menu> menuChild) {
		this.menuChild = menuChild;
	}

	/**
	 * 加入子级的menu
	 * 
	 * @param menu
	 */
	public void addMenuChild(Menu menu) {
		if (menu == null)
			throw new IllegalArgumentException("Null child menu!");
		if (menu.getMenuParent() != null)
			menu.getMenuParent().getMenuChild().remove(menu);
		menu.setMenuParent(this.menuParent);
		this.menuChild.add(menu);
	}

	/**
	 * 移出子级menu
	 * 
	 * @param menu
	 */

	public void removeMenuChild(Menu menu) {
		if (menu == null)
			throw new IllegalArgumentException("Null child menu!");
		menu.setMenuParent(null);
		this.menuChild.remove(menu);
	}

	public Set<Role> getMenuRole() {
		return menuRole;
	}

	public void setMenuRole(Set<Role> menuRole) {
		this.menuRole = menuRole;
	}

	public List<Menu> getEntityChild() {
		return null;
	}

}