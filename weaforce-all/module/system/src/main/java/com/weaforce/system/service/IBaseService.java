package com.weaforce.system.service;

import java.io.IOException;
import java.util.List;

import com.weaforce.core.common.bean.Node;
import com.weaforce.core.util.PageInfo;
import com.weaforce.system.entity.base.Board;
import com.weaforce.entity.app.Business;
import com.weaforce.system.entity.base.Calendar;
import com.weaforce.system.entity.base.Dictionary;
import com.weaforce.system.entity.base.File;
import com.weaforce.system.entity.base.DictionaryItem;
import com.weaforce.entity.app.Module;
import com.weaforce.system.entity.base.Unit;

public interface IBaseService {

	public List<Node> getPeriodNode();

	public Business getBusiness(Long businessId);

	/**
	 * 取得模块list
	 * 
	 * @return
	 */
	public List<Business> getBusinessList();

	/**
	 * 根据资源标识,取得模块list
	 * 
	 * @param isResource
	 *            标识
	 * @return
	 */
	public List<Business> getBusinessListByResource(String isResource);

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
	public Module prepareModule(Module o, Long moduleId, Long businessId);

	public void deleteModule(Long moduleId);

	/**
	 * 保存实体注册
	 * 
	 * @param o
	 * @param businessId
	 * @return
	 */
	public Module saveModule(Module o, Long businessId);

	/**
	 * 根据资源标识取得Module list
	 * 
	 * @param isResource
	 * @return
	 */
	public List<Module> getModuleListByBusinessResource(Long businessId,
			String isResource);

	public PageInfo<Module> getModulePage(PageInfo<Module> pageInfo,
			Long businessId, String moduleQueryNameEn);

	/**
	 * 取得数据字典与附件组合列表
	 * 
	 * @param entityDic
	 *            数据字典
	 * @param entityAtt
	 *            附件
	 * @return
	 */
	public List<Module> getModuleList(String entityDic, String entityAtt);

	/**
	 * 根据系统,取得模块JSON数据
	 * 
	 * @param businessId
	 *            系统
	 * @return
	 */
	public String getModuleDDL(Long businessId);

	/**
	 * 预处理字典
	 * 
	 * @param o
	 *            字典
	 * @param dictionaryId
	 *            字典主键
	 * @return
	 */
	public Dictionary prepareDictionary(Dictionary o, Long dictionaryId);

	/**
	 * 取得字典
	 * 
	 * @param dictionaryId
	 * @return
	 */
	public Dictionary getDictionary(Long dictionaryId);

	/**
	 * 保存字典
	 * 
	 * @param o
	 * @return
	 */
	public Dictionary saveDictionary(Dictionary o);

	/**
	 * 删除字典
	 * 
	 * @param dictionaryId
	 */
	public void deleteDictionary(Long dictionaryId);

	/**
	 * 取得字典 list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<Dictionary> getDictionaryList(String account);

	/**
	 * 取得字典 page
	 * 
	 * @param pageInfo
	 * @param account
	 * @param dictionaryName
	 * @return
	 */
	public PageInfo<Dictionary> getDictionaryPage(
			PageInfo<Dictionary> pageInfo, String account, String dictionaryName);

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
	public DictionaryItem prepareItem(DictionaryItem o, Long itemId,
			Long dictionaryId);

	public DictionaryItem getItem(Long itemId);

	public DictionaryItem saveItem(DictionaryItem o);

	/**
	 * 取得项目 list
	 * 
	 * @param dictionaryId
	 *            字典主键
	 * @return
	 */
	public List<DictionaryItem> getItemListByDictionary(Long dictionaryId);

	public PageInfo<DictionaryItem> getItemPage(
			PageInfo<DictionaryItem> pageInfo, String account,
			Long dictionaryId, String queryItemAnnexb);

	/**
	 * 预处理文件
	 * 
	 * @param o
	 * @param fileId
	 * @return
	 */
	public File prepareFile(File o, Long fileId);

	public File getFile(Long fileId);

	public void deleteFile(Long fileId);

	public File saveFile(File o);

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
			throws IOException;

	public List<File> getFileListByModule(String account, String creator,
			Long moduleId, Long moduleInstanceId);

	public PageInfo<File> getFilePage(PageInfo<File> pageInfo, String account,
			String creator, Long queryModuleId, String queryFileLocal);

	/**
	 * 取得日历
	 * 
	 * @param calendarId
	 * @return
	 */
	public Calendar getCalendar(Long calendarId);

	/**
	 * 删除日历
	 * 
	 * @param calendarId
	 */
	public void deleteCalendar(Long calendarId);

	/**
	 * 保存日历
	 * 
	 * @param o
	 * @return
	 */

	public Calendar saveCalendar(Calendar o);

	/**
	 * 取得日历page
	 * 
	 * @param pageInfo
	 * @param account
	 * @return
	 */
	public PageInfo<Calendar> getCalendarPage(PageInfo<Calendar> pageInfo,
			String account);

	/**
	 * 预处理单位
	 * 
	 * @param o
	 * @param unitId
	 * @return
	 */

	public Unit prepareUnit(Unit o, Long unitId);

	public Unit getUnit(Long unitId);

	public void deleteUnit(Long unitId);

	public Unit saveUnit(Unit o);

	public List<Unit> getUnitList();

	public PageInfo<Unit> getUnitPage(PageInfo<Unit> pageInfo,
			String queryUnitCode, String queryUnitName);

	public Board getBoard(Long boardId);

	public void deleteBoard(Long boardId);

	public Board saveBoard(Board o);

	public PageInfo<Board> getBoardPage(PageInfo<Board> pageInfo,
			String account, String queryTitle, String queryDate);

}
