package com.weaforce.system.dao.impl.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.base.IFileDao;
import com.weaforce.system.entity.base.File;

@Repository("fileDao")
public class FileDao extends GenericDao<File, Long> implements IFileDao {
	private static final String QUERY_FILE_BY_MODULE = " From File a Where a.account=? And a.creator=? And a.fileModuleId=? And a.fileModuleInstanceId=? ";

	public List<File> getFileListByModule(String account, String creator,
			Long moduleId, Long moduleInstanceId) {
		return listQuery(QUERY_FILE_BY_MODULE, account, creator, moduleId,
				moduleInstanceId);
	}
}
