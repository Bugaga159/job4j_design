package ru.job4j.collection.mail;

import java.util.*;

public class UnionOfUsersByEmails {
	/**
	 * key = email / value = user
	 */
	private final Map<String, String> mailsMap = new HashMap<>();
	/**
	 * key = user / value = Set<email>
	 */
	private final Map<String, Set<String>> usersMap = new HashMap<>();

	public void add(User user) {
		String userName = user.getName();
		List<String> mailList = user.getEmails();
		Set<String> mailSet = new HashSet<>();
		for (String email: mailList) {
			String name = mailsMap.put(email, userName);
			if (name != null) {
				userName = name;
			}
			mailSet.add(email);
		}
		if (usersMap.get(userName) == null) {
			usersMap.put(userName, mailSet);
		} else {
			usersMap.get(userName).addAll(mailSet);
		}
	}

	public List<User> unionList(List<User> list) {
		List<User> res = new ArrayList<>();
		UnionOfUsersByEmails checkMail = new UnionOfUsersByEmails();
		for (User user : list) {
			checkMail.add(user);
		}
		for (String s : checkMail.getUsersMap().keySet()) {
			res.add(new User(s, new ArrayList<>(checkMail.getUsersMap().get(s))));
		}
		return res;
	}

	public Map<String, Set<String>> getUsersMap() {
		return usersMap;
	}
}
