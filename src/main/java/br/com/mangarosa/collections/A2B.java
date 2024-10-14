package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class A2B<T extends Comparable<T>> implements Tree<T> {

    BinaryTreeNode<T> root;
    public int size;

    public A2B() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (!contains(value)) {
            root = addRecursive(root, value);
            size++;
        }
    }

    BinaryTreeNode<T> addRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) {
            return new BinaryTreeNode<>(value);
        }
        if (value.compareTo(current.getValue()) < 0) {
            current.setLeftChild(addRecursive(current.getLeftChild(), value));
        } else if (value.compareTo(current.getValue()) > 0) {
            current.setRightChild(addRecursive(current.getRightChild(), value));
        }
        return current;
    }

    @Override
    public void remove(T value) {
        if (contains(value)) {
            root = removeRecursive(root, value);
            size--;
        }
    }

    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) {
            return null;
        }
        if (value.compareTo(current.getValue()) < 0) {
            current.setLeftChild(removeRecursive(current.getLeftChild(), value));
        } else if (value.compareTo(current.getValue()) > 0) {
            current.setRightChild(removeRecursive(current.getRightChild(), value));
        } else {
            // Node with only one child or no child
            if (current.getLeftChild() == null) {
                return current.getRightChild();
            } else if (current.getRightChild() == null) {
                return current.getLeftChild();
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            T smallestValue = findSmallestValue(current.getRightChild());
            current.setValue(smallestValue);
            current.setRightChild(removeRecursive(current.getRightChild(), smallestValue));
        }
        return current;
    }

    private T findSmallestValue(BinaryTreeNode<T> node) {
        return node.getLeftChild() == null ? node.getValue() : findSmallestValue(node.getLeftChild());
    }

   @Override
public boolean contains(T value) {
    return containsRecursive(root, value);
}

private boolean containsRecursive(BinaryTreeNode<T> node, T value) {
    if (node == null) {
        return false;
    }
    if (value.equals(node.getValue())) {
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

    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> current, T value) {
        if (current == null) {
            return null;
        }
        if (value.equals(current.getValue())) {
            return current;
        }
        return value.compareTo(current.getValue()) < 0
                ? findNode(current.getLeftChild(), value)
                : findNode(current.getRightChild(), value);
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
        List<T> list = new ArrayList<>();
        inOrderTraversal(root, list);
        return list.isEmpty() ? null : list; // Retorna null se a lista estiver vazia
    }

    private void inOrderTraversal(BinaryTreeNode<T> node, List<T> list) {
        if (node != null) {
            inOrderTraversal(node.getLeftChild(), list);
            list.add(node.getValue());
            inOrderTraversal(node.getRightChild(), list);
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(BinaryTreeNode<T> node) {
        if (node == null) {
            return true;
        }
        int leftHeight = height(node.getLeftChild());
        int rightHeight = height(node.getRightChild());
        return Math.abs(leftHeight - rightHeight) <= 1
                && isBalanced(node.getLeftChild())
                && isBalanced(node.getRightChild());
    }

    private int height(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }

    @Override
    public List<T> inOrder() {
        List<T> list = new ArrayList<>();
        inOrderTraversal(root, list);
        return list.isEmpty() ? null : list; 
    }

    @Override
    public List<T> preOrder() {
        List<T> list = new ArrayList<>();
        preOrderTraversal(root, list);
        return list.isEmpty() ? null : list; 
    }

    private void preOrderTraversal(BinaryTreeNode<T> node, List<T> list) {
        if (node != null) {
            list.add(node.getValue());
            preOrderTraversal(node.getLeftChild(), list);
            preOrderTraversal(node.getRightChild(), list);
        }
    }

    @Override
    public List<T> postOrder() {
        List<T> list = new ArrayList<>();
        postOrderTraversal(root, list);
        return list.isEmpty() ? null : list; // Retorna null se a lista estiver vazia
    }

    private void postOrderTraversal(BinaryTreeNode<T> node, List<T> list) {
        if (node != null) {
            postOrderTraversal(node.getLeftChild(), list);
            postOrderTraversal(node.getRightChild(), list);
            list.add(node.getValue());
        }
    }

    @Override
    public T min() {
        if (root == null) {
            return null; // Trata o caso vazio
        }
        return findSmallestValue(root);
    }

    @Override
    public T max() {
        if (root == null) {
            return null; // Trata o caso vazio
        }
        return findLargestValue(root);
    }

    private T findLargestValue(BinaryTreeNode<T> node) {
        return node.getRightChild() == null ? node.getValue() : findLargestValue(node.getRightChild());
    }

    @Override
    public int height() {
        return height(root);
    }

    @Override
    public int leafCount() {
        return countLeaves(root);
    }

    private int countLeaves(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            return 1;
        }
        return countLeaves(node.getLeftChild()) + countLeaves(node.getRightChild());
    }
}
