package com.weaforce.cms.service;

import java.util.List;

import com.weaforce.cms.entity.rent.Building;
import com.weaforce.cms.entity.rent.House;
import com.weaforce.cms.entity.rent.HousePhoto;
import com.weaforce.cms.entity.rent.HouseType;
import com.weaforce.cms.entity.rent.RentTag;
import com.weaforce.core.util.PageInfo;

/**
 * 出租服务
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public interface IRentService {
	/**
	 * 预处理建筑
	 * 
	 * @param o
	 * @param buildingId
	 * @return
	 */
	public Building prepareBuilding(Building o, Long buildingId);

	/**
	 * 取得建筑
	 * 
	 * @param buildingId
	 * @return
	 */
	public Building getBuilding(Long buildingId);

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
	public Building saveBuilding(Building o, Long zoneId, Long adsId);

	/**
	 * 删除建筑
	 * 
	 * @param buildingId
	 */
	public void deleteBuilding(Long buildingId);

	/**
	 * 根据城区,取得建筑 list
	 * 
	 * @param zoneId
	 * @return
	 */

	public List<Building> getBuildingListByZone(Long zoneId);

	/**
	 * 根据广告商家,取得建筑 list
	 * 
	 * @param adsId
	 * @return
	 */
	public List<Building> getBuildingListByAds(Long adsId);

	/**
	 * 根据城区，取得建筑 Drop Down List
	 * 
	 * @param zoneId
	 *            城区主键
	 * @return
	 */
	public String getBuildingDDL(Long zoneId);

	/**
	 * 取得建筑 page
	 * 
	 * @param pageInfo
	 * @param zoneId
	 * @param buildingName
	 * @return
	 */
	public PageInfo<Building> getBuildingPage(PageInfo<Building> pageInfo,
			Long zoneId, String buildingName);

	/**
	 * 预处理标签
	 * 
	 * @param o
	 * @param tagId
	 * @return
	 */
	public RentTag prepareTag(RentTag o, Long tagId);

	/**
	 * 取得标签
	 * 
	 * @param tagId
	 * @return
	 */
	public RentTag getTag(Long tagId);

	/**
	 * 保存标签
	 * 
	 * @param o
	 * @return
	 */
	public RentTag saveTag(RentTag o);

	/**
	 * 删除标签
	 * 
	 * @param tagId
	 */
	public void deleteTag(Long tagId);

	/**
	 * 取得房间价格标签 list
	 * 
	 * @return
	 */
	public List<RentTag> getTagList();

	/**
	 * 取得标签 page
	 * 
	 * @param pageInfo
	 * @return
	 */
	public PageInfo<RentTag> getTagPage(PageInfo<RentTag> pageInfo);

	/**
	 * 预处理房间类型
	 * 
	 * @param o
	 * @param typeId
	 * @return
	 */
	public HouseType prepareType(HouseType o, Long typeId);

	/**
	 * 取得房间类型
	 * 
	 * @param typeId
	 * @return
	 */
	public HouseType getType(Long typeId);

	/**
	 * 保存房间类型
	 * 
	 * @param o
	 * @return
	 */
	public HouseType saveType(HouseType o);

	/**
	 * 删除房间类型
	 * 
	 * @param typeId
	 */
	public void deleteType(Long typeId);

	/**
	 * 取得房间价格标签 list
	 * 
	 * @return
	 */
	public List<HouseType> getTypeList();

	/**
	 * 取得房间类型 page
	 * 
	 * @param pageInfo
	 * @return
	 */
	public PageInfo<HouseType> getTypePage(PageInfo<HouseType> pageInfo);

	/**
	 * 预处理房间
	 * 
	 * @param o
	 * @param houseId
	 * @param buildingId
	 * @return
	 */
	public House prepareHouse(House o, Long houseId, Long buildingId);

	/**
	 * 取得房间
	 * 
	 * @param houseId
	 * @return
	 */
	public House getHouse(Long houseId);

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
	public House saveHouse(House o, Long buildingId, Long typeId, Long tagId);

	/**
	 * 更新房间
	 * 
	 * @param houseId
	 * @return
	 */
	public House updateHouse(Long houseId);

	/**
	 * 删除房间
	 * 
	 * @param houseId
	 */
	public void deleteHouse(Long houseId);

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
	public String getHouseJSON(Long buildingId, Long typeId, Long tagId,
			Integer pageNumber);

	/**
	 * 取得房间 page
	 * 
	 * @param pageInfo
	 * @param buildingId
	 * @param houseTitle
	 *            ;
	 * @return
	 */
	public PageInfo<House> getHousePage(PageInfo<House> pageInfo,
			Long buildingId, String houseTitle);

	/**
	 * 预处理照片
	 * 
	 * @param o
	 * @param photoId
	 * @param houseId
	 * @return
	 */
	public HousePhoto preparePhoto(HousePhoto o, Long photoId, Long houseId);

	/**
	 * 取得照片
	 * 
	 * @param photoId
	 * @return
	 */
	public HousePhoto getPhoto(Long photoId);

	/**
	 * 保存照片
	 * 
	 * @param o
	 * @param houseId
	 * @return
	 */
	public HousePhoto savePhoto(HousePhoto o, Long houseId);

	/**
	 * 删除照片
	 * 
	 * @param photoId
	 */
	public void deletePhoto(Long photoId);

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
	public PageInfo<HousePhoto> getPhotoPage(PageInfo<HousePhoto> pageInfo,
			Long houseId, String photoName);

}
