package com.weaforce.system.dao.impl.base;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.base.IHelpTipDao;
import com.weaforce.system.entity.help.HelpTip;
@Repository("helpTipDao")
public class HelpTipDao extends GenericDao<HelpTip, Long> implements
		IHelpTipDao {

}
