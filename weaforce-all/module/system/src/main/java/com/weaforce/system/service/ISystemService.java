package com.weaforce.system.service;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.weaforce.core.common.bean.Node;
import com.weaforce.core.exception.ServiceException;
import com.weaforce.core.util.PageInfo;
import com.weaforce.core.web.page.Page;
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

/**
 * <ul>
 * <h3>相关对象说明</h3>
 * <li>Menu:菜单</li>
 * <li>Organ:组织机构</li>
 * <li>Role:角色</li>
 * <li>User:用户</li>
 * </ul>
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public interface ISystemService {

	public Menu getMenu(Long menuId);

	public void deleteMenu(Long menuId);

	/**
	 * Build the menu bar
	 * 
	 * @param user
	 *            Current user
	 * @return
	 */
	public String getRoleMenu(User user);

	/**
	 * Created the drop down menu HTML body
	 * 
	 * @param menuList
	 *            　Current user menu list
	 * @return
	 */
	public String[] getItems(List<Menu> menuList);

	/**
	 * Convert the menu list to common node object list
	 * 
	 * @param menuList
	 *            Menu list
	 * @return
	 */
	public List<Node> getMenuNodeList(List<Menu> menuList, Set<Menu> roleMenus);

	/**
	 * Get simple menu array string
	 * 
	 * @param menuId
	 *            Root menu primary key
	 * @return
	 */
	public String getMenuArray(Long menuId, Set<Menu> roleMenus);

	/**
	 * Get the left menu
	 * 
	 * @param menuId
	 *            Top menu primary key
	 * @param user
	 *            Current user
	 * @return
	 */
	public String getLeftMenu(User user);

	/**
	 * Get menu tree
	 * 
	 * @param menuId
	 *            Root menu id
	 * @param user
	 *            User
	 * @return
	 */
	public String getMenuTree(Long menuId, User user);

	/**
	 * 保存菜单
	 * 
	 * @param o
	 *            菜单
	 * @param parentId
	 *            父菜单主键
	 * @return
	 */
	public Menu saveMenu(Menu o, Long parentId);

	/**
	 * 取得ul-li结构的菜单树
	 * 
	 * @param menuFid
	 *            父菜单
	 * @return
	 */

	public StringBuffer getMenuHtml(Long menuFid);

	/**
	 * Get menu children JSON by parent
	 * 
	 * @param parentId
	 *            Parent primary key
	 * @return
	 */
	public StringBuffer getMenuChildrenJSONByParent(Long parentId);

	/**
	 * Get all menu list
	 * 
	 * @return
	 */
	public List<Menu> getMenuList();

	/**
	 * Set the menu to role by user login
	 * 
	 * @param menuId
	 *            Menu primary key
	 * @param userLogin
	 *            User login
	 */
	public void setMenuToRole(long menuId, String userLogin);

	public void getMenuPage(Page<Menu> page, String menuName);

	/**
	 * Get menu page by parent
	 * 
	 * @param parentId
	 *            Parent menu Id
	 * @param currentPage
	 *            Current page number
	 * @return
	 */
	public void getPageMenuByParent(Page<Menu> page, Long parentId);

	/**
	 * 取得用户角色相关ul-li结构的菜单树
	 * 
	 * @param menuFid
	 *            父菜单
	 * @param menuRank
	 *            有效菜单
	 * @return
	 */

	public StringBuffer getRoleMenuHtml(Long menuFid, String menuRank);

	public Organ getOrgan(Long organId);

	public List<Organ> getOrganList(String account);

	public Organ saveOrgan(Organ o);

	public Role getRole(Long roleId);

	/**
	 * Save role instance
	 * 
	 * @param o
	 *            Role instance
	 * @return
	 */
	public Role saveRole(Role o);

	/**
	 * Delete the role
	 * 
	 * @param roleId
	 *            Role primary key
	 */
	public void deleteRole(Long roleId);

	/**
	 * 取得有效的role list
	 * 
	 * @param account
	 *            帐套
	 * @param isActive
	 *            是否有效
	 * @return
	 */

	public List<Role> getRoleList(String account, String isActive);

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
	public List<Role> getRoleList(User user, String account, String isActive);

	/**
	 * 取得 role page
	 * 
	 * @param pageInfo
	 * @param account
	 * @param roleQueryName
	 * @param isActive
	 * @return
	 */

	public void getRolePage(Page<Role> pageInfo, String account,
			String queryName, String isActive);

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
			Long accountId);

	/**
	 * 验证登录是否存在
	 * 
	 * @param userId
	 *            用户主键
	 * @param userLogin
	 *            登录ID
	 * @return
	 */
	public String checkUserLogin(Long userId, String userLogin);

	/**
	 * 判断用户登录是否已经存在
	 * 
	 * @param userLogin
	 *            用户登录ID
	 * @return String类型的布尔值
	 */
	@Transactional(readOnly = true)
	public String checkBeingUserLogin(String userLogin);

	/**
	 * Get user by primary key
	 * 
	 * @param userId
	 *            User primary key
	 * @return
	 */
	public User getUser(long userId);

	/**
	 * 取得用户
	 * 
	 * @param userLogin
	 *            登录ID
	 * @return
	 */

	public User getUserByLogin(String userLogin);

	/**
	 * Get user login string list by user login
	 * 
	 * @param queryLogin
	 *            User login query string
	 * @return
	 * @throws InterruptedException
	 */
	public List<String> getUserLoginListByLogin(String queryLogin)
			throws InterruptedException;

	/**
	 * 取得User list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<User> getUserListByAccount(String account);

	/**
	 * 取得用户登录ID的JSON格式字串
	 * 
	 * @param account
	 *            当前帐套
	 * @return
	 */
	public String getUserLoginJSON(String account);

	/**
	 * 取得用户 page
	 * 
	 * @param page
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
			Long queryRoleId, String queryLogicLock);

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
			throws ServiceException, Exception;

	public void saveUser(User user, User o, Long defaultRoleId,
			List<Long> checkedRoleIds) throws Exception;

	/**
	 * 保存用户
	 * 
	 * @param user
	 *            用户
	 * @return
	 * @throws ServiceException
	 */
	public User saveUser(User user) throws ServiceException;

	public void deleteUser(Long userId);

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
			throws ServiceException;

	/**
	 * 取得用户JSON格式menu
	 * 
	 * @param user
	 *            当前用户
	 * @return
	 */

	public StringBuffer getUserMenu(User user);

	/**
	 * 预处理授权
	 * 
	 * @param o
	 * @param authorityId
	 * @return
	 */

	public Authority prepareAuthority(Authority o, Long authorityId);

	public Authority getAuthority(Long authorityId);

	public Authority saveAuthority(Authority o);

	/**
	 * Save role and authorities
	 * 
	 * @param o
	 *            Role instance
	 * @param authorityIds
	 *            Authority IDs array
	 */
	public void saveRole(Role o, Long[] authorityIds);

	/**
	 * 取得授权list，提供role进行授权分配
	 * 
	 * @param account
	 * @return
	 */
	public List<Authority> getAuthorityList();

	/**
	 * Deal with the checked authority list
	 * 
	 * @param roleAuthority
	 *            Has been set to the role
	 * @return
	 */
	public List<Authority> getAuthorityList(Set<Authority> roleAuthority);

	/**
	 * 根据系统,取得授权list
	 * 
	 * @param businessId
	 *            系统
	 * @return
	 */
	public List<Authority> getAuthorityListByBusiness(Long businessId);

	/**
	 * 根据系统,取得授权JSON数据
	 * 
	 * @param businessId
	 *            系统
	 * @return
	 */
	public String getAuthorityDDL(Long businessId);

	/**
	 * 分配授权给角色
	 * 
	 * @param authorityId
	 * @param roleId
	 */

	public void checkedAuthority(Long authorityId, Long roleId);

	/**
	 * 解除角色授权
	 * 
	 * @param authorityId
	 * @param roleId
	 */
	public void uncheckAuthority(Long authorityId, Long roleId);

	/**
	 * 取得authority page
	 * 
	 * @param pageInfo
	 * @param queryAuthorityCode
	 *            代码 ROLE_*
	 * @param businessId
	 *            系统
	 * @return
	 */
	public PageInfo<Authority> getAuthorityPage(PageInfo<Authority> pageInfo,
			String authorityCode, Long businessId, Long roleId);

	/**
	 * 预处理资源
	 * 
	 * @param o
	 * @param resourceId
	 * @return
	 */
	public Resource prepareResource(Resource o, Long resourceId);

	public Resource getResource(Long resourceId);

	public void deleteResource(Resource o);

	/**
	 * 保存资源
	 * 
	 * @param o
	 * @param moduleId
	 *            模块
	 * @return
	 */
	public Resource saveResource(Resource o, Long moduleId);

	/**
	 * 根据模块取得资源list
	 * 
	 * @param moduleId
	 *            模块
	 * @return
	 */
	public List<Resource> getResourceListByModule(Long moduleId,
			Long authorityId);

	/**
	 * 绑定授权资源
	 * 
	 * @param resourceId
	 *            资源 id
	 * @param authorityId
	 *            授权id
	 */

	public void checkedResource(Long resourceId, Long authorityId);

	/**
	 * 取消绑定授权资源
	 * 
	 * @param resourceId
	 *            资源 id
	 * @param authorityId
	 *            授权id
	 */
	public void uncheckResource(Long resourceId, Long authorityId);

	public List<Menu> getMenuListByParent(Long menuFid);

	public List<Menu> getUserMenuListByRankFid(String roleMenuRank, Long menuFid);

	public Person getPerson(Long personId);

	public void deletePerson(Long personId);

	public List<Person> getPersonList(String account);

	public PageInfo<Person> getPersonPage(PageInfo<Person> pageInfo,
			String account);

	public Person savePerson(Person o);

	/**
	 * 取得日历事件
	 * 
	 * @param eventId
	 * @return
	 */
	public CalendarEvent getEvent(Long eventId);

	/**
	 * 取得当前用户某个日期的事件
	 * 
	 * @param uesrLogin
	 *            当前用户
	 * @param time
	 *            日期
	 * @return
	 */
	public CalendarEvent getEventByLoginTime(String userLogin, Long time);

	/**
	 * 保存日历事件
	 * 
	 * @param o
	 * @return
	 */
	public CalendarEvent saveEvent(CalendarEvent o);

	/**
	 * 删除日历事件
	 * 
	 * @param eventId
	 */
	public void deleteEvent(Long eventId);

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
			int month);

	/**
	 * 预处理帐套
	 * 
	 * @param o
	 * @param accountId
	 * @return
	 */
	public Account prepareAccount(Account o, Long accountId);

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
			String propertyValue);

	public Account getAccount(Long accountId);

	public void deleteAccount(Long accountId);

	public PageInfo<Account> getAccountPage(PageInfo<Account> pageInfo,
			String accountNameCn, String accountNameEn, String accountShortName);

	/**
	 * 保存帐套
	 * 
	 * @param o
	 * @return
	 */
	public Account saveAccount(Account o);

	public List<Account> getAccountListByScore(String isScore);

	public void deleteOrgan(Long organId);

	public void deleteOrgan(Organ o);

	public List<Organ> getOrganListByParent(String account, Long parentId);

	public PageInfo<Organ> getOrganPage(PageInfo<Organ> pageInfo,
			String account, String queryOrganName);

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
			Long accountId);

	public Department getDepartment(Long departmentId);

	public void deleteDepartment(Long departmentId);

	public PageInfo<Department> getDepartmentPage(
			PageInfo<Department> pageInfo, String account,
			String departmentQueryName);

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
			String isActive, boolean flag);

	/**
	 * 根据userLogin获取,config 中的部门列表
	 * 
	 * @param departmentList
	 * @param account
	 * @param userLogin
	 * @return
	 */
	public List<Department> getDepartmentListByLogin(
			List<Department> departmentList, String account, String userLogin);

	public Department saveDepartment(Department o);

	/**
	 * 根据staffId取得部门成员
	 * 
	 * @param configLogin
	 * @return
	 */
	public Staff getStaff(Long staffId);

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
			Long accountId);

	public Staff getStaffByLogin(String account, String staffLogin);

	public void deleteStaff(Long staffId);

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
			Long queryDepartmentId, String queryLogicLock);

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
			String isActive);

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
			String isActive);

	/**
	 * 根据部门ID取得有效的staff drop down list JSON data,无--- ALL ---选项
	 * 
	 * @param departmentId
	 *            部门
	 * @param isActive
	 *            活动
	 * @return
	 */
	public StringBuffer getStaffDDLNoAll(Long departmentId, String isActive);

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
			String configStaffRank);

	/**
	 * 取得staffRank
	 * 
	 * @param staffList
	 *            部门成员list
	 * @param staffRank
	 * @return
	 */
	public StringBuffer getStaffRank(List<Staff> staffList,
			StringBuffer staffRank);

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
			String isActive);

	/**
	 * 保存成员
	 * 
	 * @param o
	 *            成员
	 * @return
	 */
	public Staff saveStaff(Staff o);

	/**
	 * 保存联系人
	 * 
	 * @param staffId
	 *            当前职员
	 * @param contactId
	 *            联系人
	 * @return
	 */

	public String saveStaffContact(Long staffId, Long contactId);

	/**
	 * 保存成员,默认所在部门
	 * 
	 * @param o
	 *            　成员
	 * @param departmentId
	 *            　所在部门
	 * @return
	 */

	public Staff saveStaff(Staff o, Long departmentId);

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
			throws Exception;

	/**
	 * 取得国家
	 * 
	 * @param countryId
	 * @return
	 */
	public Country getCountry(Long countryId);

	/**
	 * 保存国家
	 * 
	 * @param o
	 * @return
	 */
	public Country saveCountry(Country o);

	/**
	 * 删除国家
	 * 
	 * @param countryId
	 */

	public void deleteCountry(Long countryId);

	/**
	 * 取得国家 list
	 * 
	 * @return
	 */
	public List<Country> getCountryList();

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
			String countryQueryNameEn, String countryQueryNameCn);

	/**
	 * 取得省份
	 * 
	 * @param provinceId
	 * @return
	 */
	public Province getProvince(Long provinceId);

	/**
	 * 保存省份
	 * 
	 * @param o
	 * @return
	 */
	public Province saveProvince(Province o);

	/**
	 * 删除省份
	 * 
	 * @param provinceId
	 */
	public void deleteProvince(Long provinceId);

	/**
	 * 取得省份 list
	 * 
	 * @param countryId
	 *            国家: 1 默认为中国
	 * @return
	 */
	public List<Province> getProvinceList(Long countryId);

	/**
	 * 取得省份 page
	 * 
	 * @param pageInfo
	 * @param countryId
	 *            国家: 1 默认为中国
	 * @param provinceQueryNameEn
	 *            英文名称
	 * @param provinceQueryNameCn
	 *            中文名称
	 * @return
	 */

	public PageInfo<Province> getProvincePage(PageInfo<Province> pageInfo,
			Long countryId, String provinceQueryNameEn,
			String provinceQueryNameCn);

	/**
	 * Prepare city
	 * 
	 * @param city
	 *            City instance
	 * @param cityId
	 *            City primary key
	 * @return
	 */
	public City prepareCity(City o, Long cityId);

	/**
	 * 保存城市
	 * 
	 * @param o
	 * @return
	 */
	public City saveCity(City o);

	/**
	 * 删除城市
	 * 
	 * @param cityId
	 */
	public void deleteCity(Long cityId);

	/**
	 * 取得城市 list
	 * 
	 * @return
	 */
	public List<City> getCityList();

	/**
	 * 取得城市 list
	 * 
	 * @param provinceId
	 *            省份
	 * @return
	 */
	public List<City> getCityListByProvince(Long provinceId);

	/**
	 * 根据省份，取得城市 list
	 * 
	 * @param provinceId
	 *            省份:1:广东省
	 * @return
	 */
	public String getCityDDL(Long provinceId);

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
			String cityQueryNameEn, String cityQueryNameCn);

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
	public Zone prepareZone(Zone o, Long zoneId, Long cityId);

	/**
	 * 保存区
	 * 
	 * @param o
	 * @param cityId
	 * @return
	 */
	public Zone saveZone(Zone o, Long cityId);

	/**
	 * 删除区
	 * 
	 * @param zoneId
	 */
	public void deleteZone(Long zoneId);

	/**
	 * 根据城市，取得城区 list
	 * 
	 * @param cityId
	 *            城市
	 * @return
	 */
	public String getZoneDDL(Long cityId);

	/**
	 * 取得区 list
	 * 
	 * @param cityId
	 * @return
	 */
	public List<Zone> getZoneListByCity(Long cityId);

	/**
	 * 取得区 page
	 * 
	 * @param pageInfo
	 * @param cityId
	 * @return
	 */
	public PageInfo<Zone> getZonePage(PageInfo<Zone> pageInfo, Long cityId);

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
	public Garden prepareGarden(Garden o, Long gardenId, Long zoneId);

	/**
	 * Save garden instance
	 * 
	 * @param o
	 *            Garden instance
	 * @param zoneId
	 *            Zone primary key
	 * @return
	 */
	public Garden saveGarden(Garden o, Long zoneId);

	/**
	 * Delete garden instance
	 * 
	 * @param gardenId
	 *            Garden primary key
	 */
	public void deleteGarden(Long gardenId);

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
			Long cityId, Long zoneId);

}
