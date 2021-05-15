package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

class SimpleTree<E> implements Tree<E> {
	private final Node<E> root;

	public SimpleTree(final E root) {
		this.root = new Node<>(root);
	}

	@Override
	public boolean add(E parent, E child) {
		boolean rsl = false;
		Optional<Node<E>> nodeParent = findBy(parent);
		if (nodeParent.isPresent()) {
			Optional<Node<E>> childParent = findBy(child);
			if (!childParent.isPresent()) {
				nodeParent.get().getChildren().add(new Node<>(child));
				rsl = true;
			}
		}
		return rsl;
	}

	@Override
	public Optional<Node<E>> findBy(E value) {
		Predicate<Node<E>> predicate = e -> e.getValue().equals(value);
		return findByPredicate(predicate);
	}

	public boolean isBinary() {
		Predicate<Node<E>> predicate = e -> e.getChildren().size() > 2;
		return findByPredicate(predicate).isEmpty();
	}

	private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
		Optional<Node<E>> rsl = Optional.empty();
		Queue<Node<E>> data = new LinkedList<>();
		data.offer(this.root);
		while (!data.isEmpty()) {
			Node<E> el = data.poll();
			if (condition.test(el)) {
				rsl = Optional.of(el);
				break;
			}
			data.addAll(el.getChildren());
		}
		return rsl;
	}
}