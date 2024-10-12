package br.com.mangarosa.impl;

import br.com.mangarosa.collections.Tree;
import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.TreeTraversal;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface TreeTraversal que realiza a travessia de uma árvore binária
 * utilizando o algoritmo in-order (em ordem), onde o nó esquerdo é visitado primeiro,
 * seguido pelo nó raiz e, por último, o nó direito.
 */
public class InOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {

    /**
     * Percorre a árvore usando o algoritmo in-order e retorna uma lista contendo os valores dos nós.
     */
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        inOrder(tree.root(), result);
        return result;
    }

    /**
     * Método auxiliar que realiza a travessia recursiva in-order de um nó específico.
     * Se o nó for nulo, a recursão termina.
     */
    private void inOrder(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            inOrder(node.getLeftChild(), result);
            result.add(node.getValue());
            inOrder(node.getRightChild(), result);
        }
    }
}
