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

import com.weaforce.core.util.Global;
import com.weaforce.core.util.ImageUtil;
import com.weaforce.system.util.FileUtils;

/**
 * 文章图片上载:调整尺寸，保持原图尺寸
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class UploadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 8883873224974515011L;

	private String imageSubPath;
	private String imageFile;
	private String imageFileResize;
	private String imageURL;
	private int maxWidth;
	private int maxHeight;
	private Long systemTime;
	private File imageOriginFile = null;

	public void init(ServletConfig config) throws ServletException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			imageSubPath = request.getParameter("subpath");
			maxWidth = Integer.parseInt(request.getParameter("maxWidth"));
			maxHeight = Integer.parseInt(request.getParameter("maxHeight"));
			FileItem item = (FileItem) upload.parseRequest(request).get(2);
			// System.out.println("item: " + item);
			if (!item.isFormField()) {
				if (item.getName() != null && !item.getName().equals("")) {// 判断是否选择了文件
					// 此时文件暂存在服务器的内存当中
					File tempFile = new File(item.getName());// 构造临时对象
					String fileName = tempFile.getName();
					String extension = FileUtils.getExtension(fileName)
							.toLowerCase();
					// 是否允许
					if (FileUtils.isAllowedImage(extension)) {
						getFileName(extension);
						// 保存文件
						item.write(imageOriginFile);

						/* ------------------- 调整图片尺寸 ------------------- */
						int type = ImageUtil.IMAGE_UNKNOWN;
						if (extension.equals("jpg") || extension.equals("jpeg"))
							type = ImageUtil.IMAGE_JPEG;
						else if (extension.equals("gif")
								|| extension.equals("png"))
							type = ImageUtil.IMAGE_PNG;
						if (type != ImageUtil.IMAGE_UNKNOWN) {
							// System.out.println("imageFile: " + imageFile
							// + " maxWidth: " + maxWidth
							// + " maxHeight: " + maxHeight
							// + " type: " + type);
							BufferedImage image = ImageUtil.resizeImage(
									imageFile, type, maxWidth, maxHeight);
							ImageUtil.saveImage(image, imageFileResize, type);
						}

						/* ------------------- 调整图片尺寸 ------------------- */

						/* ----------- 关闭上传窗口并把图片宽/高/物理路径传递给父窗口 ----------- */
						StringBuffer html = new StringBuffer(
								"<script type=\"text/javascript\"> return;");
						html.append("window.opener.OnUploadCompleted(");
						// 取得页面访问文件的名称
						html.append("\"" + imageURL + "/" + systemTime + "."
								+ extension + "\",");
						html.append("\"" + imageURL + "/resize/" + systemTime
								+ "." + extension + "\",");
						html.append("\"" + imageFile + "\",\""
								+ imageFileResize + "\"," + maxWidth + ","
								+ maxHeight + ");");
						html.append("window.parent.close();");
						html.append("</script>");
						System.out.println("html: " + html);
						response.getWriter().print(html);
						/* ----------- 关闭上传窗口并把图片宽/高/物理路传递给父窗口 ----------- */
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("upload.message ", "Uploading file failed.");
		}
	}

	/**
	 * 取得文件名称
	 * 
	 * @param extension
	 *            文件扩展名
	 * @return
	 */
	public void getFileName(String extension) {
		systemTime = System.currentTimeMillis();
		imageFile = Global.IMAGE_SERVER_PATH + "/" + imageSubPath + "/"
				+ systemTime + "." + extension;
		imageFileResize = Global.IMAGE_SERVER_PATH_RESIZE + "/" + imageSubPath
				+ "/" + systemTime + "." + extension;
		// 文件在服务器的物理磁盘中的位置
		imageOriginFile = new File(imageFile);
	}

}
