package com.weaforce.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weaforce.cms.dao.social.ICarBrandDao;
import com.weaforce.cms.dao.social.ICarDao;
import com.weaforce.cms.dao.social.ICarBunkerDao;
import com.weaforce.cms.dao.social.ICarPhotoDao;
import com.weaforce.cms.dao.social.ICarSeriesDao;
import com.weaforce.cms.dao.social.ICarShareDao;
import com.weaforce.cms.dao.social.ICarShareRequestDao;
import com.weaforce.cms.dao.social.ICarShareTypeDao;
import com.weaforce.cms.dao.social.IFriendDao;
import com.weaforce.cms.dao.social.IFriendTagDao;
import com.weaforce.cms.dao.social.ITalkCarDao;
import com.weaforce.cms.entity.social.Car;
import com.weaforce.cms.entity.social.CarBrand;
import com.weaforce.cms.entity.social.CarBunker;
import com.weaforce.cms.entity.social.CarPhoto;
import com.weaforce.cms.entity.social.CarSeries;
import com.weaforce.cms.entity.social.CarShare;
import com.weaforce.cms.entity.social.CarShareRequest;
import com.weaforce.cms.entity.social.CarShareType;
import com.weaforce.cms.entity.social.Friend;
import com.weaforce.cms.entity.social.FriendTag;
import com.weaforce.cms.entity.social.TalkCar;
import com.weaforce.cms.service.ISocialService;
import com.weaforce.core.util.PageInfo;
import com.weaforce.core.util.StringUtil;
import com.weaforce.system.service.impl.SystemService;

@Service("socialService")
@Transactional(rollbackFor = Exception.class)
public class SocialService extends SystemService implements ISocialService {
	@Autowired
	@Qualifier("carShareTypeDao")
	private ICarShareTypeDao carShareTypeDao;
	@Autowired
	@Qualifier("carBrandDao")
	private ICarBrandDao carBrandDao;
	@Autowired
	@Qualifier("carSeriesDao")
	private ICarSeriesDao carSeriesDao;
	@Autowired
	@Qualifier("carDao")
	private ICarDao carDao;
	@Autowired
	@Qualifier("carPhotoDao")
	private ICarPhotoDao carPhotoDao;
	@Autowired
	@Qualifier("carShareDao")
	private ICarShareDao carShareDao;
	@Autowired
	@Qualifier("carShareRequestDao")
	private ICarShareRequestDao carShareRequestDao;
	@Autowired
	@Qualifier("carBunkerDao")
	private ICarBunkerDao carBunkerDao;
	@Autowired
	@Qualifier("friendDao")
	private IFriendDao friendDao;
	@Autowired
	@Qualifier("friendTagDao")
	private IFriendTagDao friendTagDao;
	@Autowired
	@Qualifier("talkCarDao")
	private ITalkCarDao talkCarDao;

	/**
	 * Ready for CarShareType object
	 * 
	 * @param o
	 *            Car share type.
	 * @param typeId
	 *            Type primary key.
	 * @return
	 */
	@Transactional(readOnly = true)
	public CarShareType prepareCarShareType(CarShareType o, Long typeId) {
		if (typeId == null)
			o = new CarShareType();
		else
			o = carShareTypeDao.loadEntity(typeId);
		return o;
	}

	/**
	 * Save car share type
	 * 
	 * @param o
	 *            Car share type object
	 * @return
	 */

	public CarShareType saveCarShareType(CarShareType o) {
		return carShareTypeDao.save(o);
	}

	/**
	 * Delete car share type refer to primary key.
	 * 
	 * @param typeId
	 *            Type primary key
	 */
	public void deleteCarShareType(Long typeId) {
		carShareTypeDao.delete(typeId);
	}

	/**
	 * Get car share type list
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CarShareType> getTypeList() {
		return carShareTypeDao.getTypeList();
	}

	/**
	 * Prepare a car brand instance
	 * 
	 * @param o
	 *            Car brand instance
	 * @param brandId
	 *            Car brand primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public CarBrand prepareCarBrand(CarBrand o, Long brandId) {
		if (brandId == null)
			o = new CarBrand();
		else
			o = carBrandDao.loadEntity(brandId);
		return o;
	}

	/**
	 * Save car brand instance
	 * 
	 * @param o
	 *            Car brand instance
	 * @return
	 */
	public CarBrand saveCarBrand(CarBrand o) {
		return carBrandDao.save(o);
	}

	/**
	 * Delete car brand instance
	 * 
	 * @param brandId
	 *            Car brand primary key
	 */
	public void deleteCarBrand(Long brandId) {
		carBrandDao.delete(brandId);
	}

	/**
	 * Get car brand list
	 * 
	 * @return
	 * @throws InterruptedException 
	 */
	@Transactional(readOnly = true)
	public List<CarBrand> getCarBrandList() throws InterruptedException {
		return carBrandDao.getBrandList();
	}

	/**
	 * Get car brand page
	 * 
	 * @param pageInfo
	 *            Page info
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<CarBrand> getCarBrandPage(PageInfo<CarBrand> pageInfo) {
		StringBuffer sb = new StringBuffer(" From CarBrand a ");
		pageInfo = carBrandDao.listQuery(pageInfo,
				"Select Count(*) " + sb.toString(), sb.toString());
		return pageInfo;
	}

	/**
	 * Prepare car series instance
	 * 
	 * @param o
	 *            Car series instance
	 * @param seriesId
	 *            Car series primary key
	 * @param brandId
	 *            Car brand primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public CarSeries prepareCarSeries(CarSeries o, Long seriesId, Long brandId) {
		if (seriesId == null)
			o = new CarSeries();
		else
			o = carSeriesDao.loadEntity(seriesId);
		if (brandId != null)
			o.setSeriesBrand(carBrandDao.loadEntity(brandId));
		return o;
	}

	/**
	 * Save a car series instance
	 * 
	 * @param o
	 *            Car series instance
	 * @param brandId
	 *            Car brand primary key
	 * @return
	 */
	public CarSeries saveCarSeries(CarSeries o, Long brandId) {
		if (brandId == null)
			o.setSeriesBrand(null);
		else
			o.setSeriesBrand(carBrandDao.loadEntity(brandId));
		return carSeriesDao.save(o);
	}

	/**
	 * Delete car series
	 * 
	 * @param seriesId
	 *            Car series primary key
	 */
	public void deleteCarSeries(Long seriesId) {
		carSeriesDao.delete(seriesId);
	}

	/**
	 * Get car series list refer to brand primary key
	 * 
	 * @param brandId
	 *            Car brand primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CarSeries> getSeriesByBrand(Long brandId) {
		return carSeriesDao.getSeriesByBrand(brandId);
	}

	/**
	 * Get series JSON by brand
	 * 
	 * @param brandId
	 *            Brand primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public StringBuffer getSeriesJSON(Long brandId) {
		return carSeriesDao.getSeriesJSON(brandId);
	}

	/**
	 * Get car series page
	 * 
	 * @param pageInfo
	 *            Page info
	 * @param brandId
	 *            Car brand id
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<CarSeries> getCarSeriesPage(PageInfo<CarSeries> pageInfo,
			Long brandId) {
		StringBuffer sb = new StringBuffer(" From CarSeries a ");
		if (brandId != null)
			sb.append(" Where a.seriesBrand.brandId=" + brandId);
		pageInfo = carSeriesDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString());
		return pageInfo;
	}

	/**
	 * Prepare a car instance
	 * 
	 * @param o
	 *            Car instance
	 * @param carId
	 *            Car primary key
	 * @param seriesId
	 *            Car series primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public Car prepareCar(Car o, Long carId, Long seriesId) {
		if (carId == null)
			o = new Car();
		else
			o = carDao.loadEntity(carId);
		if (seriesId != null)
			o.setCarSeries(carSeriesDao.loadEntity(seriesId));
		return o;
	}

	/**
	 * Save a car instance
	 * 
	 * @param o
	 *            Car instance
	 * @param seriesId
	 *            Car series primary key
	 * @return
	 */
	public Car saveCar(Car o, Long seriesId) {
		if (seriesId != null)
			o.setCarSeries(carSeriesDao.loadEntity(seriesId));
		return carDao.save(o);
	}

	/**
	 * Delete a car instance
	 * 
	 * @param carId
	 *            Car primary key
	 */
	public void deleteCar(Long carId) {
		carDao.delete(carId);
	}

	/**
	 * Get car list by series primary key
	 * 
	 * @param seriesId
	 *            Car series primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Car> getCarListBySeries(Long seriesId) {
		return carDao.getCarListBySeries(seriesId);
	}

	/**
	 * Get car list by owner login
	 * 
	 * @param userLogin
	 *            Owner login
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Car> getCarListByOwner(String userLogin) {
		return carDao.getCarListByOwner(userLogin);
	}

	/**
	 * Get car page
	 * 
	 * @param pageInfo
	 *            Page info
	 * @param brandId
	 *            Car brand primary key
	 * @param seriesId
	 *            Car series primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<Car> getCarPage(PageInfo<Car> pageInfo, Long brandId,
			Long seriesId) {
		StringBuffer sb = new StringBuffer();
		if (brandId != null)
			sb.append(" a.carSeries.seriesBrand.brandId =" + brandId + " and");
		if (seriesId != null)
			sb.append(" a.carSeries.seriesId=" + seriesId + " and");

		if (sb.length() > 0) {
			sb = new StringBuffer(StringUtil.cutLastChar(sb.toString(), "and"));
			sb.insert(0, " From Car a Where ");
		} else
			sb.append("From Car a");
		System.out.println(sb.toString());
		pageInfo = carDao.listQuery(pageInfo,
				" Select Count(*) " + sb.toString(), sb.toString());
		return pageInfo;
	}

	/**
	 * Get photo list by car
	 * 
	 * @param carId
	 *            Car primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CarPhoto> getCarPhotoListByCar(Long carId) {
		return carPhotoDao.getPhotoListByCar(carId);
	}

	/**
	 * Prepare a car share instance
	 * 
	 * @param o
	 *            Car share instance object
	 * @param carId
	 *            Car primary key
	 * @param shareId
	 *            Car share primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public CarShare prepareCarShare(CarShare o, Long carId, Long shareId) {
		if (shareId == null)
			o = new CarShare();
		else
			o = carShareDao.loadEntity(shareId);
		if (carId != null)
			o.setShareCar(carDao.loadEntity(carId));
		return carShareDao.save(o);
	}

	/**
	 * Save car share
	 * 
	 * @param o
	 *            Car share
	 * @param typeId
	 *            Car type primary key
	 * @param carId
	 *            Car primary key
	 * @param cityFromId
	 *            Share city from primary key
	 * @param cityToId
	 *            Share city to primary key
	 * @param zoneFromId
	 *            Share zone from primary key
	 * @param zoneToId
	 *            Share zone to primary key
	 * @return
	 */
	public CarShare saveCarShare(CarShare o, Long typeId, Long carId,
			Long cityFromId, Long cityToId, Long zoneFromId, Long zoneToId) {
		if (typeId != null)
			o.setShareType(carShareTypeDao.loadEntity(typeId));
		if (carId != null)
			o.setShareCar(carDao.loadEntity(carId));
		if (cityFromId != null)
			o.setShareCityFrom(cityDao.loadEntity(cityFromId));
		if (cityToId != null)
			o.setShareCityTo(cityDao.loadEntity(cityToId));
		if (zoneFromId != null)
			o.setShareZoneFrom(zoneDao.loadEntity(zoneFromId));
		if (zoneToId != null)
			o.setShareZoneTo(zoneDao.loadEntity(zoneToId));
		return carShareDao.save(o);
	}

	/**
	 * Delete car share instance
	 * 
	 * @param shareId
	 *            Car share primary key
	 */
	public void deleteCarShare(Long shareId) {
		carShareDao.delete(shareId);
	}

	/**
	 * Get car share page
	 * 
	 * @param pageInfo
	 *            Page info
	 * @param typeId
	 *            Share type primary key
	 * @param cityFromId
	 *            City from primary key
	 * @param cityToId
	 *            City to primary key
	 * @param zoneFromId
	 *            Zone from primary key
	 * @param zoneToId
	 *            Zone to primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<CarShare> getCarSharePage(PageInfo<CarShare> pageInfo,
			Long typeId, Long cityFromId, Long cityToId, Long zoneFromId,
			Long zoneToId) {
		StringBuffer sb = new StringBuffer(" From CarShare a ");
		StringBuffer sbClause = new StringBuffer();
		if (typeId != null)
			sbClause.append(" a.shareType.typeId = " + typeId + " and");
		if (cityFromId != null)
			sbClause.append(" a.shareCityFrom.cityId = " + cityFromId + " and");
		if (cityToId != null)
			sbClause.append(" a.shareCityTo.cityId = " + cityToId + " and");
		if (zoneFromId != null)
			sbClause.append(" a.shareZoneFrom.zoneId = " + zoneFromId + " and");
		if (zoneToId != null)
			sbClause.append(" a.shareZoneTo.zoneId = " + zoneToId + " and");
		// Combination condition clause
		if (sbClause.length() > 0) {
			sbClause = new StringBuffer(StringUtil.cutLastChar(
					sbClause.toString(), " and"));

			System.out.println(sbClause.toString());
			sb.append("where ");
			sb = sb.append(sbClause);
		}

		pageInfo = carShareDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString());
		return pageInfo;
	}

	/**
	 * Prepare car share request
	 * 
	 * @param o
	 *            Car share request
	 * @param requestId
	 *            Request primary key
	 * @param shareId
	 *            Share primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public CarShareRequest prepareCarShareRequest(CarShareRequest o,
			Long requestId, Long shareId) {
		if (requestId == null)
			o = new CarShareRequest();
		else
			o = carShareRequestDao.loadEntity(requestId);
		if (shareId != null)
			o.setRequestShare(carShareDao.loadEntity(shareId));
		return o;
	}

	/**
	 * Save car share request
	 * 
	 * @param o
	 *            Car share request
	 * @param shareId
	 *            Car share primary key
	 * @return
	 */
	public CarShareRequest saveCarShareRequest(CarShareRequest o, Long shareId) {
		if (shareId != null)
			o.setRequestShare(carShareDao.loadEntity(shareId));
		return carShareRequestDao.save(o);
	}

	/**
	 * Delete car share request
	 * 
	 * @param requestId
	 *            Car share request primary key
	 */
	public void deleteCarShareRequest(Long requestId) {
		carShareRequestDao.delete(requestId);
	}

	/**
	 * Get car share request page
	 * 
	 * @param pageInfo
	 *            Page info
	 * @param carId
	 *            Car primary key
	 * @param shareId
	 *            Car share primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<CarShareRequest> getCarShareRequestPage(
			PageInfo<CarShareRequest> pageInfo, Long carId, Long shareId) {
		StringBuffer sb = new StringBuffer();
		if (carId != null)
			sb.append(" a.requestShare.shareCar.carId=" + carId + " and");
		if (shareId != null)
			sb.append(" a.requestShare.shareId=" + shareId + " and");
		if (sb.length() > 0) {
			sb = new StringBuffer(StringUtil.cutLastChar(sb.toString(), "and"));
			sb.insert(0, " From CarShareRequest a Where ");
		} else
			sb.append(" From CarShareRequest a ");
		System.out.println(sb.toString());
		pageInfo = carShareRequestDao.listQuery(pageInfo, "Select Count(*) "
				+ sb.toString(), sb.toString());
		return pageInfo;
	}

	/**
	 * Prepare car bunker instance
	 * 
	 * @param o
	 *            Car bunker
	 * @param bunkerId
	 *            Car bunker primary key
	 * @param carId
	 *            Car primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public CarBunker prepareCarBunker(CarBunker o, Long bunkerId, Long carId) {
		if (bunkerId == null)
			o = new CarBunker();
		else
			o = carBunkerDao.loadEntity(bunkerId);
		if (carId != null)
			o.setBunkerCar(carDao.loadEntity(carId));

		return o;
	}

	/**
	 * Save car bunker
	 * 
	 * @param o
	 *            Car bunker
	 * @param carId
	 *            Car primary key
	 * @return
	 */
	public CarBunker saveCarBunker(CarBunker o, Long carId) {
		if (carId != null)
			o.setBunkerCar(carDao.loadEntity(carId));
		return carBunkerDao.save(o);
	}

	/**
	 * Delete car bunker
	 * 
	 * @param bunkerId
	 *            Car bunker primary key
	 */
	public void deleteCarBunker(Long bunkerId) {
		carBunkerDao.delete(bunkerId);
	}

	/**
	 * Get car bunker page
	 * 
	 * @param pageInfo
	 *            Page info
	 * @param carId
	 *            Car primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<CarBunker> getCarBunkerPage(PageInfo<CarBunker> pageInfo,
			Long carId) {
		StringBuffer sb = new StringBuffer(" From CarBunker a ");
		if (carId != null)
			sb.append("Where a.bunkerCar.carId=" + carId);
		pageInfo = carBunkerDao.listQuery(pageInfo,
				"Select Count(*) " + sb.toString(), sb.toString());
		return pageInfo;
	}

	/**
	 * Save friend
	 * 
	 * @param o
	 *            Friend instance
	 * 
	 * @param loginMe
	 *            My login
	 * @param loginFriend
	 *            Friend login
	 * @return
	 */
	public String saveFriend(Friend o, String loginMe, String loginFriend) {
		String returnMessage = "{\"新增成功。\"}";
		o = friendDao.getFriendByLogin(loginMe, loginFriend);
		if (o.getFriendId() == null) {
			o.setFriendLoginMe(loginMe);
			o.setFriendLogin(loginFriend);
			friendDao.save(o);
		} else
			returnMessage = "{\"朋友已经存在。\"}";
		;
		return returnMessage;
	}

	/**
	 * Delete friend
	 * 
	 * @param frienId
	 *            Friend primary key
	 * @return
	 */
	public String deleteFriend(Long frienId) {
		friendDao.delete(frienId);
		return "{\"friendId\":" + frienId + "}";
	}

	/**
	 * Save friend tag
	 * 
	 * @param o
	 *            Friend tag instance
	 * 
	 * @return
	 */
	public FriendTag saveFriendTag(FriendTag o) {
		return friendTagDao.save(o);
	}

	/**
	 * Delete friend tag
	 * 
	 * @param tagId
	 *            Tag primary key
	 */
	public void deleteFriendTag(Long tagId) {
		friendTagDao.delete(tagId);

	}

	/**
	 * Prepare talk instance
	 * 
	 * @param o
	 *            Talk instance
	 * @param talkId
	 *            Talk primary key
	 * @param parentId
	 *            Talk referenced parent primary key
	 * @return
	 */
	@Transactional(readOnly = true)
	public TalkCar prepareTalk(TalkCar o, Long talkId, Long parentId) {
		if (talkId == null)
			o = new TalkCar();
		else
			o = talkCarDao.loadEntity(talkId);
		if (parentId != null)
			o.setTalkParent(talkCarDao.loadEntity(parentId));
		return o;
	}

	/**
	 * Save talk instance
	 * 
	 * @param o
	 *            Talk instance
	 * @param parentId
	 *            Talk referenced parent primary key
	 * @return
	 */
	public TalkCar saveTalk(TalkCar o, Long parentId) {
		if (parentId != null)
			o.setTalkParent(talkCarDao.loadEntity(parentId));
		return talkCarDao.save(o);
	}

	/**
	 * Delete talk instance
	 * 
	 * @param talkId
	 *            Talk primary key
	 */
	public String deleteTalk(Long talkId) {
		if (talkCarDao.getTalkListByParent(talkId).size() == 0) {
			talkCarDao.delete(talkId);
			return "{1}";
		}
		return "{0}";
	}
}
