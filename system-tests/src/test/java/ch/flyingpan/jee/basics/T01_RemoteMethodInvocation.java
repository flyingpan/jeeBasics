package ch.flyingpan.jee.basics;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import ch.flyingpan.jee.basics.factory.ServiceFactory;
import ch.flyingpan.jee.basics.services.RemoteTaskService;

public class T01_RemoteMethodInvocation {
	
	RemoteTaskService taskService;
	
	@Before
	public void setup() throws NamingException{
		taskService = ServiceFactory.lookupRemoteTaskService();
	}
	
	@Test
	public void testCreateTask() throws NamingException {
		TaskStatistics taskStatisticsBefore = taskService.getTaskStatistics();
		assertThat(taskStatisticsBefore.getTotalTasks(), is(2));
		
	}

}
