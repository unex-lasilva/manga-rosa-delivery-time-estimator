package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

// implementação de um percurso pós-ordem (post-order) para uma árvore binária
public class PostOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {

    // realiza o percurso pós-ordem e retorna uma lista com os elementos da árvore
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> elements = new ArrayList<>();
        postOrder(tree.root(), elements);
        return elements;
    }

    // metodo auxiliar que percorre a árvore em pós-ordem e adiciona os elementos na lista
    private void postOrder(BinaryTreeNode<T> node, List<T> elements) {
        if (node != null) {
            postOrder(node.getLeftChild(), elements); // percorre a subárvore esquerda
            postOrder(node.getRightChild(), elements); // percorre a subárvore direita
            elements.add(node.getValue()); // adiciona o valor do nó atual
        }
    }
}
