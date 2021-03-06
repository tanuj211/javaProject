package project.recommender;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.database.MySqlDBConnection;
import project.event.Event;

public class EventEventRecommender implements RecommenderInterface {

	private AlgorithmInterface algorithm;

	public EventEventRecommender(AlgorithmInterface algorithm) {
		this.algorithm = algorithm;
	}

	// Run the optimised implementation of the algorithm for EventEvent.
	@Override
	public void run() {		
		MySqlDBConnection dbConnection = null;
		try {
			dbConnection = new MySqlDBConnection();
			List<Event> eventList = dbConnection.getEventListFromDB();
			printEventList(eventList);
			algorithm.runEventEvent(eventList);
			dbConnection.insertSimilarEventsIntoDB(eventList);
		} catch (SQLException e) {
			System.out.println("Error getting DB Connection");
			return;
		}
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

//	private void insertSimilarEventsIntoDB(List<Event> eventList) {
//		System.out.println("Inserting recommendations into Database...");
//		
//		MySqlDBConnection dbConnection = null;
//
//		try {
//			dbConnection = new MySqlDBConnection();
//		} catch (SQLException e) {
//			System.out.println("Error getting DB Connection");
//		}
//
//		Connection connection = dbConnection.getConnection();
//
//		try {
//			String query;
//			int eventID, similarEventID;
//			
//			// First delete all old recommendations
//			query = "DELETE FROM similar_events";
//			Statement statement = connection.createStatement();
//			statement.execute(query);
//			System.out.println("Deleted old recommendations...");
//			
//			// Now insert all new recommendations
//			System.out.println("Adding new recommendations...");
//			statement = connection.createStatement();
//			connection.setAutoCommit(false);
//
//			for (Event event : eventList) {
//				eventID = event.getEventID();
//				List<Event> similarEvents = event.getSimilarEvents();
//				System.out.print("Similar events for Event with ID "
//						+ event.getEventID() + ": ");
//
//				for (Event similarEvent : similarEvents) {
//					similarEventID = similarEvent.getEventID();
//					query = "INSERT INTO similar_events(event_id, similar_event_id, value)"
//							+ " VALUES("
//							+ eventID
//							+ ", "
//							+ similarEventID
//							+ ", " + 1 + ")";
//
//					System.out.print(similarEvent.getEventID() + ", ");
//
//					statement.addBatch(query);
//				}
//
//				statement.executeBatch();
//				connection.commit();
//
//				System.out.println();
//			}
//
//			if (connection != null) {
//				connection.close();
//			}
//			if (statement != null) {
//				statement.close();
//			}
//
//		} catch (SQLException e) {
//			System.out
//					.println("Something is wrong with the query! or could not close variables");
//		}
//	}

//	private List<Event> getEventListFromDB() {
//		List<Event> eventList = null;
//		eventList = new ArrayList<Event>();
//
//		MySqlDBConnection dbConnection = null;
//		try {
//			dbConnection = new MySqlDBConnection();
//		} catch (SQLException e) {
//			System.out.println("Error getting DB Connection");
//		}
//
//		Connection connection = dbConnection.getConnection();
//
//		try {
//			String query = "SELECT * FROM event_features";
//			Statement statement = connection.createStatement();
//			ResultSet rs = statement.executeQuery(query);
//
//			int event_id_tracker = -1;
//			Event event = null;
//
//			while (rs.next()) {
//				int id = rs.getInt("id");
//				int event_id = rs.getInt("event_id");
//
//				int featureIndex = rs.getInt("feature");
//
//				// If the same event_id as before then add to FeatureIndex
//				if (event_id == event_id_tracker) {
//					event.addFeatureIndex(featureIndex);
//				} else {
//					// I.E. if the eventList already has at least one element
//					if (event_id_tracker > 0) {
//						eventList.add(event);
//					}
//					event = new Event(event_id);
//					event.addFeatureIndex(featureIndex);
//					event_id_tracker = event_id;
//				}
//			}
//			// ADD the last element
//			eventList.add(event);
//
//			/*
//			 * query =
//			 * "INSERT INTO test_table(string_field, int_field) VALUES('hello', 22)"
//			 * ; int val = statement.executeUpdate(query); // val == 1 for
//			 * success, 0 for failure if (val == 1) {
//			 * System.out.println("Insert performed successfully!"); } else {
//			 * System.out.println("Insert could not be performed"); }
//			 */
//			if (connection != null) {
//				connection.close();
//			}
//			if (statement != null) {
//				statement.close();
//			}
//			if (rs != null) {
//				rs.close();
//			}
//
//		} catch (SQLException e) {
//			System.out
//					.println("Something is wrong with the query! or could not close variables");
//		}
//
//		return eventList;
//	}

	private List<Event> getEventsList() {
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
		event4.addFeatureIndex(4);
		event4.addFeatureIndex(5);
		event4.addFeatureIndex(6);
		event4.addFeatureIndex(2);

		List<Event> eventList;
		eventList = new ArrayList<Event>();
		eventList.add(event1);
		eventList.add(event2);
		eventList.add(event3);
		eventList.add(event4);

		return eventList;
	}

}
