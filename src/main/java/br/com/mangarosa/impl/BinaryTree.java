package br.com.mangarosa.impl;

import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação de uma árvore binária de busca. Esta classe suporta operações como
 * adicionar, remover, verificar a existência de um valor, e converter a árvore para uma lista.
 */
public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private BinaryTreeNode<T> root;
    private int size;

    /**
     * Construtor da árvore binária. Inicializa a árvore com uma raiz nula e tamanho zero.
     */
    public BinaryTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Adiciona um valor à árvore, mantendo a propriedade de busca binária.
     */
    @Override
    public void add(T value) {
        root = addRecursive(root, value);
        size++;
    }

    /**
     * Método auxiliar para adicionar um valor recursivamente na árvore.
     * Insere o valor no local apropriado, seguindo a regra de árvore binária de busca.
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
     * Remove um valor da árvore, caso ele exista.
     */
    public void remove(T value) {
        if (contains(value)) {
            root = removeRecursive(root(), value);
            size--;
        }
    }

    /**
     * Método auxiliar para remover um valor recursivamente da árvore.
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
     * Encontra o menor valor de uma subárvore.
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
     * Verifica se a árvore contém um determinado valor.
     */
    @Override
    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    /**
     * Método auxiliar para verificar recursivamente se um valor está presente na árvore.
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
     * Verifica se a árvore está vazia.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Verifica se o valor especificado é uma folha (nó sem filhos).
     */
    @Override
    public boolean isLeaf(T value) {
        BinaryTreeNode<T> node = findNode(root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }

    /**
     * Encontra um nó com base em seu valor.
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
     * Retorna o nó raiz da árvore.
     */
    @Override
    public BinaryTreeNode<T> root() {
        return root;
    }

    /**
     * Retorna o tamanho da árvore (número de elementos).
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Converte a árvore em uma lista, realizando uma travessia in-order.
     */
    @Override
    public List<T> toList() {
        if (root() == null) {
            return null;
        }

        List<T> list = new ArrayList<>();
        toListRecursive(root(), list);
        return list;
    }

    /**
     * Método auxiliar para percorrer recursivamente a árvore e preencher uma lista com os valores in-order.
     */
    private void toListRecursive(BinaryTreeNode<T> current, List<T> list) {
        if (current != null) {
            toListRecursive(current.getLeftChild(), list);
            list.add(current.getValue());
            toListRecursive(current.getRightChild(), list);
        }
    }

    /**
     * Remove todos os elementos da árvore, deixando-a vazia.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }
}
