package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class PostOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        traverseNode(tree.root(), result);
        return result;
    }

    private void traverseNode(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            traverseNode(node.getLeftChild(), result);  //percorre à esquerda
            traverseNode(node.getRightChild(), result); //percorre à direita

            result.add(node.getValue());
        }
    }
}
