package ch.flyingpan.jee.basics.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ch.flyingpan.jee.basics.Task;
import ch.flyingpan.jee.basics.TaskDao;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless
@Remote(RemoteSampleService.class)
public class SampleBean implements RemoteSampleService {
	
	@EJB
	TaskDao taskDao;
	
	private List<String> changeLog = new ArrayList<>();
	
	@Override
	public void performChange(String change) {
		changeLog.add(change);
	}
	
	@Override
	public void emptyChangeLog() {
		changeLog = new ArrayList<>();
	}
	
	@Override
	public List<String> getChangeLog() {
		return changeLog;
	}
	
	@Override
	public void callMethodThatThrowsException() {
		throw new RuntimeException("Called method throwing runtime exception.");
	}

//	@Override
//    @TransactionTimeout(unit=TimeUnit.SECONDS, value=5)
//	public int longOperation() {
//		return taskDao.longOperation();
//	}
	
	@Override
	public void updateTask(Task task) {
		
		updateTaskInNewTransaction(task);
		throw new RuntimeException("transaction fails, but task was added in new transaction");
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateTaskInNewTransaction(Task task) {
		taskDao.updateTask(task);
	}

}
