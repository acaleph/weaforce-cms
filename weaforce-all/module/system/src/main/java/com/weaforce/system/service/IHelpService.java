package com.weaforce.system.service;

import java.util.List;

import com.weaforce.core.util.PageInfo;
import com.weaforce.system.entity.help.Help;
import com.weaforce.system.entity.help.HelpTip;

public interface IHelpService {
	public Help getHelp(Long helpId);

	public List<Help> getHelpList(Long moduleId);

	public void deleteHelp(Long helpId);

	public PageInfo<Help> getHelpPage(PageInfo<Help> pageInfo,String account);

	public Help saveHelp(Help o);

	public HelpTip getHelpTip(Long tipId);

	public List<HelpTip> getHelpTipList(Long moduleId);

	public void deleteHelpTip(Long tipId);

	public PageInfo<HelpTip> getHelpTipPage(PageInfo<HelpTip> pageInfo,String account);

	public HelpTip saveHelpTip(HelpTip o);
}
