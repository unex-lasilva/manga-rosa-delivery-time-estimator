package br.com.mangarosa.impl;

import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.InOrderTraversal;
import br.com.mangarosa.collections.Tree;

import java.util.List;

/*
IMPLEMENTA UMA ÁRVORE BINÁRIA DE BUSCA (ABB).
*/

public class BinaryTree<T extends Comparable<T>> implements Tree<T>
{
    protected BinaryTreeNode<T> root; // RAIZ DA ÁRVORE
    protected int size; // TAMANHO DA ÁRVORE

    public BinaryTree()
    {   // CONSTRUTOR INICIALIZA ÁRVORE VAZIA
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(T value)
    {   // INSERE UM VALOR NA ÁRVORE
        root = addRec(root, value);
    }

    private BinaryTreeNode<T> addRec(BinaryTreeNode<T> node, T value)
    {   // INSERE VALOR RECURSIVAMENTE
        if (node == null)
        {   // CRIA NÓ SE A POSIÇÃO ESTIVER VAZIA
            size++;
            return new BinaryTreeNode<>(value);
        }

        int cmpValue = value.compareTo(node.getValue()); // COMPARA VALORES

        if (cmpValue < 0)
        {   // VAI PARA A SUBÁRVORE ESQUERDA
            node.setLeftChild(addRec(node.getLeftChild(), value));
        } else if (cmpValue > 0)
        {   // VAI PARA A SUBÁRVORE DIREITA
            node.setRightChild(addRec(node.getRightChild(), value));
        }

        return node; // RETORNA O NÓ ATUALIZADO
    }

    @Override
    public void remove(T value)
    {   // REMOVE UM VALOR DA ÁRVORE
        root = removeRec(root, value);
    }

    private BinaryTreeNode<T> removeRec(BinaryTreeNode<T> node, T value)
    {   // REMOVE VALOR RECURSIVAMENTE
        if (node == null)
        {   // SE O NÓ NÃO EXISTE, NÃO FAZ NADA
            return null;
        }

        int cmpValue = value.compareTo(node.getValue());

        if (cmpValue < 0)
        {   // VAI PARA A SUBÁRVORE ESQUERDA
            node.setLeftChild(removeRec(node.getLeftChild(), value));
        }
        else if (cmpValue > 0)
        {   // VAI PARA A SUBÁRVORE DIREITA
            node.setRightChild(removeRec(node.getRightChild(), value));
        }
        else
        {   // ENCONTRA O NÓ A SER REMOVIDO
            if (node.getLeftChild() == null && node.getRightChild() == null)
            {   // NÓ É UMA FOLHA, REMOVE
                size--;
                return null;
            }

            if (node.getLeftChild() == null)
            {   // SOMENTE FILHO DIREITO
                size--;
                return node.getRightChild();
            }

            if (node.getRightChild() == null)
            {   // SOMENTE FILHO ESQUERDO
                size--;
                return node.getLeftChild();
            }

            // NÓ COM DOIS FILHOS, SUBSTITUI PELO MENOR DA DIREITA
            node.setValue(minValue(node.getRightChild()));
            node.setRightChild(removeRec(node.getRightChild(), node.getValue()));
        }

        return node;
    }

    protected T minValue(BinaryTreeNode<T> node)
    {   // RETORNA O MENOR VALOR DA SUBÁRVORE
        while (node.getLeftChild() != null)
        {
            node = node.getLeftChild();
        }

        return node.getValue();
    }

    @Override
    public boolean contains(T value)
    {   // VERIFICA SE UM VALOR ESTÁ NA ÁRVORE
        return containsRec(root, value);
    }

    private boolean containsRec(BinaryTreeNode<T> node, T value)
    {   // BUSCA O VALOR RECURSIVAMENTE
        if (node == null)
        {
            return false;
        }

        int cmpValue = value.compareTo(node.getValue());

        if (cmpValue == 0)
        {   // VALOR ENCONTRADO
            return true;
        }

        if (cmpValue < 0)
        {   // VAI PARA A SUBÁRVORE ESQUERDA
            return containsRec(node.getLeftChild(), value);
        }

        // VAI PARA A SUBÁRVORE DIREITA
        return containsRec(node.getRightChild(), value);
    }

    @Override
    public boolean isEmpty()
    {   // VERIFICA SE A ÁRVORE ESTÁ VAZIA
        return size == 0;
    }

    @Override
    public boolean isLeaf(T value)
    {   // VERIFICA SE UM NÓ É FOLHA
        BinaryTreeNode<T> node = findNode(root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }

    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> node, T value)
    {   // BUSCA UM NÓ PELO VALOR
        if (node == null || value.compareTo(node.getValue()) == 0)
        {
            return node;
        }

        if (value.compareTo(node.getValue()) < 0)
        {   // VAI PARA A SUBÁRVORE ESQUERDA
            return findNode(node.getLeftChild(), value);
        }

        // VAI PARA A SUBÁRVORE DIREITA
        return findNode(node.getRightChild(), value);
    }

    @Override
    public BinaryTreeNode<T> root()
    {   // RETORNA A RAIZ DA ÁRVORE
        return root;
    }

    @Override
    public int size()
    {   // RETORNA O TAMANHO DA ÁRVORE
        return size;
    }

    @Override
    public List<T> toList()
    {   // CONVERTE OS NÓS EM UMA LISTA (EM ORDEM)
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
    {   // LIMPA A ÁRVORE
        root = null;
        size = 0;
    }
}
