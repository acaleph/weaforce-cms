package com.weaforce.system.dao.impl.finance;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.finance.IBankDao;
import com.weaforce.system.entity.finance.Bank;

@Repository("bankDao")
public class BankDao extends GenericDao<Bank, Long> implements IBankDao {

}
