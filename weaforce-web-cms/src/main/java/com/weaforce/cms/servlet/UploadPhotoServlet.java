package com.weaforce.cms.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.weaforce.core.util.ImageUtil;
import com.weaforce.system.util.FileUtils;

/**
 * 像片上载
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class UploadPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = -817525736886632772L;

	// 照片存放的物理路径
	private String photoPath;
	// Web访问路径
	private String photoURL;
	// 缩小后宽度
	private int smallWidth;
	// 缩小后高度
	private int smallHeight;

	public void init(ServletConfig config) throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet()与doPost()执行一样的方法
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String category = request.getParameter("category");
		initParameter(category);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			smallWidth = Integer.parseInt(request.getParameter("smallWidth"));
			smallHeight = Integer.parseInt(request.getParameter("smallHeight"));
			FileItem item = (FileItem) upload.parseRequest(request).get(2);
			if (!item.isFormField()) {
				if (item.getName() != null && !item.getName().equals("")) {// 判断是否选择了文件
					// 此时文件暂存在服务器的内存当中
					File tempFile = new File(item.getName());// 构造临时对象
					String fileName = tempFile.getName();
					String extension = FileUtils.getExtension(fileName)
							.toLowerCase();
					// 是否允许
					if (FileUtils.isAllowedImage(extension)) {
						String photoTempFile = photoPath + "\\" + fileName;
						String photoFinalFile = photoPath
								+ "\\"
								+ fileName.substring(0, fileName
										.lastIndexOf('.')) + "_small."
								+ extension;

						// 保存临时文件在服务器的物理磁盘中:文件名不变
						File file = new File(photoTempFile);
						item.write(file);

						/* ------------------- 调整图片尺寸 ------------------- */
						int type = ImageUtil.IMAGE_UNKNOWN;
						if (extension.equals("jpg") || extension.equals("jpeg"))
							type = ImageUtil.IMAGE_JPEG;
						else if (extension.equals("gif")
								|| extension.equals("png"))
							type = ImageUtil.IMAGE_PNG;
						if (type != ImageUtil.IMAGE_UNKNOWN) {
							BufferedImage image = ImageUtil.resizeImage(
									photoTempFile, type, smallWidth,
									smallHeight);
							ImageUtil.saveImage(image, photoFinalFile, type);
						}
						/* ------------------- 调整图片尺寸 ------------------- */

						/* ------------------- 关闭上传窗口 ------------------- */
						StringBuffer html = new StringBuffer(
								"<script type=\"text/javascript\">");
						html.append("window.opener.OnUploadCompleted(\"");
						html.append(photoURL
								+ "/"
								+ fileName.substring(0, fileName
										.lastIndexOf('.')) + "_small."
								+ extension + "\",\"" + photoURL + "/"
								+ fileName);
						html.append("\");window.parent.close();");
						html.append("</script>");
						response.getWriter().print(html);
						/* ------------------- 关闭上传窗口 ------------------- */
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("upload.message ", "Uploading file failed.");
		}

	}

	private void initParameter(String category) {
			photoPath = FileUtils.getPhotoPath();
			photoURL = FileUtils.getPhotoUrl();
	}
}
