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
package com.weaforce.system.component.fckeditor.handlers;

import java.util.HashMap;
import java.util.Map;

import com.weaforce.core.util.PropertiesLoader;
import com.weaforce.system.component.fckeditor.tool.Utils;

/**
 * Handler for different resource types.
 * 
 * @version $Id: ResourceTypeHandler.java 2151 2008-07-02 22:03:15Z mosipov $
 */
public class ResourceTypeHandler {

	private String name;
	private static Map<String, ResourceTypeHandler> types = new HashMap<String, ResourceTypeHandler>(
	        4);
	private static Map<ResourceTypeHandler, String> paths = new HashMap<ResourceTypeHandler, String>(
	        4);
	public static final ResourceTypeHandler FILE = new ResourceTypeHandler("File");
	public static final ResourceTypeHandler FLASH = new ResourceTypeHandler("Flash");
	public static final ResourceTypeHandler IMAGE = new ResourceTypeHandler("Image");
	public static final ResourceTypeHandler MEDIA = new ResourceTypeHandler("Media");
	private static PropertiesLoader loader = new PropertiesLoader();

	static {
		// initialize the resource types
		types.put(FILE.getName(), FILE);
		types.put(FLASH.getName(), FLASH);
		types.put(IMAGE.getName(), IMAGE);
		types.put(MEDIA.getName(), MEDIA);

		// initialize the sub folders for each resource type
		paths.put(FILE, loader.getProperty("connector.resourceType.file.path"));
		paths.put(IMAGE, loader.getProperty("connector.resourceType.image.path"));
		paths.put(FLASH, loader.getProperty("connector.resourceType.flash.path"));
		paths.put(MEDIA, loader.getProperty("connector.resourceType.media.path"));
	}

	private ResourceTypeHandler(final String name) {
		this.name = name;
	}

	/**
	 * Getter for the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the resource type path (sub folder).
	 * 
	 * @return The path (sub folder).
	 */
	public String getPath() {
		return paths.get(this);
	}

	/**
	 * Getter for a {@link ResourceTypeHandler} for a specified string.
	 * 
	 * @param name
	 *            A resource type to retrieve.
	 * @return A {@link ResourceTypeHandler} object holding the value
	 *         represented by the string argument.
	 * @throws IllegalArgumentException
	 *            If 'name' is <code>null</code>, empty, or does not exist.
	 */
	public static ResourceTypeHandler valueOf(final String name) throws IllegalArgumentException {
		if (Utils.isEmpty(name))
			throw new IllegalArgumentException();

		ResourceTypeHandler rt = types.get(name);
		if (rt == null)
			throw new IllegalArgumentException();
		return rt;
	}

	/**
	 * 
	 * Checks if a specfied string represents a valid resource type.
	 * 
	 * @param name
	 *            A resource type string to check.
	 * @return <code>true</code> if the string representation is valid else
	 *         <code>false</code>.
	 */
	public static boolean isValid(final String name) {
		return types.containsKey(name);
	}

	/**
	 * Tries to determine ResourceType from string and return {@link #FILE} if
	 * provided string is invalid.
	 * 
	 * @param name
	 * @return resource type
	 */
	public static ResourceTypeHandler getDefaultResourceType(final String name) {
		ResourceTypeHandler rt = getResourceType(name);
		return rt == null ? FILE : rt;
	}

	/**
	 * A wrapper for {@link #valueOf(String)}. It returns <code>null</code>
	 * instead of throwing an exception.
	 * 
	 * @param name
	 *            A resource type string to check.
	 * @return A {@link ResourceTypeHandler} object holding the value
	 *         represented by the string argument, or <code>null</code>.
	 */
	public static ResourceTypeHandler getResourceType(final String name) {
		try {
			return ResourceTypeHandler.valueOf(name);
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		try {
			ResourceTypeHandler rt = (ResourceTypeHandler) obj;
			return name.equals(rt.getName());
		} catch (ClassCastException e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	
	public int hashCode() {
		return name.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	
	public String toString() {
		return name;
	}
}
