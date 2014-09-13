package com.weaforce.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.IPersonDao;
import com.weaforce.system.entity.base.Person;

@Repository("personDao")
public class PersonDao extends GenericDao<Person, Long> implements IPersonDao {
	private static final String QUERY_PEOPLE = " From Person a Where a.account=? ";

	public List<Person> getPersonList(String account) {
		return listQuery(QUERY_PEOPLE, account);
	}
}
