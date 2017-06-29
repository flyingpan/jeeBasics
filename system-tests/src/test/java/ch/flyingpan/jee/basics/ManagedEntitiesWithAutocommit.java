package ch.flyingpan.jee.basics;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import ch.flyingpan.jee.basics.services.RemoteTaskService;

//Further readings https://www.ibm.com/developerworks/library/j-ts1/index.html

public class ManagedEntitiesWithAutocommit {
	
	RemoteTaskService taskService;
	
	@Before
	public void setup() throws NamingException{
		taskService = ServiceFactory.lookupRemoteTaskService();
	}
	
	@Test
	public void testCreateTask() throws NamingException {
		taskService.changeUsername("musteruser", "mu");

		User user = taskService.getUser("musteruser");
		assertThat(user, notNullValue());
	}
}
