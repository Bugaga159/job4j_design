package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleList<E> implements Iterable {
	private int modCount = 0;
	private int index = 0;
	private Node<E> before;
	private Node<E> after;

	public void add(E value) {
		if (value != null) {
			Node<E> last = after;
			Node<E> newNode = new Node<>(last, value, null);
			after = newNode;
			if (last == null) {
				before = newNode;
			} else {
				last.next = newNode;
			}
			index++;
			modCount++;
		}
	}

	public E get(int index) {
		Objects.checkIndex(index, this.index);
		Node<E> rsl = before;
		for (int i = 0; i < index; i++) {
			rsl = rsl.next;
		}
		return rsl.item;
	}

	private static class Node<E> {
		E item;
		Node<E> next;
		Node<E> prev;

		Node(Node<E> prev, E element, Node<E> next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}

	@Override
	public Iterator iterator() {
		return new Iterator<>() {
			private int point = 0;
			private int expectedModCount = modCount;
			private Node<E> lastNode = before;

			@Override
			public boolean hasNext() {
				return point < index;
			}

			@Override
			public E next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				if (expectedModCount != modCount) {
					throw new ConcurrentModificationException();
				}
				if (point != 0) {
					lastNode = lastNode.next;
				}
				point++;
				return lastNode.item;
			}
		};
	}
}
