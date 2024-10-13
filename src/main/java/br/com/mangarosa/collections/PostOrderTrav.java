package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class PostOrderTrav<T extends Comparable<T>> implements TreeTraversal<T> {
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        postOrder(tree.root(), result);
        return result;
    }

    private void postOrder(BinaryTreeNode<T> current, List<T> result) {
        if (current != null) {
            postOrder(current.getLeftChild(), result);  // Percorre a subárvore à esquerda
            postOrder(current.getRightChild(), result);  // Percorre a subárvore à direita
            result.add(current.getValue());  // Visita a raiz
        }
    }
}
