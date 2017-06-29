package ch.flyingpan.jee.basics;

import java.util.List;

import javax.ejb.Local;


@Local
public interface UserDao {

    public User getForUsername(String username);

    public void createUser(User user);
    
    public List<User> getAll();
    
    public void updateUser(User user);
}
