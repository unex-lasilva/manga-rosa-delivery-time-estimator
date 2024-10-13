package br.com.mangarosa.impl;

import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.InOrderTraverse;
import br.com.mangarosa.collections.Tree;

import java.util.List;
/*
Implementação de uma árvore binária de busca (BST).
A complexidade média das operações é O(log n), mas no pior caso pode ser O(n).
*/
public class BinaryTree<T extends Comparable<T>> implements Tree<T> {
    protected BinaryTreeNode<T> root; // Raiz da árvore
    protected int size; // Número de elementos na árvore

    public BinaryTree() {
        // Construtor padrão
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(T value) {
        // Adiciona um novo valor à árvore
        root = addRec(root, value);
    }

    private BinaryTreeNode<T> addRec(BinaryTreeNode<T> node, T value) {
        // Método recursivo para inserir um valor na árvore
        if (node == null) {
            size++;
            return new BinaryTreeNode<>(value);
        }

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(addRec(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(addRec(node.getRightChild(), value));
        }

        return node;
    }

    @Override
    public void remove(T value) {
        // Remove um valor da árvore
        root = removeRec(root, value);
    }

    private BinaryTreeNode<T> removeRec(BinaryTreeNode<T> node, T value) {
        // Método recursivo para remover um valor da árvore
        if (node == null) {
            return null;
        }

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(removeRec(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(removeRec(node.getRightChild(), value));
        } else {
            // Nó encontrado
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                size--;
                return null; // Nó folha
            }

            if (node.getLeftChild() == null) {
                size--;
                return node.getRightChild(); // Substitui pelo filho direito
            }

            if (node.getRightChild() == null) {
                size--;
                return node.getLeftChild(); // Substitui pelo filho esquerdo
            }

            // Nó com dois filhos: encontra o menor valor no subárvore direita
            node.setValue(minValue(node.getRightChild()));
            node.setRightChild(removeRec(node.getRightChild(), node.getValue()));
        }

        return node;
    }

    protected T minValue(BinaryTreeNode<T> node) {
        // Encontra o menor valor na subárvore
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node.getValue();
    }

    @Override
    public boolean contains(T value) {
        // Verifica se a árvore contém um determinado valor
        return containsRec(root, value);
    }

    private boolean containsRec(BinaryTreeNode<T> node, T value) {
        // Método recursivo para verificar a presença de um valor na árvore
        if (node == null) {
            return false;
        }

        if (value.compareTo(node.getValue()) == 0) {
            return true; // Valor encontrado
        }

        if (value.compareTo(node.getValue()) < 0) {
            return containsRec(node.getLeftChild(), value);
        }

        return containsRec(node.getRightChild(), value);
    }

    @Override
    public boolean isEmpty() {
        // Verifica se a árvore está vazia
        return size == 0;
    }

    @Override
    public boolean isLeaf(T value) {
        // Verifica se um nó com o valor especificado é uma folha
        BinaryTreeNode<T> node = findNode(root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }

    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> node, T value) {
        // Método recursivo para encontrar um nó com o valor especificado
        if (node == null || value.compareTo(node.getValue()) == 0) {
            return node;
        }

        if (value.compareTo(node.getValue()) < 0) {
            return findNode(node.getLeftChild(), value);
        }

        return findNode(node.getRightChild(), value);
    }

    @Override
    public BinaryTreeNode<T> root() {
        // Retorna a raiz da árvore
        return root;
    }

    @Override
    public int size() {
        // Retorna o número de elementos na árvore
        return size;
    }

    @Override
    public List<T> toList() {
        // Retorna uma lista com os valores da árvore na ordem InOrder
        InOrderTraverse<T> inOrder = new InOrderTraverse<>();
        List<T> list = inOrder.traverse(this);

        if (list.isEmpty()) {
            return null;
        }

        return list;
    }

    @Override
    public void clear() {
        // Limpa a árvore, tornando-a vazia
        root = null;
        size = 0;
    }
}
