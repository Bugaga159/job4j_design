package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EventIt implements Iterator<Integer> {
	private final int[] numbers;
	private int point = 0;

	public EventIt(int[] numbers) {
		this.numbers = numbers;
	}

	@Override
	public boolean hasNext() {
		while (point < numbers.length) {
			if (numbers[point] % 2 == 0) {
				return true;
			}
			point++;
		}
		return false;
	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		return numbers[point++];
	}
}
