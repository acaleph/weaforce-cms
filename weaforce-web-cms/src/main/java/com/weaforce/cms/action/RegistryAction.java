package com.weaforce.cms.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Registry;
import com.weaforce.cms.exception.CmsException;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;

/**
 * 注册用户管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms")
public class RegistryAction extends AbstractCrudAction<Registry> {
	private static final long serialVersionUID = -9193007018171317655L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	private Registry registry;
	private Long registryId;

	private String userLogin;

	private String queryLogin;

	public void setRegistryId(Long registryId) {
		this.registryId = registryId;
	}

	
	protected void prepareModel() throws Exception {
		registry = cmsService.prepareRegistry(registry, registryId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		registry = cmsService.saveRegistry(registry);
		return list();
	}

	/**
	 * 用户注册
	 * 
	 * @return
	 * @throws CmsException
	 */
	public String registry() throws CmsException {
		return "registry";
	}

	
	public String list() throws Exception {
		pageInfo = cmsService.getRegistryPage(pageInfo, queryLogin);
		return SUCCESS;
	}

	
	public String delete() throws Exception {
		cmsService.deleteRegistry(registryId);
		return list();
	}


	/**
	 * 检查是否有重复的登录ID
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkUserLogin() throws CmsException {
		if (cmsService.getRegistry(userLogin) == null)
			return StrutsUtil.renderText("true");
		else
			return StrutsUtil.renderText("false");
	}

	/**
	 * 页面获取协议
	 * 
	 * @return
	 */
	public String getAgreement() {
		return agreementContents();
	}

	/**
	 * 从文件中读取协议
	 * 
	 * @return
	 */
	public String agreementContents() throws CmsException {
		StringBuffer contents = new StringBuffer();
		BufferedReader reader = null;
		FileReader fileReader = null;

		try {
			String directory = new StringBuffer().append("/common/agreement/")
					.toString();

			String filename = "adniu.txt";

			File file = new File(directory + filename);

			if (!file.exists()) {
				filename = "\\data\\www\\adniu\\adniu.txt";
				file = new File(filename);

				if (!file.exists()) {
					throw new FileNotFoundException(
							"Could not locate any terms agreement file");
				}
			}

			fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);

			char[] buffer = new char[2048];
			int c = 0;

			while ((c = reader.read(buffer, 0, buffer.length)) > -1) {
				contents.append(buffer, 0, c);
			}
		} catch (Exception e) {
			contents = new StringBuffer("User.agreement.noAgreement");
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (Exception e) {
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
				}
			}
		}
		return contents.toString();
	}

	public Registry getModel() {
		return registry;
	}

	public String getQueryLogin() {
		return queryLogin;
	}

	public void setQueryLogin(String queryLogin) {
		this.queryLogin = queryLogin;
	}

}
