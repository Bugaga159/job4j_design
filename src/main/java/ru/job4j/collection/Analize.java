package ru.job4j.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Analize {
	public Info diff(List<User> previous, List<User> current) {
		Info info = new Info();
		Map<Integer, String> map = new HashMap<>();
		for (User curr: current) {
			map.put(curr.id, curr.name);
		}

		for (User prev: previous) {
			String user = map.get(prev.id);
			if (user == null) {
				info.deleted++;
			}
			if (user != null && !user.equals(prev.name)) {
				info.changed++;
			}
		}
		info.added = current.size() - previous.size() + info.deleted;
		return info;

	}

	public static class User {
		int id;
		String name;

		public User(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			User user = (User) o;
			return id == user.id
					&& Objects.equals(name, user.name);
		}

		@Override
		public int hashCode() {
			return Objects.hash(id, name);
		}
	}

	public static class Info {
		int added;
		int changed;
		int deleted;

	}
}
