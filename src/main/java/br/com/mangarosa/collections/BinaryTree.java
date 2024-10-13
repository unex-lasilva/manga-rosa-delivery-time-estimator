package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private BinaryTreeNode<T> root;
    private int size;

    public BinaryTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(T value) {
        this.root = addRecursive(root, value);
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
        this.root = removeRecursive(root, value);
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
            // Node to delete found
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                return null;
            } else if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }
            T smallestValue = findSmallestValue(node.getRightChild());
            node.setValue(smallestValue);
            node.setRightChild(removeRecursive(node.getRightChild(), smallestValue));
        }
        return node;
    }

    private T findSmallestValue(BinaryTreeNode<T> root) {
        return root.getLeftChild() == null ? root.getValue() : findSmallestValue(root.getLeftChild());
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
        return root == null;
    }

    @Override
    public boolean isLeaf(T value) {
        BinaryTreeNode<T> node = findNode(root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }

    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> node, T value) {
        if (node == null || value.compareTo(node.getValue()) == 0) {
            return node;
        }
        return value.compareTo(node.getValue()) < 0
                ? findNode(node.getLeftChild(), value)
                : findNode(node.getRightChild(), value);
    }

    @Override
    public BinaryTreeNode<T> root() {
        return root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;  // Método para obter a raiz
    }

    public void setRoot(BinaryTreeNode<T> root) {
        this.root = root;  // Método para definir a raiz
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> toList() {
        if (root == null) {
            return null;  // Retorna null se a árvore estiver vazia
        }
        List<T> elements = new ArrayList<>();
        inOrderTraversal(root, elements);  // Método para fazer a travessia em ordem
        return elements;
    }

    @Override
    public T[] toArray() {
        List<T> elements = new ArrayList<>();
        inOrderTraversal(root, elements);

        @SuppressWarnings("unchecked")
        T[] array = (T[]) java.lang.reflect.Array.newInstance(
                root().getValue().getClass(), elements.size());

        return elements.toArray(array);
    }

    private void inOrderTraversal(BinaryTreeNode<T> node, List<T> elements) {
        if (node != null) {
            inOrderTraversal(node.getLeftChild(), elements);
            elements.add(node.getValue());
            inOrderTraversal(node.getRightChild(), elements);
        }
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }
}
