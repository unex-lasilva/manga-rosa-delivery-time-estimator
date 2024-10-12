package br.com.mangarosa.collections;
import br.com.mangarosa.collections.traverses.*;

import java.util.List;

public class BinaryTree <T extends Comparable<T>> implements Tree<T> {

    private BinaryTreeNode<T> root;
    private int size;

    public void setRoot(BinaryTreeNode<T> root) {
        this.root = root;
    }
    
    // Método de inserção na árvore
    @Override
    public void add(T value) {
        this.root = addNode(root, value);
    }

    // Função recursiva para inserir um novo nó
    private BinaryTreeNode<T> addNode(BinaryTreeNode<T> node, T value) {
        
        // Cria um novo nó se o atual for nulo
        if (node == null) {
            size++;
            return new BinaryTreeNode<T>(value);
        } 
        // Caso o nó atual não seja nulo, compara o valor inserido com o nó atual (maior para direita e menor para esquerda)
        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(addNode(node.getLeftChild(), value));    
        } else {
            node.setRightChild(addNode(node.getRightChild(), value));
        }

        // Retorna o nó se o valor já existe
        return node;
    }

    // Método para remover um nó da árvore
    @Override
    public void remove(T value) {
        this.root = removeNode(root, value);
        size--; 
    }

    private BinaryTreeNode<T> removeNode (BinaryTreeNode<T> node, T value) {
        // 1. O elemento não existe 
        if (node == null) {
            return null;
        }

        // Verifica se o nó atual é igual ao valor procurado
        if (node.getValue().equals(value)) {

            // 2. O nó é uma folha
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                return null;
            }
            
            // 3. O nó só tem um filho
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            }

            if (node.getRightChild() == null) {
                return node.getLeftChild();
            }

            // 4. O nó tem dois filhos
            
            // Encontra o menor valor à direita do nó atual
            T smallestValue = findSmallestValue(node.getRightChild());
            
            // Atribue o menor valor encontrado como nó atual
            node.setValue(smallestValue);

            // Remove o menor valor encontrado, evitando duplicidade
            node.setRightChild(removeNode(node.getRightChild(), smallestValue));
        } 

        // Caso o valor não seja igual, verifica se é maior ou menor e continua a recursão
        else if (value.compareTo(node.getValue()) < 0) {
        node.setLeftChild(removeNode(node.getLeftChild(), value));    
        } 
        else {
        node.setRightChild(removeNode(node.getRightChild(), value));
        }

        return node;
    }

    private T findSmallestValue(BinaryTreeNode<T> node) {
        return node.getLeftChild() != null ? findSmallestValue(node.getLeftChild()) : node.getValue();
    }

    // Método para verificar se um valor existe na árvore
    @Override
    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(BinaryTreeNode<T> node, T value) {
        // Caso o nó seja nulo, o valor não existe na árvore
        if (node == null) {
            return false;
        }
        // Caso o valor seja igual ao do nó, ele existe na árvore
        if (value.compareTo(node.getValue()) == 0) {
            return true;
        }

        // Caso o valor seja menor, continua a busca à esquerda, se for maior à direita
        return value.compareTo(node.getValue()) < 0 ? containsRecursive(node.getLeftChild(), value) : containsRecursive(node.getRightChild(), value);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Verifica se o nó é uma folha
    @Override
    public boolean isLeaf(T value) {
        BinaryTreeNode<T> node = findNode(root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }

    // Método auxiliar para retornar o nó que contém o valro buscar em isLeaf
    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> node, T value) {
        // Se o nó não existe ou o valor for igual ao do nó, retorna o próprio nó
        if (node == null || value.compareTo(node.getValue()) == 0) {
            return node;
        }

        // Se for menor ou maior, continua buscando
        return value.compareTo(node.getValue()) < 0 ? findNode(node.getLeftChild(), value) : findNode(node.getRightChild(), value);
    }

    // Retorna a raíz
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

    // Limpa a árvore, removendo a raíz e definindo seu tamanho como 0
    @Override
    public void clear() {
        root = null;
        size = 0;
    }
}
