package com.weaforce.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.INoteDao;
import com.weaforce.cms.entity.Note;
import com.weaforce.core.dao.impl.GenericDao;
@Repository("noteDao")
public class NoteDao extends GenericDao<Note, Long> implements INoteDao {

}
