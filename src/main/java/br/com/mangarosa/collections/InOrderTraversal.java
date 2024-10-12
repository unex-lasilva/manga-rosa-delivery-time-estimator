package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

// implementação de um percurso em ordem para uma árvore binária
public class InOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {

    // realiza o percurso em ordem e retorna uma lista com os elementos da árvore
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> elements = new ArrayList<>();
        inOrder(tree.root(), elements);
        return elements;
    }

    // metodo auxiliar que percorre a árvore em ordem e adiciona os elementos na lista
    private void inOrder(BinaryTreeNode<T> node, List<T> elements) {
        if (node != null) {
            inOrder(node.getLeftChild(), elements); // percorre a subárvore esquerda
            elements.add(node.getValue()); // adiciona o valor do nó atual
            inOrder(node.getRightChild(), elements); // percorre a subárvore direita
        }
    }
}