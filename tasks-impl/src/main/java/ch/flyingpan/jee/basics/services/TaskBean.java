package ch.flyingpan.jee.basics.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ch.flyingpan.jee.basics.Task;
import ch.flyingpan.jee.basics.TaskDao;
import ch.flyingpan.jee.basics.TaskStatistics;
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
	public TaskStatistics createTask(String username, Task task) {
		User user = loadUser(username);
		taskDao.createTask(user, task);
		return getTaskStatistics();
		
	}

	@Override
	public List<Task> getAll(String username) {
		User user = loadUser(username);
		return taskDao.getAll(user);
	}
	
	@Override
	public User getUser(String username) {
		return loadUser(username);
	}

	private User loadUser(String username) {
		return userDao.getForUsername(username);
	}
	
	@Override
	public TaskStatistics getTaskStatistics(){
		return new TaskStatistics(userDao.getAll());
	}

	@Override
	public void longOperation() {
		taskDao.longOperation();
		
	}

	@Override
	public void updateTask(Task task) {
		taskDao.updateTask(task);
		
	}
	
	@Override
	public Task getTask(long id) {
		return taskDao.getTask(id);
	}
	
	@Override
	public void changeUsername(String oldUsername, String newUsername) {
		User user = loadUser(oldUsername);
		user.setUsername(newUsername);
		if(newUsername.length()>4){
			userDao.updateUser(user);
		}
		
	}
	

}
