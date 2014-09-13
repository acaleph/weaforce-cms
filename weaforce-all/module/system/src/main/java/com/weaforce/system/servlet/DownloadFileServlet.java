package com.weaforce.system.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weaforce.core.util.DownloadUtil;
import com.weaforce.core.util.PropertiesLoader;
import com.weaforce.system.component.spring.Security;

public class DownloadFileServlet extends HttpServlet {

	private static final long serialVersionUID = -4996040382561982256L;

	private String serverLocation;

	/**
	 * 初始化contentType，enc，fileRoot
	 */
	public void init(ServletConfig config) throws ServletException {
		PropertiesLoader loader = new PropertiesLoader();
		serverLocation = loader.getProperty("file.server.location");
	}

	/**
	 * 构造download(fileId fileName)函数
	 * 
	 * @param fileId
	 *            文件主键
	 * @param fileName
	 *            上载时本地文件名
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (Security.getUser() == null)
			return;
		// 上载时的本地文件名称
		String fileServer = request.getParameter("fileServer");
		String fileLocal = request.getParameter("fileLocal");
		// 根据fileId生成文件服务器文件名称:不需要call db
		DownloadUtil.simpleDownload(response, serverLocation + "\\" + fileServer,
				fileLocal);
	}

}