package ch.flyingpan.jee.basics;

import java.io.Serializable;
import java.util.List;

public class TaskStatistics implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private final int totalUsers;
	
	private final int totalTasks;
	
	private final int maxTasksPerUser;
	
	private final double averageTasksPerUser;
	
	public TaskStatistics(List<User> users){
		totalUsers = users.size();
		int taskSum = 0;
		int maxTasks = 0;
		
		for(User user : users) {
			int tasksOfUser = user.getTasks().size();
			taskSum += tasksOfUser;
			
			if(tasksOfUser>maxTasks) {
				maxTasks = tasksOfUser;
			}
			
		}
		totalTasks = taskSum;
		maxTasksPerUser = maxTasks;
		averageTasksPerUser = totalTasks/totalUsers;
	}

	public int getTotalUsers() {
		return totalUsers;
	}

	public int getTotalTasks() {
		return totalTasks;
	}

	public int getMaxTasksPerUser() {
		return maxTasksPerUser;
	}

	public double getAverageTasksPerUser() {
		return averageTasksPerUser;
	}
	

}
