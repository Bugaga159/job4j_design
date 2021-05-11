package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static java.lang.StrictMath.round;

public class SimpleHashMap<K, V> implements Iterable {

	class Node<K, V> {
		private K key;
		private V value;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Node<?, ?> node = (Node<?, ?>) o;
			return Objects.equals(key, node.key)
					&& Objects.equals(value, node.value);
		}

		@Override
		public int hashCode() {
			return Objects.hash( key, value);
		}
	}

	private final static int DEFAULT_CAPACITY = 16;
	private Node<K, V>[] table = new Node[DEFAULT_CAPACITY];
	private int size;
	private final double loadFactor = 0.5;
	private double threshold = table.length * loadFactor;
	private int modCount = 0;

	public boolean insert(K key, V value) {
		boolean result = true;
		int hash = this.hash(key);
		int index = this.indexFor(hash, table.length);
		Node<K, V> node = table[index];
		if (node != null && Objects.equals(node.key, key)) {
			node.value = value;
			if (++size > threshold) {
				resize((int) round(1.5 * table.length));
			}
			return true;
		}
		if (table[index] == null) {
			table[index] = new Node(key, value);
			if (++size > threshold) {
				resize((int) round(1.5 * table.length));
			}
		} else {
			result = false;
		}
		modCount++;
		return result;
	}

	private int hash(Object key) {
		int h = key.hashCode();
		return (key == null) ? 0 : (h ^ (h >>> 16));
	}

	public int getSizeTable() {
		return table.length;
	}

	private int indexFor(int h, int length) {
		return h & (length - 1);
	}

	private void resize(int newCapacity) {
		Node<K, V>[] newTable = new Node[newCapacity];
		for (int k = 0; k < table.length; k++) {
			Node<K, V> node = table[k];
			if (node != null) {
				int hash = this.hash(node.key);
				int index = indexFor(hash, newTable.length);
				newTable[index] = node;
				table[k] = null;
			}
		}
		table = newTable;
		threshold = table.length * loadFactor;
	}

	public V get(K key) {
		int hash = this.hash(key);
		int index = this.indexFor(hash, table.length);
		Node<K, V> node = table[index];
		if (node == null || !Objects.equals(node.key, key)) {
			return null;
		} else {
			return node.value;
		}
	}

	public boolean delete(K key) {
		int hash = this.hash(key);
		int index = this.indexFor(hash, table.length);
		Node<K, V> node = table[index];
		if (node == null || !Objects.equals(node.key, key)) {
			return false;
		} else {
			table[index] = null;
			size--;
			modCount++;
			return true;
		}
	}

	@Override
	public Iterator<V> iterator() {
		return new Iterator<>() {
			int expectedModCount = modCount;
			private int point = 0;

			@Override
			public boolean hasNext() {
				while (table[point] == null && point < table.length - 1) {
					point++;
				}
				return table[point] != null;
			}

			@Override
			public V next() {
				if (expectedModCount != modCount) {
					throw new ConcurrentModificationException();
				}
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				Node<K, V> node = (Node) table[point];
				V elem = node.getValue();
				point++;
				return elem;
			}
		};
	}
}
