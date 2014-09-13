package com.weaforce.core.common.db;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import org.freeocs.commons.cache.CacheProvider;

/**
 * 全局配置 运行时候需要加入虚拟机参数如下： -Djava.ext.dirs=webapp/WEB-INF/lib;packages -cp
 * webapp/WEB-INF/classes
 * 
 * @author chirs
 */
public class Configuration {
	// Log factory
	private final static Log log = LogFactory.getLog(Configuration.class);
	// Property file
	private final static String CFG = "/weaforce.properties";

	// private final static CacheProvider cacheProvider;
	private final static DataSource dataSource;
	private static boolean show_sql = false;
	private final static Properties smtpProperties = new Properties();
	private final static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
	private final static Properties connectionProperties = new Properties();
	static {
		try {
			// Reading parameters from property file
			connectionProperties.load(Configuration.class
					.getResourceAsStream(CFG));
			// 1.cache
			// cacheProvider = (CacheProvider)
			// Class.forName(ocsProperties.getProperty("cache_provider")).newInstance();
			// cacheProvider.start();

			// 2.database
			Properties cp_props = new Properties();
			for (Object key : connectionProperties.keySet()) {
				String skey = (String) key;
				if (skey.startsWith("jdbc.")) {
					String name = skey.substring(5);
					cp_props.put(name, connectionProperties.getProperty(skey));
					if ("show_sql".equalsIgnoreCase(name)) {
						show_sql = "true".equalsIgnoreCase(connectionProperties
								.getProperty(skey));
					}
				}
			}
			dataSource = (DataSource) Class.forName(
					connectionProperties.getProperty("data_source_provider"))
					.newInstance();
			if (dataSource.getClass().getName().indexOf("c3p0") > 0) {
				// Disable JMX in C3P0
				System.setProperty(
						"com.mchange.v2.c3p0.management.ManagementCoordinator",
						"com.mchange.v2.c3p0.management.NullManagementCoordinator");
			}
			BeanUtils.populate(dataSource, cp_props);
			// 3.Email
			for (Object key : connectionProperties.keySet()) {
				String skey = (String) key;
				if (skey.startsWith("smtp.")) {
					String name = skey.substring(5);
					smtpProperties.put(name,
							connectionProperties.getProperty(skey));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unabled to init ocs.properties...");
		}

	}

	private static String lucene_path = null;

	public final static String getLucenePath() {
		if (lucene_path != null)
			return lucene_path;
		lucene_path = connectionProperties.getProperty("lucene_idx_path");
		if (lucene_path.charAt(0) == '/') {
			lucene_path = getWebrootPath()
					+ StringUtils.replace(lucene_path.substring(1), "/",
							File.separator);
		}
		if (!lucene_path.endsWith(File.separator))
			lucene_path += File.separator;

		return lucene_path;
	}

	private static String webroot = null;

	public static String getWebrootPath() {
		if (webroot == null) {
			webroot = Configuration.class.getResource(CFG).getFile();
			try {
				webroot = new File(webroot).getParentFile().getParentFile()
						.getParentFile().getCanonicalPath();
				webroot += File.separator;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return webroot;
	}

	public final static Properties getSmtpProperties() {
		return smtpProperties;
	}

	// public final static Cache getCache(String name) {
	// return cacheProvider.buildCache(name);
	// }

	public final static synchronized Connection getConnection()
			throws SQLException {
		Connection conn = conns.get();
		if (conn == null || conn.isClosed()) {
			conn = dataSource.getConnection();
			conns.set(conn);
		}
		return (show_sql && !Proxy.isProxyClass(conn.getClass())) ? new _DebugConnection(
				conn).getConnection() : conn;
	}

	public final static synchronized void closeConnection() {
		Connection conn = conns.get();
		try {
			if (conn != null && !conn.isClosed()) {
				conn.setAutoCommit(true);
				conn.close();
			}
		} catch (SQLException e) {
			log.error("Unabled to close connection!!! ", e);
		}
		conns.set(null);
	}

	public final static void init() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			throw new RuntimeException("Unabled to initialize ConnectionPool",
					e);
		}
	}

	public final static void destroy() {
		// 断开连接池
		try {
			dataSource.getClass().getMethod("close").invoke(dataSource);
		} catch (NoSuchMethodException e) {
		} catch (Exception e) {
			log.error("Unabled to destroy DataSource!!! ", e);
		}
	}
}

/**
 * 用于跟踪执行的SQL语句
 * 
 * @author chirs
 */
class _DebugConnection implements InvocationHandler {

	private final static Log log = LogFactory.getLog(_DebugConnection.class);

	private Connection conn = null;

	public _DebugConnection(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Returns the conn.
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		return (Connection) Proxy.newProxyInstance(conn.getClass()
				.getClassLoader(), conn.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method m, Object[] args)
			throws Throwable {
		try {
			String method = m.getName();
			if ("prepareStatement".equals(method)
					|| "createStatement".equals(method))
				if (log.isInfoEnabled())
					log.info("[SQL] >>> " + args[0]);

			return m.invoke(conn, args);
		} catch (InvocationTargetException e) {
			log.error(e.getTargetException());
			throw e.getTargetException();
		}
	}


}