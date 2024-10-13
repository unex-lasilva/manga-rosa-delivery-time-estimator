package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class PreOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        traverseNode(tree.root(), result);
        return result;
    }

    private void traverseNode(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {

            result.add(node.getValue());
            traverseNode(node.getLeftChild(), result);  //percorrendo à esquerda
            traverseNode(node.getRightChild(), result); //percorrendo à direita
        }
    }
}

