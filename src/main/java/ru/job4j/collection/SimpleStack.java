package ru.job4j.collection;

public class SimpleStack<T> {
	/**
	 * Связанный список для хранения
	 */
	private ForwardLinked<T> linked = new ForwardLinked<T>();

	/**
	 * Метод возвращает значение и удаляет его из коллекции.
	 * @return значение типа T
	 */
	public T pop() {
		return linked.deleteLast();
	}

	/**
	 * Метод добавляет значение в коллекцию
	 * @param value типа Т
	 */
	public void push(T value) {
		linked.add(value);
	}
}
