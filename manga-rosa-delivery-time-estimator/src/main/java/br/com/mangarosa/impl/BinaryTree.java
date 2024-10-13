package br.com.mangarosa.impl;

import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.InOrderTraversal;
import br.com.mangarosa.collections.Tree;

import java.util.List;

/*
Classe que implementa uma árvore binária de busca (abb/bst) com complexidade assintótica logarítmica O(log n) no médio
caso e complexidade assintótica linear O(n) no pior caso.
 */

public class BinaryTree<T extends Comparable<T>> implements Tree<T>
{
    protected BinaryTreeNode<T> root; // raiz da árvore
    protected int size; // tamanho da árvore

    public BinaryTree()
    {// Construtor base (sem parâmetros)
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(T value)
    {// Método público de inserir valores na árvore
        root = addRec(root, value);
    }

    private BinaryTreeNode<T> addRec(BinaryTreeNode<T> node, T value)
    {// Método privado recursivo de inserir valores, que o usuário não tem acesso
        if (node == null)
        {
            size++;
            return new BinaryTreeNode<>(value);
        }

        int cmpValue = value.compareTo(node.getValue());

        if (cmpValue < 0)
        {
            node.setLeftChild(addRec(node.getLeftChild(), value));
        } else if (cmpValue > 0)
        {
            node.setRightChild(addRec(node.getRightChild(), value));
        }

        return node;
    }

    @Override
    public void remove(T value)
    {// Método público de remover valores da árvore
        root = removeRec(root, value);
    }

    private BinaryTreeNode<T> removeRec(BinaryTreeNode<T> node, T value)
    {// Método privado recursivo de remover valores da árvore, o usuário não pode acessar
        if (node == null)
        {
            return null;
        }

        int cmpValue = value.compareTo(node.getValue());

        if (cmpValue < 0)
        {
            node.setLeftChild(removeRec(node.getLeftChild(), value));
        }
        else if (cmpValue > 0)
        {
            node.setRightChild(removeRec(node.getRightChild(), value));
        }
        else
        {
            if (node.getLeftChild() == null && node.getRightChild() == null)
            {
                size--;
                return null;
            }

            if (node.getLeftChild() == null)
            {
                size--;
                return node.getRightChild();
            }

            if (node.getRightChild() == null)
            {
                size--;
                return node.getLeftChild();
            }

            node.setValue(minValue(node.getRightChild()));
            node.setRightChild(removeRec(node.getRightChild(), node.getValue()));
        }

        return node;
    }

    protected T minValue(BinaryTreeNode<T> node)
    {// Método que encontra o menor valor dentro da árvore
        while (node.getLeftChild() != null)
        {
            node = node.getLeftChild();
        }

        return node.getValue();
    }

    @Override
    public boolean contains(T value)
    {// Método de buscar um valor na árvore pra ver se ela possui esse valor, retorna true se o valor tiver na árvore
        return containsRec(root, value);
    }

    private boolean containsRec(BinaryTreeNode<T> node, T value)
    {// Método privado recursivo de buscar um valor na árvore, o usuário não pode acessar
        if (node == null)
        {
            return false;
        }

        int cmpValue = value.compareTo(node.getValue());

        if (cmpValue == 0)
        {
            return true;
        }

        if (cmpValue < 0)
        {
            return containsRec(node.getLeftChild(), value);
        }

        return containsRec(node.getRightChild(), value);

    }

    @Override
    public boolean isEmpty()
    {// Método que verifica se a árvore tá vazia
        return size == 0;
    }

    @Override
    public boolean isLeaf(T value)
    {// Método que verifica se o nó que contém o valor passado como argumento é uma folha da árvore
        BinaryTreeNode<T> node = findNode(root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }

    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> node, T value)
    {// Método privado recursivo que encontra o nó que possui o valor passado como argumento pro método isLeaf()
        if (node == null || value.compareTo(node.getValue()) == 0)
        {
            return node;
        }

        if (value.compareTo(node.getValue()) < 0)
        {
            return findNode(node.getLeftChild(), value);
        }

        return findNode(node.getRightChild(), value);
    }

    @Override
    public BinaryTreeNode<T> root()
    {// "Getter" que retorna a raiz da árvore
        return root;
    }

    @Override
    public int size()
    {// "Getter" que retorna o tamanho da árvore
        return size;
    }

    @Override
    public  List<T> toList()
    {// Método que retorna todos os valores da árvore numa lista tipada na ordem InOrder (esquerda, eu, direita)
        InOrderTraversal<T> inOrder = new InOrderTraversal<>();
        List<T> list = inOrder.traverse(this);

        if (list.isEmpty())
        {
            return null;
        }

        return list;
    }

    @Override
    public void clear()
    {// Método que limpa a árvore (faz ela ficar vazia)
        root = null;
        size = 0;
    }
}
