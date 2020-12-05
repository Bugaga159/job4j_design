package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
	private Object[] objectsTypeT;
	private int index = 0;

	public SimpleArray(int size) {
		this.objectsTypeT = new Object[size];
	}

	public void add(T model) throws IndexOutOfBoundsException {
		objectsTypeT[index++] = model;
	}

	public void set(int index, T model) throws IndexOutOfBoundsException {
		Objects.checkIndex(index, this.index);
		objectsTypeT[index] = model;
	}

	public void remove(int index) throws IndexOutOfBoundsException {
		Objects.checkIndex(index, this.index);
		objectsTypeT[index] = null;
		this.index--;
		System.arraycopy(objectsTypeT, index + 1, objectsTypeT, index, this.index - index);
	}

	public T get(int index) throws IndexOutOfBoundsException {
		Objects.checkIndex(index, this.index);
		return (T) objectsTypeT[index];
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private int point = 0;

			@Override
			public boolean hasNext() {
				return point < index;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				return (T) objectsTypeT[point++];
			}
		};
	}
}
