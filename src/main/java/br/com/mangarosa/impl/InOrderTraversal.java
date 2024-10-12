package br.com.mangarosa.impl;

import br.com.mangarosa.collections.Tree;
import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.TreeTraversal;
import java.util.ArrayList;
import java.util.List;

/**
 * Travessia em ordem (in-order) da árvore binária.
 */
public class InOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {

    /**
     * Realiza a travessia in-order e retorna os valores dos nós.
     */
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        inOrder(tree.root(), result);
        return result;
    }

    /**
     * Travessia recursiva in-order a partir de um nó.
     */
    private void inOrder(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            inOrder(node.getLeftChild(), result);
            result.add(node.getValue());
            inOrder(node.getRightChild(), result);
        }
    }
}
