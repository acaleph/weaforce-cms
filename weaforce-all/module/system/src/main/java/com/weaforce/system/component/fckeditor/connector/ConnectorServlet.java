/*
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2003-2008 Frederico Caldeira Knabben
 * 
 * == BEGIN LICENSE ==
 * 
 * Licensed under the terms of any of the following licenses at your
 * choice:
 * 
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 * 
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 * 
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 * 
 * == END LICENSE ==
 */
package com.weaforce.system.component.fckeditor.connector;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weaforce.core.util.DateUtil;
import com.weaforce.core.util.StringUtil;
import com.weaforce.system.component.fckeditor.handlers.CommandHandler;
import com.weaforce.system.component.fckeditor.handlers.ConnectorHandler;
import com.weaforce.system.component.fckeditor.handlers.ExtensionsHandler;
import com.weaforce.system.component.fckeditor.handlers.RequestCycleHandler;
import com.weaforce.system.component.fckeditor.handlers.ResourceTypeHandler;
import com.weaforce.system.component.fckeditor.response.UploadResponse;
import com.weaforce.system.component.fckeditor.response.XmlResponse;
import com.weaforce.system.component.fckeditor.tool.UtilsFile;
import com.weaforce.system.component.fckeditor.tool.UtilsResponse;
import com.weaforce.system.util.FileUtils;

/**
 * Servlet to upload and browse files.<br />
 * 
 * This servlet accepts 4 commands which interact with the server-side
 * filesystem.<br />
 * The allowed commands are:
 * <ul>
 * <li><code>GetFolders</code>: Retrieves a list of folders in the current
 * folder</li>
 * <li><code>GetFoldersAndFiles</code>: Retrives a list of files and folders in
 * the current folder</li>
 * <li><code>CreateFolder</code>: Creates a new folder in the current folder</li>
 * <li><code>FileUpload</code>: Stores an uploaded file into the current folder.
 * (must be sent with POST)</li>
 * </ul>
 * 
 * @version $Id: ConnectorServlet.java 2101 2008-06-22 22:00:48Z mosipov $
 */
public class ConnectorServlet extends HttpServlet {

	private static final long serialVersionUID = -5742008970929377161L;
	private static final Logger logger = LoggerFactory
			.getLogger(ConnectorServlet.class);
	
	private String imageSubPath;

	/**
	 * Initialize the servlet: <code>mkdir</code> &lt;DefaultUserFilesPath&gt;
	 */
	public void init() throws ServletException, IllegalArgumentException {
	}

	/**
	 * Manage the <code>GET</code> requests (<code>GetFolders</code>,
	 * <code>GetFoldersAndFiles</code>, <code>CreateFolder</code>).<br/>
	 * 
	 * The servlet accepts commands sent in the following format:<br/>
	 * <code>connector?Command=&lt;CommandName&gt;&Type=&lt;ResourceType&gt;&CurrentFolder=&lt;FolderPath&gt;</code>
	 * <p>
	 * It executes the commands and then returns the result to the client in XML
	 * format.
	 * </p>
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("Entering ConnectorServlet#doGet");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		String commandStr = request.getParameter("Command");
		String typeStr = request.getParameter("type");
		String currentFolderStr = request.getParameter("CurrentFolder");
		imageSubPath = request.getParameter("subpath");

		logger.info("Parameter Command in doGet: {}", commandStr);
		logger.info("Parameter Type in doGet: {}", typeStr);
		logger.info("Parameter CurrentFolder in doGet: {}", currentFolderStr);

		XmlResponse xr;

		if (!RequestCycleHandler.isEnabledForFileBrowsing(request))
			xr = new XmlResponse(XmlResponse.EN_ERROR,
					Messages.NOT_AUTHORIZED_FOR_BROWSING);
		else if (!CommandHandler.isValidForGet(commandStr)) {
			xr = new XmlResponse(XmlResponse.EN_ERROR, Messages.INVALID_COMMAND);
		} else if (typeStr != null && !ResourceTypeHandler.isValid(typeStr))
			xr = new XmlResponse(XmlResponse.EN_ERROR, Messages.INVALID_TYPE);
		else if (!FileUtils.isValidPath(currentFolderStr))
			xr = new XmlResponse(XmlResponse.EN_ERROR,
					Messages.INVALID_CURRENT_FOLDER);
		else {
			CommandHandler command = CommandHandler.getCommand(commandStr);
			ResourceTypeHandler resourceType = ResourceTypeHandler
					.getDefaultResourceType(typeStr);
			// String userLogin = Security.getCurrentUserName()
			// .toLowerCase();
			String typePath = UtilsFile.constructServerSidePath(request,
					resourceType);
			typePath = typePath + "/" + imageSubPath;
			logger.info("doGet: typePath value is: {}", typePath);
			String typeDirPath = typePath;
			FileUtils.checkAndCreateDir(typeDirPath);
			File typeDir = new File(typeDirPath);

			File currentDir = new File(typeDir, currentFolderStr);

			if (!currentDir.exists())
				xr = new XmlResponse(XmlResponse.EN_INVALID_FOLDER_NAME);
			else {
				String responseUrl = UtilsResponse.constructResponseUrl(
						resourceType, imageSubPath, currentFolderStr);
				logger.info("responseUrl value in doGet(): {}", responseUrl);
				xr = new XmlResponse(command, resourceType, currentFolderStr,
						responseUrl);

				if (command.equals(CommandHandler.GET_FOLDERS))
					xr.setFolders(currentDir);
				else if (command.equals(CommandHandler.GET_FOLDERS_AND_FILES))
					xr.setFoldersAndFiles(currentDir);
				else if (command.equals(CommandHandler.CREATE_FOLDER)) {
					String newFolderStr = UtilsFile.sanitizeFolderName(request
							.getParameter("NewFolderName"));

					File newFolder = new File(currentDir, newFolderStr);
					int errorNumber = XmlResponse.EN_UKNOWN;

					if (newFolder.exists())
						errorNumber = XmlResponse.EN_ALREADY_EXISTS;
					else {
						try {
							errorNumber = (newFolder.mkdir()) ? XmlResponse.EN_OK
									: XmlResponse.EN_INVALID_FOLDER_NAME;
						} catch (SecurityException e) {
							errorNumber = XmlResponse.EN_SECURITY_ERROR;
						}
					}
					xr.setError(errorNumber);
				}
			}
		}

		out.print(xr);
		out.flush();
		out.close();
		logger.debug("Exiting ConnectorServlet#doGet");
	}

	/**
	 * Manage the <code>POST</code> requests (<code>FileUpload</code>).<br />
	 * 
	 * The servlet accepts commands sent in the following format:<br />
	 * <code>connector?Command=&lt;FileUpload&gt;&Type=&lt;ResourceType&gt;&CurrentFolder=&lt;FolderPath&gt;</code>
	 * with the file in the <code>POST</code> body.<br />
	 * <br>
	 * It stores an uploaded file (renames a file if another exists with the
	 * same name) and then returns the JavaScript callback.
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("Entering Connector#doPost");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		String commandStr = request.getParameter("Command"); // "FileUpload"
		String typeStr = request.getParameter("Type"); // Image
		String currentFolderStr = request.getParameter("CurrentFolder"); // "/"
		imageSubPath = request.getParameter("subpath");
		
		logger.info("Parameter Command in doPost: {}", commandStr);
		logger.info("Parameter Type in doPost: {}", typeStr);
		logger.info("Parameter CurrentFolder in doPost: {}", currentFolderStr);

		UploadResponse ur;

		// if this is a QuickUpload request, 'commandStr' and 'currentFolderStr'
		// are empty
		if (StringUtil.isEmpty(commandStr)
				&& StringUtil.isEmpty(currentFolderStr)) {
			commandStr = "QuickUpload";
			currentFolderStr = "/";
		}

		if (!RequestCycleHandler.isEnabledForFileUpload(request))
			ur = new UploadResponse(UploadResponse.SC_SECURITY_ERROR, null,
					null, Messages.NOT_AUTHORIZED_FOR_UPLOAD);
		else if (!CommandHandler.isValidForPost(commandStr))
			ur = new UploadResponse(UploadResponse.SC_ERROR, null, null,
					Messages.INVALID_COMMAND);
		else if (typeStr != null && !ResourceTypeHandler.isValid(typeStr))
			ur = new UploadResponse(UploadResponse.SC_ERROR, null, null,
					Messages.INVALID_TYPE);
		else if (!FileUtils.isValidPath(currentFolderStr))
			ur = UploadResponse.UR_INVALID_CURRENT_FOLDER;
		else {
			ResourceTypeHandler resourceType = ResourceTypeHandler
					.getDefaultResourceType(typeStr);
			//String userLogin = Security.getCurrentUserName().toLowerCase();
			// typePath=\\data\\file in the weaforce.properties
			String typePath = UtilsFile.constructServerSidePath(request,
					resourceType);
			typePath = typePath + "/" + imageSubPath + "/"
					+ DateUtil.getCurrentYearObliqueMonthStr();
			System.out.println("typePath: " + typePath);
			logger.info("doPost: typePath value is: {}", typePath);
			String typeDirPath = typePath;
			FileUtils.checkAndCreateDir(typeDirPath);
			File typeDir = new File(typeDirPath);

			File currentDir = new File(typeDir, currentFolderStr);

			if (!currentDir.exists())
				ur = UploadResponse.UR_INVALID_CURRENT_FOLDER;
			else {

				String newFilename = null;
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				try {
					List<FileItem> items = upload.parseRequest(request);
					// We upload only one file at the same time
					FileItem uplFile = items.get(0);
					String rawName = UtilsFile.sanitizeFileName(uplFile
							.getName());
					String filename = FilenameUtils.getName(rawName);
					// String baseName =
					// FilenameUtils.removeExtension(filename);
					String extension = FilenameUtils.getExtension(filename);
					if (!ExtensionsHandler.isAllowed(resourceType, extension))
						ur = new UploadResponse(
								UploadResponse.SC_INVALID_EXTENSION);
					else {
						filename = getFilename(typeDirPath,
								System.currentTimeMillis(), extension);
						File pathToSave = new File(currentDir, filename);
						// String responseUrl = UtilsResponse
						// .constructResponseUrl(request, resourceType,
						// currentFolderStr, true,
						// ConnectorHandler.isFullUrl());
						String responseUrl = UtilsResponse
								.constructResponseUrl(resourceType, imageSubPath,
										currentFolderStr);
						if (StringUtil.isEmpty(newFilename)) {
							responseUrl = responseUrl
									+ DateUtil.getCurrentYearObliqueMonthStr()
									+ "/";
							ur = new UploadResponse(UploadResponse.SC_OK,
									responseUrl.concat(filename));
						} else
							ur = new UploadResponse(UploadResponse.SC_RENAMED,
									responseUrl.concat(newFilename),
									newFilename);

						// secure image check
						if (resourceType.equals(ResourceTypeHandler.IMAGE)
								&& ConnectorHandler.isSecureImageUploads()) {
							if (FileUtils.isImage(uplFile.getInputStream()))
								uplFile.write(pathToSave);
							else {
								uplFile.delete();
								ur = new UploadResponse(
										UploadResponse.SC_INVALID_EXTENSION);
							}
						} else
							uplFile.write(pathToSave);

					}
				} catch (Exception e) {
					ur = new UploadResponse(UploadResponse.SC_SECURITY_ERROR);
				}
				// System.out.println("newFilename2: " + newFilename);
			}

		}
		out.print(ur);
		out.flush();
		out.close();

		logger.debug("Exiting Connector#doPost");
	}

	/**
	 * Get the unique file name
	 * 
	 * @param fileName
	 * @return
	 */
	public String getFilename(String typeDirPath, Long currentTime,
			String extension) {
		File pathToSave = new File(typeDirPath + "/" + currentTime + "."
				+ extension);
		while (pathToSave.exists()) {
			currentTime++;
			pathToSave = new File(typeDirPath + "/" + currentTime + "."
					+ extension);
		}
		return currentTime + "." + extension;
	}
}
