package com.weaforce.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weaforce.system.dao.trafic.IBusDao;
import com.weaforce.system.dao.trafic.IBusLineDao;
import com.weaforce.system.dao.trafic.IMobileDao;
import com.weaforce.entity.admin.User;
import com.weaforce.system.entity.trafic.Bus;
import com.weaforce.system.entity.trafic.BusLine;
import com.weaforce.system.entity.trafic.Mobile;
import com.weaforce.system.service.ITraficService;

@Service("traficService")
@Transactional
public class TraficService extends SystemService implements ITraficService {
	@Autowired
	@Qualifier("mobileDao")
	private IMobileDao mobileDao;
	@Autowired
	@Qualifier("busLineDao")
	private IBusLineDao busLineDao;
	@Autowired
	@Qualifier("busDao")
	private IBusDao busDao;

	/**
	 * Get mobile list
	 * 
	 * @return
	 */
	public List<Mobile> getMobileList() {
		List<Mobile> mobileList = mobileDao.getMobileList();
		// Set the user login and name to mobile instance
		for (Mobile o : mobileList) {
			User u = o.getMobileUser();
			o.setUserLogin(u.getUserLogin());
			o.setUserName(u.getUserNameCn());
		}

		return mobileList;
	}

	/**
	 * Get mobile instance
	 * 
	 * @param mobileId
	 *            Mobile primary key
	 * @return
	 */
	public Mobile getMobile(Long mobileId) {
		return mobileDao.getEntity(mobileId);
	}

	/**
	 * Get Mobile by User login
	 * 
	 * @param userLogin
	 *            User login
	 * @return
	 */
	public Mobile getMobileByUserLogin(String userLogin, String mobile) {
		return mobileDao.getMobileByUserLogin(userLogin, mobile);
	}

	/**
	 * Save mobile
	 * 
	 * @param o
	 *            Mobile instance
	 * @return
	 */
	public Mobile saveMobile(Mobile o) {
		User user = getUserByLogin(o.getUserLogin());
		if (user != null) {
			o.setMobileUser(user);
		} else
			return null;
		return mobileDao.save(o);
	}

	/**
	 * Get bus line list
	 * 
	 * @return
	 */
	public List<BusLine> getBusLineList() {
		return busLineDao.getLineList();
	}

	/**
	 * Save bus line
	 * 
	 * @param o
	 *            Bus line instance
	 * @return
	 */
	public BusLine saveBusLine(BusLine o) {
		return busLineDao.save(o);
	}

	/**
	 * Get busList by bus line
	 * 
	 * @param lineId
	 *            Bus line primary key
	 * @return
	 */
	public List<Bus> getBusList(Long lineId) {
		return busDao.getBusList(lineId);
	}

	/**
	 * Save bus
	 * 
	 * @param o
	 *            Bus instance
	 * @param lineId
	 *            Bus line primary key
	 * @return
	 */
	public Bus saveBus(Bus o, Long lineId) {
		if (lineId == null)
			o.setBusLine(null);
		else
			o.setBusLine(busLineDao.loadEntity(lineId));

		return busDao.save(o);
	}

}
