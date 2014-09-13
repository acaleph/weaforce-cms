package com.weaforce.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weaforce.cms.dao.ads.IAdsDao;
import com.weaforce.cms.dao.rent.IBuildingDao;
import com.weaforce.cms.dao.rent.IHouseDao;
import com.weaforce.cms.dao.rent.IPhotoDao;
import com.weaforce.cms.dao.rent.IHouseTypeDao;
import com.weaforce.cms.dao.rent.IRentTagDao;
import com.weaforce.cms.entity.rent.Building;
import com.weaforce.cms.entity.rent.House;
import com.weaforce.cms.entity.rent.HousePhoto;
import com.weaforce.cms.entity.rent.HouseType;
import com.weaforce.cms.entity.rent.RentTag;
import com.weaforce.cms.service.IRentService;
import com.weaforce.core.util.PageInfo;
import com.weaforce.core.util.StringUtil;
import com.weaforce.system.dao.area.IZoneDao;

/**
 * 出租服务
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Service("rentService")
@Transactional(rollbackFor = Exception.class)
public class RentService implements IRentService {
	@Autowired
	@Qualifier("zoneDao")
	private IZoneDao zoneDao;
	@Autowired
	@Qualifier("adsDao")
	private IAdsDao adsDao;
	@Autowired
	@Qualifier("buildingDao")
	private IBuildingDao buildingDao;
	@Autowired
	@Qualifier("rentTagDao")
	private IRentTagDao tagDao;
	@Autowired
	@Qualifier("houseTypeDao")
	private IHouseTypeDao typeDao;
	@Autowired
	@Qualifier("houseDao")
	private IHouseDao houseDao;
	@Autowired
	@Qualifier("housePhotoDao")
	private IPhotoDao photoDao;

	/**
	 * 预处理建筑
	 * 
	 * @param o
	 * @param buildingId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Building prepareBuilding(Building o, Long buildingId) {
		if (buildingId == null)
			o = new Building();
		else
			o = buildingDao.loadEntity(buildingId);
		return o;
	}

	/**
	 * 取得建筑
	 * 
	 * @param buildingId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Building getBuilding(Long buildingId) {
		return buildingDao.loadEntity(buildingId);
	}

	/**
	 * 保存建筑
	 * 
	 * @param o
	 *            建筑
	 * @param zoneId
	 *            所在区
	 * @param adsId
	 *            广告商家
	 * @return
	 */
	public Building saveBuilding(Building o, Long zoneId, Long adsId) {
		if (zoneId != null)
			o.setBuildingZone(zoneDao.loadEntity(zoneId));
		else
			o.setBuildingZone(null);
		if (adsId != null)
			o.setBuildingAds(adsDao.loadEntity(adsId));
		else
			o.setBuildingAds(null);
		return buildingDao.save(o);
	}

	/**
	 * 删除建筑
	 * 
	 * @param buildingId
	 */
	public void deleteBuilding(Long buildingId) {
		buildingDao.delete(buildingId);
	}

	/**
	 * 根据城区,取得建筑 list
	 * 
	 * @param zoneId
	 * @return
	 */

	public List<Building> getBuildingListByZone(Long zoneId) {
		return buildingDao.getBuildingListByZone(zoneId);
	}

	/**
	 * 根据广告商家,取得建筑 list
	 * 
	 * @param adsId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Building> getBuildingListByAds(Long adsId) {
		return buildingDao.getBuildingListByAds(adsId);
	}

	/**
	 * 根据城区，取得建筑 Drop Down List
	 * 
	 * @param zoneId
	 *            城区主键
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getBuildingDDL(Long zoneId) {
		StringBuffer sb = new StringBuffer();
		List<Building> buildingList = buildingDao.getBuildingListByZone(zoneId);
		for (Building o : buildingList)
			if (sb.length() == 0)
				sb.append("[{\"value\":\"" + o.getBuildingId()
						+ "\",\"caption\":\"" + o.getBuildingName() + "\"}");
			else
				sb.append(",{\"value\":\"" + o.getBuildingId()
						+ "\",\"caption\":\"" + o.getBuildingName() + "\"}");
		if (sb.length() > 0)
			sb.append("]");
		return sb.toString();
	}

	/**
	 * 取得建筑 page
	 * 
	 * @param pageInfo
	 * @param zoneId
	 * @param buildingName
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<Building> getBuildingPage(PageInfo<Building> pageInfo,
			Long zoneId, String buildingName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Building a Where a.buildingZone.zoneId=" + zoneId);
		if (StringUtil.isNotEmpty(buildingName))
			sb.append(" And a.buildingName like " + "'%" + buildingName + "%'");
		pageInfo = buildingDao.listQuery(pageInfo, "Select Count(*)"
				+ sb.toString(), sb.toString() + " Order by a.buildingName ");
		return pageInfo;
	}

	/**
	 * 预处理标签
	 * 
	 * @param o
	 * @param tagId
	 * @return
	 */
	@Transactional(readOnly = true)
	public RentTag prepareTag(RentTag o, Long tagId) {
		if (tagId == null)
			o = new RentTag();
		else
			o = tagDao.loadEntity(tagId);
		return o;
	}

	/**
	 * 取得标签
	 * 
	 * @param tagId
	 * @return
	 */
	@Transactional(readOnly = true)
	public RentTag getTag(Long tagId) {
		return tagDao.loadEntity(tagId);
	}

	/**
	 * 保存标签
	 * 
	 * @param o
	 * @return
	 */
	public RentTag saveTag(RentTag o) {
		return tagDao.save(o);
	}

	/**
	 * 删除标签
	 * 
	 * @param tagId
	 */
	public void deleteTag(Long tagId) {
		tagDao.delete(tagId);
	}

	/**
	 * 取得房间价格标签 list
	 * 
	 * @return
	 */
	public List<RentTag> getTagList() {
		return tagDao.getTagList();
	}

	/**
	 * 取得标签 page
	 * 
	 * @param pageInfo
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<RentTag> getTagPage(PageInfo<RentTag> pageInfo) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From RentTag a ");
		pageInfo = tagDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString());
		return pageInfo;
	}

	/**
	 * 预处理房间类型
	 * 
	 * @param o
	 * @param typeId
	 * @return
	 */
	@Transactional(readOnly = true)
	public HouseType prepareType(HouseType o, Long typeId) {
		if (typeId == null)
			o = new HouseType();
		else
			o = typeDao.loadEntity(typeId);
		return o;
	}

	/**
	 * 取得房间类型
	 * 
	 * @param typeId
	 * @return
	 */
	@Transactional(readOnly = true)
	public HouseType getType(Long typeId) {
		return typeDao.loadEntity(typeId);
	}

	/**
	 * 保存房间类型
	 * 
	 * @param o
	 * @return
	 */
	public HouseType saveType(HouseType o) {
		return typeDao.save(o);
	}

	/**
	 * 删除房间类型
	 * 
	 * @param typeId
	 */
	public void deleteType(Long typeId) {
		typeDao.delete(typeId);
	}

	/**
	 * 取得房间价格标签 list
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<HouseType> getTypeList() {
		return typeDao.getTypeList();
	}

	/**
	 * 取得房间类型 page
	 * 
	 * @param pageInfo
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<HouseType> getTypePage(PageInfo<HouseType> pageInfo) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From HouseType a ");
		pageInfo = typeDao.listQuery(pageInfo, "Select Count(*)"
				+ sb.toString(), sb.toString());
		return pageInfo;
	}

	/**
	 * 预处理房间
	 * 
	 * @param o
	 * @param houseId
	 * @param buildingId
	 * @return
	 */
	@Transactional(readOnly = true)
	public House prepareHouse(House o, Long houseId, Long buildingId) {
		if (houseId == null) {
			o = new House();
			if (buildingId != null)
				o.setHouseBuilding(buildingDao.loadEntity(buildingId));
		} else
			o = houseDao.loadEntity(houseId);
		return o;

	}

	/**
	 * 取得房间
	 * 
	 * @param houseId
	 * @return
	 */
	@Transactional(readOnly = true)
	public House getHouse(Long houseId) {
		return houseDao.loadEntity(houseId);
	}

	/**
	 * 保存房间
	 * 
	 * @param o
	 *            房间
	 * @param buildingId
	 *            建筑主键
	 * @param typeId
	 *            房间类型主键
	 * @param tagId
	 *            价格标签主键
	 * @return
	 */
	public House saveHouse(House o, Long buildingId, Long typeId, Long tagId) {
		if (buildingId != null)
			o.setHouseBuilding(buildingDao.loadEntity(buildingId));
		else
			o.setHouseBuilding(null);
		if (typeId != null)
			o.setHouseType(typeDao.loadEntity(typeId));
		else
			o.setHouseType(null);
		if (tagId != null)
			o.setHouseTag(tagDao.loadEntity(tagId));
		else
			o.setHouseTag(null);
		return houseDao.save(o);
	}

	/**
	 * 更新房间
	 * 
	 * @param houseId
	 * @return
	 */
	public House updateHouse(Long houseId) {
		House o = houseDao.loadEntity(houseId);
		o.setLastUpdateTime(System.currentTimeMillis());
		return houseDao.save(o);
	}

	/**
	 * 删除房间
	 * 
	 * @param houseId
	 */
	public void deleteHouse(Long houseId) {
		houseDao.delete(houseId);
	}

	/**
	 * 取得JSON格式房间记录
	 * 
	 * @param buildingId
	 *            建筑主键
	 * @param typeId
	 *            房间类型主键
	 * @param tagId
	 *            出租房价标签主键
	 * @param pageNumber
	 *            当前页数
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getHouseJSON(Long buildingId, Long typeId, Long tagId,
			Integer pageNumber) {
		return houseDao.getHouseJSON(buildingId, typeId, tagId, pageNumber);
	}

	/**
	 * 取得房间 page
	 * 
	 * @param pageInfo
	 * @param buildingId
	 * @param houseTitle
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<House> getHousePage(PageInfo<House> pageInfo,
			Long buildingId, String houseTitle) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From House a ");
		if (buildingId != null)
			sb.append(" Where a.houseBuilding.buildingId =" + buildingId);
		if (StringUtil.isNotEmpty(houseTitle))
			sb.append(" And a.houseTitle like '%" + houseTitle + "%'");
		pageInfo = houseDao.listQuery(pageInfo, "Select Count(*)"
				+ sb.toString(), sb.toString());
		return pageInfo;
	}

	/**
	 * 预处理照片
	 * 
	 * @param o
	 * @param photoId
	 * @param houseId
	 * @return
	 */
	@Transactional(readOnly = true)
	public HousePhoto preparePhoto(HousePhoto o, Long photoId, Long houseId) {
		if (photoId == null) {
			o = new HousePhoto();
			if (houseId != null)
				o.setPhotoHouse(houseDao.loadEntity(houseId));
		} else
			o = photoDao.loadEntity(photoId);
		return o;
	}

	/**
	 * 取得照片
	 * 
	 * @param photoId
	 * @return
	 */
	@Transactional(readOnly = true)
	public HousePhoto getPhoto(Long photoId) {
		return photoDao.loadEntity(photoId);
	}

	/**
	 * 保存照片
	 * 
	 * @param o
	 * @param houseId
	 * @return
	 */
	public HousePhoto savePhoto(HousePhoto o, Long houseId) {
		System.out.println("houseId: " + houseId);
		if (houseId != null)
			o.setPhotoHouse(houseDao.loadEntity(houseId));
		else
			o.setPhotoHouse(null);
		return photoDao.save(o);
	}

	/**
	 * 删除照片
	 * 
	 * @param photoId
	 */
	public void deletePhoto(Long photoId) {
		photoDao.delete(photoId);
	}

	/**
	 * 取得照片 page
	 * 
	 * @param pageInfo
	 * @param houseId
	 *            房间主键
	 * @param photoName
	 *            照片名称
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<HousePhoto> getPhotoPage(PageInfo<HousePhoto> pageInfo,
			Long houseId, String photoName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From HousePhoto a Where a.photoHouse.houseId=" + houseId);
		if (StringUtil.isNotEmpty(photoName))
			sb.append(" And a.photoName like " + "'%" + photoName + "%'");
		pageInfo = photoDao.listQuery(pageInfo, "Select Count(*)"
				+ sb.toString(), sb.toString() + " Order by a.photoName ");
		return pageInfo;
	}
}
