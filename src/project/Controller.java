package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import project.event.Event;
import project.recommender.AlgorithmInterface;
import project.recommender.CosineSimilarity;
import project.recommender.EventEventRecommender;
import project.recommender.RecommenderInterface;
import project.recommender.UserEventRecommender;
import project.recommender.UserUserRecommender;

public class Controller {

	public static void main(String [] args) throws IOException {
		System.out.println("The first run!");
		
		Event event1 = new Event(11);
		Event event2 = new Event(22);
		Event event3 = new Event(33);
		Event event4 = new Event(44);
		
		event1.addFeature(1);
		event1.addFeature(6);
		event1.addFeature(7);
		event1.addFeature(9);
		event1.addFeature(10);
		
		event2.addFeature(1);
		event2.addFeature(5);
		event2.addFeature(7);
		event2.addFeature(8);
		event2.addFeature(9);
		
		event3.addFeature(4);
		event3.addFeature(5);
		event3.addFeature(6);
		event3.addFeature(2);
		event3.addFeature(10);
		
		event4.addFeature(1);
		event4.addFeature(4);
		event4.addFeature(5);
		event4.addFeature(6);
		event4.addFeature(2);
		
		List<Event> eventList;
		eventList = new ArrayList<Event>();
		eventList.add(event1);
		eventList.add(event2);
		eventList.add(event3);
		eventList.add(event4);
		
		AlgorithmInterface algorithm = new CosineSimilarity(eventList);
		RecommenderInterface recommender = new EventEventRecommender(algorithm);
		recommender.run();
		//algorithm.run();
	}
}
