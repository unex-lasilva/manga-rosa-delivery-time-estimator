package br.com.mangarosa.collections;

import br.com.mangarosa.impl.BinaryTree;

/*
Esta classe implementa uma árvore binária de busca balanceada (árvore AVL). Devido às suas rotações, conseguimos garantir
uma complexidade assintótica O(log n) nas operações de inserção, remoção e busca de nós, mesmo no pior cenário, pois
as rotações ajudam a prevenir que o pior caso se concretize.
*/

public class BalancedTree<T extends Comparable<T>> extends BinaryTree<T> {

    @Override
    public void add(T value) {
        // Método público para adicionar elementos na árvore
        root = addRec(root, value);
    }

    private BinaryTreeNode<T> addRec(BinaryTreeNode<T> node, T value) {
        // Método privado e recursivo para inserção, que é oculto para o usuário
        if (node == null) {
            size++;
            return new BinaryTreeNode<>(value);
        }

        int cmpValue = value.compareTo(node.getValue());

        if (cmpValue < 0) {
            node.setLeftChild(addRec(node.getLeftChild(), value));
        } else if (cmpValue > 0) {
            node.setRightChild(addRec(node.getRightChild(), value));
        }

        return balance(node);
    }

    @Override
    public void remove(T value) {
        // Método público para remover elementos da árvore
        root = removeRec(root, value);
    }

    private BinaryTreeNode<T> removeRec(BinaryTreeNode<T> node, T value) {
        // Método privado recursivo de remoção de nós, inacessível ao usuário
        if (node == null) {
            return null;
        }

        int cmpValue = value.compareTo(node.getValue());

        if (cmpValue < 0) {
            node.setLeftChild(removeRec(node.getLeftChild(), value));
        } else if (cmpValue > 0) {
            node.setRightChild(removeRec(node.getRightChild(), value));
        } else {
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                size--;
                return null;
            } else if (node.getLeftChild() == null) {
                size--;
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                size--;
                return node.getLeftChild();
            }

            BinaryTreeNode<T> minNode = findMin(node.getRightChild());
            node.setValue(minNode.getValue());
            node.setRightChild(removeRec(node.getRightChild(), minNode.getValue()));
        }

        return balance(node);
    }

    private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
        // Encontra o menor valor (mais à esquerda) na subárvore
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }

    private BinaryTreeNode<T> balance(BinaryTreeNode<T> node) {
        // Método que realiza o balanceamento da árvore após inserção ou remoção
        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (getBalanceFactor(node.getLeftChild()) < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            }
            return rotateRight(node);
        }

        if (balanceFactor < -1) {
            if (getBalanceFactor(node.getRightChild()) > 0) {
                node.setRightChild(rotateRight(node.getRightChild()));
            }
            return rotateLeft(node);
        }

        return node;
    }

    private int getBalanceFactor(BinaryTreeNode<T> node) {
        // Calcula o fator de balanceamento do nó
        if (node == null) {
            return 0;
        }

        return getHeight(node.getLeftChild()) - getHeight(node.getRightChild());
    }

    private int getHeight(BinaryTreeNode<T> node) {
        // Método recursivo para calcular a altura de um nó
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight(node.getLeftChild());
        int rightHeight = getHeight(node.getRightChild());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    private BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> unbNode) {
        // Realiza a rotação à direita para balancear a árvore
        BinaryTreeNode<T> left = unbNode.getLeftChild();
        BinaryTreeNode<T> leftRight = left.getRightChild();

        left.setRightChild(unbNode);
        unbNode.setLeftChild(leftRight);

        return left;
    }

    private BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> unbNode) {
        // Realiza a rotação à esquerda para balancear a árvore
        BinaryTreeNode<T> right = unbNode.getRightChild();
        BinaryTreeNode<T> rightLeft = right.getLeftChild();

        right.setLeftChild(unbNode);
        unbNode.setRightChild(rightLeft);

        return right;
    }
}
