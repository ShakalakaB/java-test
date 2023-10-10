package com.awesome.wow.gt.tree;

/**
 * Your implementation of a MaxHeap.
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MaxHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MaxHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add. You may assume that data will always be valid.
     */
    public void add(T data) {
        if (backingArray.length == size + 1) {
            resize();
        }

        int index = size + 1;
        backingArray[index] = data;
        size++;

        while (index > 1) {
            int parentIndex = index / 2;

            if (data.compareTo(backingArray[parentIndex]) < 0) {
                break;
            }

            if (data.compareTo(backingArray[parentIndex]) > 0) {
                swap(parentIndex, index);
                index = parentIndex;
            }
        }
    }

    private void resize() {
        T[] newBackingArray =(T[]) new Comparable[backingArray.length * 2];
        for (int i = 1; i <= size; i++) {
            newBackingArray[i] = backingArray[i];
        }
        backingArray = newBackingArray;
    }


    /**
     * Removes and returns the max item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * You may assume that the heap is not empty.
     *
     * @return The data that was removed.
     */
    public T remove() {
        T removed = backingArray[1];

        T last = backingArray[size];
        backingArray[1] = last;
        backingArray[size] = null;
        size--;

        compare(1);

        return removed;
    }

    private void compare(int index) {
        int left = index * 2;
        int right = index * 2 + 1;

        if (left > size) {
            return;
        }

        if (left <= size && right > size) {
            if (backingArray[index].compareTo(backingArray[left]) < 0) {
                swap(index, left);
                compare(left);
            }
        }

        if (right <= size) {
            int biggerIndex = (backingArray[left].compareTo(backingArray[right]) > 0) ? left : right;
            if (backingArray[index].compareTo(backingArray[biggerIndex]) < 0) {
                swap(index, biggerIndex);
                compare(biggerIndex);
            }
        }
    }

    private void swap(int parentIndex, int childIndex) {
        T temp = backingArray[childIndex];
        backingArray[childIndex] = backingArray[parentIndex];
        backingArray[parentIndex] = temp;
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
}
