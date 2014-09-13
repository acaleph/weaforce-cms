package com.weaforce.cms.service;

import java.util.List;

import junit.framework.TestCase;

import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.weaforce.entity.admin.Authority;
import com.weaforce.entity.admin.Resource;
import com.weaforce.entity.admin.Role;
import com.weaforce.entity.admin.User;
import com.weaforce.entity.app.Menu;
import com.weaforce.entity.area.City;
import com.weaforce.entity.area.Country;
import com.weaforce.entity.area.Province;
import com.weaforce.entity.area.Zone;
import com.weaforce.entity.app.Business;
import com.weaforce.entity.app.Module;
import com.weaforce.system.entity.trafic.Bus;
import com.weaforce.system.entity.trafic.BusLine;
import com.weaforce.system.entity.trafic.Mobile;

public class HibernateTest extends TestCase {
	private static AnnotationConfiguration cfg = new AnnotationConfiguration();
	private static SessionFactory sessionFactory = null;
	private static Session session = null;
	private static Transaction tx = null;

	@BeforeClass
	public static void setUpBeforeClass() {

	}

	@Before
	public void setUp() {
		cfg.addAnnotatedClass(User.class);
		cfg.addAnnotatedClass(Role.class);
		cfg.addAnnotatedClass(Country.class);
		cfg.addAnnotatedClass(Province.class);
		cfg.addAnnotatedClass(City.class);
		cfg.addAnnotatedClass(Zone.class);
		cfg.addAnnotatedClass(Menu.class);
		cfg.addAnnotatedClass(Authority.class);
		cfg.addAnnotatedClass(Business.class);
		cfg.addAnnotatedClass(BusLine.class);
		cfg.addAnnotatedClass(Resource.class);
		cfg.addAnnotatedClass(Module.class);
		cfg.addAnnotatedClass(Bus.class);
		cfg.addAnnotatedClass(Mobile.class);

		cfg.setProperty("hibernate.dialect",
				"org.hibernate.dialect.MySQLInnoDBDialect");
		cfg.setProperty("hibernate.connection.driver_class",
				"com.mysql.jdbc.Driver");
		cfg.setProperty("hibernate.connection.url",
				"jdbc:mysql://localhost:3306/easybea");
		cfg.setProperty("hibernate.connection.username", "root");
		cfg.setProperty("hibernate.connection.password", "easy");

		cfg.setProperty("hibernate.cache.provider_class",
				"org.hibernate.cache.EhCacheProvider");
		// Hibernate search
		cfg.setProperty("hibernate.search.default.directory_provider",
				"org.hibernate.search.store.FSDirectoryProvider");
		cfg.setProperty("hibernate.search.default.indexBase", "/temp/indexes");

		sessionFactory = cfg.buildSessionFactory();
		assertNotNull(sessionFactory);
		session = sessionFactory.openSession();
		assertNotNull(session);
		tx = session.beginTransaction();
		assertNotNull(tx);
		tx.begin();
	}

	@After
	public void tearDown() {
		tx.commit();
		session.close();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		if (sessionFactory != null)
			sessionFactory.close();
	}

	@Test
	public void testGetUser() {
		User user = (User) session.get(User.class, (long) 9);
		assertTrue(user != null);
	}
	/*
	@Test
	public void testIndex() {
		FullTextSession fullTextSession = Search.createFullTextSession(session);
		assertNotNull(fullTextSession);
		QueryParser queryParser = new QueryParser(null, "userLogin",
				fullTextSession.getSearchFactory().getAnalyzer(User.class));
		try {
			org.apache.lucene.search.Query luceneQuery = queryParser
					.parse("userLogin:ARIEL@TEKCON.COM.TW");
			org.hibernate.Query hibernateQuery = fullTextSession
					.createFullTextQuery(luceneQuery, User.class);
			List list = hibernateQuery.list();
			assertTrue(list.size() == 0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	*/
}
