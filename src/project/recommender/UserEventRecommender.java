package project.recommender;

public class UserEventRecommender implements RecommenderInterface{
	
	private AlgorithmInterface algorithm;

	public UserEventRecommender(AlgorithmInterface algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public void run() {
		algorithm.runUserEvent();
	}

}
