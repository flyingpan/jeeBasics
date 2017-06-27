package ch.flyingpan.jee.basics;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import ch.zuehlke.bar.ejbbasics.Task;
import ch.zuehlke.bar.ejbbasics.services.RemoteTaskService;

public class CallToBean {
	
	@Test
	public void testCallMethod() throws NamingException {
        RemoteTaskService taskService = lookupRemoteTaskService();
		List<Task> tasks = taskService.getAll("flyingpan");
		assertThat(tasks.size(), is(2));
		
	}
    /**
     * Looks up and returns the proxy to remote stateless calculator bean
     *
     * @return
     * @throws NamingException
     */
    private static RemoteTaskService lookupRemoteTaskService() throws NamingException {
        
        Properties prop = new Properties();
        
        prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        Context context = new InitialContext(prop);
        return (RemoteTaskService) context.lookup("ejb:ejbbasics-ear/tasks-impl//TaskBean!ch.zuehlke.bar.ejbbasics.services.RemoteTaskService");
    }
 

}
