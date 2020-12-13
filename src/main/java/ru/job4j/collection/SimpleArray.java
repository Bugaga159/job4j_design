package ru.job4j.collection;

import java.util.*;

import static java.lang.StrictMath.round;

public class SimpleArray<T> implements Iterable<T> {
	private Object[] container;
	private int size = 1;
	private int modCount = 0;
	private int index = 0;

	public SimpleArray() {
		container = new Object[size];
	}

	public T get(int index) {
		Objects.checkIndex(index, this.index);
		return (T) container[index];
	}

	public void add(T model) {
		modCount++;
		if (index >= size) {
			size = (int) round(size * 1.5);
			container = Arrays.copyOf(container, size);
		}
		container[index++] = model;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int point = 0;
			int expectedModCount = modCount;
			@Override
			public boolean hasNext() {
				return point < index;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				if (expectedModCount != modCount) {
					throw new ConcurrentModificationException();
				}
				return (T) container[point++];
			}
		};
	}
}