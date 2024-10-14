package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class PostOrder<T extends Comparable<T>> implements TreeTraversal<T> {

    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        postOrder(tree.root(), result);
        return result;
    }

    private void postOrder(BinaryTreeNode<T> node, List<T> list) {
        if (node != null) {
            postOrder(node.getLeftChild(), list);  // Percorre a subárvore esquerda
            postOrder(node.getRightChild(), list); // Percorre a subárvore direita
            list.add(node.getValue());  // Visita o nó atual
        }
    }
}
