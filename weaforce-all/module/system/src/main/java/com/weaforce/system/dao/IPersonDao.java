package com.weaforce.system.dao;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.base.Person;

public interface IPersonDao extends IGenericDao<Person, Long> {

	public List<Person> getPersonList(String account);
}
