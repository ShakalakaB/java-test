package com.awesome.wow.gt.list;

import java.util.NoSuchElementException;

/**
 * Your implementation of a Singly-Linked List.
 */
public class SinglyLinkedList<T> {
    /*
     * Do not add new instance variables or modify existing ones.
     */
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the front of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: input data can't be null");
        }

        SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(data);
        node.setNext(head);
        head = node;
        if (size == 0) {
            tail = node;
        }
        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: input data can't be null");
        }

        SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(data);

        if (size == 0) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
        size++;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }

        SinglyLinkedListNode<T> removed = head;
        if (size == 1) {
            head = null;
            tail = null;
            size--;
            return removed.getData();
        }

        head = head.getNext();
        size--;

        return removed.getData();
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }

        SinglyLinkedListNode<T> removed = tail;
        if (size == 1) {
            head = null;
            tail = null;
            size--;
            return removed.getData();
        }

        SinglyLinkedListNode<T> current = head;
        while (current.getNext() != tail) {
            current = current.getNext();
        }

        tail = current;
        current.setNext(null);
        size--;

        return removed.getData();
    }

    /**
     * Adds the element to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * ASSUMPTIONS:
     * - You may assume that the index will always be valid [0, size]
     * - You may assume that the data will not be null
     *
     * @param index the index to add the new element
     * @param data  the data to add
     */
    public void addAtIndex(int index, T data) {
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);

        if (size == 0) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }

        if (index == size) {
            tail.setNext(newNode);
            tail = newNode;
            size++;
            return;
        }

        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
            size++;
            return;
        }

        if (index < size) {
            SinglyLinkedListNode<T> current  = head;
            for (int i = 1; i < index; i++) {
                current = current.getNext();
            }

            SinglyLinkedListNode<T> next = current.getNext();
            current.setNext(newNode);
            newNode.setNext(next);
            size++;
        }
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
