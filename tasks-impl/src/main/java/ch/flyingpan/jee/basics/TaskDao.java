package ch.flyingpan.jee.basics;

import java.util.List;

import javax.ejb.Local;


@Local
public interface TaskDao {

    void createTask(User user, Task task);

    List<Task> getAll(User user);

    List<Task> getRange(User user, int offset, int count);

    List<Task> getForTitle(User user, String title);

    void deleteTask(Task task);
    
    void longOperation();
    
    void updateTask(Task task);
    
    Task getTask(long id);
}