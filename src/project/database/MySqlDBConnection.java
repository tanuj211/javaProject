package project.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		initialiseConnection();
	}

	private void initialiseConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(DB_LINK, DB_USERNAME, DB_PASSWORD);
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

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
