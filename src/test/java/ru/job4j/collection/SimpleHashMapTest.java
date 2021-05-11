package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
	@Test
	public void shouldAddNodes() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		map.insert(1, "Masha");
		map.insert(2, "Igor");
		assertThat(map.get(1), is("Masha"));
		assertThat(map.get(2), is("Igor"));
	}

	@Test
	public void shouldUpdateNode() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		map.insert(1, "Masha");
		map.insert(2, "Igor");
		assertThat(map.insert(2, "Igor2"), is(true));
		assertThat(map.get(1), is("Masha"));
		assertThat(map.get(2), is("Igor2"));
	}

	@Test
	public void whenGetEmpty() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		assertNull(map.get(1));
	}

	@Test
	public void shouldDeleteNode() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		map.insert(1, "Masha");
		map.insert(2, "Igor");
		map.delete(1);
		assertThat(map.get(2), is("Igor"));
		assertNull(map.get(1));
	}

	@Test
	public void shouldGetNothing() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		map.insert(1, "Masha");
		map.insert(2, "Igor");
		map.delete(1);
		map.delete(2);
		assertNull(map.get(1));
		assertNull(map.get(2));
	}

	@Test(expected = NoSuchElementException.class)
	public void whenGetEmptyFromIt() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		map.iterator().next();
	}

	@Test(expected = ConcurrentModificationException.class)
	public void whenCorruptedItAddNewNode() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		map.insert(1, "Igor");
		Iterator<String> iterator = map.iterator();
		map.insert(2, "Sasha");
		iterator.next();
	}

	@Test(expected = ConcurrentModificationException.class)
	public void whenCorruptedItDeleteNode() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		map.insert(1, "Igor");
		map.insert(2, "Masha");
		Iterator<String> iterator = map.iterator();
		map.delete(2);
		iterator.next();
	}

	@Test
	public void shouldGetIterator() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		map.insert(1, "Igor");
		map.insert(2, "Masha");
		Iterator<String> iterator = map.iterator();
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is("Igor"));
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is("Masha"));
		assertThat(iterator.hasNext(), is(false));
	}

	@Test
	public void shouldBeSize36() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		for (int i = 0; i < 17; i++) {
			map.insert(i, "Test-" + i);
		}
		map.insert(17, "Igor");
		assertThat(map.getSizeTable(), is(24));
	}

	@Test
	public void shouldBeFalseKey() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		map.insert(1, "Igor1");
		assertThat(map.insert(17, "Igor2"), is(false));
	}
}