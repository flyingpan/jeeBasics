package ch.flyingpan.jee.basics;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import ch.flyingpan.jee.basics.factory.ServiceFactory;
import ch.flyingpan.jee.basics.services.RemoteTaskService;

public class T02_PersistentBag {
	
	RemoteTaskService taskService;
	
	@Before
	public void setup() throws NamingException{
		taskService = ServiceFactory.lookupRemoteTaskService();
	}
	
	@Test
	public void testGetAllTasksForUser() throws NamingException {
		List<Task> tasks = taskService.getAll("flyingpan");
		assertThat(tasks.size(), is(2));
		
	}


}
