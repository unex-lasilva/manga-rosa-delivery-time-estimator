package br.com.mangarosa.collections;

import br.com.mangarosa.collections.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

public class BalancedTree<T extends Comparable<T>> extends BinaryTree<T> {

    private int size = 0;  // Adicionando controle de tamanho

    @Override
    public void add(T value) {
        setRoot(addRecursive(getRoot(), value));
        size++;  // Incrementar o tamanho ao adicionar um elemento
    }

    @Override
    public void remove(T value) {
        if (contains(value)) {
            setRoot(removeRecursive(getRoot(), value));
            size--;  // Decrementar o tamanho ao remover um elemento
        }
    }

    // Sobrescreve o método size() para retornar o valor correto
    @Override
    public int size() {
        return size;
    }

    // Método de inserção com balanceamento
    private BinaryTreeNode<T> addRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) {
            return new BinaryTreeNode<>(value);
        }

        if (value.compareTo(current.getValue()) < 0) {
            current.setLeftChild(addRecursive(current.getLeftChild(), value));
        } else if (value.compareTo(current.getValue()) > 0) {
            current.setRightChild(addRecursive(current.getRightChild(), value));
        }

        return balanceTree(current);  // Aplicar balanceamento após cada inserção
    }

    // Método de remoção com balanceamento
    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) {
            return null;
        }

        if (value.equals(current.getValue())) {
            // Casos de remoção (sem filhos, com um filho ou com dois filhos)
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

        return balanceTree(current);  // Aplicar balanceamento após cada remoção
    }


    // Método para encontrar o menor valor (usado na remoção)
    private T findSmallestValue(BinaryTreeNode<T> root) {
        return root.getLeftChild() == null ? root.getValue() : findSmallestValue(root.getLeftChild());
    }

    // Método para balancear a árvore
    private BinaryTreeNode<T> balanceTree(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }

        int balanceFactor = height(node.getLeftChild()) - height(node.getRightChild());

        // Rotação à direita
        if (balanceFactor > 1) {
            if (height(node.getLeftChild().getLeftChild()) >= height(node.getLeftChild().getRightChild())) {
                node = rotateRight(node);
            } else {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
                node = rotateRight(node);
            }
        }

        // Rotação à esquerda
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

    // Método para calcular a altura da árvore
    private int height(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1;
    }

    // Rotação à direita
    private BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> y) {
        BinaryTreeNode<T> x = y.getLeftChild();
        y.setLeftChild(x.getRightChild());
        x.setRightChild(y);
        return x;
    }

    // Rotação à esquerda
    private BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> y) {
        BinaryTreeNode<T> x = y.getRightChild();
        y.setRightChild(x.getLeftChild());
        x.setLeftChild(y);
        return x;
    }

    public List<T> toList() {
        if (root() == null) {
            return null;  // Retorna null se a árvore estiver vazia
        }

        List<T> list = new ArrayList<>();
        toListRecursive(root(), list);
        return list;
    }

    private void toListRecursive(BinaryTreeNode<T> current, List<T> list) {
        if (current != null) {
            toListRecursive(current.getLeftChild(), list);
            list.add(current.getValue());
            toListRecursive(current.getRightChild(), list);
        }
    }
}
