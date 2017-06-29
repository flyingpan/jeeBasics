package ch.flyingpan.jee.basics.services;

import java.util.List;

public interface RemoteSampleService {
	
	List<String> getChangeLog();

	void callMethodThatThrowsException();

	void longOperation();

	void performChange(String change);

	void emptyChangeLog();

}
