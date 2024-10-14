package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class Avorebinaria<T extends Comparable<T>> implements Tree<T> {
    protected BinaryTreeNode<T> root;  // Nó raiz da árvore
    protected int size;  // Contador de nós na árvore

    public Avorebinaria() {
        this.root = null;
        this.size = 0;
    }
    // Adiciona um valor à árvore
    @Override
    public void add(T value) {
        root = addRecursively(root, value);
        size++;
    }
    // Insere um valor na posição correta
    private BinaryTreeNode<T> addRecursively(BinaryTreeNode<T> current, T value) {
        if (current == null) {
            return new BinaryTreeNode<>(value);
        }
        if (value.compareTo(current.getValue()) < 0) {
            current.setLeftChild(addRecursively(current.getLeftChild(), value));
        } else if (value.compareTo(current.getValue()) > 0) {
            current.setRightChild(addRecursively(current.getRightChild(), value));
        }
        return current;
    }
    // Remove um valor da árvore
    @Override
    public void remove(T value) {
        this.root = removeRecursively(root, value);
        size--;
    }

    private BinaryTreeNode<T> removeRecursively(BinaryTreeNode<T> current, T value) {
        if (current == null) return null;

        if (value.compareTo(current.getValue()) < 0) {
            current.setLeftChild(removeRecursively(current.getLeftChild(), value));
        } else if (value.compareTo(current.getValue()) > 0) {
            current.setRightChild(removeRecursively(current.getRightChild(), value));
        } else {
            // Caso onde o nó foi encontrado
            if (current.getLeftChild() == null) return current.getRightChild();
            if (current.getRightChild() == null) return current.getLeftChild();

            T smallestValue = findSmallestValue(current.getRightChild());
            current.setValue(smallestValue);
            current.setRightChild(removeRecursively(current.getRightChild(), smallestValue));
        }
        return current;
    }
     // Encontra o menor valor em uma subárvore
    private T findSmallestValue(BinaryTreeNode<T> root) {
        return root.getLeftChild() == null ? root.getValue() : findSmallestValue(root.getLeftChild());
    }
    // Verifica se um valor está presente na árvore
    @Override
    public boolean contains(T value) {
        return containsRecursively(root, value);
    }
    // Busca recursivamente por um valor na árvore
    private boolean containsRecursively(BinaryTreeNode<T> current, T value) {
        if (current == null) return false;
        if (value.equals(current.getValue())) return true;
        return value.compareTo(current.getValue()) < 0
                ? containsRecursively(current.getLeftChild(), value)
                : containsRecursively(current.getRightChild(), value);
    }
    // Verifica se a árvore está vazia
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    // Verifica se um nó é folha
    @Override
    public boolean isLeaf(T value) {
        BinaryTreeNode<T> node = findNode(root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }
    // Encontra e retorna um nó específico
    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> current, T value) {
        if (current == null || value.equals(current.getValue())) return current;
        return value.compareTo(current.getValue()) < 0
                ? findNode(current.getLeftChild(), value)
                : findNode(current.getRightChild(), value);
    }
    // Retorna a raiz da árvore
    @Override
    public BinaryTreeNode<T> root() {
        return root;
    }
    // Retorna o número de nós
    @Override
    public int size() {
        return size;
    }
     // Converte a árvore em uma lista ordenada
    @Override
    public List<T> toList() {
        if(this.root == null)
            return null;
        else {
            List<T> elements = new ArrayList<>();
            inOrder(this.root, elements);
            return elements;
        }
    }
     // Deixa em Ordem
    private void inOrder(BinaryTreeNode<T> node, List<T> elements) {
        if (node != null) {
            inOrder(node.getLeftChild(), elements);
            elements.add(node.getValue());
            inOrder(node.getRightChild(), elements);
        }
    }

    
    // Limpa a árvore
    @Override
    public void clear() {
        root = null;
        size = 0;
    }
}
