package br.com.mangarosa.collections;

public class BinaryTree <T extends Comparable<T>> implements Tree<T>{
    
    private BinaryTreeNode<T> root;
    private int size = 0;

    public BinaryTree(){
        this.root = null;
        
    }

    @Override
    public void add(T value){
        this.root = insertRecursive(this.root, value);
        size ++;
    }

    private BinaryTreeNode<T> insertRecursive(BinaryTreeNode<T> current, T value){
        if (current == null) {
            return new BinaryTreeNode<>(value);
        }
        else if (value.comparteTo(current.getValue()) < 0){ // Se o valor for menor que Zero
            current.setLeftChild(insertRecursive(current.getLeftChild(),value)); //Recur. Passando o valor da esquerda
        }
        else if (value.comparteTo(current.getValue()) > 0){ // se o valor for MAIOR que zero
            node.setRightChild(insertRecursive(current.getRightChild(),value));// Recur. com o valor da direita
        }

        return current;
    }

    @Override
    public void remove(T value) {
        root = removeRecursive(this.root, value);
        size--;
    }

    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> current, T value){
        //Quando o Elemento não existir:
        if (current == null){
            return null;
        }

        //Verificar se o valor encontrado é o que estou procurando: 
        if (value.compareTo(current.getValue()) < 0){
            current.setLeftChild(removeRecursive(current.getLeftChild(),value));
        else if (value.compareTo(current.getValue()) > 0){
            current.setRightChild(removeRecursive(current.getRightChild,value));
        }
        else{

            //Quando o Nó for uma folha:
            if(current.getLeftChild() == null && current.getRightChild() == null){
                return null;
            }
            //Quando o Nó tem 1 Filho
            else if(current.getLeftChild() == null){
                return current.getRightChild();
            }
            else if(current.getRightChild() == null){
                return current.getLeftChild();
            } 

            //Quando o Nó tem 2 Filhos
            current.setValue(findSmallest(current.getRightChild()));
            current.setRightChild(removeRecursive(current.getRightChild(),current.getValue()));

        }
        //Pega o MENOR valor da SUBARVORE a Direita. 
        private T findSmallest(BinaryTreeNode<T> current){
            return current.getLeftChild() == null ? current.getValue():findSmallest(current.getLeftChild());
            
        }
        
        return current;
    }

    @Override
    public boolean contains(T value){
        return containsRecursive(root,value);
    }

    private boolean containsRecursive(BinaryTreeNode<T> current, T value){
        if (current == null){
            return false;
        }

        if (value.equals(current.getValue())){
            return true;
        }

        return value.compareTo(current.getValue()) < 0 ? containsRecursive(current.getLeftChild(),value):containsRecursive(current.getRightChild(),value);
    }

    @Override
    public boolean isEmpty(){
        return size = 0;
    }

    @Override
    public boolean isLeaf(T value){
        BinaryTreeNode<T> current = findNode(root,value);
        return node != null && current.getLeftChild() == null && current.getRightChild() == null;
    }

    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> current, T value){
        if (current == null){
            return null;
        }
        if (value.compareTo(current.getValue()) == 0){
            return current;
        }

        return value.compareTo(current.getValue()) < 0 ? findNode(current.getLeftChild(),value):findNode(current.getRightChild(),value);
    }

    @Override
    public BinaryTreeNode<T> root(){
        return this.root;
    }

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public T[] toArray(){
        return null;
    }

    @Override
    public void clear(){
        this.root = null;
        this.size = 0;
    }

}