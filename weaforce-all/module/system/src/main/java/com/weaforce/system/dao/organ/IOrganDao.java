package com.weaforce.system.dao.organ;

import java.util.List;

import com.weaforce.core.common.bean.Node;
import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.organ.Organ;

public interface IOrganDao extends IGenericDao<Organ, Long> {

	public List<Organ> getOrganList(String account);
	
	public List<Node> initOrganTree(Long organFid, String userAccount);

	public List<Node> getOrganNode(Long organId, String account);

	
	public List<Organ> getOrganListByParent(String account,Long parentId);
	
}
