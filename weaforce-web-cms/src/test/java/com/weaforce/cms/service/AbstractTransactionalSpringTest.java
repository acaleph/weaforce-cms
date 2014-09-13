package com.weaforce.cms.service;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Abstract class to be used by tests that need a Spring application context.
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public abstract class AbstractTransactionalSpringTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	
}
