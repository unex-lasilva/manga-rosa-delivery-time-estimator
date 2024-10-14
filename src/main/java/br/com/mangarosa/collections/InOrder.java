package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class InOrder<T extends Comparable<T>> implements TreeTraversal<T> {

    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        inOrder(tree.root(), result);
        return result;
    }

    private void inOrder(BinaryTreeNode<T> node, List<T> list) {
        if (node != null) {
            inOrder(node.getLeftChild(), list);  // Percorre a subárvore esquerda
            list.add(node.getValue());  // Visita o nó atual
            inOrder(node.getRightChild(), list); // Percorre a subárvore direita
        }
    }
}
