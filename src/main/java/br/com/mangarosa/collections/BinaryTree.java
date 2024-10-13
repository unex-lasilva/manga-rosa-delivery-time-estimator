package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree <T extends Comparable<T>> implements Tree<T>{

    private BinaryTreeNode<T> root; //Nó Raiz
    public int size = 0; //Numero de Nós na Arvore

    //Construtor
    public BinaryTree(){
        this.root = null;
        this.size = 0;

    }

    //Chama um metodo recursivo que insere recursivamente os valores da Arvore.
    @Override
    public void add(T value) {
        this.root = insertRecursive(this.root, value);
        size++;
    }


    private BinaryTreeNode<T> insertRecursive(BinaryTreeNode<T> current, T value){
        if (current == null) {
            return new BinaryTreeNode<>(value); //cria um novo nó com o valor se for Null.
        }
        else if (value.compareTo(current.getValue()) < 0){ // Se o valor for menor que Zero
            current.setLeftChild(insertRecursive(current.getLeftChild(),value)); //Recur. Passando o valor da esquerda
        }
        else if (value.compareTo(current.getValue()) > 0){ // se o valor for MAIOR que zero
            current.setRightChild(insertRecursive(current.getRightChild(),value));// Recur. com o valor da direita
        }
        return current;
    }

    //Remove um valor da árvore
    @Override
    public void remove(T value) {
        if (contains(value)){
            this.root = removeRecursive(this.root, value);
            size--;
        }
    }

    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> current, T value){
        //Quando o Elemento não existir:
        if (current == null){
            return null;
        }

        //Verificar se o valor encontrado é o que estou procurando:
        if (value.compareTo(current.getValue()) < 0){
            current.setLeftChild(removeRecursive(current.getLeftChild(),value));
        }else if (value.compareTo(current.getValue()) > 0){
            current.setRightChild(removeRecursive(current.getRightChild(),value));
        }
        else{

            //Quando o Nó for uma folha:
            if(current.getLeftChild() == null && current.getRightChild() == null){
                return null;
            }
            //Quando o Nó tem 1 Filho na direita
            else if(current.getLeftChild() == null){
                return current.getRightChild();
            }
            //Quando o Nó tem 1 Filho na esquerda
            else if(current.getRightChild() == null){
                return current.getLeftChild();
            }

            //Quando o Nó tem 2 Filhos
            current.setValue(findSmallest(current.getRightChild()));
            current.setRightChild(removeRecursive(current.getRightChild(),current.getValue()));

        }
        return current;
    }

    //Pega o MENOR valor da SUBARVORE a Direita.
    protected  T findSmallest(BinaryTreeNode<T> current){
        return current.getLeftChild() == null ? current.getValue():findSmallest(current.getLeftChild());

    }

    //verifica se existe o valor na arvore
    @Override
    public boolean contains(T value) {
        return containsRecursive(root,value);
    }

    //Retorna Verdadeiro se tem, e Falso se não tem.
    private boolean containsRecursive(BinaryTreeNode<T> current, T value){
        if (current == null){
            return false;
        }
        int compare = value.compareTo(current.getValue());
        if (compare == 0){
            return true;
        }
        return compare < 0 ? containsRecursive(current.getLeftChild(),value) : containsRecursive(current.getRightChild(),value);
    }

    //Verifica se a Arvore está vazia
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean isLeaf(T value) {
        BinaryTreeNode<T> current = findNode(root, value);
        return current != null && current.getLeftChild() == null && current.getRightChild() == null;
    }

    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> current, T value){
        if (current == null){
            return null;
        }
        if (value.compareTo(current.getValue()) == 0){
            return current;
        }
        return value.compareTo(current.getValue()) < 0 ? findNode(current.getLeftChild(),value) : findNode(current.getRightChild(),value);

    }

    @Override
    public BinaryTreeNode<T> root() {
        return this.root;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> toList() {
        if (root == null){
            return null;
        }
        List<T> elements = new ArrayList<>();
        toListRecursive(root, elements);
        return elements;
    }

    private void toListRecursive(BinaryTreeNode<T> current, List<T> elements) {
        if (current != null) {
            toListRecursive(current.getLeftChild(), elements);
            elements.add(current.getValue());
            toListRecursive(current.getRightChild(), elements);
        }
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;

    }

    protected void setRoot(BinaryTreeNode<T> root){
        this.root = root;
    }

}
