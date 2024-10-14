package br.com.mangarosa.collections;

public class AvoreBalanceada<T extends Comparable<T>> extends Avorebinaria<T> {

    @Override
    public void add(T value) {
        root = addRecursively(root, value);
    }

    private BinaryTreeNode<T> addRecursively(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            size++;  // Incrementa size apenas se um nó for realmente adicionado
            return new BinaryTreeNode<>(value);
        }
        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(addRecursively(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(addRecursively(node.getRightChild(), value));
        }
        return balance(node);  // Balanceia após adição
    }

    @Override
    public void remove(T value) {
        if (contains(value)) {  // Verifica se o valor existe antes de remover
            root = removeRecursively(root, value);
            size--;  // Decrementa size apenas se o nó foi removido
        }
    }

    private BinaryTreeNode<T> removeRecursively(BinaryTreeNode<T> node, T value) {
        if (node == null) return null;

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(removeRecursively(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(removeRecursively(node.getRightChild(), value));
        } else {
            // Caso em que o nó é encontrado
            if (node.getLeftChild() == null) return node.getRightChild();
            if (node.getRightChild() == null) return node.getLeftChild();

            BinaryTreeNode<T> minLargerNode = findMin(node.getRightChild());
            node.setValue(minLargerNode.getValue());
            node.setRightChild(removeRecursively(node.getRightChild(), minLargerNode.getValue()));
        }

        return balance(node);  // Balanceia após remoção
    }

    private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }

    private BinaryTreeNode<T> balance(BinaryTreeNode<T> node) {
        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {  // Desbalanceado para a esquerda
            if (getBalanceFactor(node.getLeftChild()) < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild()));  // Rotação dupla
            }
            return rotateRight(node);  // Rotação simples à direita
        }

        if (balanceFactor < -1) {  // Desbalanceado para a direita
            if (getBalanceFactor(node.getRightChild()) > 0) {
                node.setRightChild(rotateRight(node.getRightChild()));  // Rotação dupla
            }
            return rotateLeft(node);  // Rotação simples à esquerda
        }

        return node;  // Retorna nó já balanceado
    }

    private BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> newRoot = node.getRightChild();
        node.setRightChild(newRoot.getLeftChild());
        newRoot.setLeftChild(node);
        return newRoot;
    }

    private BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> newRoot = node.getLeftChild();
        node.setLeftChild(newRoot.getRightChild());
        newRoot.setRightChild(node);
        return newRoot;
    }

    private int height(BinaryTreeNode<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }

    private int getBalanceFactor(BinaryTreeNode<T> node) {
        if (node == null) return 0;
        return height(node.getLeftChild()) - height(node.getRightChild());
    }
}
