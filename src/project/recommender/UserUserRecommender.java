package project.recommender;

import java.util.ArrayList;
import java.util.List;

import project.user.User;

public class UserUserRecommender implements RecommenderInterface{
	
	private AlgorithmInterface algorithm;

	public UserUserRecommender(AlgorithmInterface algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public void run() {
		List<User> userList = getUserList();
		algorithm.runUserUser(userList);
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
		user3.addFeatureIndex(10);

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
		user4.addFeatureIndex(8);

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
}
