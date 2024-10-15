package br.com.mangarosa.collections;
import java.util.List;
import br.com.mangarosa.collections.rotacoes.InOrderTraversal;

public class BinaryTree <T extends Comparable <T>> implements Tree<T> {

    private BinaryTreeNode<T> root;
    private int size;


    //Adiciona  um novo nó ao árvore binária
    @Override
    public void add(T value) {
        this.root = insertRecursao(root, value);
    }

    //Metodo para inserir um novo valor na árvore de forma recursiva
    private BinaryTreeNode<T> insertRecursao(BinaryTreeNode<T> node, T value){
        //se tiver vázio , insere novo nó
            if(node == null){
                size++;
                return new BinaryTreeNode<T>(value);
            }else if(value.compareTo(node.getValue()) < 0) {
                node.setLeftChild(insertRecursao(node.getLeftChild(), value));
            }
            else if(value.compareTo(node.getValue()) > 0) {
                node.setRightChild(insertRecursao(node.getRightChild(), value));
            }
            return node;
        }

    //Remove  um nó da árvore binária
    @Override
    public void remove(T value) {
        this.root = removerNo(root, value);
        size--;
    }

    //Metodo recursivo para remover um nó da árvore binária
    private BinaryTreeNode<T> removerNo(BinaryTreeNode<T> node, T value) {
        if(node == null) {
            return null;
        }
        if(node.getValue().equals(value)) {
            if(node.getLeftChild() == null && node.getRightChild() == null) {
                return null;
            }
            if(node.getLeftChild() == null) {
                return node.getRightChild();
            }
            if(node.getRightChild() == null) {
                return node.getLeftChild();
            }
            T smallestValue =  findSmallest(node.getRightChild());
            node.setValue(smallestValue);
            node.setRightChild(removerNo(node.getRightChild(), smallestValue));
        } 
        else if(value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(removerNo(node.getLeftChild(), value));
        }else{
            node.setRightChild(removerNo(node.getRightChild(), value));
        }
            return node;
        }

    //Metodo  para encontrar a menor chave de um nó
    private T findSmallest(BinaryTreeNode<T> node) {
        return node.getLeftChild() != null  ? findSmallest(node.getLeftChild()) : node.getValue();

    }

    //Metodo para verificar se um valor existe na árvore
    @Override
    public boolean contains(T value) {
        return containsNode(this.root, value);
    }

    //Metodo recursivo para verificar se um valor existe na árvore
    private boolean containsNode(BinaryTreeNode<T> node, T value) {
        if(node == null) {
            return false;
        }
        if(value.compareTo(node.getValue()) == 0){
            return true;
        } 
        return (value.compareTo(node.getValue()) < 0) ? containsNode(node.getLeftChild(), value) : containsNode(node.getRightChild(), value);

    }

    //Metodo para verificar se a árvore está vazia
    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    //Metodo para verificar se um nó é folha
    @Override
    public boolean isLeaf(T value) {
        BinaryTreeNode<T> node = findNode(root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }

    //Metodo para encontrar um nó
    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> node, T value) {
        if(node == null ||  value.compareTo(node.getValue()) == 0){
            return node;
        } 
        return  (value.compareTo(node.getValue()) < 0) ? findNode(node.getLeftChild(), value) :  findNode(node.getRightChild(), value);

    }

    //Metodo para definir o valor de um nó
    public void setRoot(BinaryTreeNode<T> root) {
        this.root = root;
    }

    //Metodo para retornar a raiz
    @Override
    public BinaryTreeNode<T> root() {
        return this.root;
    }

    //Metodo para retornar o tamanho
    @Override
    public int size() {
        return size;
    }

    //Metodo para converter em uma lista em ordem
    @Override
    public List<T> toList() {
        if (root == null) {
            return null;
        }    
        List<T> elements = new InOrderTraversal<T>().traverse(this);    
        return elements;
    }

    //Metodo para limpar a arvore
    @Override
    public void clear() {
       this.root = null;
       size = 0;
    }

    // @Override
    // public T[] toArray() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    // }


}