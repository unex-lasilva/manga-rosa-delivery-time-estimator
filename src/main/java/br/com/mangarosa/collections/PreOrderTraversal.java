package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class PreOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {

    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        preOrder(tree.root(), result);
        return result;
    }

    private void preOrder(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            result.add(node.getValue());
            preOrder(node.getLeftChild(), result);
            preOrder(node.getRightChild(), result);
        }
    }
}
