package ch.flyingpan.jee.basics;

import javax.ejb.Local;


@Local
public interface UserDao {

    public User getForUsername(String username);

    public void createUser(User user);
}
