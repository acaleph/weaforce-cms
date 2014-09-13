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
package com.weaforce.system.component.fckeditor.response;

import java.io.File;
import java.io.FileFilter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.weaforce.system.component.fckeditor.handlers.CommandHandler;
import com.weaforce.system.component.fckeditor.handlers.ResourceTypeHandler;
import com.weaforce.system.component.fckeditor.tool.Utils;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Creates an XML response for every <code>GET</code> request of the Connector
 * servlet. This class maps directly to the XML layout descibed <a
 * href="http://docs.fckeditor.net/FCKeditor_2.x/Developers_Guide/Server_Side_Integration#The_Commands">here</a>.
 * 
 * @version $Id: XmlResponse.java 2151 2008-07-02 22:03:15Z mosipov $
 * 
 */
public class XmlResponse {

	private Document document;
	private Element errorElement;
	private Element foldersElement;
	private Element filesElement;
	
	/** Error number OK */
	public static final int EN_OK = 0;
	
	/** Error number ERROR */
	public static final int EN_ERROR = 1;

	/** Error number ALREADY EXISTS */
	public static final int EN_ALREADY_EXISTS = 101;

	/** Error number INVALID FOLDER NAME */
	public static final int EN_INVALID_FOLDER_NAME = 102;

	/** Error number SECURITY ERROR */
	public static final int EN_SECURITY_ERROR = 103;

	/** Error number UNKNOWN ERROR */
	public static final int EN_UKNOWN = 110;

	/**
	 * Use this contructor if want to respond a positive message.
	 * 
	 * @param command
	 * @param resourceType
	 * @param currentFolder
	 * @param constructedUrl
	 */
	public XmlResponse(CommandHandler command, ResourceTypeHandler resourceType, 
			String currentFolder, String constructedUrl) {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}

		Element root = document.createElement("Connector");
		document.appendChild(root);
		root.setAttribute("command", command.toString());
		root.setAttribute("resourceType", resourceType.toString());

		Element currentFolderElement = document.createElement("CurrentFolder");
		currentFolderElement.setAttribute("path", currentFolder);

		currentFolderElement.setAttribute("url", constructedUrl);
		root.appendChild(currentFolderElement);

	}
	
	/**
	 * 
	 * Use this contructor if want to respond a negative/error message with
	 * custom text.
	 * 
	 * @param number
	 * @param text
	 */
	public XmlResponse(int number, String text) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}

		Element root = document.createElement("Connector");
		document.appendChild(root);
		setError(number, text);
	}
	
	/**
	 * Use this contructor if want to respond a negative/error message only.
	 * 
	 * @param number
	 */
	public XmlResponse(int number) {
		this(number, null);
	}

	/**
	 * Sets an error number with a custom message.
	 * 
	 * @param number
	 * @param text
	 */
	public void setError(int number, String text) {

		if (errorElement == null) {
			errorElement = document.createElement("Error");
			document.getDocumentElement().appendChild(errorElement);
		}

		errorElement.setAttribute("number", String.valueOf(number));
		if (Utils.isNotEmpty(text))
			errorElement.setAttribute("text", text);

	}

	/**
	 * Sets an error number.
	 * 
	 * @param number
	 */
	public void setError(int number) {
		setError(number, null);
	}

	/**
	 * Lists all folders in the given dir as XML tags.
	 * @param dir
	 */
	public void setFolders(File dir) {

		if (foldersElement != null) {
			Element parent = (Element) foldersElement.getParentNode();
			parent.removeChild(foldersElement);
		}

		foldersElement = document.createElement("Folders");
		document.getDocumentElement().appendChild(foldersElement);

		String[] fileList = dir.list(DirectoryFileFilter.DIRECTORY);
		for (String file : fileList) {
			Element folderElement = document.createElement("Folder");
			folderElement.setAttribute("name", file);
			foldersElement.appendChild(folderElement);
		}
	}
	
	/**
	 * Lists all files in the given dir as XML tags.
	 * 
	 * @param dir
	 */
	public void setFiles(File dir) {
		
		if (filesElement != null) {
			Element parent = (Element) filesElement.getParentNode();
			parent.removeChild(filesElement);
		}

		filesElement = document.createElement("Files");
		document.getDocumentElement().appendChild(filesElement);
		
		File[] fileList = dir.listFiles((FileFilter) FileFileFilter.FILE);
		long length;
		for (File file : fileList) {
			Element fileElement = document.createElement("File");
			fileElement.setAttribute("name", file.getName());
			if (file.length() < 1024)
				length = 1L;
			else 
				length = file.length()/1024;
			fileElement.setAttribute("size", String.valueOf(length));
			filesElement.appendChild(fileElement);
		}
	}
	
	/**
	 * Lists all folders and files in the given dir as XML tags.
	 * 
	 * @param dir
	 */	
	public void setFoldersAndFiles(File dir) {
		setFolders(dir);
		setFiles(dir);
	}
	
	
	public String toString() {
		document.getDocumentElement().normalize();
		TransformerFactory factory = TransformerFactory.newInstance();

		StringWriter sw = new StringWriter();

		try {
			Transformer transformer = factory.newTransformer();

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(sw);

			transformer.transform(source, result);
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}

		return sw.toString();
	}

}
