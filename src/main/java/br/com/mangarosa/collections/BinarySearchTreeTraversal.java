package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;


public class BinarySearchTreeTraversal<T extends Comparable<T>> implements TreeTraversal<T> {

    /**
     * Realiza a travessia In-Order da árvore.
     * Este método é a implementação padrão da interface TreeTraversal.
     *
     * @param tree A árvore a ser percorrida.
     * @return Uma lista contendo os elementos da árvore em ordem In-Order.
     */
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        inOrderTraversal(tree.root(), result);
        return result;
    }

    /**
     * Realiza a travessia In-Order da árvore.
     *
     * @param node   O nó atual da árvore.
     * @param result A lista onde os elementos percorridos serão adicionados.
     */
    private void inOrderTraversal(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            inOrderTraversal(node.getLeftChild(), result);
            result.add(node.getValue());
            inOrderTraversal(node.getRightChild(), result);
        }
    }

    /**
     * Realiza a travessia Pre-Order da árvore.
     *
     * @param tree A árvore a ser percorrida.
     * @return Uma lista contendo os elementos da árvore em ordem Pre-Order.
     */
    public List<T> preOrderTraversal(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        preOrderTraversal(tree.root(), result);
        return result;
    }

    /**
     * Método auxiliar para a travessia Pre-Order.
     *
     * @param node   O nó atual da árvore.
     * @param result A lista onde os elementos percorridos serão adicionados.
     */
    private void preOrderTraversal(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            result.add(node.getValue());
            preOrderTraversal(node.getLeftChild(), result);
            preOrderTraversal(node.getRightChild(), result);
        }
    }

    /**
     * Realiza a travessia Post-Order da árvore.
     *
     * @param tree A árvore a ser percorrida.
     * @return Uma lista contendo os elementos da árvore em ordem Post-Order.
     */
    public List<T> postOrderTraversal(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        postOrderTraversal(tree.root(), result);
        return result;
    }

    /**
     * Método auxiliar para a travessia Post-Order.
     *
     * @param node   O nó atual da árvore.
     * @param result A lista onde os elementos percorridos serão adicionados.
     */
    private void postOrderTraversal(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            postOrderTraversal(node.getLeftChild(), result);
            postOrderTraversal(node.getRightChild(), result);
            result.add(node.getValue());
        }
    }
}
