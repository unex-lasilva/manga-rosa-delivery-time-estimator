package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class InOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        inOrder(tree.root(), result);
        return result;
    }

    private void inOrder(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            inOrder(node.getLeftChild(), result);  // Percorre a subárvore à esquerda
            result.add(node.getValue());  // Visita a raiz
            inOrder(node.getRightChild(), result);  // Percorre a subárvore à direita
        }
    }
}