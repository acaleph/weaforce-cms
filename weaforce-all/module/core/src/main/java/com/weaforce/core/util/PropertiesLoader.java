package com.weaforce.core.util;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesLoader {

	protected static final Log logger = LogFactory
			.getLog(PropertiesLoader.class);

	private static Properties properties = new Properties();

	public PropertiesLoader() {
		try {
			properties.load(PropertiesLoader.class.getClassLoader()
					.getResourceAsStream("weaforce.properties"));
			logger.info("Loading wewaforce.properties successfully");
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public String getProperty(final String key) {
		return properties.getProperty(key);
	}

	public void setProperty(final String key, final String value) {
		properties.setProperty(key, value);
	}
}
