package br.com.mangarosa.collections;

public class BalancedTree<T extends Comparable<T>> extends BinaryTree<T> {

    @Override
    public void add(T value) {
        root = insert(root, value);
    }

    @Override
    public void remove(T value) {
        root = delete(root, value);
    }

    protected BinaryTreeNode<T> insert(BinaryTreeNode<T> node, T value) {  
        node = super.insert(node, value);  
        return balance(node);  
    }

    protected BinaryTreeNode<T> delete(BinaryTreeNode<T> node, T value) {  
        node = super.delete(node, value);  
        return balance(node);  
    }

    private BinaryTreeNode<T> balance(BinaryTreeNode<T> node) {
        if (node == null) return null;  
        
        int balanceFactor = height(node.getLeftChild()) - height(node.getRightChild());

        if (balanceFactor > 1) {
            if (height(node.getLeftChild().getLeftChild()) >= height(node.getLeftChild().getRightChild())) {
                return rightRotate(node);  
            } else {
                node.setLeftChild(leftRotate(node.getLeftChild()));  
                return rightRotate(node);
            }
        }

        if (balanceFactor < -1) {
            if (height(node.getRightChild().getRightChild()) >= height(node.getRightChild().getLeftChild())) {
                return leftRotate(node);  
            } else {
                node.setRightChild(rightRotate(node.getRightChild()));  
                return leftRotate(node);
            }
        }

        return node; 
    }

    private BinaryTreeNode<T> rightRotate(BinaryTreeNode<T> y) {
        BinaryTreeNode<T> x = y.getLeftChild();
        y.setLeftChild(x.getRightChild());
        x.setRightChild(y);
        return x;  
    }

    private BinaryTreeNode<T> leftRotate(BinaryTreeNode<T> x) {
        BinaryTreeNode<T> y = x.getRightChild();
        x.setRightChild(y.getLeftChild());
        y.setLeftChild(x);
        return y;  
    }

    private int height(BinaryTreeNode<T> node) {
        if (node == null) return 0;  // Nó é nulo?
        return 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }
}
