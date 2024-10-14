package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class A2B<T extends Comparable<T>> implements Tree<T> {

    BinaryTreeNode<T> root;
    public int size = 0;

    @Override
    public void add(T value) {
        root = addRecursive(root, value);
        size++;
    }

    private BinaryTreeNode<T> addRecursive(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            return new BinaryTreeNode<>(value);
        }

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(addRecursive(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(addRecursive(node.getRightChild(), value));
        }
        return node;
    }

    @Override
    public void remove(T value) {
        root = removeRecursive(root, value);
        size--;
    }

    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            return null;
        }

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(removeRecursive(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(removeRecursive(node.getRightChild(), value));
        } else {
            if (node.getLeftChild() == null)
                return node.getRightChild();
            else if (node.getRightChild() == null)
                return node.getLeftChild();

            node.setValue(minValue(node.getRightChild()));
            node.setRightChild(removeRecursive(node.getRightChild(), node.getValue()));
        }

        return node;
    }

    private T minValue(BinaryTreeNode<T> node) {
        T minv = node.getValue();
        while (node.getLeftChild() != null) {
            minv = node.getLeftChild().getValue();
            node = node.getLeftChild();
        }
        return minv;
    }

    @Override
    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            return false;
        }

        if (value.compareTo(node.getValue()) == 0) {
            return true;
        }
        return value.compareTo(node.getValue()) < 0
                ? containsRecursive(node.getLeftChild(), value)
                : containsRecursive(node.getRightChild(), value);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isLeaf(T value) {
        BinaryTreeNode<T> node = findNode(root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }

    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> node, T value) {
        if (node == null || value == null) {
            return null;
        }

        if (value.compareTo(node.getValue()) == 0) {
            return node;
        }

        if (value.compareTo(node.getValue()) < 0) {
            return findNode(node.getLeftChild(), value);
        } else {
            return findNode(node.getRightChild(), value);
        }
    }

    @Override
    public BinaryTreeNode<T> root() {
        return root;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> toList() {
        List<T> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    private void inOrderTraversal(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            inOrderTraversal(node.getLeftChild(), result);
            result.add(node.getValue());
            inOrderTraversal(node.getRightChild(), result);
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }
}
