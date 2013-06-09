package project.recommender;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.database.MySqlDBConnection;
import project.event.Event;
import project.user.User;

public class UserEventRecommender implements RecommenderInterface{
	
	private AlgorithmInterface algorithm;

	public UserEventRecommender(AlgorithmInterface algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public void run() {
//		List<User> userList = getUserList();
//		List<Event> eventList = getEventList();
//		algorithm.runUserEvent(userList, eventList);
		
		MySqlDBConnection dbConnection = null;
		try {
			dbConnection = new MySqlDBConnection();
			List<Event> eventList = dbConnection.getEventListFromDB();
			List<User> userList = dbConnection.getUserListFromDB();
			printEventList(eventList);
			printUserList(userList);
			algorithm.runUserEvent(userList, eventList);
			//dbConnection.insertContentBasedRecommendationsIntoDB(userList);
		} catch (SQLException e) {
			System.out.println("Error getting DB Connection");
			return;
		}
	}
	
	private void printUserList(List<User> userList) {
		for (User user : userList) {
			System.out.println("User ID: " + user.getUserID());
			System.out.print("Feature Index Vector: ");
			for (int i : user.getFeatureIndexVector()) {
				System.out.print(i + ", ");
			}
			System.out.println();
			System.out.print("Feature Value Vector: ");
			for (int i : user.getFeatureValueVector()) {
				System.out.print(i + ", ");
			}
			
			System.out.println();
			System.out.println("--------------------------");
		}
		System.out.println();
	}

	private void printEventList(List<Event> eventList) {
		for (Event event : eventList) {
			System.out.println("Event ID: " + event.getEventID());
			System.out.print("Feature Index Vector: ");
			for (int i : event.getFeatureIndexVector()) {
				System.out.print(i + ", ");
			}
			System.out.println();
			System.out.println("--------------------------");
		}
		System.out.println();
	}

	private List<User> getUserList() {
		User user1 = new User(1);
		User user2 = new User(2);
		User user3 = new User(3);
		User user4 = new User(4);
		
		user1.addFeatureIndex(1);
		user1.addFeatureIndex(3);
		user1.addFeatureIndex(5);
		user1.addFeatureIndex(7);
		user1.addFeatureIndex(9);

		user1.addFeatureValue(3);
		user1.addFeatureValue(3);
		user1.addFeatureValue(3);
		user1.addFeatureValue(3);
		user1.addFeatureValue(3);

		user2.addFeatureIndex(1);
		user2.addFeatureIndex(3);
		user2.addFeatureIndex(5);
		user2.addFeatureIndex(8);
		user2.addFeatureIndex(10);

		user2.addFeatureValue(3);
		user2.addFeatureValue(3);
		user2.addFeatureValue(3);
		user2.addFeatureValue(3);
		user2.addFeatureValue(3);

		user3.addFeatureIndex(1);
		user3.addFeatureIndex(2);
		user3.addFeatureIndex(5);
		user3.addFeatureIndex(7);
		user3.addFeatureIndex(8);
		user3.addFeatureIndex(9);

		user3.addFeatureValue(3);
		user3.addFeatureValue(3);
		user3.addFeatureValue(3);
		user3.addFeatureValue(3);
		user3.addFeatureValue(3);
		user3.addFeatureValue(3);
		
		user4.addFeatureIndex(3);
		user4.addFeatureIndex(4);
		user4.addFeatureIndex(5);
		user4.addFeatureIndex(6);
		user4.addFeatureIndex(7);
		user4.addFeatureIndex(9);

		user4.addFeatureValue(2);
		user4.addFeatureValue(2);
		user4.addFeatureValue(2);
		user4.addFeatureValue(2);
		user4.addFeatureValue(1);
		user4.addFeatureValue(1);

		List<User> userList;
		userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		userList.add(user4);
		return userList;
	}

	private List<Event> getEventList() {
		Event event1 = new Event(11);
		Event event2 = new Event(22);
		Event event3 = new Event(33);
		Event event4 = new Event(44);

		event1.addFeatureIndex(1);
		event1.addFeatureIndex(6);
		event1.addFeatureIndex(7);
		event1.addFeatureIndex(9);
		event1.addFeatureIndex(10);

		event2.addFeatureIndex(1);
		event2.addFeatureIndex(5);
		event2.addFeatureIndex(7);
		event2.addFeatureIndex(8);
		event2.addFeatureIndex(9);

		event3.addFeatureIndex(4);
		event3.addFeatureIndex(5);
		event3.addFeatureIndex(6);
		event3.addFeatureIndex(2);
		event3.addFeatureIndex(10);

		event4.addFeatureIndex(1);
		event4.addFeatureIndex(3);
		event4.addFeatureIndex(5);
		event4.addFeatureIndex(7);
		event4.addFeatureIndex(9);

		List<Event> eventList;
		eventList = new ArrayList<Event>();
		eventList.add(event1);
		eventList.add(event2);
		eventList.add(event3);
		eventList.add(event4);
		
		return eventList;
	}
}
