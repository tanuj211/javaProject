package project.event;

import java.util.ArrayList;
import java.util.List;

public class Event {

	private int eventID;
	private List<Integer> featureVector;
	private List<Event> similarEvents;
	
	public Event(int eventId) {
		eventID = eventId;
		featureVector = new ArrayList<Integer>();
		similarEvents = new ArrayList<Event>();
	}
	
	public void addFeature(int feature) {
		featureVector.add(feature);
	}
	
	public void addSimilarEvent(Event event) {
		similarEvents.add(event);
	}
	
	public List<Event> getSimilarEvents() {
		return similarEvents;
	}

	// Setters and getters for the fields of this class
	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public List<Integer> getFeatureVector() {
		return featureVector;
	}

	public void setFeatureVector(List<Integer> featureVector) {
		this.featureVector = featureVector;
	}

	
}
