package com.weaforce.system.dao.impl.base;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.base.IBoardDao;
import com.weaforce.system.entity.base.Board;
@Repository("boardDao")
public class BoardDao extends GenericDao<Board, Long> implements IBoardDao {

}
