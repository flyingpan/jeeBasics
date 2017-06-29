package ch.flyingpan.jee.basics;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.flyingpan.jee.basics.services.RemoteTaskService;

public class ServiceFactory {
	
    /**
     * Looks up and returns the proxy to remote stateless calculator bean
     *
     * @return
     * @throws NamingException
     */
    public static RemoteTaskService lookupRemoteTaskService() throws NamingException {
        
        Properties prop = new Properties();
        
        prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        Context context = new InitialContext(prop);
        return (RemoteTaskService) context.lookup("ejb:jeebasics-ear/tasks-impl//TaskBean!ch.flyingpan.jee.basics.services.RemoteTaskService");
    }
 

}
