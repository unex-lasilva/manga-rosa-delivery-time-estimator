package br.com.mangarosa.collections;

// arvore binaria para organizar elementos de forma ordenada
public class BinaryTree<T extends Comparable<T>> implements Tree<T> {
    protected BinaryTreeNode<T> root; // no raiz da arvore
    private int size; // contador de elementos na arvore

    // adiciona um valor na arvore na posicao correta
    @Override
    public void add(T value) {
        root = addRecursive(root, value);
        size++;
    }

    // funcao auxiliar recursiva que encontra a posicao correta para o novo valor
    private BinaryTreeNode<T> addRecursive(BinaryTreeNode<T> node, T value) {
        if (node == null) return new BinaryTreeNode<>(value);
        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(addRecursive(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(addRecursive(node.getRightChild(), value));
        }
        return node;
    }

    // remove um valor da arvore, ajustando os nos conforme necessario
    @Override
    public void remove(T value) {
        root = removeRecursive(root, value);
        size--;
    }

    // funcao auxiliar recursiva que remove o valor e reorganiza a arvore
    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> node, T value) {
        if (node == null) return null;
        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(removeRecursive(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(removeRecursive(node.getRightChild(), value));
        } else {
            if (node.getLeftChild() == null) return node.getRightChild();
            if (node.getRightChild() == null) return node.getLeftChild();
            T smallestValue = findSmallestValue(node.getRightChild());
            node.setValue(smallestValue);
            node.setRightChild(removeRecursive(node.getRightChild(), smallestValue));
        }
        return node;
    }

    // encontra o menor valor a partir de um no (usado para reorganizar durante remocao)
    private T findSmallestValue(BinaryTreeNode<T> node) {
        return node.getLeftChild() == null ? node.getValue() : findSmallestValue(node.getLeftChild());
    }

    // verifica se a arvore contem um valor especifico
    @Override
    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    // funcao auxiliar recursiva que busca o valor na arvore
    private boolean containsRecursive(BinaryTreeNode<T> node, T value) {
        if (node == null) return false;
        if (value.compareTo(node.getValue()) == 0) return true;
        return value.compareTo(node.getValue()) < 0
                ? containsRecursive(node.getLeftChild(), value)
                : containsRecursive(node.getRightChild(), value);
    }

    // retorna true se a arvore estiver vazia
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // verifica se um valor especifico e uma folha (nao tem filhos)
    @Override
    public boolean isLeaf(T value) {
        BinaryTreeNode<T> node = findNode(root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }

    // encontra o no que contem um valor especifico
    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> node, T value) {
        if (node == null || value.compareTo(node.getValue()) == 0) return node;
        return value.compareTo(node.getValue()) < 0
                ? findNode(node.getLeftChild(), value)
                : findNode(node.getRightChild(), value);
    }

    // retorna a raiz da arvore
    @Override
    public BinaryTreeNode<T> root() {
        return root;
    }

    // retorna o numero de elementos na arvore
    @Override
    public int size() {
        return size;
    }

    // converte a arvore para um array
    @Override
    public T[] toArray() {
        return null;
    }

    // limpa a arvore, removendo todos os elementos
    @Override
    public void clear() {
        root = null;
        size = 0;
    }
}
