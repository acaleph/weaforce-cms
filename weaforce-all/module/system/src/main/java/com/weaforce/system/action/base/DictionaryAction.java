package com.weaforce.system.action.base;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.entity.base.Dictionary;
import com.weaforce.system.service.IBaseService;

/**
 * 数据字典管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/system/base")
public class DictionaryAction extends AbstractCrudAction<Dictionary> {
	private static final long serialVersionUID = -6543539077185588883L;
	@Autowired
	@Qualifier("baseService")
	private IBaseService baseService;

	private Dictionary dictionary;
	private Long dictionaryId;

	private String queryName;

	public void setDictionaryId(Long dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	
	protected void prepareModel() throws Exception {
		dictionary = baseService.prepareDictionary(dictionary, dictionaryId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		dictionary = baseService.saveDictionary(dictionary);
		return list();
	}

	
	public String delete() throws Exception {
		baseService.deleteDictionary(dictionaryId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = baseService.getDictionaryPage(pageInfo, getAdmin()
				.getAccount(), queryName);
		return SUCCESS;
	}

	

	
	public Dictionary getModel() {
		return dictionary;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	

}
