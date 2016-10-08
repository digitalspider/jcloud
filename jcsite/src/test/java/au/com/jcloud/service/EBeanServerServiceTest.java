package au.com.jcloud.service;

import com.avaje.ebean.EbeanServer;

import org.apache.log4j.Logger;
import org.junit.Test;

import au.com.jcloud.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by david on 2/09/16.
 */

public class EBeanServerServiceTest {

	private static final Logger LOG = Logger.getLogger(EBeanServerServiceTest.class);

	@Test
	public void testCreateServer() {
		EbeanServer server = EBeanServerService.createEbeanServer();
		LOG.info("server="+server);
	}

	@Test
	public void testCreateUser() {
		EbeanServer server = EBeanServerService.createEbeanServer();
		LOG.info("server="+server);
		User user = new User();
		user.setUsername("username");
		user.setFirstName("first");
		user.setLastName("last");
		user.setEmail("email");
		server.save(user);

		User foundUser = server.find(User.class).findUnique();
		LOG.info("user="+user);
		assertNotNull(foundUser);
		assertNotNull(foundUser.getId());
		assertEquals("email", foundUser.getEmail());
	}
}
