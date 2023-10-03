package com.awesome.wow.gt.tree;

import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 3;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data can't be null");
        }
        if (backingArray.length == size + 1) {
            resize();
        }

        int index = size + 1;
        backingArray[index] = data;
        size++;

        while (index > 1) {
            int parentIndex = index / 2;

            if (data.compareTo(backingArray[parentIndex]) > 0) {
                break;
            }

            if (data.compareTo(backingArray[parentIndex]) < 0) {
                swap(parentIndex, index);
                index = parentIndex;
            }
        }
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("The heap is empty");
        }

        T removed = backingArray[1];

        T last = backingArray[size];
        backingArray[1] = last;
        backingArray[size] = null;
        size--;

        int index = 1;
        while (index <= size) {
            int leftIndex = 2 * index;
            int rightIndex = 2 * index + 1;
            if (leftIndex > size) {
                break;
            }

            if (leftIndex <= size && rightIndex > size) {
                if (backingArray[leftIndex].compareTo(backingArray[index]) >= 0) {
                    break;
                } else {
                    swap(index, leftIndex);
                    index = leftIndex;
                }
            }

            if (rightIndex <= size) {
                int smallerIndex = leftIndex;
                if (backingArray[leftIndex].compareTo(backingArray[rightIndex]) > 0) {
                    smallerIndex = rightIndex;
                }

                if (backingArray[smallerIndex].compareTo(backingArray[index]) < 0) {
                    swap(index, smallerIndex);
                    index = smallerIndex;
                } else {
                    break;
                }
            }
        }
        return removed;
    }

    private void swap(int index, int smallerIndex) {
        T temp = backingArray[index];
        backingArray[index] = backingArray[smallerIndex];
        backingArray[smallerIndex] = temp;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    private void resize() {
        T[] newBackingArray =(T[]) new Comparable[backingArray.length * 2];
//        T[] newBackingArray =(T[]) new Object[backingArray.length * 2];
        for (int i = 1; i <= size; i++) {
            newBackingArray[i] = backingArray[i];
        }
        backingArray = newBackingArray;
    }
}
