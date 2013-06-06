package project.recommender;

import java.util.List;

import project.event.Event;
import project.user.User;

public interface AlgorithmInterface {

//	/*
//	 * The following method is a generic, unoptimised 'run' implementation
//	 * of the algorithm
//	 */
//	public void run();
	
	/*
	 * These functions are provided if someone wants to implement an optimised version
	 * of the algorithm for a specific type of recommender. (Optimisation may
	 * involve exploiting properties of representation, etc. to minimise 
	 * computation required.)
	 */
	public void runEventEvent(List<Event> eventList);

	public void runUserEvent(List<User> userList, List<Event> eventList);

	public void runUserUser(List<User> userList);
}
