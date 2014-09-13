package com.weaforce.system.dao.impl.organ;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.common.bean.Node;
import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.organ.IOrganDao;
import com.weaforce.system.entity.organ.Organ;

@Repository("organDao")
public class OrganDao extends GenericDao<Organ, Long> implements IOrganDao {

	private static final String QUERY_ORGAN = " From Organ a where  a.account=? ";
	private static final String QUERY_ORGAN_BY_FID = " From Organ a where a.account=? And  a.organParent.organId = ? ";
	private static final String QUERY_ORGAN_BY_ID = " From Organ a where  a.account=? and a.organId = ? ";
	private static final String QUERY_ORGAN_BY_SELF = " From Organ a Where a.account=? And a.organParent.organId is null ";

	public List<Organ> getOrganList(String account) {
		return listQuery(QUERY_ORGAN, account);
	}

	public List<Node> initOrganTree(Long organFid, String account) {
		List<Node> nodes = new ArrayList<Node>();
		List<Organ> tempList = new ArrayList<Organ>();
		if (organFid == 0) {
			tempList = listQuery(QUERY_ORGAN_BY_FID, account, organFid);
		} else {
			tempList = listQuery(QUERY_ORGAN_BY_ID, account, organFid);
		}
		Node node = new Node();
		for (Organ o : tempList) {
			node.setId(o.getOrganId());
			node.setName(o.getOrganName());
			node.setPid(o.getOrganParent().getOrganId());
			nodes.add(node);
			node = new Node();
		}
		return nodes;
	}

	public List<Node> getOrganNode(Long organId, String account) {
		List<Node> nodes = new ArrayList<Node>();
		List<Organ> tempList = listQuery(QUERY_ORGAN_BY_FID, organId, account);
		for (int i = 0; i < tempList.size(); i++) {
			Organ temp = tempList.get(i);
			Node node = new Node();
			node.setId(temp.getOrganId());
			node.setName(temp.getOrganName());
			node.setPid(temp.getOrganParent().getOrganId());
			nodes.add(node);
		}
		return nodes;
	}

	public List<Organ> getOrganListByParent(String account, Long parentId) {
		if (parentId == null || parentId == 1)
			return listQuery(QUERY_ORGAN_BY_SELF, account);
		else
			return listQuery(QUERY_ORGAN_BY_FID, account, parentId);
	}
}
