package com.weaforce.system.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.weaforce.core.common.bean.Node;
import com.weaforce.core.util.DateUtil;
import com.weaforce.core.util.Global;
import com.weaforce.core.util.PageInfo;
import com.weaforce.core.util.StringUtil;
import com.weaforce.system.dao.base.IBoardDao;
import com.weaforce.system.dao.base.IBusinessDao;
import com.weaforce.system.dao.base.ICalendarDao;
import com.weaforce.system.dao.base.IDictionaryDao;
import com.weaforce.system.dao.base.IFileDao;
import com.weaforce.system.dao.base.IItemDao;
import com.weaforce.system.dao.base.IModuleDao;
import com.weaforce.system.dao.base.IUnitDao;
import com.weaforce.system.entity.base.Board;
import com.weaforce.entity.app.Business;
import com.weaforce.system.entity.base.Calendar;
import com.weaforce.system.entity.base.Dictionary;
import com.weaforce.system.entity.base.File;
import com.weaforce.system.entity.base.DictionaryItem;
import com.weaforce.entity.app.Module;
import com.weaforce.system.entity.base.Unit;
import com.weaforce.system.service.IBaseService;
import com.weaforce.system.util.FileUtils;

@Service("baseService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BaseService implements IBaseService {
	@Autowired
	@Qualifier("businessDao")
	private IBusinessDao businessDao;
	@Autowired
	@Qualifier("moduleDao")
	private IModuleDao moduleDao;
	@Autowired
	@Qualifier("dictionaryDao")
	private IDictionaryDao dictionaryDao;
	@Autowired
	@Qualifier("itemDao")
	private IItemDao itemDao;
	@Autowired
	@Qualifier("fileDao")
	private IFileDao fileDao;
	@Autowired
	@Qualifier("calendarDao")
	private ICalendarDao calendarDao;
	@Autowired
	@Qualifier("unitDao")
	private IUnitDao unitDao;
	@Autowired
	@Qualifier("boardDao")
	private IBoardDao boardDao;

	@Transactional(readOnly = true)
	public List<Node> getPeriodNode() {
		List<Node> nodes = new ArrayList<Node>();
		Node node = new Node();
		node.setId(Global.INTERVAL_DAY);
		node.setName("-- Day --");
		nodes.add(node);
		node = new Node();
		node.setId(Global.INTERVAL_WEEK);
		node.setName("-- Week --");
		nodes.add(node);
		node = new Node();
		node.setId(Global.INTERVAL_MONTH * 1000);
		node.setName("-- Month --");
		nodes.add(node);
		node = new Node();
		node.setId(Global.INTERVAL_YEAR * 1000);
		node.setName("-- Year --");
		nodes.add(node);
		return nodes;
	}

	@Transactional(readOnly = true)
	public Business getBusiness(Long businessId) {
		return businessDao.loadEntity(businessId);
	}

	/**
	 * 取得模块list
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Business> getBusinessList() {
		return businessDao.getBusinessList();
	}

	/**
	 * 根据资源标识,取得模块list
	 * 
	 * @param isResource
	 *            标识
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Business> getBusinessListByResource(String isResource) {
		return businessDao.getBusinessListByResource(isResource);
	}

	/**
	 * Prepare module instance
	 * 
	 * @param o
	 *            Module
	 * @param moduleId
	 *            Module primary key
	 * @param businessId
	 *            Business primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public Module prepareModule(Module o, Long moduleId, Long businessId) {
		if (moduleId == null)
			o = new Module();
		else
			o = moduleDao.loadEntity(moduleId);
		o.setModuleBusiness(businessDao.loadEntity(businessId));
		return o;
	}

	public void deleteModule(Long moduleId) {
		moduleDao.delete(moduleId);
	}

	/**
	 * 保存实体注册
	 * 
	 * @param o
	 * @param businessId
	 * @return
	 */
	public Module saveModule(Module o, Long businessId) {
		if (businessId != null)
			o.setModuleBusiness(businessDao.loadEntity(businessId));
		else
			o.setModuleBusiness(null);
		return moduleDao.save(o);
	}

	/**
	 * 根据资源标识取得Module list
	 * 
	 * @param isResource
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Module> getModuleListByBusinessResource(Long businessId,
			String isResource) {
		return moduleDao
				.getModuleListByBusinessResource(businessId, isResource);
	}

	@Transactional(readOnly = true)
	public PageInfo<Module> getModulePage(PageInfo<Module> pageInfo,
			Long businessId, String moduleQueryNameEn) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Module a ");
		if (businessId != null) {
			sb.append(" Where a.moduleBusiness.businessId=" + businessId);
			if (StringUtil.isNotEmpty(moduleQueryNameEn))
				sb.append(" And a.moduleNameEn like " + "'%"
						+ moduleQueryNameEn + "%'");
		} else if (StringUtil.isNotEmpty(moduleQueryNameEn)) {
			sb.append(" Where a.moduleNameEn like " + "'%" + moduleQueryNameEn
					+ "%'");
		}
		pageInfo = moduleDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.moduleNameEn ");
		return pageInfo;
	}

	/**
	 * 取得数据字典与附件组合列表
	 * 
	 * @param entityDic
	 *            数据字典
	 * @param entityAtt
	 *            附件
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Module> getModuleList(String entityDic, String entityAtt) {
		return moduleDao.getModuleList(entityDic, entityAtt);
	}

	/**
	 * 根据系统,取得模块JSON数据
	 * 
	 * @param businessId
	 *            系统
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getModuleDDL(Long businessId) {
		StringBuffer sb = new StringBuffer();
		List<Module> moduleList = moduleDao.getModuleListByBusinessResource(
				businessId, "1");
		for (Module m : moduleList)
			if (sb.length() == 0)
				sb.append("[{\"value\":\"" + m.getModuleId()
						+ "\",\"caption\":\"" + m.getModuleNameCn() + "\"}");
			else
				sb.append(",{\"value\":\"" + m.getModuleId()
						+ "\",\"caption\":\"" + m.getModuleNameCn() + "\"}");
		if (sb.length() > 0)
			sb.append("]");
		return sb.toString();
	}

	/**
	 * 预处理字典
	 * 
	 * @param o
	 *            字典
	 * @param dictionaryId
	 *            字典主键
	 * @return
	 */
	@Transactional(readOnly = true)
	public Dictionary prepareDictionary(Dictionary o, Long dictionaryId) {
		if (dictionaryId == null)
			o = new Dictionary();
		else
			o = dictionaryDao.loadEntity(dictionaryId);
		return o;
	}

	/**
	 * 取得字典
	 * 
	 * @param dictionaryId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Dictionary getDictionary(Long dictionaryId) {
		return dictionaryDao.loadEntity(dictionaryId);
	}

	/**
	 * 保存字典
	 * 
	 * @param o
	 * @return
	 */
	public Dictionary saveDictionary(Dictionary o) {
		return dictionaryDao.save(o);
	}

	/**
	 * 删除字典
	 * 
	 * @param dictionaryId
	 */
	public void deleteDictionary(Long dictionaryId) {
		dictionaryDao.delete(dictionaryId);
	}

	/**
	 * 取得字典 list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Dictionary> getDictionaryList(String account) {
		return dictionaryDao.getDictionaryList(account);
	}

	/**
	 * 取得字典 page
	 * 
	 * @param pageInfo
	 * @param account
	 * @param dictionaryName
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<Dictionary> getDictionaryPage(
			PageInfo<Dictionary> pageInfo, String account, String dictionaryName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Dictionary a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(dictionaryName)) {
			sb.append(" And a.dictionaryName like " + "'%" + dictionaryName
					+ "%'");
		}
		pageInfo = dictionaryDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString());
		return pageInfo;
	}

	/**
	 * 预处理项目
	 * 
	 * @param o
	 *            项目
	 * @param itemId
	 *            项目主键
	 * @param dictionaryId
	 *            字典主键
	 * @return
	 */
	@Transactional(readOnly = true)
	public DictionaryItem prepareItem(DictionaryItem o, Long itemId,
			Long dictionaryId) {
		if (itemId == null) {
			o = new DictionaryItem();
			if (dictionaryId != null)
				o.setItemDictionaryId(dictionaryId);
		} else
			o = itemDao.loadEntity(itemId);
		return o;
	}

	@Transactional(readOnly = true)
	public DictionaryItem getItem(Long itemId) {
		return itemDao.loadEntity(itemId);
	}

	public DictionaryItem saveItem(DictionaryItem o) {
		return itemDao.save(o);
	}

	/**
	 * 取得项目 list
	 * 
	 * @param dictionaryId
	 *            字典主键
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<DictionaryItem> getItemListByDictionary(Long dictionaryId) {
		return itemDao.getItemListByDictionary(dictionaryId);
	}

	@Transactional(readOnly = true)
	public PageInfo<DictionaryItem> getItemPage(
			PageInfo<DictionaryItem> pageInfo, String account,
			Long dictionaryId, String queryItemAnnexb) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Item a Where a.account= '" + account + "' ");
		if (dictionaryId != null) {
			sb.append(" And a.itemDictionaryId = " + dictionaryId);
		}
		pageInfo = itemDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.itemGroupOrder ");
		return pageInfo;
	}

	/**
	 * 预处理文件
	 * 
	 * @param o
	 * @param fileId
	 * @return
	 */
	@Transactional(readOnly = true)
	public File prepareFile(File o, Long fileId) {
		if (fileId == null)
			o = new File();
		else
			o = fileDao.loadEntity(fileId);
		return o;
	}

	@Transactional(readOnly = true)
	public File getFile(Long fileId) {
		return fileDao.loadEntity(fileId);
	}

	public void deleteFile(Long fileId) {
		File o = fileDao.loadEntity(fileId);
		if (o != null) {
			fileDao.delete(o);
			FileUtils.deleteFile(FileUtils.getFileServerLocation() + "\\"
					+ o.getFileServer());
		}
	}

	public File saveFile(File o) {
		return fileDao.save(o);
	}

	/**
	 * 上载文件
	 * 
	 * @param moduleId
	 *            模块
	 * @param uploadFileName
	 *            上载文件名称
	 * @param uploads
	 *            文件数组
	 * @param fileVersionMaxs
	 *            版本
	 * @param fileVersionMids
	 *            版本
	 * @param fileVersionMins
	 *            版本
	 * @throws IOException
	 */
	public void uploadFile(Long moduleId, List<String> uploadFileName,
			List<java.io.File> uploads, List<String> fileVersionMaxs,
			List<String> fileVersionMids, List<String> fileVersionMins)
			throws IOException {
		for (String fileName : uploadFileName) {
			File f = new File();
			f.setFileLocal(fileName.toLowerCase());
			f.setFileModuleId(moduleId);
			int fileIndex = uploadFileName.indexOf(fileName);
			String extension = fileName.substring(
					fileName.lastIndexOf(".") + 1, fileName.length())
					.toLowerCase();
			if (FileUtils.isAllowed(extension)) {
				f.setFileExtension(extension);
				f.setFileVersionMax(Byte.valueOf(fileVersionMaxs.get(fileIndex)));
				f.setFileVersionMid(Byte.valueOf(fileVersionMids.get(fileIndex)));
				f.setFileVersionMin(Byte.valueOf(fileVersionMins.get(fileIndex)));
				// Default is file name
				// f.setFileUrl(com.weaforce.core.util.Global.FILE_SERVER_LOCATION);
				f.setFileUrl(FileUtils.getServerLocation());
				f = fileDao.save(f);
				// 取得服务器端文件名称
				String fileNameTemplate = com.weaforce.core.util.Global.FILE_TEMPLATE;
				String fileNameServer = fileNameTemplate.substring(0, 20 - f
						.getFileId().toString().length())
						+ f.getFileId().toString() + "." + f.getFileExtension();
				f.setFileServer(fileNameServer);
				final java.io.File src = uploads.get(fileIndex);
				final java.io.File dst = new java.io.File(f.getFileUrl() + "\\"
						+ fileNameServer);
				f.setFileLength(src.length());
				fileDao.save(f);
				// 完全保存文件信息至DB后,再执行上传
				org.apache.commons.io.FileUtils.copyFile(src, dst);
			}
		}
	}

	@Transactional(readOnly = true)
	public List<File> getFileListByModule(String account, String creator,
			Long moduleId, Long moduleInstanceId) {
		return fileDao.getFileListByModule(account, creator, moduleId,
				moduleInstanceId);
	}

	@Transactional(readOnly = true)
	public PageInfo<File> getFilePage(PageInfo<File> pageInfo, String account,
			String creator, Long queryModuleId, String queryFileLocal) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From File a Where a.account= '" + account
				+ "' And a.creator ='" + creator
				+ "' And a.fileModuleInstanceId='0'");
		if (queryModuleId != null)
			sb.append(" And a.fileModuleId = '" + queryModuleId + "' ");
		if (StringUtil.isNotEmpty(queryFileLocal))
			sb.append(" And a.fileLocal like '%" + queryFileLocal + "%' ");
		pageInfo = fileDao
				.listQuery(
						pageInfo,
						"Select Count(*)" + sb.toString(),
						sb.toString()
								+ " Order by a.fileLocal,a.fileVersionMax,a.fileVersionMid,a.fileVersionMin ");
		return pageInfo;

	}

	/**
	 * 取得日历
	 * 
	 * @param calendarId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Calendar getCalendar(Long calendarId) {
		return calendarDao.loadEntity(calendarId);
	}

	/**
	 * 删除日历
	 * 
	 * @param calendarId
	 */
	public void deleteCalendar(Long calendarId) {
		calendarDao.delete(calendarId);
	}

	/**
	 * 保存日历
	 * 
	 * @param o
	 * @return
	 */
	public Calendar saveCalendar(Calendar o) {
		return calendarDao.save(o);
	}

	/**
	 * 取得日历page
	 * 
	 * @param pageInfo
	 * @param account
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<Calendar> getCalendarPage(PageInfo<Calendar> pageInfo,
			String account) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Calendar a Where a.account= '" + account + "' ");
		pageInfo = calendarDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.calendarDate ");
		return pageInfo;
	}

	/**
	 * 预处理单位
	 * 
	 * @param o
	 * @param unitId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Unit prepareUnit(Unit o, Long unitId) {
		if (unitId == null)
			o = new Unit();
		else
			o = unitDao.loadEntity(unitId);
		return o;
	}

	@Transactional(readOnly = true)
	public Unit getUnit(Long unitId) {
		return unitDao.loadEntity(unitId);
	}

	public void deleteUnit(Long unitId) {
		unitDao.delete(unitId);
	}

	public Unit saveUnit(Unit o) {
		o.setUnitCode(o.getUnitCode().toUpperCase());
		return unitDao.save(o);
	}

	@Transactional(readOnly = true)
	public List<Unit> getUnitList() {
		return unitDao.getUnitList();
	}

	@Transactional(readOnly = true)
	public PageInfo<Unit> getUnitPage(PageInfo<Unit> pageInfo,
			String queryUnitCode, String queryUnitName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Unit a ");
		if (StringUtil.isNotEmpty(queryUnitCode)) {
			sb.append(" Where a.unitCode like " + "'%" + queryUnitCode + "%'");
			if (StringUtil.isNotEmpty(queryUnitName))
				sb.append(" And a.unitName like " + "'%" + queryUnitName + "%'");
		} else {
			if (StringUtil.isNotEmpty(queryUnitName))
				sb.append(" Where a.unitName like " + "'%" + queryUnitName
						+ "%'");
		}
		pageInfo = unitDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.unitCode ");
		return pageInfo;
	}

	@Transactional(readOnly = true)
	public Board getBoard(Long boardId) {
		return boardDao.loadEntity(boardId);
	}

	public void deleteBoard(Long boardId) {
		boardDao.delete(boardId);
	}

	public Board saveBoard(Board o) {
		return boardDao.save(o);
	}

	@Transactional(readOnly = true)
	public PageInfo<Board> getBoardPage(PageInfo<Board> pageInfo,
			String account, String queryTitle, String queryDate) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Board a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(queryTitle))
			sb.append(" And a.boardTitle like " + "'%" + queryTitle + "%'");
		if (StringUtil.isNotEmpty(queryDate))
			sb.append(" And a.boardDate = '" + DateUtil.getUTCDate(queryDate)
					+ "'");
		pageInfo = boardDao.listQuery(pageInfo,
				" Select Count(*) " + sb.toString(), sb.toString()
						+ " Order by a.boardDate desc ");
		return pageInfo;
	}

}
