package ch.flyingpan.jee.basics.factory;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.flyingpan.jee.basics.services.RemoteSampleService;
import ch.flyingpan.jee.basics.services.RemoteTaskService;

public class ServiceFactory {
	
    /**
     * Looks up and returns the proxy to remote stateless task service
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
    
    /**
     * Looks up and returns the proxy to remote stateless sample service
     *
     * @return
     * @throws NamingException
     */
    public static RemoteSampleService lookupRemoteSampleService() throws NamingException {
        
        Properties prop = new Properties();
        
        prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        Context context = new InitialContext(prop);
        return (RemoteSampleService) context.lookup("ejb:jeebasics-ear/tasks-impl//SampleBean!ch.flyingpan.jee.basics.services.RemoteSampleService");
    }
 

}
