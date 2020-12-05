package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MemStoreTest {

	@Test
	public void whenFindById() {
		Store store = new UserStore();
		User user = new User("2");
		store.add(new User("1"));
		store.add(user);
		store.add(new User("3"));
		assertThat(store.findById("2"), is(user));
	}

	@Test
	public void whenFindByIdFail() {
		Store store = new UserStore();
		store.add(new User("1"));
		store.add(new User("3"));
		assertNull(store.findById("4"));
	}

	@Test
	public void whenReplace() {
		Store store = new UserStore();
		User user = new User("2");
		store.add(new User("1"));
		store.add(new User("3"));
		assertThat(store.replace("3", user), is(true));
		assertThat(store.findById("2"), is(user));
	}

	@Test
	public void whenReplaceFail() {
		Store store = new UserStore();
		User user = new User("2");
		store.add(new User("1"));
		store.add(new User("3"));
		assertThat(store.replace("4", user), is(false));
	}

	@Test
	public void whenDelete() {
		Store store = new UserStore();
		store.add(new User("1"));
		store.add(new User("3"));
		assertThat(store.delete("3"), is(true));
		assertNull(store.findById("3"));
	}

	@Test
	public void whenDeleteFail() {
		Store store = new UserStore();
		store.add(new User("1"));
		store.add(new User("3"));
		assertThat(store.delete("2"), is(false));
	}
}