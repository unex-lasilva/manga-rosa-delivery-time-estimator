package br.com.mangarosa.impl;

import br.com.mangarosa.collections.Tree;
import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.TreeTraversal;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface TreeTraversal que realiza a travessia de uma árvore binária
 * utilizando o algoritmo pre-order (pré-ordem), onde o nó raiz é visitado antes dos filhos.
 */
public class PreOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {

    /**
     * Percorre a árvore usando o algoritmo pre-order e retorna uma lista contendo os valores dos nós.
     */
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        preOrder(tree.root(), result);
        return result;
    }

    /**
     * Método auxiliar que realiza a travessia recursiva pre-order de um nó específico.
     * Se o nó for nulo, a recursão termina.
     */
    private void preOrder(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            result.add(node.getValue());
            preOrder(node.getLeftChild(), result);
            preOrder(node.getRightChild(), result);
        }
    }
}
