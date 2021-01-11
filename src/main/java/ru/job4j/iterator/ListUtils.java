package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {
	/**
	 * Метод вставляет значение до индекса
	 * @param list
	 * @param index
	 * @param value
	 * @param <T>
	 */
	public static <T> void addBefore(List<T> list, int index, T value) {
		Objects.checkIndex(index, list.size());
		ListIterator<T> i = list.listIterator();
		while (i.hasNext()) {
			if (i.nextIndex() == index) {
				i.add(value);
				break;
			}
			i.next();
		}
	}

	/**
	 * Метод вставляет значение после индекса
	 * @param list
	 * @param index
	 * @param value
	 * @param <T>
	 */
	public static <T> void addAfter(List<T> list, int index, T value) {
		Objects.checkIndex(index, list.size());
		ListIterator<T> iterator = list.listIterator();
		while (iterator.hasNext()) {
			if (iterator.nextIndex() == index) {
				iterator.next();
				iterator.add(value);
				break;
			}
			iterator.next();
		}
	}

	/**
	 * Метод удаляет все элементы, которые удовлетворяют предикату.
	 * @param list
	 * @param filter
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> removeIf(List<T> list, Predicate<T> filter) {
		ListIterator<T> iterator = list.listIterator();
		while (iterator.hasNext()) {
			if (filter.test(iterator.next())) {
				iterator.remove();
			}
		}
		return list;
	}

	/**
	 * Метод заменяет все элементы, которые удовлетворяют предикату
	 * @param list
	 * @param filter
	 * @param value
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> replaceIf(List<T> list, Predicate<T> filter, T value) {
		ListIterator<T> iterator = list.listIterator();
		while (iterator.hasNext()) {
			if (filter.test(iterator.next())) {
				iterator.set(value);
			}
		}
		return list;
	}

	/**
	 * Метод удаляет из списка те элементы, которые есть в elements
	 * @param list
	 * @param elements
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> removeAll(List<T> list, List<T> elements) {
		ListIterator<T> iterator;
		for (T del : elements) {
			iterator = list.listIterator();
			while (iterator.hasNext()) {
				if (iterator.next().equals(del)) {
					iterator.remove();
				}
			}
		}
		return list;
	}
}
