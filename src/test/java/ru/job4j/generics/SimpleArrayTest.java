package ru.job4j.generics;

import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

	@Test
	public void whenAddInArray() {
		SimpleArray simpleArray = new SimpleArray(2);
		for (int i = 1; i < 3; i++) {
			simpleArray.add(i);
		}
		Iterator<Integer> iterator = simpleArray.iterator();
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(1));
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(2));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void whenAddInArrayException() {
		SimpleArray simpleArray = new SimpleArray(2);
		for (int i = 1; i < 4; i++) {
			simpleArray.add(i);
		}
	}

	@Test
	public void whenSetInArray() {
		SimpleArray simpleArray = new SimpleArray(2);
		for (int i = 1; i < 3; i++) {
			simpleArray.add(i);
		}
		simpleArray.set(1, 3);
		Iterator<Integer> iterator = simpleArray.iterator();
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(1));
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(3));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void whenSetInArrayException() {
		SimpleArray simpleArray = new SimpleArray(2);
		for (int i = 1; i < 3; i++) {
			simpleArray.add(i);
		}
		simpleArray.set(2, 3);
	}

	@Test
	public void whenRemoveInArray() {
		SimpleArray simpleArray = new SimpleArray(2);
		for (int i = 1; i < 3; i++) {
			simpleArray.add(i);
		}
		simpleArray.remove(1);
		Iterator<Integer> iterator = simpleArray.iterator();
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(1));
		assertThat(iterator.hasNext(), is(false));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void whenRemoveInArrayException() {
		SimpleArray simpleArray = new SimpleArray(2);
		for (int i = 1; i < 3; i++) {
			simpleArray.add(i);
		}
		simpleArray.remove(2);
	}

	@Test
	public void whenGetInArray() {
		SimpleArray simpleArray = new SimpleArray(2);
		for (int i = 1; i < 3; i++) {
			simpleArray.add(i);
		}
		assertThat(simpleArray.get(0), is(1));
		assertThat(simpleArray.get(1), is(2));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void whenGetInArrayException() {
		SimpleArray simpleArray = new SimpleArray(2);
		for (int i = 1; i < 3; i++) {
			simpleArray.add(i);
		}
		simpleArray.get(2);
	}
}