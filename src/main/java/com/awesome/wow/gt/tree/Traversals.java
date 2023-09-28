package com.awesome.wow.gt.tree;

import java.util.List;
import java.util.ArrayList;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(TreeNode<T> root) {
        List<T> list = new ArrayList<>();
        preorderTraverse(root, list);
        return list;
    }

    private void preorderTraverse(TreeNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        list.add(node.getData());
        preorderTraverse(node.getLeft(), list);
        preorderTraverse(node.getRight(), list);
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(TreeNode<T> root) {
        List<T> list = new ArrayList<>();
        inorderTraverse(root, list);
        return list;
    }

    private void inorderTraverse(TreeNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }

        inorderTraverse(node.getLeft(), list);
        list.add(node.getData());
        inorderTraverse(node.getRight(), list);
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(TreeNode<T> root) {
        List<T> list = new ArrayList<>();
        postorderTraverse(root, list);
        return list;
    }

    private void postorderTraverse(TreeNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }

        postorderTraverse(node.getLeft(), list);
        postorderTraverse(node.getRight(), list);
        list.add(node.getData());
    }
}
