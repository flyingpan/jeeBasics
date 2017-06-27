package ch.zuehlke.bar.ejbbasics.services;

import java.util.List;

import ch.zuehlke.bar.ejbbasics.Task;

public interface RemoteTaskService {

    void createTask(String username, Task task);

    List<Task> getAll(String username);

}
