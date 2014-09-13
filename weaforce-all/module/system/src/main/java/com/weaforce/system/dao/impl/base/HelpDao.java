package com.weaforce.system.dao.impl.base;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.base.IHelpDao;
import com.weaforce.system.entity.help.Help;
@Repository("helpDao")
public class HelpDao extends GenericDao<Help, Long> implements IHelpDao {

}
