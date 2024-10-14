package br.com.mangarosa.collections;

public class BalancedTree<T extends Comparable<T>> {

    public BinaryTreeNode<T> balance(BinaryTreeNode<T> node) {
        int balanceFactor = height(node.getLeftChild()) - height(node.getRightChild());

        // Left heavy
        if (balanceFactor > 1) {
            if (height(node.getLeftChild().getLeftChild()) >= height(node.getLeftChild().getRightChild())) {
                return rotateRight(node); // Left-Left case
            } else {
                node.setLeftChild(rotateLeft(node.getLeftChild())); // Left-Right case
                return rotateRight(node);
            }
        }

        // Right heavy
        if (balanceFactor < -1) {
            if (height(node.getRightChild().getRightChild()) >= height(node.getRightChild().getLeftChild())) {
                return rotateLeft(node); // Right-Right case
            } else {
                node.setRightChild(rotateRight(node.getRightChild())); // Right-Left case
                return rotateLeft(node);
            }
        }

        return node;
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
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }
}
