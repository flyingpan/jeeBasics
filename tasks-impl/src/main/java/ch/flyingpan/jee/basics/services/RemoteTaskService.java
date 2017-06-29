package ch.flyingpan.jee.basics.services;

import java.util.List;

import ch.flyingpan.jee.basics.Task;
import ch.flyingpan.jee.basics.TaskStatistics;
import ch.flyingpan.jee.basics.User;

public interface RemoteTaskService {

	TaskStatistics createTask(String username, Task task);

    List<Task> getAll(String username);
    
    User getUser(String username);

	TaskStatistics getTaskStatistics();
	
	void longOperation();
	
	void updateTask(Task task);

	Task getTask(long id);
	
	void changeUsername(String oldUsername, String newUsername);
}
