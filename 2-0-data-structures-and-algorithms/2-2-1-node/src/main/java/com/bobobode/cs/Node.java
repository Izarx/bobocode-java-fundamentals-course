package com.bobobode.cs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class {@link Node} is a very simple data structure that consists of an element itself and the reference to the next
 * node. An element can have any value since it's a generic. A reference to the next node allows to link {@link Node}
 * objects and build more comprehensive data structures on top of those liked nodes.
 *
 * @param <T> a generic type T
 * @author Taras Boychuk
 */

@Getter
@Setter
@NoArgsConstructor
public class Node<T> {
    // todo:
	private T element;
	private Node<T> nextNode;

	public Node(T element) {
		this.element = element;
	}
}
