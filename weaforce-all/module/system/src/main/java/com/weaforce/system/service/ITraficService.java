package com.weaforce.system.service;

import java.util.List;

import com.weaforce.system.entity.trafic.Bus;
import com.weaforce.system.entity.trafic.BusLine;
import com.weaforce.system.entity.trafic.Mobile;

public interface ITraficService extends ISystemService {
	/**
	 * Get mobile list
	 * 
	 * @return
	 */
	public List<Mobile> getMobileList();
	
	/**
	 * Get mobile instance
	 * 
	 * @param mobileId
	 *            Mobile primary key
	 * @return
	 */
	public Mobile getMobile(Long mobileId);
	/**
	 * Get Mobile by User login
	 * 
	 * @param userLogin
	 *            User login
	 * @return
	 */
	public Mobile getMobileByUserLogin(String userLogin, String mobile);

	/**
	 * Save mobile
	 * 
	 * @param o
	 *            Mobile instance
	 * @return
	 */
	public Mobile saveMobile(Mobile o);

	/**
	 * Get bus line list
	 * 
	 * @return
	 */
	public List<BusLine> getBusLineList();

	/**
	 * Save bus line
	 * 
	 * @param o
	 *            Bus line instance
	 * @return
	 */
	public BusLine saveBusLine(BusLine o);

	/**
	 * Get busList by bus line
	 * 
	 * @param lineId
	 *            Bus line primary key
	 * @return
	 */
	public List<Bus> getBusList(Long lineId);

	/**
	 * Save bus
	 * 
	 * @param o
	 *            Bus instance
	 * @param lineId
	 *            : Bus line primary key
	 * @return
	 */
	public Bus saveBus(Bus o, Long lineId);

	
}
