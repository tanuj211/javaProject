package project.recommender;

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
	public void runEventEvent();

	public void runUserEvent();

	public void runUserUser();
}
