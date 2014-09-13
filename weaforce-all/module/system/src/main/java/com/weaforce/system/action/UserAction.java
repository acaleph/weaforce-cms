package com.weaforce.system.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.core.util.MD5;
import com.weaforce.system.component.spring.Security;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.entity.admin.Role;
import com.weaforce.entity.admin.User;
import com.weaforce.entity.area.City;
import com.weaforce.entity.area.Zone;
import com.weaforce.system.service.ISystemService;

/**
 * 用户管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/system")
public class UserAction extends AbstractCrudAction<User> {

	private static final long serialVersionUID = -8982576440050008196L;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	private Long userId;
	private String userLogin;
	private User user;
	private Long accountId;
	private String accountName;
	private String roleName;
	private Long cityId;
	private Long zoneId;

	private List<Role> roleList;
	private List<Long> checkedRoleIds = new ArrayList<Long>();
	private List<City> cityList;
	private List<Zone> zoneList;

	private Long queryRoleId;
	private String userQueryNameEn;
	private String userQueryNameCn;
	private String queryLogicLock = "0";

	private String passwordCurrent;
	private String passwordNew;

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 当从帐套管理中新增用户时，将帐套ID转为字符，写入到当前新增用户信息中
	 * 
	 * @return
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	protected void prepareModel() throws Exception {
		user = systemService.prepareUser(user, userId, queryRoleId, accountId);
	}

	public String input() throws Exception {
		// 城市
		cityList = systemService.getCityListByProvince(Long.valueOf("1"));
		if (cityId == null)
			if (cityList.size() > 0)
				cityId = cityList.get(0).getCityId();
		// 城区/镇
		zoneList = systemService.getZoneListByCity(cityId);
		if (zoneId == null)
			if (zoneList.size() > 0)
				zoneId = zoneList.get(0).getZoneId();
		roleList = systemService.getRoleList(getAdmin().getAccount(),
				"1");
		checkedRoleIds = user.getUserRoleIds();
		return INPUT;
	}

	public String list() throws Exception {
		roleList = systemService.getRoleList(getAdmin().getAccount(),
				"1");
		Role o = new Role();
		o.setRoleName("--- ALL ---");
		roleList.add(0, o);
		if (queryRoleId == null)
			if (roleList.size() > 0)
				queryRoleId = roleList.get(0).getRoleId();
		return SUCCESS;
	}

	public String save() throws Exception {
		//user = systemService.saveUser(user, queryRoleId, checkedRoleIds,
		//		cityId, zoneId);
		return list();
	}

	public String lock() throws Exception {
		return list();
	}

	/**
	 * 解锁
	 * 
	 * @return
	 * @throws Exception
	 */
	public String unlock() throws Exception {
		return list();

	}

	public String delete() throws Exception {
		return lock();
	}

	/**
	 * 取得当前用户的menu tree
	 * <ul>
	 * <li>对于系统用户，不需要检查账期，对非系统用户，账期不存在,不允许创建menu tree</li>
	 * <li>当前非系统用户没有在部门成员中,不允许创建menu tree:系统用户可以不属于任何部门</li>
	 * </ul>
	 * 
	 * @return
	 * @throws Exception
	 */
	public String menu() throws Exception {
		user = systemService.getUserByLogin(getAdmin().getUserLogin());
		return StrutsUtil
				.renderJSON(systemService.getUserMenu(user).toString());
	}

	public void preparePassword() {
		if (Security.getCurrentUserName() != null) {
			user = systemService.getUserByLogin(Security
					.getCurrentUserName());
			accountName = systemService.getAccount(
					Long.valueOf(user.getAccount())).getAccountNameCn();
			roleName = user.getDefaultUserRole().getRoleName();
		}
	}

	public String password() throws Exception {
		return "password";
	}

	/**
	 * user-password.jsp显示帐套名称
	 * 
	 * @return
	 */

	public String getAccountName() {
		return accountName;
	}

	/**
	 * user-password.jsp显示当前用户组名
	 * 
	 * @return
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 取得当前密码
	 * 
	 * @param passwordCurrent
	 */
	public void setPasswordCurrent(String passwordCurrent) {
		this.passwordCurrent = passwordCurrent;
	}

	/**
	 * 取得新密码
	 * 
	 * @param passwordNew
	 */
	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String changePassword() throws Exception {
		user = systemService.getUserByLoginPassword(getAdmin().getUserLogin(),
				passwordCurrent);
		if (user != null) {
			user.setUserPassword(passwordNew);
			user.setUserPwd(MD5.toMD5(user.getUserPassword()));
			user = systemService.saveUser(user);
			return StrutsUtil.renderText("更改密码成功!");
		}
		return StrutsUtil.renderText("更改密码失败!");
	}

	/**
	 * 直接从页面获取userLogin参数，供给checkUserLogin()进行验证
	 * 
	 * @param userLogin
	 */
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	/**
	 * 检查是否有重复的登录ID
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkUserLogin() throws Exception {
		return StrutsUtil.renderText(systemService.checkUserLogin(userId,
				userLogin));
	}

	/**
	 * 检查用户是否已经存在
	 * 
	 * @return String类型的布尔值
	 * @throws Exception
	 */
	public String checkBeingUserLogin() throws Exception {
		return StrutsUtil.renderText(systemService
				.checkBeingUserLogin(userLogin));
	}

	/**
	 * 取得城市主键
	 * 
	 * @return
	 */

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	/**
	 * 取得区域主键
	 * 
	 * @return
	 */

	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	/**
	 * 取得城市 list
	 * 
	 * @return
	 */
	public List<City> getCityList() {
		return cityList;
	}

	/**
	 * 取得区域 list
	 * 
	 * @return
	 */
	public List<Zone> getZoneList() {
		return zoneList;
	}

	/**
	 * 取得角色 list
	 * 
	 * @return
	 */
	public List<Role> getRoleList() {
		return roleList;
	}

	public List<Long> getCheckedRoleIds() {
		return checkedRoleIds;
	}

	public void setCheckedRoleIds(List<Long> checkedRoleIds) {
		this.checkedRoleIds = checkedRoleIds;
	}

	public Long getQueryRoleId() {
		return queryRoleId;
	}

	public void setQueryRoleId(Long queryRoleId) {
		this.queryRoleId = queryRoleId;
	}

	public String getUserQueryNameEn() {
		return userQueryNameEn;
	}

	public void setUserQueryNameEn(String userQueryNameEn) {
		this.userQueryNameEn = userQueryNameEn;
	}

	public String getUserQueryNameCn() {
		return userQueryNameCn;
	}

	public void setUserQueryNameCn(String userQueryNameCn) {
		this.userQueryNameCn = userQueryNameCn;
	}

	public String getQueryLogicLock() {
		return queryLogicLock;
	}

	public void setQueryLogicLock(String queryLogicLock) {
		this.queryLogicLock = queryLogicLock;
	}

	public User getModel() {
		return this.user;
	}
}
