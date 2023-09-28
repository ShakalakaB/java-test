package com.awesome.wow.gt.list;

public class SinglyLinkedList2<T> {
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
