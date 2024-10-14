package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class PreOrder<T extends Comparable<T>> implements TreeTraversal<T> {

    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        preOrder(tree.root(), result);
        return result;
    }

    private void preOrder(BinaryTreeNode<T> node, List<T> list) {
        if (node != null) {
            list.add(node.getValue());  // Visita o nó atual
            preOrder(node.getLeftChild(), list);  // Percorre a subárvore esquerda
            preOrder(node.getRightChild(), list); // Percorre a subárvore direita
        }
    }
}
