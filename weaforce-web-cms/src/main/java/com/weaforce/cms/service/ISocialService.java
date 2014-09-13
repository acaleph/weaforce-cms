package com.weaforce.cms.service;

import java.util.List;

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
import com.weaforce.core.util.PageInfo;
import com.weaforce.system.service.ISystemService;

public interface ISocialService extends ISystemService {
	/**
	 * Ready for CarShareType object
	 * 
	 * @param o
	 *            Car share type.
	 * @param typeId
	 *            Type primary key.
	 * @return
	 */
	public CarShareType prepareCarShareType(CarShareType o, Long typeId);

	/**
	 * Save car share type
	 * 
	 * @param o
	 *            Car share type object
	 * @return
	 */

	public CarShareType saveCarShareType(CarShareType o);

	/**
	 * Delete car share type refer to primary key.
	 * 
	 * @param typeId
	 *            Type primary key
	 */
	public void deleteCarShareType(Long typeId);

	/**
	 * Get car share type list
	 * 
	 * @return
	 */
	public List<CarShareType> getTypeList();

	/**
	 * Prepare a car brand instance
	 * 
	 * @param o
	 *            Car brand instance
	 * @param brandId
	 *            Car brand primary key
	 * @return
	 */

	public CarBrand prepareCarBrand(CarBrand o, Long brandId);

	/**
	 * Delete car brand instance
	 * 
	 * @param o
	 *            Car brand instance
	 * @return
	 */
	public CarBrand saveCarBrand(CarBrand o);

	/**
	 * Delete car brand instance
	 * 
	 * @param brandId
	 *            Car brand primary key
	 */
	public void deleteCarBrand(Long brandId);

	/**
	 * Get car brand list
	 * 
	 * @return
	 * @throws InterruptedException 
	 */
	public List<CarBrand> getCarBrandList() throws InterruptedException;

	/**
	 * Get car brand page
	 * 
	 * @param pageInfo
	 *            Page info
	 * @return
	 */
	public PageInfo<CarBrand> getCarBrandPage(PageInfo<CarBrand> pageInfo);

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
	public CarSeries prepareCarSeries(CarSeries o, Long seriesId, Long brandId);

	/**
	 * Save a car series instance
	 * 
	 * @param o
	 *            Car series instance
	 * @param brandId
	 *            Car brand primary key
	 * @return
	 */
	public CarSeries saveCarSeries(CarSeries o, Long brandId);

	/**
	 * Delete car series
	 * 
	 * @param seriesId
	 *            Car series primary key
	 */
	public void deleteCarSeries(Long seriesId);

	/**
	 * Get car series list refer to brand primary key
	 * 
	 * @param brandId
	 *            Car brand primary key
	 * @return
	 */
	public List<CarSeries> getSeriesByBrand(Long brandId);

	/**
	 * Get series JSON by brand
	 * 
	 * @param brandId
	 *            Brand primary key
	 * @return
	 */
	public StringBuffer getSeriesJSON(Long brandId);

	/**
	 * Get car series page
	 * 
	 * @param pageInfo
	 *            Page info
	 * @param brandId
	 *            Car brand id
	 * @return
	 */
	public PageInfo<CarSeries> getCarSeriesPage(PageInfo<CarSeries> pageInfo,
			Long brandId);

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
	public Car prepareCar(Car o, Long carId, Long seriesId);

	/**
	 * Save a car instance
	 * 
	 * @param o
	 *            Car instance
	 * @param seriesId
	 *            Car series primary key
	 * @return
	 */
	public Car saveCar(Car o, Long seriesId);

	/**
	 * Delete a car instance
	 * 
	 * @param carId
	 *            Car primary key
	 */
	public void deleteCar(Long carId);

	/**
	 * Get car list by series primary key
	 * 
	 * @param seriesId
	 *            Car series primary key
	 * @return
	 */
	public List<Car> getCarListBySeries(Long seriesId);

	/**
	 * Get car list by owner login
	 * 
	 * @param userLogin
	 *            Owner login
	 * @return
	 */
	public List<Car> getCarListByOwner(String userLogin);

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
	public PageInfo<Car> getCarPage(PageInfo<Car> pageInfo, Long brandId,
			Long seriesId);

	/**
	 * Get photo list by car
	 * 
	 * @param carId
	 *            Car primary key
	 * @return
	 */
	public List<CarPhoto> getCarPhotoListByCar(Long carId);

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
	public CarShare prepareCarShare(CarShare o, Long carId, Long shareId);

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
			Long cityFromId, Long cityToId, Long zoneFromId, Long zoneToId);

	/**
	 * Delete car share instance
	 * 
	 * @param shareId
	 *            Car share primary key
	 */
	public void deleteCarShare(Long shareId);

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
	public PageInfo<CarShare> getCarSharePage(PageInfo<CarShare> pageInfo,
			Long typeId, Long cityFromId, Long cityToId, Long zoneFromId,
			Long zoneToId);

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
	public CarShareRequest prepareCarShareRequest(CarShareRequest o,
			Long requestId, Long shareId);

	/**
	 * Save car share request
	 * 
	 * @param o
	 *            Car share request
	 * @param shareId
	 *            Car share primary key
	 * @return
	 */
	public CarShareRequest saveCarShareRequest(CarShareRequest o, Long shareId);

	/**
	 * Delete car share request
	 * 
	 * @param requestId
	 *            Car share request primary key
	 */
	public void deleteCarShareRequest(Long requestId);

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
	public PageInfo<CarShareRequest> getCarShareRequestPage(
			PageInfo<CarShareRequest> pageInfo, Long carId, Long shareId);

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
	public CarBunker prepareCarBunker(CarBunker o, Long bunkerId, Long carId);

	/**
	 * Save car bunker
	 * 
	 * @param o
	 *            Car bunker
	 * @param carId
	 *            Car primary key
	 * @return
	 */
	public CarBunker saveCarBunker(CarBunker o, Long carId);

	/**
	 * Delete car bunker
	 * 
	 * @param bunkerId
	 *            Car bunker primary key
	 */
	public void deleteCarBunker(Long bunkerId);

	/**
	 * Get car bunker page
	 * 
	 * @param pageInfo
	 *            Page info
	 * @param carId
	 *            Car primary key
	 * @return
	 */
	public PageInfo<CarBunker> getCarBunkerPage(PageInfo<CarBunker> pageInfo,
			Long carId);

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
	public String saveFriend(Friend o, String loginMe, String loginFriend);

	/**
	 * Delete friend
	 * 
	 * @param frienId
	 *            Friend primary key
	 * @return
	 */
	public String deleteFriend(Long frienId);

	/**
	 * Save friend tag
	 * 
	 * @param o
	 *            Friend tag instance
	 * 
	 * @return
	 */
	public FriendTag saveFriendTag(FriendTag o);

	/**
	 * Delete friend tag
	 * 
	 * @param tagId
	 *            Tag primary key
	 */
	public void deleteFriendTag(Long tagId);

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
	public TalkCar prepareTalk(TalkCar o, Long talkId, Long parentId);

	/**
	 * Save talk instance
	 * 
	 * @param o
	 *            Talk instance
	 * @param parentId
	 *            Talk referenced parent primary key
	 * @return
	 */
	public TalkCar saveTalk(TalkCar o, Long parentId);

	/**
	 * Delete talk instance
	 * 
	 * @param talkId
	 *            Talk primary key
	 */
	public String deleteTalk(Long talkId);
}
