package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {
    protected BinaryTreeNode<T> root;  // Alterado para protected
    private int size = 0;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTreeNode<T> root() {
        return this.root;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isLeaf(T value) {
        BinaryTreeNode<T> node = findNode(value, root);
        if (node != null) {
            return node.getLeftChild() == null && node.getRightChild() == null;
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    public void add(T value) {
        root = insert(root, value);
    }

    public void remove(T value) {
        root = delete(root, value);
    }

    public boolean contains(T value) {
        return containsRec(root, value);
    }

    public List<T> toList() {
        List<T> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result.isEmpty() ? null : result;
    }

    protected BinaryTreeNode<T> insert(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            size++;
            return new BinaryTreeNode<>(value);
        }
        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(insert(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(insert(node.getRightChild(), value));
        }
        return node;
    }

    protected BinaryTreeNode<T> delete(BinaryTreeNode<T> node, T value) {
        if (node == null) return null;

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(delete(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(delete(node.getRightChild(), value));
        } else {
            if (node.getLeftChild() == null || node.getRightChild() == null) {
                BinaryTreeNode<T> temp = node.getLeftChild() != null ? node.getLeftChild() : node.getRightChild();
                size--;
                return temp;
            } else {
                BinaryTreeNode<T> successor = findMin(node.getRightChild());
                node.setValue(successor.getValue());
                node.setRightChild(delete(node.getRightChild(), successor.getValue()));
            }
        }
        return node;
    }

    protected BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
        while (node.getLeftChild() != null) node = node.getLeftChild();
        return node;
    }

    protected boolean containsRec(BinaryTreeNode<T> node, T value) {
        if (node == null) return false;
        if (value.equals(node.getValue())) return true;
        if (value.compareTo(node.getValue()) < 0) return containsRec(node.getLeftChild(), value);
        return containsRec(node.getRightChild(), value);
    }

    protected void inOrderTraversal(BinaryTreeNode<T> node, List<T> list) {
        if (node == null) return;
        inOrderTraversal(node.getLeftChild(), list);
        list.add(node.getValue());
        inOrderTraversal(node.getRightChild(), list);
    }

    protected BinaryTreeNode<T> findNode(T value, BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }
        if (value.equals(node.getValue())) {
            return node;
        }
        if (value.compareTo(node.getValue()) < 0) {
            return findNode(value, node.getLeftChild());
        } else {
            return findNode(value, node.getRightChild());
        }
    }
}
