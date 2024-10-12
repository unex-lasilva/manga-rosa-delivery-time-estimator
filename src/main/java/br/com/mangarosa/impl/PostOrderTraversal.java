package br.com.mangarosa.impl;

import br.com.mangarosa.collections.Tree;
import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.TreeTraversal;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface TreeTraversal que realiza a travessia de uma árvore binária
 * utilizando o algoritmo post-order (pós-ordem), onde os nós filhos são visitados antes da raiz.
 */
public class PostOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {

    /**
     * Percorre a árvore usando o algoritmo post-order e retorna uma lista contendo os valores dos nós.
     */
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        postOrder(tree.root(), result); //
        return result;
    }

    /**
     * Método auxiliar que realiza a travessia recursiva post-order de um nó específico.
     * Se o nó for nulo, a recursão termina.
     */
    private void postOrder(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            postOrder(node.getLeftChild(), result);
            postOrder(node.getRightChild(), result);
            result.add(node.getValue());
        }
    }
}
