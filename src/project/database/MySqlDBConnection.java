package project.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import project.event.Event;
import project.user.User;

public class MySqlDBConnection {

	// public static void main(String [] args) throws IOException {
	// System.out.println("Hello, this is the DB file!");

	private String DB_LINK = "jdbc:mysql://localhost/project";
	private String DB_USERNAME = "tanuj";
	private String DB_PASSWORD = "tanuj211";

	private Connection connection = null;

	public MySqlDBConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to load the JDBC Driver.");
			return;
		}
		//initialiseConnection();
	}

	private void initialiseConnection() {
		if (connection == null) {
			try {
				this.connection = DriverManager.getConnection(DB_LINK, DB_USERNAME, DB_PASSWORD);
			} catch (SQLException e) {
				System.out.println("Connection failed :(");
				e.printStackTrace();
				return;
			}
		}

		if (connection != null) {
			System.out.println("Database connection established!");
			
		} else {
			System.out.println("Unable to make a connection!");
		}
		System.out.println();
	}
	
	public List<Event> getEventListFromDB() {
		initialiseConnection();
		
		List<Event> eventList = new ArrayList<Event>();
		try {
			String query = "SELECT * FROM event_features ORDER BY event_id, feature";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			int event_id_tracker = -1;
			Event event = null;

			while (rs.next()) {
				int id = rs.getInt("id");
				int event_id = rs.getInt("event_id");

				int featureIndex = rs.getInt("feature");

				// If the same event_id as before then add to FeatureIndex
				if (event_id == event_id_tracker) {
					event.addFeatureIndex(featureIndex);
				} else {
					// I.E. if the eventList already has at least one element
					if (event_id_tracker > 0) {
						eventList.add(event);
					}
					event = new Event(event_id);
					event.addFeatureIndex(featureIndex);
					event_id_tracker = event_id;
				}
			}
			// ADD the last element
			eventList.add(event);

			/*
			 * query =
			 * "INSERT INTO test_table(string_field, int_field) VALUES('hello', 22)"
			 * ; int val = statement.executeUpdate(query); // val == 1 for
			 * success, 0 for failure if (val == 1) {
			 * System.out.println("Insert performed successfully!"); } else {
			 * System.out.println("Insert could not be performed"); }
			 */
			if (connection != null) {
				connection.close();
				connection = null;
				System.out.println("Database Connection Closed");
			}
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}

		} catch (SQLException e) {
			System.out
					.println("Something is wrong with the query! or could not close variables");
		}

		return eventList;
	}

	public void insertSimilarEventsIntoDB(List<Event> eventList) {
		System.out.println("Inserting recommendations into Database...");
		initialiseConnection();

		try {
			String query;
			int eventID, similarEventID;
			
			// First delete all old recommendations
			query = "DELETE FROM similar_events";
			Statement statement = connection.createStatement();
			statement.execute(query);
			System.out.println("Deleted old recommendations...");
			
			// Now insert all new recommendations
			System.out.println("Adding new recommendations...");
			statement = connection.createStatement();
			connection.setAutoCommit(false);

			for (Event event : eventList) {
				eventID = event.getEventID();
				List<Event> similarEvents = event.getSimilarEvents();
				System.out.print("Similar events for Event with ID "
						+ event.getEventID() + ": ");

				for (Event similarEvent : similarEvents) {
					similarEventID = similarEvent.getEventID();
					query = "INSERT INTO similar_events(event_id, similar_event_id, value)"
							+ " VALUES("
							+ eventID
							+ ", "
							+ similarEventID
							+ ", " + 1 + ")";

					System.out.print(similarEvent.getEventID() + ", ");

					statement.addBatch(query);
				}

				statement.executeBatch();
				connection.commit();

				System.out.println();
			}

			if (connection != null) {
				connection.close();
				connection = null;
				System.out.println("Database Connection Closed");

			}
			if (statement != null) {
				statement.close();
			}

		} catch (SQLException e) {
			System.out
					.println("Something is wrong with the query! or could not close variables");
		}
	}
	
	public List<User> getUserListFromDB() {
		initialiseConnection();
		
		List<User> userList = new ArrayList<User>();
		try {
			String query = "SELECT * FROM user_features ORDER BY user_id, feature";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			int user_id_tracker = -1;
			User user = null;

			while (rs.next()) {
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");

				int featureIndex = rs.getInt("feature");
				int featureValue = rs.getInt("value");

				// If the same event_id as before then add to FeatureIndex
				if (user_id == user_id_tracker) {
					user.addFeatureIndex(featureIndex);
					user.addFeatureValue(featureValue);
				} else {
					// I.E. if the eventList already has at least one element
					if (user_id_tracker > 0) {
						userList.add(user);
					}
					user = new User(user_id);
					user.addFeatureIndex(featureIndex);
					user.addFeatureValue(featureValue);
					user_id_tracker = user_id;
				}
			}
			// ADD the last element
			userList.add(user);

			if (connection != null) {
				connection.close();
				connection = null;
				System.out.println("Database Connection Closed");
			}
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}

		} catch (SQLException e) {
			System.out
					.println("Something is wrong with the query! or could not close variables");
		}

		return userList;
	}

	public void insertContentBasedRecommendationsIntoDB(List<User> userList) {
		System.out.println("Inserting Event recommendations into Database...");
		initialiseConnection();

		try {
			String query;
			int userID, recommendedEventID;
			
			// First delete all old recommendations
			query = "DELETE FROM content_based_recommendations";
			Statement statement = connection.createStatement();
			statement.execute(query);
			System.out.println("Deleted old recommendations...");
			
			// Now insert all new recommendations
			System.out.println("Adding new recommendations...");
			statement = connection.createStatement();
			connection.setAutoCommit(false);

			for (User user : userList) {
				userID = user.getUserID();
				List<Event> recommendedEvents = user.getRecommendedEvents();
				System.out.print("Recommended events for User with ID "
						+ user.getUserID() + ": ");

				for (Event recommendedEvent : recommendedEvents) {
					recommendedEventID = recommendedEvent.getEventID();
					query = "INSERT INTO content_based_recommendations(user_id, event_id)"
							+ " VALUES("
							+ userID
							+ ", "
							+ recommendedEventID
							+ ")";

					System.out.print(recommendedEventID + ", ");

					statement.addBatch(query);
				}

				statement.executeBatch();
				connection.commit();

				System.out.println();
			}

			if (connection != null) {
				connection.close();
				connection = null;
				System.out.println("Database Connection Closed");

			}
			if (statement != null) {
				statement.close();
			}

		} catch (SQLException e) {
			System.out
					.println("Something is wrong with the query! or could not close variables");
		}
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void insertCollaborativeRecommendationsIntoDB(List<User> userList) {
		compileSimilarUsersEventList(userList);
		insertSimilarUsersEventList(userList);
	}
	
	private void compileSimilarUsersEventList(List<User> userList) {
		initialiseConnection();
		Statement statement = null;
		ResultSet rs = null;
		
		System.out.println("Compiling Events from Similar Users...");

		for (User user : userList) {
			List<Integer> globalEventIDList = new ArrayList<Integer>();// For similar User's Events
			List<Integer> globalEventCountList = new ArrayList<Integer>();// Corresponding counts
			
			List<User> similarUsers = user.getSimilarUsers();
			for (User similarUser : similarUsers) {
				int similarUserId = similarUser.getUserID();
				try {
					String query = "SELECT event_id " +
							"FROM attendees " +
							"WHERE user_id = " + similarUserId;
					statement = connection.createStatement();
					rs = statement.executeQuery(query);
		
					while (rs.next()) {
						int event_id = rs.getInt("event_id");
						if(globalEventIDList.contains(event_id)) {
							int index = globalEventIDList.indexOf(event_id);
							int oldCount = globalEventCountList.get(index);
							globalEventCountList.set(index, oldCount + 1);
						} else {
							globalEventIDList.add(event_id);
							globalEventCountList.add(1);
						}
					}
					
					
				} catch (SQLException e) {
					System.out.println("Something is wrong with the query!");
				} 
			}
			printEventTally(globalEventIDList, globalEventCountList);
			user.setSimilarUsersEventIDList(globalEventIDList);
			user.setSimilarUsersEventCountList(globalEventCountList);
			System.out.println();
		}
		
		try {
			if (connection != null) {
				connection.close();
				connection = null;
				System.out.println("Database Connection Closed");
			}
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("Could not close variables!");
		}
	}
	
	
	private void insertSimilarUsersEventList(List<User> userList) {
		System.out.println("Inserting Collaborative Event recommendations into Database...");
		initialiseConnection();

		try {
			String query;
			int userID;
			
			// First delete all old recommendations
			query = "DELETE FROM collaborative_recommendations";
			Statement statement = connection.createStatement();
			statement.execute(query);
			System.out.println("Deleted old recommendations...");
			
			// Now insert all new recommendations
			System.out.println("Adding new recommendations...");
			statement = connection.createStatement();
			connection.setAutoCommit(false);

			for (User user : userList) {
				userID = user.getUserID();
				List<Integer> similarUsersEventIDList = user.getSimilarUsersEventIDList();
				List<Integer> similarUsersEventCountList = user.getSimilarUsersEventCountList();
				
				printEventTally(similarUsersEventIDList, similarUsersEventCountList);
				System.out.println();

				for(int i = 0; i < similarUsersEventIDList.size(); i++) {
					int recommendedEventID = similarUsersEventIDList.get(i);
					int recommendedEventCount = similarUsersEventCountList.get(i);
					query = "INSERT INTO collaborative_recommendations(user_id, event_id, count)"
							+ " VALUES("
							+ userID
							+ ", "
							+ recommendedEventID
							+ ", "
							+ recommendedEventCount
							+ ")";

					statement.addBatch(query);
				}

				statement.executeBatch();
				connection.commit();

				System.out.println();
			}

			if (connection != null) {
				connection.close();
				connection = null;
				System.out.println("Database Connection Closed");

			}
			if (statement != null) {
				statement.close();
			}

		} catch (SQLException e) {
			System.out
					.println("Something is wrong with the query! or could not close variables");
		}
			
	}

	private void printEventTally(List<Integer> globalEventIDList, List<Integer> globalCountList) {
		if(globalEventIDList.size() != globalCountList.size()) {
			System.out.println("INCONSISTENCY DETECTED!! Global EventID List and Global Count List not the same size!");
		} else {
			for(int i = 0; i < globalEventIDList.size(); i++) {
				System.out.println("Count for Event with ID " + 
									globalEventIDList.get(i) + ": " + 
									globalCountList.get(i));
			}
		}
	} 
	
}
