package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizeTest {

	@Test
	public void nothingAddedToList() {
		Analize analize = new Analize();
		Analize.Info info = analize.diff(new ArrayList<Analize.User>(), new ArrayList<Analize.User>());
		assertThat(info.added, is(0));
		assertThat(info.deleted, is(0));
		assertThat(info.changed, is(0));
	}

	@Test
	public void addedOneInList() {
		Analize analize = new Analize();
		List<Analize.User> users = List.of(new Analize.User(2, "Pupkin"));
		Analize.Info info = analize.diff(new ArrayList<Analize.User>(), users);
		assertThat(info.added, is(1));
		assertThat(info.deleted, is(0));
		assertThat(info.changed, is(0));
	}

	@Test
	public void changedOneInList() {
		Analize analize = new Analize();
		List<Analize.User> users1 = List.of(new Analize.User(2, "Pupkin"));
		List<Analize.User> users2 = List.of(new Analize.User(2, "Igor"));
		Analize.Info info = analize.diff(users1, users2);
		assertThat(info.added, is(0));
		assertThat(info.deleted, is(0));
		assertThat(info.changed, is(1));
	}

	@Test
	public void deletedOneInList() {
		Analize analize = new Analize();
		List<Analize.User> users = List.of(new Analize.User(2, "Pupkin"));
		Analize.Info info = analize.diff(users, new ArrayList<Analize.User>());
		assertThat(info.added, is(0));
		assertThat(info.deleted, is(1));
		assertThat(info.changed, is(0));
	}

	@Test
	public void usedAllMethods() {
		Analize analize = new Analize();
		List<Analize.User> users1 = List.of(
				new Analize.User(2, "Pupkin"),
				new Analize.User(6, "Igor"),
				new Analize.User(1, "Ivan")
		);
		List<Analize.User> users2 = List.of(
				new Analize.User(2, "Pupkin"),
				new Analize.User(1, "Ivan 2.0"),
				new Analize.User(1, "New Igor 3.0")
		);
		Analize.Info info = analize.diff(users1, users2);
		assertThat(info.added, is(1));
		assertThat(info.deleted, is(1));
		assertThat(info.changed, is(1));
	}
}