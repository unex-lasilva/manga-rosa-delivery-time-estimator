package br.com.mangarosa.collections;

import java.util.List;

import br.com.mangarosa.collections.ordenacao.InOrderTraversal;

public class BinaryTree<T extends Comparable<T>> implements Tree<T>{


    private BinaryTreeNode<T> root;
    private int size;


    public void setRoot(BinaryTreeNode<T> root) {
        this.root = root;
    }


    // Inserir um novo valor na árvore
    @Override
    public void add(T value) {
        this.root = addRecursao(root, value);
        
    }

    // Recursão para inserir um novo nó na árvore
    private BinaryTreeNode<T> addRecursao(BinaryTreeNode<T> noCurrent, T value) {

        // Se a nó atual estiver vazio, cria um novo nó
        if (noCurrent == null) {
            size++;
            return new BinaryTreeNode<T>(value);
        }

        // Compara o valor e decide se vai ou não para a esquerda
        if (value.compareTo(noCurrent.getValue()) < 0) {
            noCurrent.setLeftChild(addRecursao(noCurrent.getLeftChild(), value));

        // Compara o valor e decide se vai ou não para a direita
        } else if (value.compareTo(noCurrent.getValue()) > 0) {
            noCurrent.setRightChild(addRecursao(noCurrent.getRightChild(), value));
        } 

        // Se o valor já existir, não faz nada
        return noCurrent;
    }

  // Remove um valor da árvore
    @Override
    public void remove(T value) {
        this.root = removeRecursao(root, value);
        size--;
    }

    // Recursão para excluir um nó da árvore
    private BinaryTreeNode<T> removeRecursao(BinaryTreeNode<T> noCurrent, T value) {

        // Se o nó não existe
        if(noCurrent == null){
            return null;
        }

        // Verifica se o nó atual é o que estamos procurando
        if(noCurrent.getValue().equals(value)){
            

            // Se o nó é folha
            if(noCurrent.getLeftChild() == null && noCurrent.getRightChild() == null){
                return null;
            }

            // Se o nó existir com um filho
            if(noCurrent.getLeftChild() == null) {
                return noCurrent.getRightChild();
            }
            if(noCurrent.getRightChild() == null) {
                return noCurrent.getLeftChild();
            }
    
            // Se o nó existir com dois filhos
            // Encontra o valor que está a extrema esquerda da direita
            T  small = getSmall(noCurrent.getRightChild()); 
            // Atualiza o nó atual
            noCurrent.setValue(small);
            // Remove o valor antigo de seu nó original
            noCurrent.setRightChild(removeRecursao(noCurrent.getRightChild(), small));
    }

        // Verifica se o valor a ser removido é menor que o valor do nó atual
        else if (value.compareTo(noCurrent.getValue()) < 0) {
            // Se for menor, chama a função de remoção recursiva no nó da esquerda
            noCurrent.setLeftChild(removeRecursao(noCurrent.getLeftChild(), value));
        } else {
            // Se não for menor, chama a função de remoção recursiva no nó da direita
            noCurrent.setRightChild(removeRecursao(noCurrent.getRightChild(), value));
        }

        return noCurrent;
    }

    // Metodo para encontrar o menor valor, o que está a extrema esquerda da direita
    private T getSmall (BinaryTreeNode<T> noCurrent) {
        return noCurrent.getLeftChild() != null ? getSmall(noCurrent.getLeftChild()): noCurrent.getValue() ;

    }


    // Metodo de busca na árvore binária
    @Override
    public boolean contains(T value) {
        return containsRecursao(root, value);
    }

    // Recursão para buscar um nó na árvore
    private boolean containsRecursao (BinaryTreeNode<T> noCurrent, T value) {

        // Se a árvore estiver vazia, não realiza a busca
        if (noCurrent == null) {
            return false;
        }
        // Se o valor for igual ao valor do nó atual, retorna true
        else if (value.compareTo(noCurrent.getValue()) == 0){
            return true;
        }
        // Se for menor, chama a função de busca recursiva no nó da esquerda, se for maior chama na função a direita
        else if (value.compareTo(noCurrent.getValue()) < 0) {
           return containsRecursao(noCurrent.getLeftChild(), value);
            }
            else{
               return containsRecursao(noCurrent.getRightChild(), value);
            } 
    }

    // Metodo verifica se árvore está vazia
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Verifica se o nó é uma folha, não tem filhos
    @Override
    public boolean isLeaf(T value) {
        BinaryTreeNode<T> node = isLeafTeste(root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;

    }

    // Metodo auxiliar para verificar se é folha
    private BinaryTreeNode<T>  isLeafTeste(BinaryTreeNode<T> noCurrent, T value) {
        if (noCurrent == null || value.compareTo(noCurrent.getValue()) == 0) {
            return noCurrent;
        }
        return value.compareTo(noCurrent.getValue()) < 0 ? isLeafTeste(noCurrent.getLeftChild(), value) : isLeafTeste(noCurrent.getRightChild(), value);
    }
    

    // Retorna a raiz
    @Override
    public BinaryTreeNode<T> root() {
        return this.root;
    }

    // Retorna o tamanho da árvore
    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> toList() {
        if (root == null) {
            return null;  // Retorna null se a árvore estiver vazia
        }
         // Realiza a travessia in-order e obtém a lista de elementos
         List<T> elements = new InOrderTraversal<T>().traverse(this);
        // Converte a lista para um array e retorna
        return elements;
    }

    // Esvazia a árvore
    @Override
    public void clear() {
        this.root = null;
        size = 0;
    }
    


}