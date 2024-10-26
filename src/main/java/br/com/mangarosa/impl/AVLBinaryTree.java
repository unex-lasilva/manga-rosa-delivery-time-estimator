package br.com.mangarosa.impl;

import br.com.mangarosa.collections.BinaryTreeNode;

public class AVLBinaryTree<T extends Comparable<T>> extends BinaryTree<T>{

    @Override
    public void add(T value) {
        super.add(value);
        this.root = balance(this.root());
    }

    @Override
    public void remove(T value) {
        super.remove(value);
        this.root = balance(this.root());
    }

    protected int height(BinaryTreeNode<T> node){
        return node != null ? Math.max( height(node.getLeftChild()), height(node.getRightChild()) ) + 1: 0;
    }

    protected int balanceFactor(BinaryTreeNode<T> node){
        return node != null ? height(node.getLeftChild()) - height(node.getRightChild()) : 0;
    }

    private BinaryTreeNode<T> balance(BinaryTreeNode<T> node){
        if(node == null){
            return null;
        } else {
            BinaryTreeNode<T> leftNode = node.getLeftChild();
            BinaryTreeNode<T> rightNode = node.getRightChild();

            node.setLeftChild( balance(leftNode) );
            node.setRightChild( balance(rightNode) );

            int balanceFactor = balanceFactor(node);
            int leftBalance = balanceFactor(leftNode);
            int rightBalance = balanceFactor(rightNode);

            // left rotation
            if(balanceFactor < -1 && rightBalance <= 0){
                return leftRotate(node);
            }

            // right rotation
            if(balanceFactor > 1 && leftBalance >= 0){
                return rightRotate(node);
            }

            // right left rotation
            if(balanceFactor < -1 && rightBalance >= 0){
                node.setRightChild( rightRotate(node.getRightChild()) );
                return leftRotate(node);
            }

            // left right rotation
            if(balanceFactor > 1 && leftBalance <= 0){
                node.setLeftChild( leftRotate( node.getLeftChild() ) );
                return rightRotate(node);
            }

           return node;
        }
    }

    private BinaryTreeNode<T> rightRotate(BinaryTreeNode<T> node) {

        BinaryTreeNode<T> left = node.getLeftChild();
        BinaryTreeNode<T> rightLeft = left.getRightChild();

        left.setRightChild(node);
        node.setLeftChild(rightLeft);

        return left;
    }

    private BinaryTreeNode<T> leftRotate(BinaryTreeNode<T> node) {

        BinaryTreeNode<T> right = node.getRightChild();
        BinaryTreeNode<T> leftRight = right.getLeftChild();

        right.setLeftChild(node);
        node.setRightChild(leftRight);

        return right;
    }
}
