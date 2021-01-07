package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleListTest {
	private SimpleList<Integer> list = new SimpleList<>();

	@Test
	public void whenAddAndGet() {
		list.add(12);
		list.add(244);
		list.add(3);
		assertThat(list.get(2), is(3));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void whenGetOutOfBounds() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.get(3);
	}

	@Test(expected = NoSuchElementException.class)
	public void whenGetEmptyFromIt() {
		list.iterator().next();
	}

	@Test(expected = ConcurrentModificationException.class)
	public void whenCorruptedIt() {
		list.add(43);
		Iterator<Integer> it = list.iterator();
		list.add(6);
		it.next();
	}
}