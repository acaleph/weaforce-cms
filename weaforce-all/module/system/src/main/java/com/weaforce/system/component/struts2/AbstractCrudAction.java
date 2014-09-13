package com.weaforce.system.component.struts2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weaforce.core.util.PageInfo;
import com.weaforce.system.component.spring.Security;
import com.weaforce.entity.admin.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * Struts2中CRUD典型Action规范类. 规定使用Preparable,ModelDriven接口,规范了一些函数的命名.
 * 
 * @param <T>
 *            CRUD所管理的对象类型
 * 
 * @author acaleph8@yahoo.com.cn
 */
public abstract class AbstractCrudAction<T> extends ActionSupport implements
		ModelDriven<T>, Preparable {

	private static final long serialVersionUID = -6452351760405970597L;

	protected Log logger = LogFactory.getLog(this.getClass());

	protected PageInfo<T> pageInfo = new PageInfo<T>();

	/**
	 * Action函数,默认action函数，默认指向list函数.
	 */
	public final String execute() throws Exception {
		return list();
	}

	/**
	 * Action函数,编辑Entity实例.
	 * 
	 * @return
	 */
	public abstract String input() throws Exception;

	/**
	 * Action函数,显示Entity列表.
	 * 
	 * @return
	 */
	public abstract String list() throws Exception;

	/**
	 * Action函数,新增或修改Entity.
	 * 
	 * @return
	 */
	public abstract String save() throws Exception;

	/**
	 * Action函数,删除Entity.
	 * 
	 * @return
	 */
	public abstract String delete() throws Exception;

	/**
	 * 在save()前执行二次绑定.
	 */
	public void prepareSave() throws Exception {
		prepareModel();
	}

	/**
	 * 在edit()前执行二次绑定.
	 */
	public void prepareInput() throws Exception {
		prepareModel();
	}

	/**
	 * 在lock()前执行二次绑定.
	 */
	public void prepareLock() throws Exception {
		prepareModel();
	}

	/**
	 * 屏蔽公共的二次绑定
	 */
	public void prepare() throws Exception {

	}

	/**
	 * 等同于prepare()的内部函数.
	 */
	protected abstract void prepareModel() throws Exception;

	public PageInfo<T> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<T> pageInfo) {
		this.pageInfo = pageInfo;
	}

	protected User getAdmin() {
		return Security.getUser();
	}
}
