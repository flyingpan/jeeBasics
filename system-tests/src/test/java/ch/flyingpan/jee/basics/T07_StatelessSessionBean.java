package ch.flyingpan.jee.basics;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import ch.flyingpan.jee.basics.factory.ServiceFactory;
import ch.flyingpan.jee.basics.services.RemoteSampleService;

public class T07_StatelessSessionBean {
	
	RemoteSampleService sampleService;
	
	@Before
	public void setup() throws NamingException{
		sampleService = ServiceFactory.lookupRemoteSampleService();
	}
	
	@Test
	public void contractThatStatelessSessionBeanNotEnsuredByContainer() throws NamingException {
		sampleService.emptyChangeLog();
		
		sampleService.performChange("some change");
		
		List<String> changeLog = sampleService.getChangeLog();
		assertThat(changeLog.size(), is(1));

		try {
			sampleService.callMethodThatThrowsException();
		} catch (Exception e){
			
		}
		changeLog = sampleService.getChangeLog();
		assertThat(changeLog.size(), is(1));
	}

}
