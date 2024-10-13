package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class InOrderTrav<T extends Comparable<T>> implements TreeTraversal<T> {
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        inOrder(tree.root(), result);
        return result;
    }

    private void inOrder(BinaryTreeNode<T> current, List<T> result) {
        if (current != null) {
            inOrder(current.getLeftChild(), result);  // Percorre a subárvore à esquerda
            result.add(current.getValue());  // Visita a raiz
            inOrder(current.getRightChild(), result);  // Percorre a subárvore à direita
        }
    }
}