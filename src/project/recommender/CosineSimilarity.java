package project.recommender;

import java.util.List;

import project.event.Event;
import project.user.User;

public class CosineSimilarity implements AlgorithmInterface {

	private int[][] similarityMatrix;

	private List<Event> eventList;
	private int eventListLength;

	private List<User> userList;
	private int userListLength;

	public CosineSimilarity() {
	}

	/*
	 * ********CODE FOR EVENT-EVENT********
	 */
	@Override
	public void runEventEvent(List<Event> eventList) {
		this.eventList = eventList;
		eventListLength = eventList.size();

		calculateEventEventSimilarity(eventList);
		printMatrix(similarityMatrix);
		getTopNEventEvent(2);
		printSimilarEvents();
	}
	
	private int dotProductEventEvent(Event event1, Event event2) {
		int sum = 0;
		List<Integer> featureVector1 = event1.getFeatureIndexVector();
		List<Integer> featureVector2 = event2.getFeatureIndexVector();

		for (int i : featureVector1) {
			if (featureVector2.contains(i)) {
				sum += 1;
			}
		}
		return sum;
	}
	
	private void calculateEventEventSimilarity(List<Event> eventList) {
		initialiseMatrix(eventListLength, eventListLength);
		int i, j;
		for (i = 0; i < eventListLength; i++) {
			Event event1 = eventList.get(i);
			Event event2;
			int dotProduct = 0;
			for (j = i + 1; j < eventListLength; j++) {
				event2 = eventList.get(j);
				dotProduct = dotProductEventEvent(event1, event2);
				similarityMatrix[i][j] = dotProduct;
				similarityMatrix[j][i] = dotProduct;
			}
		}
	}
	
	private void getTopNEventEvent(int n) {
		if (n > similarityMatrix.length) {
			System.out
					.println("n is greater than similarity matrix's row length!!");
		}
		
		System.out.println("----TOP " + n + " Events for each User----");
		int i, j;
		int[] topIndexes = new int[n];
		int[] topValues = new int[n];
		for (i = 0; i < similarityMatrix.length; i++) {
			Event event1 = eventList.get(i);
			Event event2;
			for (j = 0; j < n; j++) {
				topIndexes[j] = j;
				topValues[j] = similarityMatrix[i][j];
			}

			for (j = n; j < similarityMatrix[i].length; j++) {
				if (isGreatherThan(similarityMatrix[i][j], topValues)) {
					int k = getSmallestValueIndex(topValues);
					topIndexes[k] = j;
					topValues[k] = similarityMatrix[i][j];
				}
			}
			
			System.out.print("Row " + i + ": ");
			for (int a = 0; a < n; a++) {
				event2 = eventList.get(topIndexes[a]);
				// The following if is needed to prevent same event similarity
				// to be included.
				// Can add more conditions here to strengthen the condition for
				// similar events
				if (topIndexes[a] != i) {
					System.out.print(topValues[a] + "(Index = " + topIndexes[a]
							+ "), ");
					event1.addSimilarEvent(event2);
				}
			}
			System.out.println();
		}
		System.out.println("-----END of Top N-----");
		System.out.println();

	}

	private void printSimilarEvents() {
		for (int i = 0; i < eventListLength; i++) {
			Event event = eventList.get(i);
			List<Event> similarEvents = event.getSimilarEvents();
			System.out.print("Similar events for Event with ID "
					+ event.getEventID() + ": ");
			for (Event similarEvent : similarEvents) {
				System.out.print(similarEvent.getEventID() + ", ");
			}
			System.out.println();
		}
	}
	
	/*
	 * ********CODE FOR USER-EVENT*********
	 */
	
	@Override
	public void runUserEvent(List<User> userList, List<Event> eventList) {
		this.userList = userList;
		this.userListLength = userList.size();
		this.eventList = eventList;
		this.eventListLength = eventList.size();

		calculateUserEventSimilarity(userList, eventList);
		printMatrix(similarityMatrix);
		getTopNUserEvent(3);
		printEventsForUsers();
	}
	
	private void printEventsForUsers() {
		for (int i = 0; i < userListLength; i++) {
			User user = userList.get(i);
			List<Event> recommendedEvents = user.getRecommendedEvents();

			System.out.print("Recommended events for User with ID "
					+ user.getUserID() + ": ");
			for (Event recommendedEvent : recommendedEvents) {
				System.out.print(recommendedEvent.getEventID() + ", ");
			}
			System.out.println();
		}
	}
	
	private void getTopNUserEvent(int n) {
		if (n > similarityMatrix.length) {
			System.out.println("n is greater than similarity matrix's row length!!");
		}

		System.out.println("----TOP " + n + " Events for each User----");
		int i, j;
		int[] topIndexes = new int[n];
		int[] topValues = new int[n];
		for (i = 0; i < similarityMatrix.length; i++) {

			for (j = 0; j < n; j++) {
				topIndexes[j] = j;
				topValues[j] = similarityMatrix[i][j];
			}

			for (j = n; j < similarityMatrix[i].length; j++) {
				if (isGreatherThan(similarityMatrix[i][j], topValues)) {
					int k = getSmallestValueIndex(topValues);
					topIndexes[k] = j;
					topValues[k] = similarityMatrix[i][j];
				}
			}
			
			User user = userList.get(i);
			Event event;
			System.out.print("Row " + i + ": ");
			for (int a = 0; a < n; a++) {
				event = eventList.get(topIndexes[a]);
				// The following if is needed to prevent same event similarity
				// to be included.
				// Can add more conditions here to strengthen the condition for
				// similar events - NO NEED HERE!!!
				
				//********************
				// ADD THRESHOLD VERIFICATION CHECK HERE!!!
				//********************
				
				System.out.print(topValues[a] + "(Index = " + topIndexes[a] + "), ");
				user.addRecommendedEvent(event);
			}
			System.out.println();
		}
		System.out.println("-----END of TOP N-----");
		System.out.println();
	}

	private void calculateUserEventSimilarity(List<User> userList, List<Event> eventList) {
		initialiseMatrix(userListLength, eventListLength);
		int i, j;
		for (i = 0; i < userListLength; i++) {
			User user = userList.get(i);
			Event event;
			int dotProduct = 0;
			for (j = 0; j < eventListLength; j++) {
				event = eventList.get(j);
				dotProduct = dotProductUserEvent(user, event);
				similarityMatrix[i][j] = dotProduct;
			}
		}
	}

	private int dotProductUserEvent(User user, Event event) {
		int sum = 0;
		List<Integer> userFeatureValueVector = user.getNormalisedFeatureValueVector();
		List<Integer> userFeatureIndexVector = user.getFeatureIndexVector();
		List<Integer> eventFeatureIndexVector = event.getFeatureIndexVector();
		int index = 0;
		for (int i : userFeatureIndexVector) {
			if (eventFeatureIndexVector.contains(i)) {
				index = userFeatureIndexVector.indexOf(i);
				sum += userFeatureValueVector.get(index);
			}
			//index++;
		}
		return sum;
	}

	/*
	 * ********CODE FOR USER-USER**********
	 */
	
	@Override
	public void runUserUser(List<User> userList) {
		this.userList = userList;
		this.userListLength = userList.size();

		calculateUserUserSimilarity(userList);
		printMatrix(similarityMatrix);
		getTopNUserUser(2);
		printSimilarUsers();
	}

	private void printSimilarUsers() {
		for (int i = 0; i < userListLength; i++) {
			User user = userList.get(i);
			List<User> similarUsers = user.getSimilarUsers();
			System.out.print("Similar users for User with ID "
					+ user.getUserID() + ": ");
			for (User similarUser : similarUsers) {
				System.out.print(similarUser.getUserID() + ", ");
			}
			System.out.println();
		}
	}

	private void getTopNUserUser(int n) {
		if (n > similarityMatrix.length) {
			System.out
					.println("n is greater than similarity matrix's row length!!");
		}
		
		System.out.println("----TOP " + n + " Users for each User----");
		int i, j;
		int[] topIndexes = new int[n];
		int[] topValues = new int[n];
		for (i = 0; i < similarityMatrix.length; i++) {
			for (j = 0; j < n; j++) {
				topIndexes[j] = j;
				topValues[j] = similarityMatrix[i][j];
			}

			for (j = n; j < similarityMatrix[i].length; j++) {
				if (isGreatherThan(similarityMatrix[i][j], topValues)) {
					int k = getSmallestValueIndex(topValues);
					topIndexes[k] = j;
					topValues[k] = similarityMatrix[i][j];
				}
			}
			
			User user1 = userList.get(i);
			User user2;
			System.out.print("Row " + i + ": ");
			for (int a = 0; a < n; a++) {
				user2 = userList.get(topIndexes[a]);
				// The following if is needed to prevent same user similarity
				// to be included.
				// Can add more conditions here to strengthen the condition for
				// similar users
				if (topIndexes[a] != i) {
					System.out.print(topValues[a] + "(Index = " + topIndexes[a]
							+ "), ");
					user1.addSimilarUser(user2);
				}
			}
			System.out.println();
		}
		System.out.println("-----END of Top N-----");
		System.out.println();

		}

	private void calculateUserUserSimilarity(List<User> userList) {
		initialiseMatrix(userListLength, userListLength);
		int i, j;
		for (i = 0; i < userListLength; i++) {
			User user1 = userList.get(i);
			User user2;
			int dotProduct = 0;
			for (j = i + 1; j < userListLength; j++) {
				user2 = userList.get(j);
				dotProduct = dotProductUserUser(user1, user2);
				similarityMatrix[i][j] = dotProduct;
				similarityMatrix[j][i] = dotProduct;
			}
		}
	}

	private int dotProductUserUser(User user1, User user2) {
		int sum = 0;
		List<Integer> user1FeatureValueVector = user1.getNormalisedFeatureValueVector();
		List<Integer> user1FeatureIndexVector = user1.getFeatureIndexVector();
		
		List<Integer> user2FeatureValueVector = user2.getNormalisedFeatureValueVector();
		List<Integer> user2FeatureIndexVector = user2.getFeatureIndexVector();
		
		//int index = 0;
		for (int i : user1FeatureIndexVector) {
			if (user2FeatureIndexVector.contains(i)) {
				int user1Index = user1FeatureIndexVector.indexOf(i);
				int user2Index = user2FeatureIndexVector.indexOf(i); // Won't return -1 since we know it exists due to the if condition
				sum += (user1FeatureValueVector.get(user1Index) * user2FeatureValueVector.get(user2Index));
			}
			//index++;
		}
		return sum;
	}

	/*
	 * *******GENERIC CODE***********
	 */
	
	private void initialiseMatrix(int rowSize, int columnSize) {
		similarityMatrix = new int[rowSize][columnSize];
		int i, j;
		for (i = 0; i < rowSize; i++) {
			for (j = 0; j < columnSize; j++) {
				similarityMatrix[i][j] = 0;
			}
		}
	}

	private void printMatrix(int[][] similarityMatrix) {
		int i, j;
		
		System.out.println("-----SIMILARITY MATRIX-------");
		for (i = 0; i < similarityMatrix.length; i++) {
			for (j = 0; j < similarityMatrix[i].length; j++) {
				System.out.print(similarityMatrix[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println("---END SIMILARITY MATRIX-----");
		System.out.println();
	}

	private int getSmallestValueIndex(int[] topValues) {
		int smallestValue = topValues[0];
		int smallestIndex = 0;
		for (int i = 1; i < topValues.length; i++) {
			if (topValues[i] < smallestValue) {
				smallestValue = topValues[i];
				smallestIndex = i;
			}
		}
		return smallestIndex;
	}

	private boolean isGreatherThan(int number, int[] topValues) {
		for (int i = 0; i < topValues.length; i++) {
			if (number > topValues[i]) {
				return true;
			}
		}
		return false;
	}

}
