package br.com.mangarosa.collections;

import br.com.mangarosa.impl.BinaryTree;
import java.util.ArrayList;
import java.util.List;

/**
 * Árvore binária balanceada com rotações para manter o equilíbrio.
 */
public class BalancedTree<T extends Comparable<T>> extends BinaryTree<T> {

    private int size = 0;

    /**
     * Adiciona um valor e mantém o balanceamento.
     */
    @Override
    public void add(T value) {
        setRoot(addRecursive(getRoot(), value));
        size++;
    }

    /**
     * Remove um valor e mantém o balanceamento.
     */
    @Override
    public void remove(T value) {
        if (contains(value)) {
            setRoot(removeRecursive(getRoot(), value));
            size--;
        }
    }

    /**
     * Retorna o número de elementos.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Insere um valor e aplica balanceamento.
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

        return balanceTree(current);
    }

    /**
     * Remove um valor e aplica balanceamento.
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
        } else if (value.compareTo(current.getValue()) < 0) {
            current.setLeftChild(removeRecursive(current.getLeftChild(), value));
        } else {
            current.setRightChild(removeRecursive(current.getRightChild(), value));
        }

        return balanceTree(current);
    }

    /**
     * Encontra o menor valor.
     */
    private T findSmallestValue(BinaryTreeNode<T> root) {
        return root.getLeftChild() == null ? root.getValue() : findSmallestValue(root.getLeftChild());
    }

    /**
     * Aplica rotações para balanceamento.
     */
    private BinaryTreeNode<T> balanceTree(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }

        int balanceFactor = height(node.getLeftChild()) - height(node.getRightChild());

        if (balanceFactor > 1) {
            if (height(node.getLeftChild().getLeftChild()) >= height(node.getLeftChild().getRightChild())) {
                node = rotateRight(node);
            } else {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
                node = rotateRight(node);
            }
        }

        if (balanceFactor < -1) {
            if (height(node.getRightChild().getRightChild()) >= height(node.getRightChild().getLeftChild())) {
                node = rotateLeft(node);
            } else {
                node.setRightChild(rotateRight(node.getRightChild()));
                node = rotateLeft(node);
            }
        }

        return node;
    }

    /**
     * Calcula a altura de um nó.
     */
    private int height(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1;
    }

    /**
     * Rotação simples à direita.
     */
    private BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> y) {
        BinaryTreeNode<T> x = y.getLeftChild();
        y.setLeftChild(x.getRightChild());
        x.setRightChild(y);
        return x;
    }

    /**
     * Rotação simples à esquerda.
     */
    private BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> y) {
        BinaryTreeNode<T> x = y.getRightChild();
        y.setRightChild(x.getLeftChild());
        x.setLeftChild(y);
        return x;
    }

    /**
     * Converte a árvore para uma lista (in-order).
     */
    public List<T> toList() {
        if (root() == null) {
            return null;
        }

        List<T> list = new ArrayList<>();
        toListRecursive(root(), list);
        return list;
    }

    /**
     * Preenche a lista (in-order) recursivamente.
     */
    private void toListRecursive(BinaryTreeNode<T> current, List<T> list) {
        if (current != null) {
            toListRecursive(current.getLeftChild(), list);
            list.add(current.getValue());
            toListRecursive(current.getRightChild(), list);
        }
    }
}
