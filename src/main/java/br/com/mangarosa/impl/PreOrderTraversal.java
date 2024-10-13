package br.com.mangarosa.impl;

import br.com.mangarosa.collections.Tree;
import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.TreeTraversal;
import java.util.ArrayList;
import java.util.List;

/**
 * Travessia pré-ordem (pre-order) da árvore binária.
 */
public class PreOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {

    /**
     * Realiza a travessia pre-order e retorna os valores dos nós.
     */
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        preOrder(tree.root(), result);
        return result;
    }

    /**
     * Travessia recursiva pre-order a partir de um nó.
     */
    private void preOrder(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            result.add(node.getValue());
            preOrder(node.getLeftChild(), result);
            preOrder(node.getRightChild(), result);
        }
    }
}
