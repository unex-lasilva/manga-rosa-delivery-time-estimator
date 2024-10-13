package br.com.mangarosa.collections;

public class BinaryTreeNode<T extends Comparable<T>> {

    private T value;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;
    private  int altura;

    public BinaryTreeNode (T value){
        this.value = value;
        this.altura = 0;
        
    }

    
    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
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

    public BinaryTreeNode<T> getLeftChild(){
        return this.left;
    }

    public BinaryTreeNode<T> getRightChild(){
        return this.right;
    }

    public T getValue(){
        return this.value;
    }

    @Override
    public String toString
    () {
        return "node{" +
                "value=" + value +
                '}';
    }
}
