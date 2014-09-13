package com.weaforce.system.action.organ;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.entity.admin.User;
import com.weaforce.system.entity.organ.Department;
import com.weaforce.system.entity.organ.Staff;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system/organ")
public class StaffAction extends AbstractCrudAction<Staff> {
	private static final long serialVersionUID = -1194754372019468947L;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	private Staff staff;
	private Long staffId;
	private String staffLogin;
	private Long accountId;
	private Long contactId;

	private List<Department> departmentList;
	private Long departmentId;

	private String queryStaffLogin;
	private String queryStaffName;
	private String queryLogicLock = "0";

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	/**
	 * 当从帐套管理中新增部门职员时，将帐套ID转为字符，写入到当前新增用户信息中
	 * 
	 * @param accountId
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	protected void prepareModel() throws Exception {
		staff = systemService.prepareStaff(staff, staffId, departmentId,
				accountId);
	}

	public String input() throws Exception {
		if (accountId == null)
			departmentList = systemService.getDepartmentListByActive(getAdmin()
					.getAccount(), "1", false);
		else
			departmentList = systemService.getDepartmentListByActive(
					accountId.toString(), "1", false);
		// 默认将所在部门加入到MTM列表中
		Department o = staff.getStaffDepartment();
		if (o != null)
			if (departmentList.indexOf(o) == -1)
				departmentList.add(o);
		return INPUT;
	}

	/**
	 * <h3>保存部门成员</h3>
	 * <ul>
	 * <li>如果有相同的登录ID将不被保存</li>
	 * </ul>
	 */

	public String save() throws Exception {
		staff.setStaffLogin(staffLogin.toUpperCase());
		if (staffId == null)
			if (systemService.getStaffByLogin(getAdmin().getAccount(),
					staff.getStaffLogin()) != null)
				return ERROR;
		staff = systemService.saveStaff(staff, departmentId);
		return list();
	}

	public String list() throws Exception {
		departmentList = systemService.getDepartmentListByActive(getAdmin()
				.getAccount(), "1", true);
		if (departmentId == null)
			if (departmentList.size() > 0)
				departmentId = departmentList.get(0).getDepartmentId();
		pageInfo = systemService.getStaffPage(pageInfo,
				getAdmin().getAccount(), queryStaffName, queryStaffLogin,
				departmentId, queryLogicLock);
		return SUCCESS;
	}

	public String lock() throws Exception {
		if (staffId != null) {
			staff = systemService.getStaff(staffId);
		}
		return list();
	}

	public String unLock() throws Exception {
		if (staffId != null) {
			staff = systemService.getStaff(staffId);
		}
		return list();
	}

	/**
	 * 直接将此人设置为非活动
	 */

	public String delete() throws Exception {
		if (staffId != null) {
			staff = systemService.getStaff(staffId);
			if ("1".equals(staff.getStaffIsActive())) {
				staff.setStaffIsActive("0");
				staff = systemService.saveStaff(staff);
			}
		}
		return list();
	}

	/**
	 * 预处理联系人
	 * 
	 * @throws Exception
	 */
	public void prepareContact() throws Exception {
		prepareModel();
	}

	/**
	 * 跳转到设置联系人页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String contact() throws Exception {
		// 多对多映射
		departmentList = staff.getConfigDepartment();
		if (!departmentList.contains(staff.getStaffDepartment()))
			departmentList.add(staff.getStaffDepartment());
		if (departmentId == null)
			if (departmentList.size() > 0)
				departmentId = departmentList.get(0).getDepartmentId();
		return "contact";
	}

	/**
	 * 联系人主键
	 * 
	 * @param contactId
	 */
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public String saveContact() throws Exception {
		return StrutsUtil.renderJSON(systemService.saveStaffContact(staffId,
				contactId));
	}

	public Staff getModel() {
		return staff;
	}

	/**
	 * 登录
	 * 
	 * @param staffLogin
	 */
	public void setStaffLogin(String staffLogin) {
		this.staffLogin = staffLogin;
	}

	/**
	 * 部门成员的登录ID必需在User中存在,系统用户不允许设置为部门成员。
	 * 
	 * @return
	 * @throws Exception
	 */

	public String checkStaffLogin() throws Exception {
		User user = systemService.getUserByLogin(staffLogin);
		if (null != user) {
			if ("1".equals(user.getUserType()))
				return StrutsUtil.renderText("false");
			else
				return StrutsUtil.renderText("true");
		} else
			return StrutsUtil.renderText("false");
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	/**
	 * 部门ID
	 * 
	 * @param departmentId
	 */
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * 根据部门ID取得有效的staff list,有--- ALL ---选项
	 * 
	 * @return
	 */
	public String getStaffListDDLWithAll() throws Exception {
		return StrutsUtil.renderJSON(systemService.getStaffListDDLWithAll(
				departmentId, "1").toString());
	}

	/**
	 * 根据部门ID取得有效的staff list,无--- ALL ---选项
	 * 
	 * @return
	 */
	public String getStaffDDLNoAll() throws Exception {
		return StrutsUtil.renderJSON(systemService.getStaffDDLNoAll(
				departmentId, "1").toString());
	}

	/**
	 * 根据部门ID取得有效的staff list JSON data
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getStaffListJSON() throws Exception {
		staff = systemService.getStaff(staffId);
		return StrutsUtil.renderJSON(systemService.getStaffListJSON(
				departmentId, "1", staff.getConfigStaffRank()).toString());
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public String getQueryStaffLogin() {
		return queryStaffLogin;
	}

	public void setQueryStaffLogin(String queryStaffLogin) {
		this.queryStaffLogin = queryStaffLogin;
	}

	public String getQueryStaffName() {
		return queryStaffName;
	}

	public void setQueryStaffName(String queryStaffName) {
		this.queryStaffName = queryStaffName;
	}

	public String getQueryLogicLock() {
		return queryLogicLock;
	}

	public void setQueryLogicLock(String queryLogicLock) {
		this.queryLogicLock = queryLogicLock;
	}

}
