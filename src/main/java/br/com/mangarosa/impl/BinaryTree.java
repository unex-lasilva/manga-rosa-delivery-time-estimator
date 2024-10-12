package br.com.mangarosa.impl;

import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.Tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private BinaryTreeNode<T> root;

    @Override
    public void add(T value) {
        this.root = this.addRecursive(this.root, value);
    }

    @Override
    public void remove(T value) {
        this.root = removeRecursive(this.root, value);
    }

    @Override
    public boolean contains(T value) {
        return find(this.root, value) != null;
    }

    @Override
    public boolean isEmpty(){
        return this.root == null;
    }

    @Override
    public boolean isLeaf(T value) {
        BinaryTreeNode<T> node = find(this.root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }

    @Override
    public BinaryTreeNode<T> root() {
        return this.root;
    }

    @Override
    public int size(){
        return countElements(this.root);
    }

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

    @Override
    public void clear(){
        this.root = null;
    }

    private BinaryTreeNode<T> addRecursive(BinaryTreeNode<T> current, T value){
        if(current == null){
            return new BinaryTreeNode<>(value);
        }
        if(value.compareTo(current.getValue()) < 0){
            current.setLeftChild( addRecursive(current.getLeftChild(), value) );
        } else if(value.compareTo(current.getValue()) > 0){
            current.setRightChild( addRecursive(current.getRightChild(), value));
        }
        return current;
    }

    private BinaryTreeNode<T> find(BinaryTreeNode<T> current, T value){
        if(current == null){
            return null;
        }
        if(value.compareTo(current.getValue()) == 0){
            return current;
        }
        return value.compareTo(current.getValue()) < 0 ?
                find(current.getLeftChild(), value)
                : find(current.getRightChild(), value);
    }

    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> current, T value){
        if(current == null){
            return null;
        }
        if(value.compareTo(current.getValue()) == 0){
            if(current.getLeftChild() == null && current.getRightChild() == null){
                return null;
            }
            if(current.getLeftChild() == null){
                return current.getRightChild();
            }
            if(current.getRightChild() == null){
                return current.getLeftChild();
            }
            final T smallestValue = findSmallest(current.getRightChild());
            current.setValue(smallestValue);
            current.setRightChild(removeRecursive(current.getRightChild(), smallestValue));
        } else if (value.compareTo(current.getValue()) < 0) {
            current.setLeftChild(removeRecursive(current.getLeftChild(), value));
        } else {
            current.setRightChild(removeRecursive(current.getRightChild(), value));
        }
        return current;
    }

    private T findSmallest(BinaryTreeNode<T> current){
        return current.getLeftChild() != null ? findSmallest(current.getLeftChild()) : current.getValue();
    }

    private int countElements(BinaryTreeNode<T> current){
        if(current == null){
            return 0;
        }
        return countElements(current.getLeftChild()) + countElements(current.getRightChild()) + 1;
    }

    protected void inOrder(BinaryTreeNode<T> node, List<T> nodes){
        if(node != null){
            inOrder(node.getLeftChild(), nodes);
            nodes.add(node.getValue());
            inOrder(node.getRightChild(), nodes);
        }
    }
}
