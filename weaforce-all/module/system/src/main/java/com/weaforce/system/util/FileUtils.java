package com.weaforce.system.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.devlib.schmidt.imageinfo.ImageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weaforce.core.common.bean.Node;
import com.weaforce.core.common.bean.TreeNode;
import com.weaforce.core.util.PropertiesLoader;
import com.weaforce.core.util.StringUtil;
import com.weaforce.system.component.fckeditor.tool.UtilsFile;

/**
 * 文件上载/下载工具类:weaforce.properties
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class FileUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(UtilsFile.class);

	/**
	 * 取得属性
	 * 
	 * @param property
	 * @return
	 */
	public static String getProperty(String property) {
		PropertiesLoader loader = new PropertiesLoader();
		return loader.getProperty(property);
	}

	/**
	 * 创建目录
	 * 
	 * @param filePath
	 *            目录:/data/www/weaforce
	 */
	public static void checkAndCreateDir(String filePath) {
		try {
			if (filePath != null) {
				File file = new File(filePath); // 建立文件路径
				if (!file.exists()) // 如果没有指定目录
					file.mkdirs();// 建一个
				logger.debug("Dir '{}' successfully created", filePath);
			}
		} catch (Exception e) {
			logger.info("mkdirs error, {} !", e.getMessage());
		}
	}

	/**
	 * 检查文件是否存在
	 * 
	 * @param filePath
	 *            文件完整名称和目录
	 * @return
	 */
	public static boolean checkFile(String filePath) {
		if (StringUtil.isNotEmpty(filePath)) {
			File file = new File(filePath);
			if (file.exists())
				return true;
		}
		return false;
	}

	/**
	 * 转存文件
	 * 
	 * @param srcFilePath
	 *            源文件完整名称和目录
	 * @param destFileName
	 *            目标文件名称
	 * @param destPath
	 *            目标文件目录
	 */
	public static void moveFile(String srcFilePath, String destFileName,
			String destPath) {
		File srcFile = new File(srcFilePath);
		if (srcFile.exists())
			srcFile.renameTo(new File(destPath, destFileName));
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 *            文件完整名称和目录
	 */

	public static void deleteFile(String filePath) {
		if (StringUtil.isNotEmpty(filePath)) {
			File file = new File(filePath);
			if (file.exists())
				file.delete();
		}
	}

	/**
	 * 取得上传文件所在服务器路径
	 * 
	 * @return
	 */
	public static String getFileServerLocation() {
		return getProperty("file.server.location");
	}

	/**
	 * 返回文件扩展名(小写)
	 * 
	 * @param fileName
	 *            文件扩展名
	 * @return
	 */
	public static String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

	}

	/**
	 * 判断文件是否允许上传
	 * 
	 * @param extension
	 *            文件扩展
	 * @return
	 */
	public static boolean isAllowed(final String extension) {
		if (StringUtil.isEmpty(extension))
			return false;
		else {
			String ext = extension.toLowerCase();
			Set<String> set = new HashSet<String>();
			set = StringUtil.getSet(getProperty("file.extension.allowed"), "|");
			if (set.size() == 0)
				return false;
			return set.contains(ext);
		}

	}

	/**
	 * 判断Image是否允许上传
	 * 
	 * @param extension
	 *            Image扩展
	 * @return
	 */
	public static boolean isAllowedImage(final String extension) {
		if (StringUtil.isEmpty(extension))
			return false;
		else {
			String ext = extension.toLowerCase();
			Set<String> set = new HashSet<String>();
			set = StringUtil
					.getSet(getProperty("image.extension.allowed"), "|");
			if (set.size() == 0)
				return false;
			return set.contains(ext);
		}

	}

	/**
	 * 取得文件服务器路径
	 * 
	 * @return
	 */
	public static String getServerLocation() {
		return getProperty("file.server.location");
	}

	/**
	 * 取得保存文章的物理“根”目录
	 * 
	 * @return
	 */
	public static String getArticleHtmlPath() {
		return getProperty("article.html.path");
	}

	/**
	 * 取得文章保存charset格式
	 * 
	 * @return
	 */
	public static String getArticleHtmlCharset() {
		return getProperty("article.html.charset");
	}

	/**
	 * 取得文章图片目录
	 * 
	 * @return
	 */
	public static String getArticleImagePath() {
		return getProperty("image.server.path");
	}

	/**
	 * 取得广告像片所在目录
	 * 
	 * @return
	 */
	public static String getPhotoPath() {
		return getProperty("photo.server.path");
	}

	/**
	 * 取得广告像片Web访问URL
	 * 
	 * @return
	 */
	public static String getPhotoUrl() {
		return getProperty("photo.server.url");
	}

	/**
	 * Checks if a path corresponds to the rules defined <a href=
	 * "http://docs.fckeditor.net/FCKeditor_2.x/Developers_Guide/Server_Side_Integration#File_Browser_Requests"
	 * >here</a>.
	 * 
	 * @param path
	 * @return <code>true</code> if path corresponds to rules or
	 *         <code>false</code>.
	 */
	public static boolean isValidPath(final String path) {
		if (StringUtil.isEmpty(path))
			return false;
		if (!path.startsWith("/"))
			return false;
		if (!path.endsWith("/"))
			return false;

		if (!path.equals(FilenameUtils.separatorsToUnix(FilenameUtils
				.normalize(path))))
			return false;

		return true;
	}

	/**
	 * Checks if the underlying file of the InputStream is an image.
	 * 
	 * @param in
	 *            An input stream
	 * @return <code>true</code> if the underlying file is an image else
	 *         <code>false</code>.
	 */
	public static boolean isImage(final InputStream in) {
		ImageInfo ii = new ImageInfo();
		ii.setInput(in);
		return ii.check();
	}

	public static void main(String[] args) {
		File f = new File("/app/nginx/0.7.65" + "");
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		Map<String, Long> pidMap = new HashMap<String, Long>();
		tree(f, 1, 0L, nodeList, pidMap);
		// simpleFileTree(f, 1L, nodeList);
		// nodeList.remove(0);
		for (TreeNode o : nodeList)
			System.out
					.println("id: " + o.getId() + " pid: " + o.getPid()
							+ " name: " + o.getName() + " isParent: "
							+ o.getIsParent());

	}

	public static void tree(File f, int level, long id,
			List<TreeNode> nodeList, Map<String, Long> pidMap) {
		// String preStr = "";
		// for (int i = 0; i < level; i++) {
		// preStr += "-";
		// }

		File[] children = f.listFiles();
		for (File child : children) {
			id++;
			String path = child.getPath();
			if (pidMap.get(path) == null)
				pidMap.put(path, id);

			TreeNode o = new TreeNode();
			o.setId(id);
			o.setPid(pidMap.get(child.getParent()));
			o.setName(child.getName());
			nodeList.add(o);
			if (child.isDirectory()) {
				tree(child, level + 1, id, nodeList, pidMap);
			}
		}

		// for (int i = 0; i < children.length; i++) {
		// System.out.println(preStr + children[i].getName());
		// if (children[i].isDirectory()) {
		// tree(children[i], level + 1, id, pid, nodeList);
		// }
		// }
	}

	public static void simpleFileTree(File f, Long id, List<TreeNode> list) {
		File[] children = f.listFiles();
		Long systemTime = System.currentTimeMillis();
		for (File child : children) {
			TreeNode o = new TreeNode();
			o.setId(systemTime++);
			o.setPid(id);
			o.setName(child.getName());
			if (child.isFile()) {
				o.setIsParent(false);
			}
			list.add(o);
		}
	}

	public static void addToNodeList(long id, long pid, File[] children,
			List<Node> nodeList) {
		for (File child : children) {
			Node o = new Node(id, pid, child.getName());
			nodeList.add((int) id, o);
			id++;
		}
	}

}
