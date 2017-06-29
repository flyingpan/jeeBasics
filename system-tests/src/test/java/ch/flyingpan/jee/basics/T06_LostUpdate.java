package ch.flyingpan.jee.basics;

import javax.ejb.EJBTransactionRolledbackException;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import ch.flyingpan.jee.basics.factory.ServiceFactory;
import ch.flyingpan.jee.basics.services.RemoteTaskService;

public class T06_LostUpdate {
	
	RemoteTaskService taskService;
	
	@Before
	public void setup() throws NamingException{
		taskService = ServiceFactory.lookupRemoteTaskService();
		setTaskToOrigState();
	}
	
	@Test(expected=EJBTransactionRolledbackException.class)
	public void updateTask_overwritingChangedState_throwingException() throws NamingException {
		
		Task changeFirstTask = taskService.getTask(1);
		Task inParallelChangedTask = taskService.getTask(1);
		
		changeFirstTask.setTitle("changed Titel 1");
		changeFirstTask.setStatus(Status.DONE);
		
		taskService.updateTask(changeFirstTask);
		
		inParallelChangedTask.setTitle("changed Titel 2");
		
		taskService.updateTask(inParallelChangedTask);
		
	}

	private void setTaskToOrigState() {
		//set task to orig Title in order that executing the test twice there is always a change when setting the title
		Task origTask = taskService.getTask(1);
		origTask.setTitle("orig Title");
		origTask.setStatus(Status.NEW);
		
		taskService.updateTask(origTask);
	}

}
