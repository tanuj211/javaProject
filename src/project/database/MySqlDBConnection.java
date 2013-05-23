package project.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlDBConnection {

	public static void main(String [] args) throws IOException {
		System.out.println("Hello, this is the DB file!");
		
		String DB_LINK = "jdbc:mysql://localhost/project";
		String DB_USERNAME = "tanuj";
		String DB_PASSWORD = "tanuj211";
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to load the JDBC Driver.");
			return;
		}
		
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(DB_LINK, DB_USERNAME, DB_PASSWORD);
			} catch (SQLException e){
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
		
		
		try {
			String query = "SELECT * FROM event_features";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				int event_id = rs.getInt("event_id");
				int featureNo = rs.getInt("feature");
				
				System.out.println("ID: " + id);
				System.out.println("Event_id: " + event_id);
				System.out.println("featureNo: " + featureNo);
				System.out.println("--------------------------");
			}
			
			query = "INSERT INTO test_table(string_field, int_field) VALUES('hello', 22)";
			int val = statement.executeUpdate(query); // val == 1 for success, 0 for failure
			if (val == 1) {
				System.out.println("Insert performed successfully!"); 
			} else {
				System.out.println("Insert could not be performed");
			}
			
			if (connection != null) {
				connection.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
						
		} catch (SQLException e) {
			System.out.println("Something is wrong with the query! or could not close variables");
		}
	}
}
