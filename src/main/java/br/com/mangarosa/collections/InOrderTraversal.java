package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class InOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        if (tree != null) { //verificando se a arvore é nula
            traverseNode(tree.root(), result);
        }
        return result;
    }

    private void traverseNode(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            traverseNode(node.getLeftChild(), result);  //percorrendo à esquerda

            result.add(node.getValue());
            traverseNode(node.getRightChild(), result); //percorrendo à direita
        }
    }
}
