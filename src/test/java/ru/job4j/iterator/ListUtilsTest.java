package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

	@Test
	public void whenAddBefore() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
		ListUtils.addBefore(input, 1, 2);
		assertThat(Arrays.asList(1, 2, 3), Is.is(input));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void whenAddBeforeWithInvalidIndex() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
		ListUtils.addBefore(input, 3, 2);
	}

	@Test
	public void whenAddAfter() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
		ListUtils.addAfter(input, 1, 2);
		assertThat(input, is(Arrays.asList(1, 3, 2)));
	}

	@Test
	public void whenRemoveIf() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4, 7));
		ListUtils.removeIf(input, e -> e > 3 && e < 7);
		assertThat(input, is(Arrays.asList(1, 3, 7)));
	}

	@Test
	public void whenReplaceIf() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4, 9));
		ListUtils.replaceIf(input, e -> e % 3 == 0, 11);
		assertThat(input, is(Arrays.asList(1, 11, 4, 11)));
	}

	@Test
	public void whenRemoveAll() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4, 9));
		List<Integer> rem = new ArrayList<>(Arrays.asList(1, 9));
		ListUtils.removeAll(input, rem);
		assertThat(input, is(Arrays.asList(3, 4)));
	}
}