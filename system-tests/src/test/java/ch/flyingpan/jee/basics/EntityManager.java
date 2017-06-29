package ch.flyingpan.jee.basics;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import ch.flyingpan.jee.basics.services.RemoteTaskService;

public class EntityManager {
	
	RemoteTaskService taskService;
	
	@Before
	public void setup() throws NamingException{
		taskService = ServiceFactory.lookupRemoteTaskService();
	}
	
	@Test
	public void testCreateTask() throws NamingException {
		TaskStatistics taskStatisticsBefore = taskService.getTaskStatistics();
		int totalTasksBefore = taskStatisticsBefore.getTotalTasks();
		TaskStatistics taskStatisticsAfter = taskService.createTask("flyingpan", new Task("someTask"));
		assertThat(taskStatisticsAfter.getTotalTasks(), is(totalTasksBefore+1));
	}
}
