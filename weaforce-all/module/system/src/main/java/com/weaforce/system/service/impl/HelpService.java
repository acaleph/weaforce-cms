package com.weaforce.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.weaforce.core.util.PageInfo;
import com.weaforce.system.dao.base.IHelpDao;
import com.weaforce.system.dao.base.IHelpTipDao;
import com.weaforce.system.entity.help.Help;
import com.weaforce.system.entity.help.HelpTip;
import com.weaforce.system.service.IHelpService;

@Service("helpService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class HelpService implements IHelpService {
	@Autowired
	@Qualifier("helpDao")
	private IHelpDao helpDao;
	@Autowired
	@Qualifier("helpTipDao")
	private IHelpTipDao helpTipDao;

	public void deleteHelp(Long helpId) {
		helpDao.delete(helpId);
	}

	public void deleteHelpTip(Long tipId) {
		helpTipDao.delete(tipId);
	}

	public Help getHelp(Long helpId) {
		return helpDao.loadEntity(helpId);
	}

	public List<Help> getHelpList(Long moduleId) {
		return null;
	}

	public PageInfo<Help> getHelpPage(PageInfo<Help> pageInfo, String account) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Help a Where a.account= '" + account + "' ");
		pageInfo = helpDao.listQuery(pageInfo, "Select Count(*)"
				+ sb.toString(), sb.toString() + " Order by a.helpTitle ");
		return pageInfo;
	}

	public HelpTip getHelpTip(Long tipId) {
		return null;
	}

	public List<HelpTip> getHelpTipList(Long moduleId) {
		return null;
	}

	public PageInfo<HelpTip> getHelpTipPage(PageInfo<HelpTip> pageInfo, String account) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From HelpTip a Where a.account = '" + account + "' ");
		pageInfo = helpTipDao.listQuery(pageInfo, "Select Count(*)"
				+ sb.toString(), sb.toString() + " Order by a.tipTitle ");
		return pageInfo;
	}

	public Help saveHelp(Help o) {
		return helpDao.save(o);
	}

	public HelpTip saveHelpTip(HelpTip o) {
		return helpTipDao.save(o);
	}

}
