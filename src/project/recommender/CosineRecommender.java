package project.recommender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import project.event.Event;

public class CosineRecommender {
	
	static int [] [] similarityMatrix;
	static int eventListLength;

	public static void main(String [] args) {
		Event event1 = new Event(1);
		Event event2 = new Event(2);
		Event event3 = new Event(3);
		
		event1.addFeature(1);
		event1.addFeature(6);
		event1.addFeature(7);
		event1.addFeature(9);
		event1.addFeature(10);
		
		event2.addFeature(1);
		event2.addFeature(5);
		event2.addFeature(7);
		event2.addFeature(8);
		event2.addFeature(9);
		
		event3.addFeature(4);
		event3.addFeature(5);
		event3.addFeature(6);
		event3.addFeature(2);
		event3.addFeature(10);
		
		List<Event> eventList = new ArrayList<Event>();
		eventList.add(event1);
		eventList.add(event2);
		eventList.add(event3);
		
		eventListLength = eventList.size();
		
		calculateSimilarity(eventList);

		//initialiseMatrix();
		printMatrix(similarityMatrix);
		
//		List<Integer> feature1 = new ArrayList<Integer>();
//		feature1.add(1);
//		feature1.add(3);
//		feature1.add(5);
//		feature1.add(6);
//		feature1.add(10);
//		
//		List<Integer> feature2 = new ArrayList<Integer>();
//		feature2.add(1);
//		feature2.add(3);
//		feature2.add(5);
//		feature2.add(6);
//		feature2.add(10);
		
//		Collections.sort(feature1);
//		Collections.sort(feature2);
		
//		int dotProduct = dotProduct(event1, event2);
//		System.out.println("The dot product is: " + dotProduct);
			
	}


	private static int dotProduct(Event event1, Event event2) {
		int sum = 0;
		List<Integer> featureVector1 = event1.getFeatureVector();
		List<Integer> featureVector2 = event2.getFeatureVector();
		
		for (int i : featureVector1) {
			if (featureVector2.contains(i)) {
				sum += 1;
			}
		}
		return sum;
	}
	
	private static void initialiseMatrix() {
		similarityMatrix = new int [eventListLength] [eventListLength];
		int i,j;
		for (i = 0; i < eventListLength; i++) {
			for (j = 0; j < eventListLength; j++) {
				similarityMatrix [i][j] = 0;
			}
		}
	}
	
	private static void calculateSimilarity(List<Event> eventList) {
		initialiseMatrix();
		int i,j;
		for (i = 0; i < eventListLength; i++) {
			Event event1 = eventList.get(i);
			Event event2;
			int dotProduct = 0;
			for (j = i+1; j < eventListLength; j++) {
				event2 = eventList.get(j);
				dotProduct = dotProduct(event1, event2);
				similarityMatrix[i][j] = dotProduct;
				similarityMatrix[j][i] = dotProduct;
			}
		}
	}
	
	private static void printMatrix(int[][] similarityMatrix) {
		int i,j;
		for(i = 0; i < similarityMatrix.length; i++) {
			for(j = 0; j < similarityMatrix[i].length; j++) {
				System.out.print(similarityMatrix[i] [j] + ", ");
			}
			System.out.println();
		}
	}
	
	

}
