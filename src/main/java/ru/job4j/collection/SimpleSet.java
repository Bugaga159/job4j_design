package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
	private SimpleArray<E> list = new SimpleArray<>();

	public void add(E model) {
		if (!contains(model)) {
			list.add(model);
		}
	}

	public boolean contains(E model) {
		for (E e : list) {
			if (e.equals(model)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}
}
