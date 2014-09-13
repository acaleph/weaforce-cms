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
package com.weaforce.system.component.fckeditor.tool;

import java.io.File;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.devlib.schmidt.imageinfo.ImageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weaforce.system.component.fckeditor.handlers.ConnectorHandler;
import com.weaforce.system.component.fckeditor.handlers.ResourceTypeHandler;

/**
 * Some static helper methods in conjunction with files.
 *
 * @version $Id: UtilsFile.java 2151 2008-07-02 22:03:15Z mosipov $
 */
public class UtilsFile {
	
	private static final Logger logger = LoggerFactory.getLogger(UtilsFile.class);

	/**
	 * Do a cleanup of the file name to avoid possible problems.<br>
	 * The <code>forceSingleExtension<code> property will be respected!
	 * 
	 * @param fileName
	 * @return folder name where \ / | : ? * &quot; &lt; &gt; 'control chars' replaced by '_'
	 */
    public static String sanitizeFileName(final String fileName) {
    	if (fileName == null)
    		return null;
    	if (fileName.equals(""))
    		return "";
    
    	String name = (ConnectorHandler.isForceSingleExtension()) ? UtilsFile.forceSingleExtension(fileName)
    	        : fileName;
    
    	// Remove \ / | : ? * " < > with _
    	return name.replaceAll("\\/|\\/|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}", "_");
    }

	/**
	 * Do a cleanup of the folder name to avoid possible problems.
	 * 
	 * @param folderName
	 * @return folder name where . \ / | : ? * &quot; &lt; &gt; 'control chars'
	 *         replaced by '_'
	 */
    public static String sanitizeFolderName(final String folderName) {
    	if (folderName == null)
    		return null;
    	if (folderName.equals(""))
    		return "";
    
    	// Remove . \ / | : ? * " < > with _
    	return folderName.replaceAll("\\.|\\/|\\/|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}", "_");
    }

	/**
	 * Checks if the underlying file of the InputStream is an image.
	 * 
	 * @param in An input stream
	 * @return <code>true</code> if the underlying file is an image else
	 *         <code>false</code>.
	 */
	public static boolean isImage(final InputStream in) {
    	ImageInfo ii = new ImageInfo();
    	ii.setInput(in);
    	return ii.check();
    }

	/**
	 * Checks if a path corresponds to the rules defined <a
	 * href="http://docs.fckeditor.net/FCKeditor_2.x/Developers_Guide/Server_Side_Integration#File_Browser_Requests">here</a>.
	 * 
	 * @param path
	 * @return <code>true</code> if path corresponds to rules or
	 *         <code>false</code>.
	 */
    public static boolean isValidPath(final String path) {
    	if (Utils.isEmpty(path))
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
	 * Replaces all dots except the last one with underscores in a filename.
	 * 
	 * @param filename
	 * @return string with a single dot only
	 */
    public static String forceSingleExtension(final String filename) {
    	return filename.replaceAll("\\.(?![^.]+$)", "_");
    }

	/**
     * Checks if a filename contains more than one dot.
     * 
     * @param filename
     * @return <code>true</code> if filename contains severals dots else
     *         <code>false</code>
     */
    public static boolean isSingleExtension(final String filename) {
    	return filename.matches("[^\\.]+\\.[^\\.]+");
    }
    
    /**
	 * Checks for a dir and creates it if it does not exist.
	 * 
	 * @param dir Directory to check/create.
	 */
	public static void checkDirAndCreate(File dir) {
		if (!dir.exists()) {
			dir.mkdirs();
			logger.debug("Dir '{}' successfully created", dir);
		}
	}
	
	/**
	 * Compose server-side response path.
	 * 
	 * @param request
	 * @param resourceType
	 * @return server-side path of <code>resourceType</code>.
	 */
	public static String constructServerSidePath(HttpServletRequest request,
			ResourceTypeHandler resourceType) {
		StringBuffer sb = new StringBuffer(ConnectorHandler
				.getUserFilesPath(request));
		sb.append(resourceType.getPath());

		return sb.toString();
	}

}
