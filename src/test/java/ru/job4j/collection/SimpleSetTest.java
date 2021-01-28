package ru.job4j.collection;

import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

	@Test
	public void whenAddInSet() {
		SimpleSet<String> list = new SimpleSet<>();
		list.add("first");
		list.add("second");
		list.add("second");
		list.add("third");
		Iterator<String> iterator = list.iterator();
		assertThat(iterator.next(), is("first"));
		assertThat(iterator.next(), is("second"));
		assertThat(iterator.next(), is("third"));
		assertThat(iterator.hasNext(), is(false));
	}

}