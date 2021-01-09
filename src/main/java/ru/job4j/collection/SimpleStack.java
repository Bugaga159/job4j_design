package ru.job4j.collection;

public class SimpleStack<T> {
	/**
	 * Связанный список для хранения
	 */
	private ForwardLinked<T> linked = new ForwardLinked<T>();
	private int size = 0;

	/**
	 * Метод возвращает значение и удаляет его из коллекции.
	 * @return значение типа T
	 */
	public T pop() {
		size--;
		return linked.deleteLast();
	}

	/**
	 * Метод добавляет значение в коллекцию
	 * @param value типа Т
	 */
	public void push(T value) {
		linked.add(value);
		size++;
	}

	public boolean isEmpty() {
		return size == 0;
	}
}
