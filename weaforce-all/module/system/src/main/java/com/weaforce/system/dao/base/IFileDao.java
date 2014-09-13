package com.weaforce.system.dao.base;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.base.File;

public interface IFileDao extends IGenericDao<File, Long> {
	public List<File> getFileListByModule(String account, String creator,
			Long moduleId, Long moduleInstanceId);
}
