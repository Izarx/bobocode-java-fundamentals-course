package com.bobocode.cs;


import java.util.Arrays;
import java.util.NoSuchElementException;

import com.bobocode.util.ExerciseNotCompletedException;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {

    static class Node<T> {
        private T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }

        public static <T> Node<T> valueOf(T value) {
            return new Node<>(value);
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> createdList = new LinkedList<>();
        Arrays.stream(elements).forEach(createdList::add);
        return createdList;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        Node<T> addedNode = new Node<>(element);
        if (isEmpty()) {
            head = addedNode;
            tail = addedNode;
        } else {
            tail.next = addedNode;
            tail = addedNode;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        Node<T> currentNode = Node.valueOf(element);
        Node<T> nodeBeforeIndex = null;
        if (index == 0) {
            currentNode.next = head;
            head = currentNode;
        } else {
            nodeBeforeIndex = getNodeByIndex(index - 1);
            currentNode.next = nodeBeforeIndex.next;
            nodeBeforeIndex.next = currentNode;
        }
        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        getNodeByIndex(index).value = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        return getNodeByIndex(index).value;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        checkElementsExist();
        return getNodeByIndex(0).value;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        checkElementsExist();
        return getNodeByIndex(size-1).value;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        if (isEmpty()) {
           throw new IndexOutOfBoundsException();
        }
        Node<T> nodeBeforeIndex = null;
        T deletedElement = null;
        if (index == 0) {
            deletedElement = head.value;
            head = head.next;
        } else {
            nodeBeforeIndex = getNodeByIndex(index - 1);
            deletedElement = nodeBeforeIndex.next.value;
            if (index == size - 1) {
                nodeBeforeIndex.next = null;
                tail = nodeBeforeIndex;
            } else {
                nodeBeforeIndex.next = nodeBeforeIndex.next.next;
            }
        }
        size--;
        return deletedElement;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        if (isEmpty()) {
            return false;
        }
        Node<T> currentNode = head;
        boolean isContains = head.value.equals(element);
        for (int i = 1; i < size - 1; i++) {
            currentNode = currentNode.next;
            isContains = currentNode.value.equals(element);
        }
        return isContains;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkElementsExist() {
        if (head  == null) {
            throw new NoSuchElementException();
        }
    }

    private Node<T> getNodeByIndex (int index) {
        indexCheck(index);
        Node<T> currentNode = head;
        for (int i = 1; i <= index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }
}
