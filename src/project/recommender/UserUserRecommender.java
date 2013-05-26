package project.recommender;

public class UserUserRecommender implements RecommenderInterface{
	
	private AlgorithmInterface algorithm;

	public UserUserRecommender(AlgorithmInterface algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public void run() {
		algorithm.runUserUser();
	}

}
