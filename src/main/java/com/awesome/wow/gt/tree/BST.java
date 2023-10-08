package com.awesome.wow.gt.tree;

import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
       if (data == null) {
           throw new IllegalArgumentException("Input data can't be null");
       }

        root = addNode(root, data);
    }

    private BSTNode<T> addNode(BSTNode<T> node, T data) {
        if (node == null) {
            size++;
            return new BSTNode<>(data);
        }
        if (data.compareTo(node.getData()) > 0) { // data > node.data
            node.setRight(addNode(node.getRight(), data));
        } else if (data.compareTo(node.getData()) < 0) { // data < node.data
            node.setLeft(addNode(node.getLeft(), data));
        }
        // data == node.data
        return node;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data can't be null");
        }

        BSTNode<T> container = new BSTNode<>(null);
        root = removeNode(root, container, data);

        return container.getData();
    }

    private BSTNode<T> removeNode(BSTNode<T> node, BSTNode<T> container, T data) {
        if (node == null) {
            throw new NoSuchElementException("the data is not in the tree");
        }

        if (data.compareTo(node.getData()) > 0) { // data > node.data
            node.setRight(removeNode(node.getRight(), container, data));
        } else if (data.compareTo(node.getData()) < 0) { // data < node.data
            node.setLeft(removeNode(node.getLeft(), container, data));
        } else {  // data == node.data
            container.setData(node.getData());
            size--;

            // no child
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }

            // one child
            if ((node.getLeft() != null && node.getRight() == null)) {
                return node.getLeft();
            }
            if (node.getLeft() == null && node.getRight() != null) {
                return node.getRight();
            }
            // two child
            BSTNode<T> container2 = new BSTNode<>(null);
            node.setRight(removeSuccessor(node.getRight(), container2));
            node.setData(container2.getData());
        }

        return node;
    }

    private BSTNode<T> removeSuccessor(BSTNode<T> node, BSTNode<T> container) {
        if (node.getLeft() == null) {
            container.setData(node.getData());
            return node.getRight();
        }

        node.setLeft(removeSuccessor(node.getLeft(), container));
        return node;
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This should be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to search for. You may assume data is never null.
     * @return true if the parameter is contained within the tree, false otherwise.
     */
    public boolean contains(T data) {
        BSTNode<T> node = compareNode(root, data);
        if (node == null) {
            return false;
        } else {
            return true;
        }
    }

    private BSTNode<T> compareNode(BSTNode<T> node, T data) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.getData()) > 0) { // data > node.data
            return compareNode(node.getRight(), data);
        } else if (data.compareTo(node.getData()) < 0) { // data < node.data
            return compareNode(node.getLeft(), data);
        }
        // data == node.data
        return node;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
