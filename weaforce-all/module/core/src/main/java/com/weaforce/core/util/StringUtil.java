package com.weaforce.core.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


/**
 * 字符串工具集合
 * 
 * @author acaleph8@yahoo.com.cn
 */
public class StringUtil extends org.apache.commons.lang.StringUtils {

	/**
	 * 如果系统中存在旧版本的数据，则此值不能修改，否则在进行密码解析的时候出错
	 */
	private static final String PASSWORD_CRYPT_KEY = "__FreeOA_";

	private final static String DES = "DES";

	/**
	 * retrive the extend name of the given filename
	 * 
	 * @param fn
	 * @return String
	 */
	public static String getFileExtend(String fn) {
		if (isEmpty(fn))
			return null;
		int idx = fn.lastIndexOf('.') + 1;
		if (idx == 0 || idx >= fn.length())
			return null;
		return fn.substring(idx);
	}

	/**
	 * 将字符串用ch分割并放入队列
	 * 
	 * @param tags
	 * @param ch
	 * @return List
	 */
	public static List<String> stringToList(String tags, String ch) {
		if (tags == null)
			return null;
		ArrayList<String> tagList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(tags, ch);
		while (st.hasMoreElements()) {
			tagList.add(st.nextToken());
		}
		return tagList;
	}

	/**
	 * 将字符串用空格分割并放入队列
	 * 
	 * @param tags
	 * @return List
	 */
	public static List<String> stringToList(String tags) {
		if (tags == null)
			return null;
		ArrayList<String> tagList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(tags);
		while (st.hasMoreElements()) {
			tagList.add(st.nextToken());
		}
		return tagList;
	}

	/**
	 * 将字符串以delimiter分隔，返回Set
	 * 
	 * @param str
	 * @param delimiter
	 * @return java.util.Set
	 */

	public static Set<String> stringToSet(String str, final String delimiter) {
		if (isEmpty(delimiter))
			throw new IllegalArgumentException(
					"[EASY] Argument 'delimiter' shouldn't be empty!");
		if (isEmpty(str))
			return new HashSet<String>();

		Set<String> set = new HashSet<String>();
		StringTokenizer st = new StringTokenizer(str, delimiter);
		while (st.hasMoreTokens()) {
			String tmp = st.nextToken();
			if (isNotEmpty(tmp)) // simple empty filter
				set.add(tmp.toLowerCase());
		}
		return set;
	}

	/**
	 * BASE64编码
	 * 
	 * @param bytes
	 * @return String
	 */
	public static byte[] enBASE64(byte[] bytes) {
		return Base64Code.encode(bytes);
	}

	/**
	 * BASE64反编码
	 * 
	 * @param bytes
	 * @return byte[]
	 */
	public static byte[] deBASE64(byte[] bytes) {
		return Base64Code.decode(bytes);
	}

	/**
	 * BASE64编码
	 * 
	 * @param s
	 * @return String
	 */
	public static String enBASE64(String s) {
		if (s != null) {
			byte abyte0[] = s.getBytes();
			abyte0 = Base64Code.encode(abyte0);
			s = new String(abyte0);
			return s;
		}
		return null;
	}

	/**
	 * BASE64反编码
	 * 
	 * @param s
	 * @return String
	 */
	public static String deBASE64(String s) {
		if (s != null) {
			byte abyte0[] = s.getBytes();
			abyte0 = Base64Code.decode(abyte0);
			s = new String(abyte0);
			abyte0 = null;
			return s;
		}
		return null;
	}

	/**
	 * HTML输出内容格式转换
	 * 
	 * @param content
	 * @return String
	 */
	public static String formatContent(String content) {
		if (content == null)
			return "";
		String randomStr = String.valueOf(System.currentTimeMillis());
		String html = StringUtil.replace(content, "&nbsp;", randomStr);
		html = StringUtil.replace(html, "&", "&amp;");
		html = StringUtil.replace(html, "'", "&apos;");
		html = StringUtil.replace(html, "\"", "&quot;");
		html = StringUtil.replace(html, "\t", "&nbsp;&nbsp;");// 替换跳格
		html = StringUtil.replace(html, " ", "&nbsp;");// 替换空格
		html = StringUtil.replace(html, "<", "&lt;");
		html = StringUtil.replace(html, ">", "&gt;");
		return StringUtil.replace(html, randomStr, "&nbsp;").trim();
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * 
	 * @param email
	 * @return boolean
	 */
	public static boolean isEmail(String email) {
		if (email == null)
			return false;
		email = email.trim();
		if (email.indexOf(' ') != -1)
			return false;

		int idx = email.indexOf('@');
		if (idx == -1 || idx == 0 || (idx + 1) == email.length())
			return false;
		if (email.indexOf('@', idx + 1) != -1)
			return false;
		if (email.indexOf('.') == -1)
			return false;
		return true;
	}

	/**
	 * 加密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);
	}

	/**
	 * 数据解密
	 * 
	 * @param data
	 * @param key
	 *            密钥
	 * @return String
	 * @throws Exception
	 */
	public final static String decrypt(String data, String key) {
		if (data != null)
			try {
				return new String(decrypt(hex2byte(data.getBytes()),
						key.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * 数据加密
	 * 
	 * @param data
	 * @param key
	 *            密钥
	 * @return String
	 * @throws Exception
	 */
	public final static String encrypt(String data, String key) {
		if (data != null)
			try {
				return byte2hex(encrypt(data.getBytes(), key.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * 密码解密
	 * 
	 * @param data
	 * @return String
	 * @throws Exception
	 */
	public final static String decryptPassword(String data) {
		if (data != null)
			try {
				return new String(decrypt(hex2byte(data.getBytes()),
						PASSWORD_CRYPT_KEY.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * 密码加密
	 * 
	 * @param password
	 * @return String
	 * @throws Exception
	 */
	public final static String encryptPassword(String password) {
		if (password != null)
			try {
				return byte2hex(encrypt(password.getBytes(),
						PASSWORD_CRYPT_KEY.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return String
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; b != null && n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 大小写无关的字符串替换策略
	 * 
	 * @param str
	 * @param src
	 * @param obj
	 * @return String
	 */
	public static String replaceIgnoreCase(String str, String src, String obj) {
		String l_str = str.toLowerCase();
		String l_src = src.toLowerCase();
		int fromIdx = 0;
		StringBuffer result = new StringBuffer();
		do {
			int idx = l_str.indexOf(l_src, fromIdx);
			if (idx == -1)
				break;
			result.append(str.substring(fromIdx, idx));
			result.append(obj);
			fromIdx = idx + src.length();
		} while (true);
		result.append(str.substring(fromIdx));
		return result.toString();
	}

	/**
	 * 该方法返回一个字符的DBCS编码值
	 * 
	 * @param cc
	 * @return int
	 */
	protected static int getCode(char cc) {
		byte[] bs = String.valueOf(cc).getBytes();
		int code = (bs[0] << 8) | (bs[1] & 0x00FF);
		if (bs.length < 2)
			code = (int) cc;
		bs = null;
		return code;
	}

	/**
	 * 用户名必须是数字或者字母的结合
	 * 
	 * @param username
	 * @return boolean
	 */
	public static boolean isLegalUsername(String username) {
		for (int i = 0; i < username.length(); i++) {
			char ch = username.charAt(i);
			if (!isAscii(ch) && ch != '.' && ch != '_' && ch != '-'
					&& ch != '+' && ch != '(' && ch != ')' && ch != '*'
					&& ch != '^' && ch != '@' && ch != '%' && ch != '$'
					&& ch != '#' && ch != '~' && ch != '-')
				return false;
		}
		return true;
	}

	/**
	 * 判断是否是字母和数字的结合
	 * 
	 * @param name
	 * @return boolean
	 */
	public static boolean isAsciiOrDigit(String name) {
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (!isAscii(ch))
				return false;
		}
		return true;
	}

	public static boolean isAscii(char ch) {
		return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
				|| (ch >= '0' && ch <= '9');
	}

	/**
	 * 判断字符串是否有空格和非法字符
	 * 
	 * @param req
	 * @param name
	 * @return
	 */
	public static boolean isString(String str) {
		if (isEmpty(str)) {
			return false;
		} else {
			if (str.length() < 5) {
				return false;
			}
			if (str.length() > 30) {
				return false;
			}
			String reg = "^\\w+$";
			Pattern cp = Pattern.compile(reg);
			Matcher cm = cp.matcher(str);
			return cm.matches();
		}
	}

	/**
	 * 去除String 中的"-"字符 3-2,3-5,3-7 : 2,5,7
	 * 
	 * @param tokenString
	 * @return
	 */
	public static String deleteDashChar(String tokenString, String ch) {
		String[] at = tokenString.split(ch);
		String noDashString = "";
		for (int i = 0; i < at.length; i++) {
			if ("".equals(noDashString)) {
				noDashString = noDashString
						+ at[i].substring(at[i].lastIndexOf("-") + 1);
			} else {
				noDashString = noDashString + ","
						+ at[i].substring(at[i].lastIndexOf("-") + 1);
			}
		}
		return noDashString;
	}

	/**
	 * 检查字串是否为空
	 * 
	 * @param str
	 * @return true or false
	 */
	public static boolean isEmpty(final String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 合并目标对象的主键到主键队列中
	 * 
	 * @param clazzRank
	 *            目标对象主键ID队列
	 * @param primaryKey
	 *            目标对象主键
	 * @return
	 */
	public static String addClazzRank(String clazzRank, Long primaryKey) {
		if (clazzRank == null || "".equals(clazzRank))
			clazzRank = ",";
		else
			clazzRank = "," + clazzRank + ",";
		String pk = "," + primaryKey + ",";
		if (!clazzRank.contains(pk))
			clazzRank += pk.substring(1);
		// 把字串还原
		if (clazzRank.length() > 2)
			clazzRank = clazzRank.substring(1, clazzRank.length() - 1);
		else
			clazzRank = "";
		return clazzRank;
	}

	/**
	 * 把目标对象主键从队列中删除
	 * 
	 * @param clazzRank
	 *            目标对象主键ID队列
	 * @param primaryKey
	 *            目标对象主键
	 * @return
	 */

	public static String removeClazzRank(String clazzRank, Long primaryKey) {
		if (clazzRank.length() > 0) {
			clazzRank = "," + clazzRank + ",";
			String pk = "," + primaryKey + ",";
			if (clazzRank.indexOf(pk) != -1) {
				StringBuffer sb = new StringBuffer(clazzRank);
				sb.replace(sb.indexOf(pk), sb.indexOf(pk) + pk.length(), ",");
				clazzRank = sb.toString();
			}
			// 把字串还原
			if (",".equals(clazzRank))
				clazzRank = "";
			else
				clazzRank = clazzRank.substring(1, clazzRank.length() - 1);
		}
		return clazzRank;
	}

	/**
	 * 替换目标字串
	 * 
	 * @param content
	 *            完整字串
	 * @param sourceString
	 *            要替换字串
	 * @param targetString
	 *            目标字串
	 * @return
	 */
	public static String replaceAll(String content, String sourceString,
			String targetString) {
		StringBuffer sb = new StringBuffer(content);
		if (isNotEmpty(targetString)) {
			int index = 0;
			while (content.indexOf(sourceString, index) != -1) {// 如果在content中包含有sourceString
				index = content.indexOf(sourceString, index);// 记录sourceString出现的位置
				sb.replace(
						content.indexOf(sourceString, index),
						content.indexOf(sourceString, index)
								+ sourceString.length(), targetString);// 替换操作，将sourceString替换成targetString
				index = index + targetString.length();// 记录替换完后的位置
				content = sb.toString();
			}
		}
		return sb.toString();
	}

	/**
	 * 返回字串set
	 * 
	 * @param stringList
	 *            串
	 * @param delimiter
	 *            分隔符
	 * @return
	 */
	public static Set<String> getSet(final String stringList,
			final String delimiter) {
		if (isEmpty(delimiter))
			throw new IllegalArgumentException(
					"Argument 'delimiter' shouldn't be empty!");
		if (isEmpty(stringList))
			return new HashSet<String>();
		Set<String> set = new HashSet<String>();
		StringTokenizer st = new StringTokenizer(stringList, delimiter);
		while (st.hasMoreTokens()) {
			String tmp = st.nextToken();
			if (isNotEmpty(tmp)) // simple empty filter
				set.add(tmp.toLowerCase());
		}
		return set;
	}

	/**
	 * 根据电子邮件，取得网站（小写）
	 * 
	 * @param email
	 *            电子邮件
	 * @return
	 */
	public static String getSiteFromEmail(String email) {
		if (isNotEmpty(email)) {
			return email.substring(email.indexOf("@") + 1, email.length())
					.toLowerCase();
		}
		return null;
	}

	/**
	 * 根据电子邮件，取得网站名称（小写）
	 * 
	 * @param email
	 *            电子邮件
	 * @return
	 */
	public static String getSiteNameFromEmail(String email) {
		String site = getSiteFromEmail(email);
		return site.substring(0, site.indexOf("."));
	}

	/**
	 * 剪掉字串最后字符
	 * 
	 * @param sourceString
	 *            源字串
	 * @param ch
	 *            字符标识
	 * @return
	 */
	public static String cutLastChar(String sourceString, String ch) {
		if (sourceString.length() > 1) {
			int lastIndex = sourceString.lastIndexOf(ch);
			if (lastIndex > 0)
				sourceString =  sourceString.substring(0, lastIndex);
		}
		return sourceString;
	}

	public static void main(String[] arts) {
		long l = 24L * 60 * 60 * 1000 * 1000;
		System.out.println("l: " + l);
		long x = 0x180000000L;
		System.out.println("x: " + x);
		System.out.println(0x70000001);
		System.out.println(0x80000001);
		System.out.println(0x80000001L);
		System.out.println(0x80000000);
		System.out.println(0x8000000000000000L);
		System.out.println(getSiteFromEmail("yanjiacheng@weaforce.com"));
		System.out.println(cutLastChar("\"yanjiacheng@weaforce.com\"", ","));
		String userLogin = "[\"SUSAN@WEAFORCE.COM\",\"XIANGCHAOYAN@WEAFORCE.COM\",\"CONE-GLOBAL@WEAFORCE.COM\",\"ACALEPH8@WEAFORCE.COM\",\"TERRY@WEAFORCE.COM\",\"CMSWEBER@LIVEZINE.COM.CN\",\"LILIN@WEAFORCE.COM\",\"MANFRED@WEAFORCE.COM\",\"CONE-APP-ADVANCE@WEAFORCE.COM\",\"CONE-APP-ADMIN@WEAFORCE.COM\",\"CONE-APP@WEAFORCE.COM\",\"CONE-ADMIN@WEAFORCE.COM\",\"YANJIACHENG@WEAFORCE.COM\",\"ZHANGSHIYI@WEAFORCE.COM\",\"LIUFANHAI@WEAFORCE.COM\",\"WANGHUASHENG@WEAFORCE.COM\",\"YANGYUECHUN@WEAFORCE.COM\",\"YUJIANMIN@WEAFORCE.COM\",\"PENGDEQUAN@WEAFORCE.COM\",\"CAIMINGDING@WEAFORCE.COM\",\"TUCHUANMING@WEAFORCE.COM\",\"YECHUNMAN@WEAFORCE.COM\",\"LOUIE@WEAFORCE.COM\",\"YANGDEJUN@WEAFORCE.COM\",\"HAOXIANQING@WEAFORCE.COM\",\"SUNCHUNHUA@WEAFORCE.COM\",\"ZHANGXIONG@WEAFORCE.COM\",\"QINYIJIAN@WEAFORCE.COM\",\"LIDONGLING@WEAFORCE.COM\",\"LINHAIZHEN@WEAFORCE.COM\",\"WANGRIQING@WEAFORCE.COM\",\"ZENGSHUMING@WEAFORCE.COM\",\"CORA@WEAFORCE.COM\",\"ZHANGJINFEN@WEAFORCE.COM\",\"LIYANMEI@WEAFORCE.COM\",\"GUIWENHUI@WEAFORCE.COM\",\"JXC@WEAFORCE.COM\",\"WPS@WEAFORCE.COM\",";
		System.out.println(cutLastChar(userLogin, ","));
	}
}