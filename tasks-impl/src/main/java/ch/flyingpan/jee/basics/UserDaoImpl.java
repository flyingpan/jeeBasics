package ch.flyingpan.jee.basics;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserDaoImpl implements UserDao {

	@PersistenceContext
    EntityManager em;

    public User getForUsername(String username) {
        List<User> result = em.createQuery("select u from User u where u.username = ?", User.class).setParameter(1, username)
                .getResultList();

        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public void createUser(User user) {
        em.persist(user);
    }
    
    @Override
    public List<User> getAll() {
        TypedQuery<User> query = querySelectAllTasksFromUser();
        return query.getResultList();
    }

    private TypedQuery<User> querySelectAllTasksFromUser() {
        return em.createQuery("SELECT u FROM User u", User.class);
    }
    
	@Override
	public void updateUser(User user) {
        if (!em.contains(user)) {
            user = em.merge(user);
        }
	}
}
