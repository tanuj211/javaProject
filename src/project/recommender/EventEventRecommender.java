package project.recommender;

public class EventEventRecommender implements RecommenderInterface {
	
	private AlgorithmInterface algorithm;

	public EventEventRecommender(AlgorithmInterface algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public void run() {
		// Run the optimised implementation of the algorithm for EventEvent.
		algorithm.runEventEvent();
	}

}
