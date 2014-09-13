package com.weaforce.system.action.base;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.entity.base.Dictionary;
import com.weaforce.system.entity.base.DictionaryItem;
import com.weaforce.system.service.IBaseService;

@ParentPackage("default")
@Namespace("/system/base")
public class ItemAction extends AbstractCrudAction<DictionaryItem> {
	private static final long serialVersionUID = -8201469975580433258L;
	@Autowired
	@Qualifier("baseService")
	private IBaseService baseService;

	private DictionaryItem item;
	private Long itemId;

	private Long dictionaryId;
	private String queryItemAnnexb;

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	
	protected void prepareModel() throws Exception {
		item = baseService.prepareItem(item, itemId, dictionaryId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String list() throws Exception {
		if (dictionaryId != null)
			pageInfo = baseService.getItemPage(pageInfo, getAdmin()
					.getAccount(), dictionaryId, queryItemAnnexb);
		return SUCCESS;
	}

	
	public String lock() throws Exception {
		return save();
	}

	
	public String delete() throws Exception {

		return list();
	}

	
	public String save() throws Exception {
		item = baseService.saveItem(item);
		return list();
	}

	public Long getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(Long dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	public String getQueryItemAnnexb() {
		return queryItemAnnexb;
	}

	public void setQueryItemAnnexb(String queryItemAnnexb) {
		this.queryItemAnnexb = queryItemAnnexb;
	}

	/**
	 * 通用字典
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Dictionary> getDictionaryList() throws Exception {
		return baseService.getDictionaryList(getAdmin().getAccount());
	}

	public DictionaryItem getModel() {
		return item;
	}
}
