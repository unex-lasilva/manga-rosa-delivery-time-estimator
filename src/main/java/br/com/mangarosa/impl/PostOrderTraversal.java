package br.com.mangarosa.impl;

import br.com.mangarosa.collections.Tree;
import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.TreeTraversal;
import java.util.ArrayList;
import java.util.List;

/**
 * Travessia pós-ordem (post-order) da árvore binária.
 */
public class PostOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {

    /**
     * Realiza a travessia post-order e retorna os valores dos nós.
     */
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        postOrder(tree.root(), result);
        return result;
    }

    /**
     * Travessia recursiva post-order a partir de um nó.
     */
    private void postOrder(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            postOrder(node.getLeftChild(), result);
            postOrder(node.getRightChild(), result);
            result.add(node.getValue());
        }
    }
}
