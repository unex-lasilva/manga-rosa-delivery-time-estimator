package br.com.mangarosa.collections;

public class BinaryTreeNode<T extends Comparable<T>> {

    private T value;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    // Construtor para inicializar o nó com o valor dado
    public BinaryTreeNode(T value){
        this.value = value;
    }

    // Atualiza o valor armazenado neste nó
    public void setValue(T value) {
        this.value = value;
    }

    // Define o filho à esquerda deste nó
    public void setLeftChild(BinaryTreeNode<T> left) {
        this.left = left;
    }

    // Define o filho à direita deste nó
    public void setRightChild(BinaryTreeNode<T> right) {
        this.right = right;
    }

    // Retorna o filho à esquerda deste nó
    public BinaryTreeNode<T> getLeftChild(){
        return this.left;
    }

    // Retorna o filho à direita deste nó
    public BinaryTreeNode<T> getRightChild(){
        return this.right;
    }

    // Retorna o valor armazenado neste nó
    public T getValue(){
        return this.value;
    }

    // Representação em string do nó
    @Override
    public String toString() {
        return "node{" +
                "value=" + value +
                '}';
    }
}
