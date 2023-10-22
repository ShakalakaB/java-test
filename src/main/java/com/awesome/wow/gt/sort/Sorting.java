package com.awesome.wow.gt.sort;

import java.util.Comparator;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int stopIndex = arr.length - 1;
        while (stopIndex > 0) {
            int lastSwapIndex = 0;
            for (int i = 0; i < stopIndex; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    lastSwapIndex = i;
                }
            }
            stopIndex = lastSwapIndex;
        }

    }

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int i = arr.length - 1; i > 0; i--) {
            int maxIndex = 0;
            for (int j = 1; j <= i; j++) {
                if (comparator.compare(arr[j], arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            swap(arr, i, maxIndex);
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            int j  = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                swap(arr, j - 1, j);
                j--;
            }
        }
    }

    private static <T> void swap(T[] arr, int index1, int index2) {
        T temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
