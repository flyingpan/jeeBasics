package ch.flyingpan.jee.basics;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.ejb.EJBException;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import ch.flyingpan.jee.basics.factory.ServiceFactory;
import ch.flyingpan.jee.basics.services.RemoteSampleService;
import ch.flyingpan.jee.basics.services.RemoteTaskService;

public class T08_Annotations {
	
	RemoteSampleService sampleService;
	RemoteTaskService taskService;
	
	
	@Before
	public void setup() throws NamingException{
		sampleService = ServiceFactory.lookupRemoteSampleService();
		taskService = ServiceFactory.lookupRemoteTaskService();
		
		setTaskToOrigState();
	}
	
	@Test
	public void annotationNotWorkingOnLocalMethodCall() throws NamingException {
		Task task = taskService.getTask(1);
		long taskId = task.getId();
		task.setStatus(Status.DONE);
		
		//update fails but update is done in a new transaction
		try {
			sampleService.updateTask(task);
		}catch(EJBException e) {
			
		}
		
		Task changedTask = taskService.getTask(taskId);
		assertThat(changedTask.getStatus(), is(Status.DONE));
	}
	
	private void setTaskToOrigState() {
		//set task to orig Title in order that executing the test twice there is always a change when setting the title
		Task origTask = taskService.getTask(1);
		origTask.setTitle("orig Title");
		origTask.setStatus(Status.NEW);
		
		taskService.updateTask(origTask);
	}

}
