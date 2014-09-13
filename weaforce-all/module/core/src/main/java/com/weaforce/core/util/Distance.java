package com.weaforce.core.util;

/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
/*::                                                                         :*/
/*::  This routine calculates the distance between two points (given the     :*/
/*::  latitude/longitude of those points). It is being used to calculate     :*/
/*::  the distance between two ZIP Codes or Postal Codes using our           :*/
/*::  ZIPCodeWorld(TM) and PostalCodeWorld(TM) products.                     :*/
/*::                                                                         :*/
/*::  Definitions:                                                           :*/
/*::    South latitudes are negative, east longitudes are positive           :*/
/*::                                                                         :*/
/*::  Passed to function:                                                    :*/
/*::    lat1, lon1 = Latitude and Longitude of point 1 (in decimal degrees)  :*/
/*::    lat2, lon2 = Latitude and Longitude of point 2 (in decimal degrees)  :*/
/*::    unit = the unit you desire for results                               :*/
/*::           where: 'M' is statute miles                                   :*/
/*::                  'K' is kilometers (default)                            :*/
/*::                  'N' is nautical miles                                  :*/
/*::  United States ZIP Code/ Canadian Postal Code databases with latitude & :*/
/*::  longitude are available at http://www.zipcodeworld.com                 :*/
/*::                                                                         :*/
/*::  For enquiries, please contact sales@zipcodeworld.com                   :*/
/*::                                                                         :*/
/*::  Official Web site: http://www.zipcodeworld.com                         :*/
/*::                                                                         :*/
/*::  Hexa Software Development Center © All Rights Reserved 2004            :*/
/*::                                                                         :*/
/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
public class Distance {

	public static double getDistance(double lat1, double lon1, double lat2,
			double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}
		return (dist);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

	public static void main(String[] args) {
		// HongKong-Latitude:22.29000091552734400, Logitude:
		// 114.16833496093750400
		// Singapore-Latitude:1.25833320617675776, Logitude:
		// 103.79833221435547200
		System.out.println(getDistance(22.29000091552734400,
				114.16833496093750400, 1.25833320617675776,
				103.79833221435547200, "N")
				+ " Nautical Miles\n");
		System.out.println(getDistance(22.29000091552734400,
				114.16833496093750400, 1.25833320617675776,
				103.79833221435547200, "K")
				+ " Kilometers\n");
	}
}