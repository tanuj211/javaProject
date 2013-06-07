package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import project.event.Event;
import project.recommender.AlgorithmInterface;
import project.recommender.CosineSimilarity;
import project.recommender.EventEventRecommender;
import project.recommender.PearsonCorrelation;
import project.recommender.RecommenderInterface;
import project.recommender.UserEventRecommender;
import project.recommender.UserUserRecommender;

public class Controller {

	public static void main(String [] args) throws IOException {
		System.out.println("Running....");
		System.out.println();
	
		AlgorithmInterface algorithm = new PearsonCorrelation();
		RecommenderInterface recommender = new UserUserRecommender(algorithm);
		recommender.run();
	}
}
