package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class PostOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        postOrder(tree.root(), result);
        return result;
    }

    private void postOrder(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            postOrder(node.getLeftChild(), result);  // Percorre a subárvore à esquerda
            postOrder(node.getRightChild(), result);  // Percorre a subárvore à direita
            result.add(node.getValue());  // Visita a raiz
        }
    }
}