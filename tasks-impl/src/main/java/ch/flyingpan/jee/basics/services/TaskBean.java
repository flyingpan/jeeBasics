package ch.flyingpan.jee.basics.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ch.flyingpan.jee.basics.Task;
import ch.flyingpan.jee.basics.TaskDao;
import ch.flyingpan.jee.basics.User;
import ch.flyingpan.jee.basics.UserDao;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless
@Remote(RemoteTaskService.class)
public class TaskBean implements RemoteTaskService{
	
	@EJB
	TaskDao taskDao;
	
	@EJB
	UserDao userDao;

	@Override
	public void createTask(String username, Task task) {
		User user = userDao.getForUsername(username);
		taskDao.createTask(user, task);
		
	}

	@Override
	public List<Task> getAll(String username) {
		User user = userDao.getForUsername(username);
		return taskDao.getAll(user);
	}

}
