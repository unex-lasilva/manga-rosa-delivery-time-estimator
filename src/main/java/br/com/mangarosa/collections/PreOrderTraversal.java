package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

// implementação de um percurso pré-ordem (pre-order) para uma árvore binária
public class PreOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {

    // realiza o percurso pré-ordem e retorna uma lista com os elementos da árvore
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> elements = new ArrayList<>();
        preOrder(tree.root(), elements);
        return elements;
    }

    // metodo auxiliar que percorre a árvore em pré-ordem e adiciona os elementos na lista
    private void preOrder(BinaryTreeNode<T> node, List<T> elements) {
        if (node != null) {
            elements.add(node.getValue()); // adiciona o valor do nó atual
            preOrder(node.getLeftChild(), elements); // percorre a subárvore esquerda
            preOrder(node.getRightChild(), elements); // percorre a subárvore direita
        }
    }
}
