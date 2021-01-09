package ru.job4j.collection;

/**
 * Класс очереди
 * реализованный через два стека
 * @param <T>
 */
public class SimpleQueue<T> {
	/**
	 * Коллекция стек для сохранения
	 */
	private final SimpleStack<T> in = new SimpleStack<>();
	/**
	 * Коллекция стек для вывода значения
	 */
	private final SimpleStack<T> out = new SimpleStack<>();

	/**
	 * Метод возвращать первое значение и
	 * удалять его из коллекции "out"
	 * @return
	 */
	public T poll() {
		if (out.isEmpty()) {
			while (!in.isEmpty()) {
				out.push(in.pop());
			}
		}
		return out.pop();
	}

	/**
	 * Метод помещает значение в конец
	 * @param value
	 */
	public void push(T value) {
		in.push(value);
	}
}
