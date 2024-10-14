package br.com.mangarosa.collections;

public class BinaryTreeNode<T extends Comparable<T>> {

    private T value;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;
    private int height;  // Novo campo para armazenar a altura do nó

    public BinaryTreeNode(T value) {
        this.value = value;
        this.height = 1; // Ao criar um novo nó, sua altura é 1
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setLeftChild(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public void setRightChild(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public BinaryTreeNode<T> getLeftChild() {
        return this.left;
    }

    public BinaryTreeNode<T> getRightChild() {
        return this.right;
    }

    public T getValue() {
        return this.value;
    }

    // Método para obter a altura do nó
    public int getHeight() {
        return height;
    }

    // Método para definir a altura do nó
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "node{" +
                "value=" + value +
                ", height=" + height + // Inclui a altura no toString()
                '}';
    }
}
