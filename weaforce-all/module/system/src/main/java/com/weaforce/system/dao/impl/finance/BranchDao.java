package com.weaforce.system.dao.impl.finance;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.finance.IBranchDao;
import com.weaforce.system.entity.finance.BankBranch;
@Repository("bankBranchDao")
public class BranchDao extends GenericDao<BankBranch, Long> implements
		IBranchDao {

}
