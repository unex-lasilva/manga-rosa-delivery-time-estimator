package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private BinaryTreeNode<T> root; //raiz da arvore
    public int size; //tamanho da arvore

    public BinaryTree() {
        this.root = null;
    }//raiz iniciando como nula

    @Override
    public void add(T value) {
        root = insertRecursive(root, value); // inserindo valor
        size++; //
    }

    private BinaryTreeNode<T> insertRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) return new BinaryTreeNode<>(value); //se nao tiver no, cria um novo
        if (value.compareTo(current.getValue()) < 0)
            current.setLeftChild(insertRecursive(current.getLeftChild(), value)); //inserindo a esquerda
        else if (value.compareTo(current.getValue()) > 0)
            current.setRightChild(insertRecursive(current.getRightChild(), value)); //inserindo a direita
        return current; //retorna o no atual
    }

    @Override
    public void remove(T value) {
        if (contains(value)) { //verifica se o valor existe
            root = removeRecursive(root, value); //remove o valor
            size--; //
        }
    }

    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) return null; //se nao tiver no, retorna nulo
        if (value.compareTo(current.getValue()) < 0)
            current.setLeftChild(removeRecursive(current.getLeftChild(), value)); //removendo a esquerda
        else if (value.compareTo(current.getValue()) > 0)
            current.setRightChild(removeRecursive(current.getRightChild(), value)); //removendo a direita
        else {
            if (current.getLeftChild() == null) return current.getRightChild(); //sem filho a esquerda
            if (current.getRightChild() == null) return current.getLeftChild(); //sem filho a direita
            current.setValue(findSmallest(current.getRightChild())); //troca o valor pelo menor a direita
            current.setRightChild(removeRecursive(current.getRightChild(), current.getValue())); //remove o menor a direita
        }
        return current; //retorna o no atual
    }

    protected T findSmallest(BinaryTreeNode<T> current) {
        return current.getLeftChild() == null ? current.getValue() : findSmallest(current.getLeftChild()); //encontra o menor valor
    }

    @Override
    public boolean contains(T value) {
        return containsRecursive(root, value);}//verificando se o valor esta na arvore

    private boolean containsRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) return false; //se nao houver no, retorna falso
        int compare = value.compareTo(current.getValue());
        return compare == 0 || (compare < 0 ? containsRecursive(current.getLeftChild(), value) : containsRecursive(current.getRightChild(), value)); //
    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean isLeaf(T value) {
        BinaryTreeNode<T> current = findNode(root, value);
        return current != null && current.getLeftChild() == null && current.getRightChild() == null; //verifica se e uma folha
    }

    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> current, T value) {
        if (current == null) return null; //se nao houver no, retorna nulo
        int compare = value.compareTo(current.getValue());
        return compare == 0 ? current : (compare < 0 ? findNode(current.getLeftChild(), value) : findNode(current.getRightChild(), value));
    }

    public BinaryTreeNode<T> root() {
        return root;
    }//retorna a raiz

    public int size() {
        return size;
    }//retorna o tamanho

    @Override
    public List<T> toList() {
        if (root == null) return null; //se a raiz for nula, retorna nulo
        List<T> elements = new ArrayList<>();//cria uma lista para os elementos
        toListRecursive(root, elements); //preenchendo a lista recursivamente
        return elements; //retorna a lista
    }

    private void toListRecursive(BinaryTreeNode<T> current, List<T> elements) {
        if (current != null) {
            toListRecursive(current.getLeftChild(), elements); //adiciona filhos a esquerda
            elements.add(current.getValue()); //adiciona o valor atual a lista
            toListRecursive(current.getRightChild(), elements); //adiciona filhos a direita
        }
    }

    @Override
    public void clear() {
        root = null; //limpa a arvore definindo a raiz como nula
        size = 0;
    }

    protected void setRoot(BinaryTreeNode<T> root) {this.root = root;}// Define a raiz da arvore
}
