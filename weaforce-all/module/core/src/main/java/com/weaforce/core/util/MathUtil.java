package com.weaforce.core.util;

import java.math.BigDecimal;

public class MathUtil {
	/**
	 * Integer 数值除法运算,根据精度四舍五入
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            精度
	 * @return
	 */
	public static Integer divide(Integer v1, Integer v2, Integer scale) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).intValue();
	}
	public static void main(String[] args){
		System.out.println(divide(9,2,0)); //5
	}
}
