package com.bobocode.cs;

import java.util.Arrays;
import java.util.Objects;

import com.bobocode.cs.exception.EmptyStackException;
import com.bobocode.util.ExerciseNotCompletedException;

/**
 * {@link LinkedStack} is a stack implementation that is based on singly linked generic nodes.
 * A node is implemented as inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedStack<T> implements Stack<T> {

    private static class Node<T> {
        T value;
        Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private int size;

    /**
     * This method creates a stack of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new stack of elements that were passed as method parameters
     */
    public static <T> LinkedStack<T> of(T... elements) {
        LinkedStack<T> linkedStack = new LinkedStack<>();
        Arrays.stream(elements).forEach(linkedStack::push);
        return linkedStack;
    }

    /**
     * The method pushes an element onto the top of this stack. This has exactly the same effect as:
     * addElement(item)
     *
     * @param element elements to add
     */
    @Override
    public void push(T element) {
        Node<T> current = new Node<>(Objects.requireNonNull(element));
        if (head != null) {
            current.next = head;
        }
        head = current;
        size++;
    }

    /**
     * This method removes the object at the top of this stack
     * and returns that object as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException - if this stack is empty
     */
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T retrievedValue = head.value;
        head = head.next;
        size--;
        return retrievedValue;
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if a stack is empty
     *
     * @return {@code true} if a stack is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

}
