package com.weaforce.system.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.weaforce.core.util.BootStrap;
import com.weaforce.core.common.bean.Node;
import com.weaforce.core.common.bean.TreeNode;
import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.core.exception.ServiceException;
import com.weaforce.core.util.CollectionUtil;
import com.weaforce.core.util.DateUtil;
import com.weaforce.core.util.Global;
import com.weaforce.core.util.JsonBuilder;
import com.weaforce.core.util.MD5;
import com.weaforce.core.util.PageInfo;
import com.weaforce.core.util.StringUtil;
import com.weaforce.core.web.page.Page;
import com.weaforce.system.dao.IAuthorityDao;
import com.weaforce.system.dao.ICalendarEventDao;
import com.weaforce.system.dao.IMenuDao;
import com.weaforce.system.dao.IPersonDao;
import com.weaforce.system.dao.IResourceDao;
import com.weaforce.system.dao.IRoleDao;
import com.weaforce.system.dao.IUserDao;
import com.weaforce.system.dao.area.ICityDao;
import com.weaforce.system.dao.area.ICountryDao;
import com.weaforce.system.dao.area.IGardenDao;
import com.weaforce.system.dao.area.IProvinceDao;
import com.weaforce.system.dao.area.IZoneDao;
import com.weaforce.system.dao.base.IModuleDao;
import com.weaforce.system.dao.organ.IAccountDao;
import com.weaforce.system.dao.organ.IDepartmentDao;
import com.weaforce.system.dao.organ.IOrganDao;
import com.weaforce.system.dao.organ.IStaffDao;
import com.weaforce.entity.admin.Authority;
import com.weaforce.entity.admin.Resource;
import com.weaforce.entity.admin.Role;
import com.weaforce.entity.admin.User;
import com.weaforce.entity.app.Menu;
import com.weaforce.entity.area.City;
import com.weaforce.entity.area.Country;
import com.weaforce.system.entity.area.Garden;
import com.weaforce.entity.area.Province;
import com.weaforce.entity.area.Zone;
import com.weaforce.system.entity.base.CalendarEvent;
import com.weaforce.system.entity.base.Person;
import com.weaforce.system.entity.organ.Account;
import com.weaforce.system.entity.organ.Department;
import com.weaforce.system.entity.organ.Organ;
import com.weaforce.system.entity.organ.Staff;
import com.weaforce.system.service.ISystemService;
import com.weaforce.system.util.FileUtils;

@Service("systemService")
public class SystemService implements ISystemService {
	@Autowired
	@Qualifier("moduleDao")
	private IModuleDao moduleDao;
	@Autowired
	@Qualifier("menuDao")
	private IMenuDao menuDao;
	@Autowired
	@Qualifier("roleDao")
	private IRoleDao roleDao;
	@Autowired
	@Qualifier("userDao")
	protected IUserDao userDao;
	@Autowired
	@Qualifier("authorityDao")
	private IAuthorityDao authorityDao;
	@Autowired
	@Qualifier("resourceDao")
	private IResourceDao resourceDao;
	@Autowired
	@Qualifier("personDao")
	private IPersonDao personDao;
	@Autowired
	@Qualifier("calendarEventDao")
	private ICalendarEventDao eventDao;
	@Autowired
	@Qualifier("accountDao")
	private IAccountDao accountDao;
	@Autowired
	@Qualifier("organDao")
	private IOrganDao organDao;
	@Autowired
	@Qualifier("departmentDao")
	private IDepartmentDao departmentDao;
	@Autowired
	@Qualifier("staffDao")
	private IStaffDao staffDao;
	@Autowired
	@Qualifier("countryDao")
	private ICountryDao countryDao;
	@Autowired
	@Qualifier("provinceDao")
	private IProvinceDao provinceDao;
	@Autowired
	@Qualifier("cityDao")
	protected ICityDao cityDao;
	@Autowired
	@Qualifier("zoneDao")
	protected IZoneDao zoneDao;
	@Autowired
	@Qualifier("gardenDao")
	protected IGardenDao gardenDao;

	private static final String QUERY_MENU = " From Menu a ";
	private static final String QUERY_MENU_BY_PARENT = "From Menu a Where a.menuParent.menuId = ? ";
	private static final String QUERY_MENU_COUNT_BY_PARENT = "Select Count(*) From Menu a Where a.menuParent.menuId = ? ";

	public SystemService() {
	}

	public Menu getMenu(Long menuId) {
		return menuDao.loadEntity(menuId);
	}

	/**
	 * Build the menu bar
	 * 
	 * @param user
	 *            Current user
	 * @return
	 */
	public String getRoleMenu(User user) {
		Set<Menu> menuSet = user.getDefaultUserRole().getRoleMenu();
		StringBuffer dropdown = new StringBuffer();
		for (Menu m : menuSet) {
			// Has the menu items
			if (m.getMenuUrl() == null || m.getMenuUrl() == "") {
				List<Menu> menuChild = m.getMenuChild();
				// Leaf nodes
				if (menuChild.size() > 0
						&& menuChild.get(0).getMenuChild().size() == 0) {
					String dropdownMenuItems[] = new String[] {};
					dropdownMenuItems = getItems(menuChild);
					dropdown.append(BootStrap.getDropdownMenu(
							m.getMenuNameCn(), dropdownMenuItems));
				}
			}
		}
		return dropdown.toString();
	}

	/**
	 * Created the drop down menu HTML body
	 * 
	 * @param menuList
	 *            　Current user menu list
	 * @return
	 */
	public String[] getItems(List<Menu> menuList) {
		String url = "";
		String item = "";
		int length = menuList.size();
		String items[] = new String[length];
		for (int i = 0; i < menuList.size(); i++) {
			Menu o = menuList.get(i);
			url = o.getMenuUrl();
			item = o.getMenuNameCn();
			items[i] = BootStrap.getItem(url, item);
		}
		return items;
	}

	/**
	 * Convert the menu list to common node object list
	 * 
	 * @param menuList
	 *            Menu list
	 * @return
	 */
	public List<Node> getMenuNodeList(List<Menu> menuList, Set<Menu> roleMenus) {
		List<Node> nodeList = new ArrayList<Node>();
		for (final Menu m : menuList)
			nodeList.add(getMenuNode(m, roleMenus));
		return nodeList;
	}

	/**
	 * Convert menu to node
	 * 
	 * @param m
	 *            Menu instance
	 * @return
	 */
	public Node getMenuNode(Menu m, Set<Menu> roleMenus) {
		Node o = new Node();
		o.setId(m.getMenuId());
		o.setName(m.getMenuNameCn());
		if (m.getMenuChild().size() == 0) {
			o.setIsLeaf(true);
			// Show if the check box would be shown on the tree
			o.setChecked(roleMenus.contains(m));
		}
		o.setSeq(m.getMenuGroupOrder());
		o.setUrl(m.getMenuUrl() == null ? "#" : m.getMenuUrl());
		if (m.getMenuParent() == null)
			o.setPid(0L);
		else
			o.setPid(m.getMenuParent().getMenuId());
		return o;
	}

	/**
	 * Get simple menu array string
	 * 
	 * @param menuId
	 *            Root menu primary key
	 * @return
	 */
	public String getMenuArray(Long menuId, Set<Menu> roleMenus) {
		List<Menu> menuList = getMenuList();
		List<Node> nodeList = getMenuNodeList(menuList, roleMenus);
		return JsonBuilder.toJson(nodeList);
	}

	/**
	 * Set tree node children
	 * 
	 * @param o
	 *            Tree node instance
	 * @param list
	 *            Menu list
	 */
	public void setMenuTreeNodeChildren(TreeNode o, List<Menu> list,
			Set<Menu> roleMenus) {
		List<TreeNode> children = new ArrayList<TreeNode>();
		for (Menu m : list) {
			TreeNode n = new TreeNode();
			if (roleMenus.contains(m)) {
				setMenuTreeNode(m, n, roleMenus);
				children.add(n);
			}
		}
		o.setChildren(children);
	}

	/**
	 * Get the left menu
	 * 
	 * @param menuId
	 *            Top menu primary key
	 * @param user
	 *            Current user
	 * @return
	 */
	public String getLeftMenu(User user) {
		List<Menu> tempList = new ArrayList<Menu>();
		Set<Menu> roleMenus = user.getDefaultUserRole().getRoleMenu();
		// Get all the parent menu list of the leaf menu nodes
		for (final Menu m : roleMenus) {
			if (m.getMenuUrl() != null) {
				Menu parentMenu = m.getMenuParent();
				if (!tempList.contains(parentMenu))
					tempList.add(parentMenu);
			}
		}
		// Convert menu list to be a tree node list with only 2 levels
		List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
		for (final Menu m : tempList) {
			TreeNode o = new TreeNode();
			setMenuTreeNode(m, o, roleMenus);
			setMenuTreeNodeChildren(o, m.getMenuChild(), roleMenus);
			treeNodeList.add(o);
		}
		return BootStrap.getAccordion(treeNodeList, "leftMenu");
	}

	/**
	 * Convert menu to tree node
	 * 
	 * @param m
	 *            Menu instance
	 * @return
	 */
	public void setMenuTreeNode(Menu m, TreeNode o, Set<Menu> roleMenus) {
		o.setId(m.getMenuId());
		o.setName(m.getMenuNameCn());
		// The menu has been set to the current role
		if (m.getMenuChild().size() == 0)
			o.setChecked(roleMenus.contains(m));
		o.setSeq(m.getMenuGroupOrder());
		o.setUrl(m.getMenuUrl() == null ? "#" : m.getMenuUrl());
		o.setOption(m.getMenuIcon() == null ? "" : m.getMenuIcon());
		if (m.getMenuParent() == null)
			o.setPid(0L);
		else
			o.setPid(m.getMenuParent().getMenuId());
	}

	/**
	 * Get menu tree
	 * 
	 * @param menuId
	 *            Root menu id
	 * @return
	 */
	public String getMenuTree(Long menuId, User user) {
		Map<Long, List<TreeNode>> map = new HashMap<Long, List<TreeNode>>();
		List<Menu> menuList = getMenuListByParent(menuId, 0,
				new ArrayList<Menu>());
		Set<Menu> roleMenu = user.getDefaultUserRole().getRoleMenu();
		List<Node> nodeList = getMenuNodeList(menuList, roleMenu);
		JsonBuilder.setTreeNodeMap(map, nodeList, true);
		Menu o = getMenu(menuId);
		TreeNode root = new TreeNode(o.getMenuId(),
				o.getMenuParent() == null ? null : o.getMenuParent()
						.getMenuId(), o.getMenuNameCn());
		JsonBuilder.setTreeNode(map, root);
		List<TreeNode> children = new ArrayList<TreeNode>();
		children.add(root);
		return JsonBuilder.toJson(children);
	}

	/**
	 * Get the menu list by the parent menu primary key, a recursion method
	 * 
	 * @param menuId
	 *            Menu primary key
	 * @param currentIndex
	 *            Current index
	 * @param menuList
	 *            Menu list
	 * @return
	 */
	public List<Menu> getMenuListByParent(Long menuId, int currentIndex,
			List<Menu> menuList) {
		List<Menu> menuChildren = getMenu(menuId).getMenuChild();
		if (menuChildren.size() > 0)
			menuList.addAll(currentIndex, menuChildren);
		if (currentIndex < menuList.size()) {
			currentIndex++;
			return getMenuListByParent(menuList.get(currentIndex - 1)
					.getMenuId(), currentIndex, menuList);
		} else
			return menuList;
	}

	/**
	 * 保存菜单
	 * 
	 * @param o
	 *            菜单
	 * @param parentId
	 *            父菜单主键
	 * @return
	 */
	public Menu saveMenu(Menu o, Long parentId) {
		if ("".equals(o.getMenuUrl()))
			o.setMenuUrl(null);
		if (parentId != null) {
			Menu parent = menuDao.loadEntity(parentId);
			o.setMenuParent(parent);
			// parent.addMenuChild(o);
		}
		return menuDao.save(o);
	}

	/**
	 * 取得ul-li结构的菜单树
	 * 
	 * @param menuFid
	 *            父菜单
	 * @return
	 */

	public StringBuffer getMenuHtml(Long menuFid) {
		StringBuffer menuTree = new StringBuffer();
		List<Menu> list = menuDao.getMenuListByParent(menuFid);
		menuTree.append("<ul class=\"jqueryTree\" style=\"display: none;\"> \n");
		for (Menu o : list) {
			// System.out.println("son: " + o.getMenuChild().size());
			if (o.getMenuChild().size() <= 0)
				menuTree.append("<li class=\"isHref\" >");
			else
				menuTree.append("<li class='noHref collapsed'>");
			menuTree.append("<a href=\"#\" rel=\"" + o.getMenuId() + "\"> ");
			menuTree.append(o.getMenuNameCn() + "</a></li> \n");
		}
		menuTree.append("</ul>");
		return menuTree;
	}

	/**
	 * Get menu children JSON by parent
	 * 
	 * @param parentId
	 *            Parent primary key
	 * @return
	 */
	public StringBuffer getMenuChildrenJSONByParent(Long parentId) {
		StringBuffer sb = new StringBuffer();
		List<Menu> menuList = new ArrayList<Menu>();
		return menuDao.getMenuChildrenJSONByParent(parentId, 0, sb, menuList);
	}

	/**
	 * Get all menu list
	 * 
	 * @return
	 */
	public List<Menu> getMenuList() {
		return menuDao.listQuery(QUERY_MENU);
	}

	/**
	 * Set the menu to role by user login
	 * 
	 * @param menuId
	 *            Menu primary key
	 * @param userLogin
	 *            User login
	 */
	public void setMenuToRole(long menuId, String userLogin) {
		User user = getUserByLogin(userLogin);
		Role r = user.getDefaultUserRole();
		Set<Menu> roleMenu = r.getRoleMenu();
		Menu m = getMenu(menuId);
		if (m.getMenuUrl() != null) {
			if (roleMenu.contains(m))
				roleMenu.remove(m);
			else
				roleMenu.add(m);
			r.setRoleMenu(roleMenu);
			String leftMenu = getLeftMenu(user);
			r.setRoleMenuContent(leftMenu);
			roleDao.save(r);
		}
	}

	public void getMenuPage(Page<Menu> page, String menuName) {
		if (menuName == null)
			menuName = "'%'";
		else
			menuName = "'%" + menuName + "%'";
		menuDao.getPage(page,
				"Select Count(*) From Menu a Where a.menuNameCn like "
						+ menuName, "From Menu a Where menuNameCn like"
						+ menuName);
	}

	/**
	 * Get menu page by parent
	 * 
	 * @param parentId
	 *            Parent menu Id
	 * @param currentPage
	 *            Current page number
	 * @return
	 */
	public void getPageMenuByParent(Page<Menu> page, Long parentId) {
		menuDao.getPage(page, QUERY_MENU_COUNT_BY_PARENT, QUERY_MENU_BY_PARENT,
				parentId);
	}

	/**
	 * 取得用户角色相关ul-li结构的菜单树
	 * 
	 * @param menuFid
	 *            父菜单
	 * @param menuRank
	 *            有效菜单
	 * @return
	 */

	public StringBuffer getRoleMenuHtml(Long menuFid, String menuRank) {
		StringBuffer menuHtml = new StringBuffer();
		menuHtml.append("<ul class=\"jqueryTree\" style=\"display: none;\"> \n");
		List<Menu> list = getUserMenuListByRankFid(menuRank, menuFid);
		for (Menu o : list) {
			if (!"".equals(o.getMenuUrl()) && o.getMenuUrl().length() > 0)
				menuHtml.append("<li class='minus'>");
			else
				menuHtml.append("<li class='plus collapsed'>");
			menuHtml.append("<input type='checkbox' id='" + o.getMenuId()
					+ "' name='authority' value='" + o.getMenuNameCn()
					+ "'> </li> \n");
		}
		menuHtml.append("</ul>");
		return menuHtml;
	}

	public void deleteMenu(Long menuId) {
		menuDao.delete(menuId);
	}

	public List<Organ> getOrganList(String account) {
		return organDao.getOrganList(account);
	}

	public Role getRole(Long roleId) {
		return roleDao.loadEntity(roleId);
	}

	/**
	 * Save role instance
	 * 
	 * @param o
	 *            Role instance
	 * @return
	 */
	public Role saveRole(Role o) {
		return roleDao.save(o);
	}

	/**
	 * Save role and authorities
	 * 
	 * @param o
	 *            Role instance
	 * @param authorityIds
	 *            Authority IDs array
	 */
	public void saveRole(Role o, Long[] authorityIds) {
		Set<Authority> roleAuthority = new LinkedHashSet<Authority>();
		for (Long id : authorityIds)
			roleAuthority.add(authorityDao.getEntity(id));
		o.setRoleAuthority(roleAuthority);

	}

	public void deleteRole(Long roleId) {
		roleDao.delete(roleId);
	}

	/**
	 * 预处理用户
	 * 
	 * @param o
	 * @param userId
	 * @param defaultRoleId
	 * @param accountId
	 *            当从帐套管理中新增用户时，将帐套ID转为字符，写入到当前新增用户信息中
	 * @return
	 */

	public User prepareUser(User o, Long userId, Long defaultRoleId,
			Long accountId) {
		if (userId == null) {
			o = new User();
			if (defaultRoleId != null)
				o.setDefaultUserRole(roleDao.loadEntity(defaultRoleId));
			if (accountId != null) {
				o.setAccount(accountId.toString());
				// 默认需要部门信息
				o.setUserType("1");
			}
		} else
			o = userDao.loadEntity(userId);
		return o;
	}

	/**
	 * 验证登录是否存在
	 * 
	 * @param userId
	 *            用户主键
	 * @param userLogin
	 *            登录ID
	 * @return
	 */

	public String checkUserLogin(Long userId, String userLogin) {
		User o = getUserByLogin(userLogin);
		if (o == null)
			return "true";
		if (userId == null) {
			return "false";
		} else {
			if (userId.compareTo(o.getUserId()) == 0)
				return "true";
			else
				return "false";
		}
	}

	/**
	 * 判断用户登录是否已经存在
	 * 
	 * @param userLogin
	 *            用户登录ID
	 * @return String类型的布尔值
	 */

	public String checkBeingUserLogin(String userLogin) {
		return userDao.checkBeingUserLogin(userLogin);
	}

	/**
	 * Get user by primary key
	 * 
	 * @param userId
	 *            User primary key
	 * @return
	 */
	public User getUser(long userId) {
		return userDao.getEntity(userId);
	}

	/**
	 * 取得用户
	 * 
	 * @param userLogin
	 *            登录ID
	 * @return
	 */

	public User getUserByLogin(String userLogin) {
		return userDao.loadEntity("userLogin", userLogin);
	}

	/**
	 * Get user login string list by user login
	 * 
	 * @param queryLogin
	 *            User login query string
	 * @return
	 * @throws InterruptedException
	 */
	public List<String> getUserLoginListByLogin(String queryLogin)
			throws InterruptedException {
		List<User> userList = userDao.getUserListByLogin(queryLogin);
		// List<User> userList = userDao.getUserListByAccount("100");
		List<String> loginList = new ArrayList<String>();
		for (User o : userList)
			loginList.add(o.getUserLogin());
		return loginList;
	}

	/**
	 * 取得User list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */

	public List<User> getUserListByAccount(String account) {
		return userDao.getUserListByAccount(account);
	}

	/**
	 * 取得用户登录ID的JSON格式字串
	 * 
	 * @param account
	 *            当前帐套
	 * @return
	 */
	public String getUserLoginJSON(String account) {
		return userDao.getUserLoginJSON(account);
	}

	/**
	 * 取得用户 page
	 * 
	 * @param pageInfo
	 * @param account
	 * @param logicDelete
	 *            逻辑删除
	 * @param userQueryNameEn
	 *            英文名称
	 * @param userQueryNameCn
	 *            中文名称
	 * @param queryRoleId
	 *            角色
	 * @return
	 */

	public void getUserPage(Page<User> page, String account,
			String logicDelete, String userQueryNameEn, String userQueryNameCn,
			Long queryRoleId, String queryLogicLock) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From User a Where a.account ='" + account + "' ");
		if (StringUtils.isNotEmpty(logicDelete))
			sb.append(" And a.logicDelete ='" + logicDelete + "' ");
		if (StringUtils.isNotEmpty(userQueryNameEn))
			sb.append(" And a.userNameEn like " + "'%" + userQueryNameEn + "%'");
		if (StringUtils.isNotEmpty(userQueryNameCn))
			sb.append(" And a.userNameCn like " + "'%" + userQueryNameCn
					+ "%' ");
		if (queryRoleId != null)
			sb.append(" And a.defaultUserRole.roleId = " + queryRoleId);
		if (StringUtils.isNotEmpty(queryLogicLock))
			sb.append(" And a.logicLock='" + queryLogicLock + "' ");
		else
			sb.append(" And a.logicLock='0' ");
		userDao.getPage(page, "Select Count(*)" + sb.toString(), sb.toString()
				+ " Order by a.userNameEn ");
	}

	/**
	 * 取得有效的role list
	 * 
	 * @param account
	 *            帐套
	 * @param isActive
	 *            是否有效
	 * @return
	 */

	public List<Role> getRoleList(String account, String isActive) {
		return roleDao.getRoleListByActive(account, isActive);
	}

	/**
	 * Get role list
	 * 
	 * @param user
	 *            Edit user
	 * @param account
	 *            Account
	 * @param isActive
	 *            If is active
	 * @return
	 */
	public List<Role> getRoleList(User user, String account, String isActive) {
		List<Role> roleList = roleDao.getRoleListByActive(account, isActive);
		Role o = user.getDefaultUserRole();
		if (o != null) {
			roleList.remove(o);
			o.setSelected(true);
			roleList.add(o);
		}
		// Set checked option
		Set<Role> userRole = user.getUserRole();
		for (Role r : roleList)
			r.setChecked(userRole.contains(r));
		return roleList;
	}

	/**
	 * 取得 role page
	 * 
	 * @param pageInfo
	 * @param account
	 * @param roleQueryName
	 * @param isActive
	 * @return
	 */

	public void getRolePage(Page<Role> page, String account, String queryName,
			String isActive) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Role a Where (a.account ='" + account
				+ "' or a.roleIsSystem='1') ");
		if (StringUtil.isNotEmpty(queryName))
			sb.append(" And a.roleName like " + "'%" + queryName + "%'");
		if (StringUtil.isNotEmpty(isActive))
			sb.append(" And a.roleIsActive=" + isActive);
		roleDao.getPage(page, "Select Count(*)" + sb.toString(), sb.toString()
				+ " Order by a.roleName ");
	}

	/**
	 * Save user
	 * 
	 * @param o
	 *            用户
	 * @param roleId
	 *            默认角色
	 * @param checkedRoleIds
	 *            角色列表
	 * @param cityId
	 *            City primary key
	 * @param zoneId
	 *            Zone primary key
	 * @return
	 * @throws ServiceException
	 * @throws Exception
	 */
	public User saveUser(User user, User o, Long roleId,
			List<Long> checkedRoleIds, Long cityId, Long zoneId)
			throws Exception {
		if (roleId != null)
			o.setDefaultUserRole(roleDao.loadEntity(roleId));
		o.setUserPwd(MD5.toMD5(o.getUserPassword()));
		o.setUserLogin(o.getUserLogin().toUpperCase());
		if (checkedRoleIds.indexOf(roleId) == -1)
			checkedRoleIds.add(roleId);
		CollectionUtil.mergeByCheckedIds(o.getUserRole(), checkedRoleIds,
				Role.class, "roleId");

		if (cityId != null)
			o.setUserCity(cityDao.loadEntity(cityId));
		else
			o.setUserCity(null);
		if (zoneId != null)
			o.setUserZone(zoneDao.loadEntity(zoneId));
		else
			o.setUserZone(null);
		setPrimaryEntity(user, o);
		o = userDao.save(o);
		// Ready for images server location
		FileUtils.checkAndCreateDir(Global.IMAGE_SERVER_PATH + "/"
				+ o.getUserId());
		// Resized image file location, would be hidden to user
		FileUtils.checkAndCreateDir(Global.IMAGE_SERVER_PATH_RESIZE + "/"
				+ o.getUserId());
		// Image files
		FileUtils.checkAndCreateDir(Global.IMAGE_SERVER_PATH + "/"
				+ o.getUserId() + "/image");
		// Normal files
		FileUtils.checkAndCreateDir(Global.IMAGE_SERVER_PATH + "/"
				+ o.getUserId() + "/file");
		return o;
	}

	public void saveUser(User user, User o, Long defaultRoleId,
			List<Long> checkedRoleIds) throws Exception {
		setPrimaryEntity(user, o);
		if (defaultRoleId != null)
			o.setDefaultUserRole(roleDao.loadEntity(defaultRoleId));
		o.setUserPwd(MD5.toMD5(o.getUserPassword()));
		o.setUserLogin(o.getUserLogin().toUpperCase());
		if (checkedRoleIds.indexOf(defaultRoleId) == -1)
			checkedRoleIds.add(defaultRoleId);
		CollectionUtil.mergeByCheckedIds(o.getUserRole(), checkedRoleIds,
				Role.class, "roleId");
		o = userDao.save(o);
		// Ready for images server location
		FileUtils.checkAndCreateDir(Global.IMAGE_SERVER_PATH + "/"
				+ o.getUserId());
		FileUtils.checkAndCreateDir(Global.IMAGE_SERVER_PATH_RESIZE + "/"
				+ o.getUserId());

	}

	/**
	 * 保存用户
	 * 
	 * @param user
	 *            用户
	 * @return
	 * @throws ServiceException
	 */
	public User saveUser(User user) throws ServiceException {
		return userDao.save(user);
	}

	public void deleteUser(Long userId) {
		userDao.delete(userId);
	}

	/**
	 * 取得用户,通过登录ID和密码
	 * 
	 * @param login
	 *            登录ID
	 * @param password
	 *            密码
	 * @return
	 * @throws ServiceException
	 */

	public User getUserByLoginPassword(String login, String password)
			throws ServiceException {
		return userDao.getUserByLoginPassword(login, password);
	}

	/**
	 * 取得用户JSON格式menu
	 * 
	 * @param userLogin
	 *            当前用户
	 * @return
	 */

	public StringBuffer getUserMenu(User user) {
		StringBuffer sb = new StringBuffer("[");
		Set<Menu> menuSet = user.getDefaultUserRole().getRoleMenu();
		for (Menu m : menuSet) {
			String url = "";
			if (m.getMenuUrl() != null)
				url = "/"
						+ m.getMenuUrl().substring(0,
								m.getMenuUrl().lastIndexOf(".") + 1) + "action";
			String menuFid = "0";
			if (m.getMenuParent() != null)
				menuFid = m.getMenuParent().getMenuId().toString();
			sb.append("{\"menuId\":\"" + m.getMenuId() + "\",\"menuFid\":\""
					+ menuFid + "\",\"menuName\":\"" + m.getMenuNameCn()
					+ "\",\"menuUrl\":\"" + url + "\"},");
		}
		sb = new StringBuffer(StringUtil.cutLastChar(sb.toString(), ","));
		sb.append("]");
		return sb;
	}

	public List<Menu> getMenuListByParent(Long menuFid) {
		return menuDao.getMenuListByParent(menuFid);
	}

	public List<Menu> getUserMenuListByRankFid(String roleMenuRank, Long menuFid) {
		return menuDao.getMenuListByRankFid(roleMenuRank, menuFid);
	}

	/**
	 * 预处理授权
	 * 
	 * @param o
	 * @param authorityId
	 * @return
	 */

	public Authority prepareAuthority(Authority o, Long authorityId) {
		if (authorityId == null)
			o = new Authority();
		else
			o = authorityDao.loadEntity(authorityId);
		return o;
	}

	public Authority getAuthority(Long authorityId) {
		return authorityDao.loadEntity(authorityId);
	}

	public Authority saveAuthority(Authority o) {
		return authorityDao.save(o);
	}

	/**
	 * 取得授权list，提供role进行授权分配
	 * 
	 * @param account
	 * @return
	 */

	public List<Authority> getAuthorityList() {
		return authorityDao.getAuthorityList();
	}

	/**
	 * Deal with the checked authority list
	 * 
	 * @param roleAuthority
	 *            Has been set to the role
	 * @return
	 */
	public List<Authority> getAuthorityList(Set<Authority> roleAuthority) {
		List<Authority> allList = authorityDao.getAuthorityList();
		for (Authority o : allList)
			o.setChecked(roleAuthority.contains(o));
		return allList;
	}

	/**
	 * 根据系统,取得授权list
	 * 
	 * @param businessId
	 *            系统
	 * @return
	 */

	public List<Authority> getAuthorityListByBusiness(Long businessId) {
		return authorityDao.getAuthorityListByBusiness(businessId);
	}

	/**
	 * 分配授权给角色
	 * 
	 * @param authorityId
	 *            授权
	 * @param roleId
	 *            角色
	 */

	public void checkedAuthority(Long authorityId, Long roleId) {
		if (authorityId != null && roleId != null) {
			Role role = roleDao.loadEntity(roleId);
			Set<Authority> authoritySet = role.getRoleAuthority();
			Authority authority = authorityDao.loadEntity(authorityId);
			if (!authoritySet.contains(authority)) {
				authoritySet.add(authority);
				roleDao.save(role);
			}
		}
	}

	/**
	 * 解除角色授权
	 * 
	 * @param authorityId
	 *            授权
	 * @param roleId
	 *            角色
	 */
	public void uncheckAuthority(Long authorityId, Long roleId) {
		if (authorityId != null && roleId != null) {
			Role role = roleDao.loadEntity(roleId);
			Set<Authority> authoritySet = role.getRoleAuthority();
			Authority authority = authorityDao.loadEntity(authorityId);
			if (authoritySet.contains(authority)) {
				authoritySet.remove(authority);
				roleDao.save(role);
			}
		}
	}

	/**
	 * 根据系统,取得授权JSON数据
	 * 
	 * @param businessId
	 *            系统
	 * @return
	 */

	public String getAuthorityDDL(Long businessId) {
		StringBuffer sb = new StringBuffer("[");
		List<Authority> authorityList = authorityDao
				.getAuthorityListByBusiness(businessId);
		for (Authority o : authorityList)
			sb.append("{\"value\":\"" + o.getAuthorityId()
					+ "\",\"caption\":\"" + o.getAuthorityCode() + "\"},");
		sb = new StringBuffer(StringUtil.cutLastChar(sb.toString(), ","));
		sb.append("]");
		return sb.toString();
	}

	/**
	 * 取得authority page
	 * 
	 * @param pageInfo
	 * @param authorityCode
	 *            代码 ROLE_*
	 * @param businessId
	 *            系统
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageInfo<Authority> getAuthorityPage(PageInfo<Authority> pageInfo,
			String authorityCode, Long businessId, Long roleId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Authority a ");
		if (businessId != null) {
			sb.append(" Where a.authorityBusiness.businessId=" + businessId);
			if (StringUtil.isNotEmpty(authorityCode))
				sb.append(" And a.authorityCode like '%" + authorityCode + "%'");
		} else {
			if (StringUtil.isNotEmpty(authorityCode))
				sb.append(" Whree a.authorityCode like '%" + authorityCode
						+ "%'");
		}
		pageInfo = authorityDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.authorityCode ");
		List<Authority> authorityList = (List<Authority>) pageInfo.getResult();
		Role role = roleDao.loadEntity(roleId);
		if (role != null)
			for (Authority a : role.getRoleAuthority())
				if (authorityList.indexOf(a) != -1)
					a.setChecked(true);
		return pageInfo;
	}

	/**
	 * 预处理资源
	 * 
	 * @param o
	 * @param resourceId
	 * @return
	 */

	public Resource prepareResource(Resource o, Long resourceId) {
		if (resourceId == null)
			o = new Resource();
		else
			o = resourceDao.loadEntity(resourceId);
		return o;
	}

	public Resource getResource(Long resourceId) {
		return resourceDao.getEntity(resourceId);
	}

	public void deleteResource(Resource o) {
		resourceDao.delete(o);
	}

	/**
	 * 保存资源
	 * 
	 * @param o
	 * @param moduleId
	 *            模块
	 * @return
	 */
	public Resource saveResource(Resource o, Long moduleId) {
		if (moduleId != null)
			o.setResourceModule(moduleDao.loadEntity(moduleId));
		else
			o.setResourceModule(null);
		return resourceDao.save(o);
	}

	/**
	 * 根据模块取得资源list
	 * 
	 * @param moduleId
	 *            模块
	 * @return
	 */

	public List<Resource> getResourceListByModule(Long moduleId,
			Long authorityId) {
		List<Resource> resourceList = resourceDao
				.getResourceListByModule(moduleId);
		// 设置是否选中授权
		if (authorityId != null)
			for (Resource r : resourceList) {
				Set<Authority> authoritySet = r.getResourceAuthority();
				Authority a = authorityDao.loadEntity(authorityId);
				if (authoritySet.contains(a))
					r.setChecked("1");
			}
		return resourceList;
	}

	/**
	 * 绑定授权资源
	 * 
	 * @param resourceId
	 *            资源 id
	 * @param authorityId
	 *            授权id
	 */

	public void checkedResource(Long resourceId, Long authorityId) {
		if (resourceId != null && authorityId != null) {
			Resource resource = resourceDao.loadEntity(resourceId);
			Set<Authority> authoritySet = resource.getResourceAuthority();
			Authority a = authorityDao.loadEntity(authorityId);
			if (!authoritySet.contains(a)) {
				authoritySet.add(a);
				resourceDao.save(resource);
			}
		}
	}

	/**
	 * 取消绑定授权资源
	 * 
	 * @param resourceId
	 *            资源 id
	 * @param authorityId
	 *            授权id
	 */
	public void uncheckResource(Long resourceId, Long authorityId) {
		if (resourceId != null && authorityId != null) {
			Resource resource = resourceDao.loadEntity(resourceId);
			Set<Authority> authoritySet = resource.getResourceAuthority();
			Authority a = authorityDao.loadEntity(authorityId);
			if (authoritySet.contains(a)) {
				authoritySet.remove(a);
				resourceDao.save(resource);
			}
		}
	}

	public Person getPerson(Long personId) {
		return personDao.loadEntity(personId);
	}

	public void deletePerson(Long personId) {
		personDao.delete(personId);
	}

	public List<Person> getPersonList(String account) {
		return personDao.getPersonList(account);
	}

	public PageInfo<Person> getPersonPage(PageInfo<Person> pageInfo,
			String account) {
		return pageInfo;
	}

	public Person savePerson(Person o) {
		return personDao.save(o);
	}

	/**
	 * 取得日历事件
	 * 
	 * @param eventId
	 * @return
	 */

	public CalendarEvent getEvent(Long eventId) {
		return eventDao.loadEntity(eventId);
	}

	/**
	 * 取得当前用户某个日期的事件
	 * 
	 * @param uesrLogin
	 *            当前用户
	 * @param time
	 *            日期
	 * @return
	 */

	public CalendarEvent getEventByLoginTime(String userLogin, Long time) {
		return eventDao.getEventByLoginTime(userLogin, time);
	}

	/**
	 * 保存日历事件
	 * 
	 * @param o
	 * @return
	 */
	public CalendarEvent saveEvent(CalendarEvent o) {
		return eventDao.save(o);
	}

	/**
	 * 删除日历事件
	 * 
	 * @param eventId
	 */
	public void deleteEvent(Long eventId) {
		eventDao.delete(eventId);
	}

	/**
	 * 以JSON格式取日历事件
	 * 
	 * @param userLogin
	 *            当前用户
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return
	 */

	public StringBuffer getCalendarEventJSON(String userLogin, int year,
			int month) {
		StringBuffer sb = new StringBuffer();
		int[][] cals = DateUtil.getCalendarArray(year, month);
		int row = cals.length; // 行长度
		StringBuffer cellTemplate = new StringBuffer(
				"[{\"day\":\"${day}\",\"utc\":\"${utc}\",\"class\":\"${class}\",\"event\":\"${event}\"}]");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		Long todayUTC = DateUtil.getUTCDate(DateUtil.defaultFormat(new Date(cal
				.getTimeInMillis())));
		for (int i = 0; i < row; i++) {
			if (sb.length() == 0)
				sb.append("[{");
			else
				sb.append(",{");
			// 列长度一定为 7
			for (int j = 0; j < 7; j++) {
				int day = cals[i][j];
				if (day != 0) {
					cal.set(Calendar.DAY_OF_MONTH, day);
					Long dayUTC = DateUtil.getUTCDate(DateUtil
							.defaultFormat(new Date(cal.getTimeInMillis())));
					cellTemplate.replace(cellTemplate.indexOf("${utc}"),
							cellTemplate.indexOf("${utc}") + "${utc}".length(),
							dayUTC.toString());
					// 判断当前日期是否有备忘录
					if (eventDao.getEventByLoginTime(userLogin, dayUTC) != null) {
						cellTemplate.replace(
								cellTemplate.indexOf("${event}"),
								cellTemplate.indexOf("${event}")
										+ "${event}".length(), "yes");
						if (todayUTC.toString().equals(dayUTC.toString()))
							cellTemplate.replace(
									cellTemplate.indexOf("${class}"),
									cellTemplate.indexOf("${class}")
											+ "${class}".length(),
									"mydate_aday mydate_eday");
						else
							cellTemplate.replace(
									cellTemplate.indexOf("${class}"),
									cellTemplate.indexOf("${class}")
											+ "${class}".length(),
									"mydate_eday");
					} else {
						cellTemplate.replace(
								cellTemplate.indexOf("${event}"),
								cellTemplate.indexOf("${event}")
										+ "${event}".length(), "no");
						if (todayUTC.toString().equals(dayUTC.toString()))
							cellTemplate.replace(
									cellTemplate.indexOf("${class}"),
									cellTemplate.indexOf("${class}")
											+ "${class}".length(),
									"mydate_aday");
						else
							cellTemplate
									.replace(cellTemplate.indexOf("${class}"),
											cellTemplate.indexOf("${class}")
													+ "${class}".length(),
											"mydate_day");
					}
					cellTemplate.replace(cellTemplate.indexOf("${day}"),
							cellTemplate.indexOf("${day}") + "${day}".length(),
							String.valueOf(day));
				} else {
					cellTemplate.replace(
							cellTemplate.indexOf("${class}"),
							cellTemplate.indexOf("${class}")
									+ "${class}".length(), "mydate_day");
					cellTemplate.replace(cellTemplate.indexOf("${utc}"),
							cellTemplate.indexOf("${utc}") + "${utc}".length(),
							"*");
					cellTemplate.replace(cellTemplate.indexOf("${day}"),
							cellTemplate.indexOf("${day}") + "${day}".length(),
							"*");
					cellTemplate.replace(
							cellTemplate.indexOf("${event}"),
							cellTemplate.indexOf("${event}")
									+ "${event}".length(), "no");
				}
				// week day
				switch (j) {
				case 0:
					sb.append("\"sun\":" + cellTemplate.toString());
					break;
				case 1:
					sb.append(",\"mon\":" + cellTemplate.toString());
					break;
				case 2:
					sb.append(",\"tue\":" + cellTemplate.toString());
					break;
				case 3:
					sb.append(",\"wed\":" + cellTemplate.toString());
					break;
				case 4:
					sb.append(",\"thu\":" + cellTemplate.toString());
					break;
				case 5:
					sb.append(",\"fri\":" + cellTemplate.toString());
					break;
				case 6:
					sb.append(",\"sat\":" + cellTemplate.toString());
					break;
				}
				cellTemplate = new StringBuffer(
						"[{\"day\":\"${day}\",\"utc\":\"${utc}\",\"class\":\"${class}\",\"event\":\"${event}\"}]");
			}
			sb.append("}");
		}
		sb.append("]");
		return sb;
	}

	/**
	 * 预处理帐套
	 * 
	 * @param o
	 * @param accountId
	 * @return
	 */

	public Account prepareAccount(Account o, Long accountId) {
		if (accountId == null)
			o = new Account();
		else
			o = accountDao.loadEntity(accountId);
		return o;
	}

	/**
	 * 验证account是否存在
	 * 
	 * @param accountId
	 *            帐套主键
	 * @param propertyName
	 *            属性名称
	 * @param propertyValue
	 *            属性值
	 * @return
	 */

	public String checkAccount(Long accountId, String propertyName,
			String propertyValue) {
		Account o = accountDao.loadEntity(propertyName, propertyValue);
		if (o == null)
			return "true";
		if (accountId == null)
			return "false";
		else {
			if (accountId.compareTo(o.getAccountId()) == 0)
				return "true";
			else
				return "false";
		}
	}

	public Account getAccount(Long accountId) {
		return accountDao.loadEntity(accountId);
	}

	public void deleteAccount(Long accountId) {
		accountDao.delete(accountId);
	}

	public PageInfo<Account> getAccountPage(PageInfo<Account> pageInfo,
			String accountNameCn, String accountNameEn, String accountShortName) {
		StringBuffer sb = new StringBuffer("From Account a");
		if (accountNameCn != null && !"".equals(accountNameCn))
			if ("From Account a".equals(sb.toString()))
				sb.append(" Where a.accountNameCn like '%" + accountNameCn
						+ "%'");
			else
				sb.append(" And a.accountNameCn like '%" + accountNameCn + "%'");
		if (accountNameEn != null && !"".equals(accountNameEn))
			if ("From Account a".equals(sb.toString()))
				sb.append(" Where a.accountNameEn like '%" + accountNameEn
						+ "%'");
			else
				sb.append(" And a.accountNameEn like '%" + accountNameEn + "%'");
		if (accountShortName != null && !"".equals(accountShortName))
			if ("From Account a".equals(sb.toString()))
				sb.append(" Where a.accountShortName like '%"
						+ accountShortName + "%'");
			else
				sb.append(" And a.accountShortName like '%" + accountShortName
						+ "%'");
		System.out.println("pageInfo: " + pageInfo.getCurPage());
		pageInfo = accountDao.listQuery(pageInfo,
				"Select Count(*) " + sb.toString(), sb.toString()
						+ " Order by a.accountShortName ");
		return pageInfo;
	}

	/**
	 * 保存帐套
	 * 
	 * @param o
	 * @return
	 */
	public Account saveAccount(Account o) {
		o.setAccountEmail(o.getAccountEmail().toUpperCase());
		return accountDao.save(o);
	}

	public List<Account> getAccountListByScore(String isScore) {
		return accountDao.getAccountListByScore(isScore);
	}

	public Organ getOrgan(Long organId) {
		return organDao.loadEntity(organId);
	}

	public void deleteOrgan(Long organId) {
		organDao.delete(organId);
	}

	public void deleteOrgan(Organ o) {
		organDao.delete(o);
	}

	public Organ saveOrgan(Organ o) {
		return organDao.save(o);
	}

	public List<Organ> getOrganListByParent(String account, Long parentId) {
		return organDao.getOrganListByParent(account, parentId);
	}

	public PageInfo<Organ> getOrganPage(PageInfo<Organ> pageInfo,
			String account, String queryOrganName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Organ a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(queryOrganName)) {
			sb.append(" And a.organName like " + "'%" + queryOrganName + "%'");
		}
		pageInfo = organDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.organName ");
		return pageInfo;
	}

	/**
	 * 预处理部门
	 * 
	 * @param o
	 * @param departmentId
	 * @param accountId
	 *            当从帐套管理中新增部门时，将帐套ID转为字符，写入到当前新增用户信息中
	 * @return
	 */

	public Department prepareDepartment(Department o, Long departmentId,
			Long accountId) {
		if (departmentId == null) {
			o = new Department();
			if (accountId != null)
				o.setAccount(accountId.toString());
		} else
			o = departmentDao.loadEntity(departmentId);
		return o;
	}

	public Department getDepartment(Long departmentId) {
		return departmentDao.loadEntity(departmentId);
	}

	public void deleteDepartment(Long departmentId) {
		departmentDao.delete(departmentId);
	}

	public PageInfo<Department> getDepartmentPage(
			PageInfo<Department> pageInfo, String account,
			String departmentQueryName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Department a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(departmentQueryName)) {
			sb.append(" And a.departmentName like " + "'%"
					+ departmentQueryName + "%'");
		}
		pageInfo = departmentDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.departmentName ");
		return pageInfo;
	}

	/**
	 * 取得活动部门list
	 * 
	 * @param account
	 *            帐套
	 * @param isActive
	 *            活动
	 * @param flag
	 *            是否增加ALL选项
	 * @return
	 */

	public List<Department> getDepartmentListByActive(String account,
			String isActive, boolean flag) {
		return departmentDao.getDepartmentListByActive(account, isActive, flag);
	}

	/**
	 * 根据userLogin获取,config 中的部门列表
	 * 
	 * @param departmentList
	 * @param account
	 * @param userLogin
	 * @return
	 */

	public List<Department> getDepartmentListByLogin(
			List<Department> departmentList, String account, String userLogin) {
		if (departmentList == null)
			departmentList = new ArrayList<Department>();
		Staff config = staffDao.loadEntity("staffLogin", userLogin);
		return departmentList = config.getConfigDepartment();
	}

	public Department saveDepartment(Department o) {
		return departmentDao.save(o);
	}

	public Staff getStaff(Long staffId) {
		return staffDao.loadEntity(staffId);
	}

	/**
	 * 预处理部门职员
	 * 
	 * @param o
	 * @param staffId
	 * @param accountId
	 * @param departmentId
	 * @return
	 */

	public Staff prepareStaff(Staff o, Long staffId, Long departmentId,
			Long accountId) {
		if (staffId == null) {
			o = new Staff();
			if (departmentId != null)
				o.setStaffDepartment(departmentDao.loadEntity(departmentId));
			if (accountId != null)
				o.setAccount(accountId.toString());
		} else
			o = staffDao.loadEntity(staffId);
		return o;
	}

	public Staff getStaffByLogin(String account, String staffLogin) {
		return staffDao.getStaffByLogin(account, staffLogin);
	}

	public void deleteStaff(Long staffId) {
		staffDao.delete(staffId);
	}

	/**
	 * 取得部门成员
	 * 
	 * @param pageInfo
	 * @param account
	 *            帐套
	 * @param queryStaffName
	 *            成员名称
	 * @param queryStaffLogin
	 *            登录ID
	 * @param queryDepartmentId
	 *            部门ID
	 * @param queryLogicLock
	 *            锁
	 * @return
	 */

	public PageInfo<Staff> getStaffPage(PageInfo<Staff> pageInfo,
			String account, String queryStaffName, String queryStaffLogin,
			Long queryDepartmentId, String queryLogicLock) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Staff a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(queryStaffName)) {
			sb.append(" And a.staffName like " + "'%" + queryStaffName + "%'");
		}
		if (StringUtil.isNotEmpty(queryStaffLogin)) {
			sb.append(" And a.staffLogin like " + "'%" + queryStaffLogin + "%'");
		}
		if (queryDepartmentId != null)
			sb.append(" And a.staffDepartment.departmentId = "
					+ queryDepartmentId);
		if (StringUtil.isNotEmpty(queryLogicLock))
			sb.append(" And a.logicLock='" + queryLogicLock + "' ");
		else
			sb.append(" And a.logicLock = '0' ");
		pageInfo = staffDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.staffLogin ");
		return pageInfo;
	}

	/**
	 * 取得部门成员列表
	 * 
	 * @param departmentId
	 *            部门ID
	 * @param isActive
	 *            是否有效
	 * @return
	 */

	public List<Staff> getStaffListByDepartmentActive(Long departmentId,
			String isActive) {
		return staffDao.getStaffListByDepartmentActive(departmentId, isActive);
	}

	/**
	 * 根据部门ID取得有效的staff drop down list JSON data,有--- ALL ---选项
	 * 
	 * @param departmentId
	 *            部门
	 * @param isActive
	 *            活动
	 * @return
	 */

	public StringBuffer getStaffListDDLWithAll(Long departmentId,
			String isActive) {
		StringBuffer sb = new StringBuffer();
		sb.append("[{\"value\":\"\",\"caption\":\"--- ALL ---\"}");
		List<Staff> staffList = getStaffListByDepartmentActive(departmentId,
				isActive);
		for (Staff s : staffList) {
			sb.append(",{\"value\":\"" + s.getStaffId() + "\",\"caption\":\""
					+ s.getStaffName() + "\"}");
		}
		sb.append("]");
		return sb;
	}

	/**
	 * 根据部门ID取得有效的staff drop down list JSON data,无--- ALL ---选项
	 * 
	 * @param departmentId
	 *            部门
	 * @param isActive
	 *            活动
	 * @return
	 */

	public StringBuffer getStaffDDLNoAll(Long departmentId, String isActive) {
		StringBuffer sb = new StringBuffer();
		List<Staff> staffList = getStaffListByDepartmentActive(departmentId,
				isActive);
		for (Staff s : staffList) {
			if (sb.length() == 0)
				sb.append("[{\"value\":\"" + s.getStaffId()
						+ "\",\"caption\":\"" + s.getStaffName() + "\"}");
			else
				sb.append(",{\"value\":\"" + s.getStaffId()
						+ "\",\"caption\":\"" + s.getStaffName() + "\"}");
		}
		if (sb.length() == 0)
			sb.append("[{}]");
		else
			sb.append("]");
		return sb;
	}

	/**
	 * 根据部门ID取得有效的staff list JSON data
	 * 
	 * @param departmentId
	 *            部门
	 * @param isActive
	 *            活动
	 * @param configStaffRank
	 *            联系人队列
	 * @return
	 */

	public StringBuffer getStaffListJSON(Long departmentId, String isActive,
			String configStaffRank) {
		StringBuffer sb = new StringBuffer("[");
		List<Staff> staffList = getStaffListByDepartmentActive(departmentId,
				isActive);
		if (StringUtils.isEmpty(configStaffRank))
			configStaffRank = "";
		else
			configStaffRank = "," + configStaffRank + ",";
		String checked = "0";
		for (Staff s : staffList) {
			if (configStaffRank.contains(s.getStaffId().toString()))
				checked = "1";
			else
				checked = "0";
			sb.append("{\"staffId\":\"" + s.getStaffId()
					+ "\",\"checkboxValue\":\""
					+ s.getStaffDepartment().getDepartmentId() + "-"
					+ s.getStaffId() + "\",\"checked\":\"" + checked
					+ "\",\"staffLogin\":\"" + s.getStaffLogin()
					+ "\",\"staffName\":\"" + s.getStaffName() + "\"},");
		}
		sb = new StringBuffer(StringUtil.cutLastChar(sb.toString(), ","));
		sb.append("]");
		return sb;
	}

	/**
	 * 取得staffRank
	 * 
	 * @param staffList
	 *            部门成员list
	 * @param staffRank
	 * @return
	 */

	public StringBuffer getStaffRank(List<Staff> staffList,
			StringBuffer staffRank) {
		for (Staff s : staffList) {
			if (s.getStaffId() != null)
				if (staffRank.length() == 0)
					staffRank.append(s.getStaffId());
				else
					staffRank.append("," + s.getStaffId());
		}
		return staffRank;
	}

	/**
	 * 取得职员 list
	 * 
	 * @param userLogin
	 *            当前用户登录ID
	 * @param isActive
	 *            活动
	 * @return
	 */

	public List<Staff> getStaffListByLoginActive(String userLogin,
			String isActive) {
		return staffDao.getStaffListByLoginActive(userLogin, isActive);
	}

	/**
	 * 保存成员
	 * 
	 * @param o
	 *            成员
	 * @return
	 */
	public Staff saveStaff(Staff o) {
		return staffDao.save(o);
	}

	/**
	 * 保存联系人
	 * 
	 * @param staffId
	 *            当前职员
	 * @param contactId
	 *            联系人
	 * @return
	 */

	public String saveStaffContact(Long staffId, Long contactId) {
		Staff o = staffDao.loadEntity(staffId);
		String configStaffRank = o.getConfigStaffRank();
		String flag = "0";
		if (configStaffRank == null || "".equals(configStaffRank)) {
			o.setConfigStaffRank(contactId.toString());
			flag = "1";
		} else {
			configStaffRank = "," + configStaffRank + ",";
			if (configStaffRank.indexOf("," + contactId + ",") == -1) {
				o.setConfigStaffRank(configStaffRank.substring(1,
						configStaffRank.length()) + contactId);
				flag = "1";
			} else {
				configStaffRank = StringUtil.replaceAll(configStaffRank, ","
						+ contactId + ",", ",");
				o.setConfigStaffRank(configStaffRank.substring(1,
						configStaffRank.length() - 1));
			}
		}
		o = staffDao.save(o);
		if ("1".equals(flag))
			return "[{\"returnMsg\":\"增加成功!\"}]";
		else
			return "[{\"returnMsg\":\"删除成功!\"}]";
	}

	/**
	 * 保存成员,默认所在部门
	 * 
	 * @param o
	 *            　成员
	 * @param departmentId
	 *            　所在部门
	 * @return
	 */
	public Staff saveStaff(Staff o, Long departmentId) {
		if (departmentId != null)
			o.setStaffDepartment(departmentDao.loadEntity(departmentId));
		return staffDao.save(o);
	}

	/**
	 * 保存成员,部门
	 * 
	 * @param o
	 *            成员
	 * @param checkedDepartmentIds
	 *            部门ids
	 * @return
	 * @throws Exception
	 */
	public Staff saveStaff(Staff o, List<Long> checkedDepartmentIds)
			throws Exception {
		// 把当前用户所在的部门加入到部门列中,减少MTM过程中的验证
		if (!checkedDepartmentIds.contains(o.getStaffDepartment()
				.getDepartmentId()))
			checkedDepartmentIds.add(o.getStaffDepartment().getDepartmentId());
		// ManyToMany departments setup.
		CollectionUtil.mergeByCheckedIds(o.getConfigDepartment(),
				checkedDepartmentIds, Department.class, "departmentId");
		return staffDao.save(o);
	}

	/**
	 * 取得国家
	 * 
	 * @param countryId
	 * @return
	 */

	public Country getCountry(Long countryId) {
		return countryDao.loadEntity(countryId);
	}

	/**
	 * 保存国家
	 * 
	 * @param o
	 * @return
	 */
	public Country saveCountry(Country o) {
		return countryDao.save(o);
	}

	/**
	 * 删除国家
	 * 
	 * @param countryId
	 */
	public void deleteCountry(Long countryId) {
		countryDao.delete(countryId);
	}

	/**
	 * 取得国家 list
	 * 
	 * @return
	 */

	public List<Country> getCountryList() {
		return countryDao.getCountryList();
	}

	/**
	 * 取得国家 page
	 * 
	 * @param pageInfo
	 * @param countryQueryNameEn
	 *            英文名称
	 * @param countryQueryNameCn
	 *            中文名称
	 * @return
	 */

	public PageInfo<Country> getCountryPage(PageInfo<Country> pageInfo,
			String countryQueryNameEn, String countryQueryNameCn) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Country a ");
		if (StringUtil.isNotEmpty(countryQueryNameEn)) {
			sb.append(" Where a.countryNameEn like '%" + countryQueryNameEn
					+ "%' ");
			if (StringUtil.isNotEmpty(countryQueryNameCn)) {
				sb.append(" And a.countryNameCn like '%" + countryQueryNameCn
						+ "%' ");
			}
		} else {
			if (StringUtil.isNotEmpty(countryQueryNameCn)) {
				sb.append(" Where a.countryNameCn like '%" + countryQueryNameCn
						+ "%' ");
			}
		}
		pageInfo = countryDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.countryNameEn ");
		return pageInfo;
	}

	/**
	 * 取得省份
	 * 
	 * @param provinceId
	 * @return
	 */

	public Province getProvince(Long provinceId) {
		return provinceDao.loadEntity(provinceId);
	}

	/**
	 * 保存省份
	 * 
	 * @param o
	 * @return
	 */
	public Province saveProvince(Province o) {
		return provinceDao.save(o);
	}

	/**
	 * 删除省份
	 * 
	 * @param provinceId
	 */
	public void deleteProvince(Long provinceId) {
		provinceDao.delete(provinceId);
	}

	/**
	 * 取得省份 list
	 * 
	 * @param countryId
	 *            国家:1 默认为中国
	 * @return
	 */

	public List<Province> getProvinceList(Long countryId) {
		return provinceDao.getProvinceList(countryId);
	}

	/**
	 * 取得省份 page
	 * 
	 * @param pageInfo
	 * @param countryId
	 *            国家
	 * @param provinceQueryNameEn
	 *            英文名称
	 * @param provinceQueryNameCn
	 *            中文名称
	 * @return
	 */

	public PageInfo<Province> getProvincePage(PageInfo<Province> pageInfo,
			Long countryId, String queryProvinceNameEn,
			String queryProvinceNameCn) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Province a Where a.provinceCountry.countryId = "
				+ countryId);
		if (StringUtil.isNotEmpty(queryProvinceNameEn)) {
			sb.append(" And a.provinceNameCn like '%" + queryProvinceNameEn
					+ "%' ");
		}
		if (StringUtil.isNotEmpty(queryProvinceNameCn))
			sb.append(" And a.provinceNameCn like '%" + queryProvinceNameCn
					+ "%' ");
		pageInfo = provinceDao.listQuery(pageInfo,
				"Select Count(*) " + sb.toString(), sb.toString()
						+ " Order by a.provinceNameEn ");
		return pageInfo;
	}

	/**
	 * Prepare city
	 * 
	 * @param city
	 *            City instance
	 * @param cityId
	 *            City primary key
	 * @return
	 */

	public City prepareCity(City o, Long cityId) {
		if (cityId == null)
			o = new City();
		else
			o = cityDao.loadEntity(cityId);
		return o;
	}

	/**
	 * 保存城市
	 * 
	 * @param o
	 * @return
	 */
	public City saveCity(City o) {
		return cityDao.save(o);
	}

	/**
	 * 删除城市
	 * 
	 * @param cityId
	 */
	public void deleteCity(Long cityId) {
		cityDao.delete(cityId);
	}

	/**
	 * 取得城市 list
	 * 
	 * @return
	 */

	public List<City> getCityList() {
		return cityDao.getCityList();
	}

	/**
	 * 取得城市 list
	 * 
	 * @param provinceId
	 *            省份:1:广东省
	 * @return
	 */

	public List<City> getCityListByProvince(Long provinceId) {
		return cityDao.getCityList(provinceId);
	}

	/**
	 * 根据省份，取得城市 JSON
	 * 
	 * @param provinceId
	 *            省份
	 * @return
	 */

	public String getCityDDL(Long provinceId) {
		StringBuffer sb = new StringBuffer();
		List<City> cityList = cityDao.getCityList(provinceId);
		for (City o : cityList)
			if (sb.length() == 0)
				sb.append("[{\"value\":\"" + o.getCityId()
						+ "\",\"caption\":\"" + o.getCityNameCn() + "\"}");
			else
				sb.append(",{\"value\":\"" + o.getCityId()
						+ "\",\"caption\":\"" + o.getCityNameCn() + "\"}");
		if (sb.length() > 0)
			sb.append("]");
		return sb.toString();
	}

	/**
	 * 取得城市 page
	 * 
	 * @param pageInfo
	 * @param provinceId
	 *            省份
	 * @param cityQueryNameEn
	 *            英文名称
	 * @param cityQueryNameCn
	 *            中文名称
	 * @return
	 */

	public PageInfo<City> getCityPage(PageInfo<City> pageInfo, Long provinceId,
			String cityQueryNameEn, String cityQueryNameCn) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From City a Where a.cityProvince.provinceId=" + provinceId);
		if (StringUtil.isNotEmpty(cityQueryNameEn))
			sb.append(" And a.cityNameEn like '%" + cityQueryNameEn + "%' ");
		if (StringUtil.isNotEmpty(cityQueryNameCn))
			sb.append(" And a.cityNameCn like '%" + cityQueryNameCn + "%' ");
		pageInfo = cityDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.cityNameEn ");
		return pageInfo;
	}

	/**
	 * Prepare zone instance
	 * 
	 * @param o
	 *            Zone instance
	 * @param zoneId
	 *            Zone primary key
	 * @param cityId
	 *            City primary key
	 * @return
	 */

	public Zone prepareZone(Zone o, Long zoneId, Long cityId) {
		if (zoneId == null)
			o = new Zone();
		else
			o = zoneDao.loadEntity(zoneId);
		if (cityId != null)
			o.setZoneCity(cityDao.loadEntity(cityId));
		return o;
	}

	/**
	 * 保存区
	 * 
	 * @param o
	 * @param cityId
	 * @return
	 */
	public Zone saveZone(Zone o, Long cityId) {
		if (cityId != null)
			o.setZoneCity(cityDao.loadEntity(cityId));
		else
			o.setZoneCity(null);
		return zoneDao.save(o);
	}

	/**
	 * 删除区
	 * 
	 * @param zoneId
	 */
	public void deleteZone(Long zoneId) {
		zoneDao.delete(zoneId);
	}

	/**
	 * 根据城市，取得城区 list
	 * 
	 * @param cityId
	 *            城市
	 * @return
	 */

	public String getZoneDDL(Long cityId) {
		StringBuffer sb = new StringBuffer();
		List<Zone> zoneList = zoneDao.getZoneListByCity(cityId);
		for (Zone o : zoneList)
			if (sb.length() == 0)
				sb.append("[{\"value\":\"" + o.getZoneId()
						+ "\",\"caption\":\"" + o.getZoneName() + "\"}");
			else
				sb.append(",{\"value\":\"" + o.getZoneId()
						+ "\",\"caption\":\"" + o.getZoneName() + "\"}");
		if (sb.length() > 0)
			sb.append("]");
		return sb.toString();
	}

	/**
	 * 取得区 list
	 * 
	 * @param cityId
	 * @return
	 */

	public List<Zone> getZoneListByCity(Long cityId) {
		return zoneDao.getZoneListByCity(cityId);
	}

	/**
	 * 取得区 page
	 * 
	 * @param pageInfo
	 * @param cityId
	 * @return
	 */

	public PageInfo<Zone> getZonePage(PageInfo<Zone> pageInfo, Long cityId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Zone a Where a.zoneCity.cityId= '" + cityId + "'");
		pageInfo = zoneDao.listQuery(pageInfo,
				"Select Count(*) " + sb.toString(), sb.toString()
						+ " Order by a.zoneOrder ");
		return pageInfo;
	}

	/**
	 * Prepare garden instance
	 * 
	 * @param o
	 *            Garden instance
	 * @param gardenId
	 *            Garden primary key
	 * @param zoneId
	 *            Zone primary key
	 * @return
	 */

	public Garden prepareGarden(Garden o, Long gardenId, Long zoneId) {
		if (gardenId == null)
			o = new Garden();
		else
			o = gardenDao.loadEntity(gardenId);
		return o;
	}

	/**
	 * Save garden instance
	 * 
	 * @param o
	 *            Garden instance
	 * @param zoneId
	 *            Zone primary key
	 * @return
	 */
	public Garden saveGarden(Garden o, Long zoneId) {
		return gardenDao.save(o);
	}

	/**
	 * Delete garden instance
	 * 
	 * @param gardenId
	 *            Garden primary key
	 */
	public void deleteGarden(Long gardenId) {
		gardenDao.delete(gardenId);
	}

	/**
	 * Get garden page
	 * 
	 * @param pageInfo
	 *            Page info
	 * @param cityId
	 *            City primary key
	 * @param zoneId
	 *            Zone primary key
	 * @return
	 */

	public PageInfo<Garden> getGardenPage(PageInfo<Garden> pageInfo,
			Long cityId, Long zoneId) {
		StringBuffer sb = new StringBuffer();
		if (cityId != null)
			sb.append(" a.gardenZone.zoneCity.cityId = " + cityId + " and");
		if (zoneId != null)
			sb.append(" a.gardenZone.zoneId=" + zoneId + " and");
		if (sb.length() > 0) {
			sb = new StringBuffer(StringUtil.cutLastChar(sb.toString(), "and"));
			sb.insert(0, " From Garden a Where ");
		} else
			sb.append("From Garden a");
		System.out.println(sb.toString());
		pageInfo = gardenDao.listQuery(pageInfo,
				" Select Count(*) " + sb.toString(), sb.toString());
		return pageInfo;
	}

	public void setPrimaryEntity(User user, PrimaryEntity o) {
		if (o.getAccount() == null) {
			o.setAccount(user.getAccount());
			o.setCreator(user.getUserLogin());
		} else {
			o.setLastUpdate(user.getUserLogin());
		}
		o.setLastUpdateTime(System.currentTimeMillis());
	}

}
