package ch.flyingpan.jee.basics.services;

import java.util.List;

import ch.flyingpan.jee.basics.Task;

public interface RemoteSampleService {
	
	List<String> getChangeLog();

	void callMethodThatThrowsException();

//	int longOperation();

	void performChange(String change);

	void emptyChangeLog();

	void updateTask(Task task);

	void updateTaskInNewTransaction(Task task);
	
}
