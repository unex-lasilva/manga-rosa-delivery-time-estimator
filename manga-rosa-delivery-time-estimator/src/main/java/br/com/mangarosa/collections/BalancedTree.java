package br.com.mangarosa.collections;

import br.com.mangarosa.impl.BinaryTree;
/*
Classe que implementa uma árvore binária de busca balanceada (árvore AVL).
* As rotações garantem que a complexidade assintótica para inserções, remoções
e buscas seja O(log n), mesmo no pior caso.
*/
public class BalancedTree<T extends Comparable<T>> extends BinaryTree<T> {

    @Override
    public void add(T value) {
        // Método público para inserir um valor na árvore.
        root = addRec(root, value);
    }

    private BinaryTreeNode<T> addRec(BinaryTreeNode<T> node, T value) {
        // Método recursivo privado para adicionar valores à árvore.
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
        // Método público para remover um valor da árvore.
        root = removeRec(root, value);
    }

    private BinaryTreeNode<T> removeRec(BinaryTreeNode<T> node, T value) {
        // Método recursivo privado para remover valores da árvore.
        if (node == null) {
            return null;
        }

        int cmpValue = value.compareTo(node.getValue());

        if (cmpValue < 0) {
            node.setLeftChild(removeRec(node.getLeftChild(), value));
        } else if (cmpValue > 0) {
            node.setRightChild(removeRec(node.getRightChild(), value));
        } else {
            // Caso em que encontramos o nó a ser removido.
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                size--;
                return null;
            }

            if (node.getLeftChild() == null) {
                size--;
                return node.getRightChild();
            }

            if (node.getRightChild() == null) {
                size--;
                return node.getLeftChild();
            }

            // Substitui o nó pelo menor valor da subárvore à direita.
            node.setValue(minValue(node.getRightChild()));
            node.setRightChild(removeRec(node.getRightChild(), node.getValue()));
        }

        return balance(node);
    }

    private BinaryTreeNode<T> balance(BinaryTreeNode<T> node) {
        // Método para balancear a árvore após inserções ou remoções.
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
        // Retorna o fator de balanceamento de um nó.
        if (node == null) {
            return 0;
        }
        return getHeight(node.getLeftChild()) - getHeight(node.getRightChild());
    }

    private int getHeight(BinaryTreeNode<T> node) {
        // Calcula a altura de um nó recursivamente.
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.getLeftChild());
        int rightHeight = getHeight(node.getRightChild());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    private BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> unbNode) {
        // Rotação à direita para balancear a árvore.
        BinaryTreeNode<T> left = unbNode.getLeftChild();
        BinaryTreeNode<T> leftRight = left.getRightChild();

        left.setRightChild(unbNode);
        unbNode.setLeftChild(leftRight);

        return left;
    }

    private BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> unbNode) {
        // Rotação à esquerda para balancear a árvore.
        BinaryTreeNode<T> right = unbNode.getRightChild();
        BinaryTreeNode<T> rightLeft = right.getLeftChild();

        right.setLeftChild(unbNode);
        unbNode.setRightChild(rightLeft);

        return right;
    }
}
