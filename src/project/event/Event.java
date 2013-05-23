package project.event;

import java.util.ArrayList;
import java.util.List;

public class Event {

	private int eventID;
	private List<Integer> featureVector;
	
	public Event(int eventId) {
		eventID = eventId;
		featureVector = new ArrayList<Integer>();
	}
	
	public void addFeature(int feature) {
		featureVector.add(feature);
	}

	// Setter and getters
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
