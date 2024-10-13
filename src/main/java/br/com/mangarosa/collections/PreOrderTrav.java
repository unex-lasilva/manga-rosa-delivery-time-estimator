package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class PreOrderTrav<T extends Comparable<T>> implements TreeTraversal<T>{

    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> resultado = new ArrayList<>();
        preOrder(tree.root(), resultado);
        return resultado;
    }

    private void preOrder(BinaryTreeNode<T> current, List<T> resultado) {
        if (current != null) {
            resultado.add(current.getValue());  // Raiz
            preOrder(current.getLeftChild(), resultado);  // Percorre a esquerda
            preOrder(current.getRightChild(), resultado);  // Percorre a direita
        }
    }
}
