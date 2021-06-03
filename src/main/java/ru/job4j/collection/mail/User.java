package ru.job4j.collection.mail;

import java.util.List;

public class User {
	public String name;
	private List<String> emails;

	public User(String name, List<String> emails) {
		this.name = name;
		this.emails = emails;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
}
