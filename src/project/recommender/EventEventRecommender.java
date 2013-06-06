package project.recommender;

import java.util.ArrayList;
import java.util.List;

import project.event.Event;

public class EventEventRecommender implements RecommenderInterface {

	private AlgorithmInterface algorithm;

	public EventEventRecommender(AlgorithmInterface algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public void run() {
		// Run the optimised implementation of the algorithm for EventEvent.
		List<Event> eventList = getEventsList();
		algorithm.runEventEvent(eventList);
	}

	private List<Event> getEventsList() {
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
		
		return eventList;
	}

}
