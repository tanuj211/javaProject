package project.recommender;

import java.util.List;

import project.event.Event;
import project.user.User;

public class PearsonCorrelation implements AlgorithmInterface{
	
	private static final int VECTOR_LENGTH = 10;

	private double[][] similarityMatrix;

	private List<User> userList;
	private int userListLength;
	
	public PearsonCorrelation() {}

	@Override
	public void runEventEvent(List<Event> eventList) {
		System.out.println("You cannot run Event Event Recommendation with Pearson Correlation Coefficient.");
	}

	@Override
	public void runUserEvent(List<User> userList, List<Event> eventList) {
		System.out.println("You cannot run User Event Recommendation with Pearson Correlation Coefficient.");		
	}

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
		double[] topValues = new double[n];
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

	private void calculateUserUserSimilarity(List<User> userList2) {
		initialiseMatrix(userListLength, userListLength);
		int i, j;
		for (i = 0; i < userListLength; i++) {
			User user1 = userList.get(i);
			User user2;
			double PearsonCoefficient = 0;
			for (j = i + 1; j < userListLength; j++) {
				user2 = userList.get(j);
				PearsonCoefficient = calculatePearsonCoefficient(user1, user2);
				similarityMatrix[i][j] = PearsonCoefficient;
				similarityMatrix[j][i] = PearsonCoefficient;
			}
		}
	}

	private double calculatePearsonCoefficient(User user1, User user2) {
		List<Integer> user1FeatureValueVector = user1.getFeatureValueVector();
		List<Integer> user1FeatureIndexVector = user1.getFeatureIndexVector();
		double user1AverageValue = getAverageValue(user1FeatureValueVector);
		//double user1ValueVectorMagnitude = getAvgReducedMagnitude(user1FeatureValueVector, user1AverageValue);
		
		List<Integer> user2FeatureValueVector = user2.getFeatureValueVector();
		List<Integer> user2FeatureIndexVector = user2.getFeatureIndexVector();
		double user2AverageValue = getAverageValue(user2FeatureValueVector);
		//double user2ValueVectorMagnitude = getAvgReducedMagnitude(user2FeatureValueVector, user2AverageValue);
		
		double pearsonCoefficient;
		double numerator = 0;
		double user1Denominator = 0;
		double user2Denominator = 0;;
		
		for(int i = 1; i <= VECTOR_LENGTH; i++) {
			double user1Value;
			double user2Value;
			if (user1FeatureIndexVector.contains(i)) {
				int user1Index = user1FeatureIndexVector.indexOf(i);
				user1Value = (user1FeatureValueVector.get(user1Index) - user1AverageValue);
			} else {
				user1Value = (0 - user1AverageValue);
			}
			
			if (user2FeatureIndexVector.contains(i)) {
				int user2Index = user2FeatureIndexVector.indexOf(i);
				user2Value = (user2FeatureValueVector.get(user2Index) - user2AverageValue);
			} else {
				user2Value = (0 - user2AverageValue);
			}
			
			numerator += user1Value * user2Value;
			user1Denominator += Math.pow(user1Value, 2);
			user2Denominator += Math.pow(user2Value, 2);
		}
		pearsonCoefficient = numerator / (Math.sqrt(user1Denominator) * Math.sqrt(user2Denominator));
		
		return pearsonCoefficient;
	}

//	private double getAvgReducedMagnitude(List<Integer> FeatureValueVector, double AverageValue) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	private double getAverageValue (List<Integer> user1FeatureValueVector) {
		double sum = 0;
		for (int i : user1FeatureValueVector) {
			sum += i;
		}
		return sum/VECTOR_LENGTH;
	}

	private void initialiseMatrix(int rowSize, int columnSize) {
		similarityMatrix = new double[rowSize][columnSize];
		int i, j;
		for (i = 0; i < rowSize; i++) {
			for (j = 0; j < columnSize; j++) {
				similarityMatrix[i][j] = 0;
			}
		}
	}

	private void printMatrix(double[][] similarityMatrix) {
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

	private int getSmallestValueIndex(double[] topValues) {
		double smallestValue = topValues[0];
		int smallestIndex = 0;
		for (int i = 1; i < topValues.length; i++) {
			if (topValues[i] < smallestValue) {
				smallestValue = topValues[i];
				smallestIndex = i;
			}
		}
		return smallestIndex;
	}

	private boolean isGreatherThan(double similarityMatrix, double[] topValues) {
		for (int i = 0; i < topValues.length; i++) {
			if (similarityMatrix > topValues[i]) {
				return true;
			}
		}
		return false;
	}

}
