package ru.job4j.collection.mail;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class UnionOfUsersByEmailsTest {
	private User user1 = new User(
			"user1", List.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
	private User user2 = new User(
			"user2", List.of("foo@gmail.com", "ups@pisem.net"));
	private User user3 = new User(
			"user3", List.of("xyz@pisem.net", "vasya@pupkin.com"));
	private User user4 = new User(
			"user4", List.of("ups@pisem.net", "aaa@bbb.ru"));
	private User user5 = new User(
			"user5", List.of("xyz@pisem.net"));

	@Test
	public void addUser() {
		UnionOfUsersByEmails checkMail = new UnionOfUsersByEmails();
		checkMail.add(user1);
		checkMail.add(user2);
		checkMail.add(user3);
		checkMail.add(user4);
		checkMail.add(user5);
		Assert.assertEquals(2, checkMail.getUsersMap().size());
	}

	@Test
	public void addAllUsers() {
		UnionOfUsersByEmails union = new UnionOfUsersByEmails();
		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		userList.add(user4);
		userList.add(user5);
		List<User> list = union.unionList(userList);
		Assert.assertTrue(list.get(0).getEmails().contains("xxx@ya.ru"));
		Assert.assertTrue(list.get(0).getEmails().contains("foo@gmail.com"));
		Assert.assertTrue(list.get(0).getEmails().contains("lol@mail.ru"));
		Assert.assertTrue(list.get(0).getEmails().contains("ups@pisem.net"));
		Assert.assertTrue(list.get(0).getEmails().contains("aaa@bbb.ru"));
		Assert.assertTrue(list.get(1).getEmails().contains("vasya@pupkin.com"));
		Assert.assertTrue(list.get(1).getEmails().contains("xyz@pisem.net"));
	}

}