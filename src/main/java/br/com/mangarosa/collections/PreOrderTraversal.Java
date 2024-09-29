package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class PreOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T>{
    
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> resultado = new ArrayList<>();
        preOrder(tree.root(), resultado);
        return resultado;
    }

    private void preOrder(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            result.add(node.getValue());  // Raiz
            preOrder(node.getLeftChild(), resultado);  // Percorre a esquerda
            preOrder(node.getRightChild(), resultado);  // Percorre a direita
        }
    }
}