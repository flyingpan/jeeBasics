package ch.flyingpan.jee.basics.services;

import java.util.List;

import ch.flyingpan.jee.basics.Task;

public interface RemoteTaskService {

    void createTask(String username, Task task);

    List<Task> getAll(String username);

}
