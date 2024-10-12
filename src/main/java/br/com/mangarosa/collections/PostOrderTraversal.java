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
            postOrder(node.getLeftChild(), result);
            postOrder(node.getRightChild(), result);
            result.add(node.getValue());
        }
    }
}
