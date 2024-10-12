package br.com.mangarosa.impl;

import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.Tree;
import java.util.ArrayList;
import java.util.List;

/**
 * Árvore binária de busca com operações básicas de inserção, remoção e busca.
 */
public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private BinaryTreeNode<T> root;
    private int size;

    /**
     * Construtor inicializa a árvore vazia.
     */
    public BinaryTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Adiciona um valor, mantendo a estrutura de busca binária.
     */
    @Override
    public void add(T value) {
        root = addRecursive(root, value);
        size++;
    }

    /**
     * Insere um valor recursivamente.
     */
    private BinaryTreeNode<T> addRecursive(BinaryTreeNode<T> current, T value) {
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

    /**
     * Remove um valor, se presente.
     */
    public void remove(T value) {
        if (contains(value)) {
            root = removeRecursive(root, value);
            size--;
        }
    }

    /**
     * Remove recursivamente um valor.
     */
    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) {
            return null;
        }

        if (value.equals(current.getValue())) {
            if (current.getLeftChild() == null && current.getRightChild() == null) {
                return null;
            }
            if (current.getRightChild() == null) {
                return current.getLeftChild();
            }
            if (current.getLeftChild() == null) {
                return current.getRightChild();
            }

            T smallestValue = findSmallestValue(current.getRightChild());
            current.setValue(smallestValue);
            current.setRightChild(removeRecursive(current.getRightChild(), smallestValue));
            return current;
        }

        if (value.compareTo(current.getValue()) < 0) {
            current.setLeftChild(removeRecursive(current.getLeftChild(), value));
        } else {
            current.setRightChild(removeRecursive(current.getRightChild(), value));
        }

        return current;
    }

    /**
     * Encontra o menor valor da subárvore.
     */
    private T findSmallestValue(BinaryTreeNode<T> root) {
        return root.getLeftChild() == null ? root.getValue() : findSmallestValue(root.getLeftChild());
    }

    protected BinaryTreeNode<T> getRoot() {
        return root;
    }

    protected void setRoot(BinaryTreeNode<T> root) {
        this.root = root;
    }

    /**
     * Verifica se um valor está na árvore.
     */
    @Override
    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    /**
     * Busca recursivamente um valor.
     */
    private boolean containsRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) {
            return false;
        }
        if (value.equals(current.getValue())) {
            return true;
        }

        return value.compareTo(current.getValue()) < 0
                ? containsRecursive(current.getLeftChild(), value)
                : containsRecursive(current.getRightChild(), value);
    }

    /**
     * Retorna true se a árvore está vazia.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Verifica se o valor é uma folha.
     */
    @Override
    public boolean isLeaf(T value) {
        BinaryTreeNode<T> node = findNode(root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }

    /**
     * Encontra um nó pelo valor.
     */
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

    /**
     * Retorna a raiz da árvore.
     */
    @Override
    public BinaryTreeNode<T> root() {
        return root;
    }

    /**
     * Retorna o tamanho da árvore.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Converte a árvore para uma lista (in-order).
     */
    @Override
    public List<T> toList() {
        if (root == null) {
            return null;
        }

        List<T> list = new ArrayList<>();
        toListRecursive(root, list);
        return list;
    }

    /**
     * Percorre a árvore e preenche uma lista (in-order).
     */
    private void toListRecursive(BinaryTreeNode<T> current, List<T> list) {
        if (current != null) {
            toListRecursive(current.getLeftChild(), list);
            list.add(current.getValue());
            toListRecursive(current.getRightChild(), list);
        }
    }

    /**
     * Remove todos os elementos da árvore.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }
}
