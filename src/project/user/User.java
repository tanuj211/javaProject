package project.user;

import java.util.ArrayList;
import java.util.List;

import project.event.Event;

public class User {

	private int userID;
	private List<Integer> featureIndexVector;
	private List<Integer> featureValueVector;
	private List<Event> recommendedEvents;
	private List<User> similarUsers;
	
	public User(int userId) {
		userID = userId;
		featureIndexVector = new ArrayList<Integer>();
		featureValueVector = new ArrayList<Integer>();
		recommendedEvents = new ArrayList<Event>();
		similarUsers = new ArrayList<User>();
	}
	
	public void addFeatureIndex(int featureIndex) {
		featureIndexVector.add(featureIndex);
	}
	
	public void addFeatureValue(int featureValue) {
		featureValueVector.add(featureValue);
	}
	
	public void addRecommendedEvent(Event event) {
		recommendedEvents.add(event);
	}
	
	public void addSimilarUser(User user) {
		similarUsers.add(user);
	}
	
	public List<User> getSimilarUsers() {
		return similarUsers;
	}

	public void setSimilarUsers(List<User> similarUsers) {
		this.similarUsers = similarUsers;
	}

	public List<Event> getRecommendedEvents() {
		return recommendedEvents;
	}

	public void setRecommendedEvents(List<Event> recommendedEvents) {
		this.recommendedEvents = recommendedEvents;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public List<Integer> getFeatureIndexVector() {
		return featureIndexVector;
	}
	public void setFeatureIndexVector(List<Integer> featureIndexVector) {
		this.featureIndexVector = featureIndexVector;
	}
	public List<Integer> getFeatureValueVector() {
		return featureValueVector;
	}
	public void setFeatureValueVector(List<Integer> featureValueVector) {
		this.featureValueVector = featureValueVector;
	}

	public List<Integer> getNormalisedFeatureValueVector() {
		return featureValueVector;
	}
	
}
