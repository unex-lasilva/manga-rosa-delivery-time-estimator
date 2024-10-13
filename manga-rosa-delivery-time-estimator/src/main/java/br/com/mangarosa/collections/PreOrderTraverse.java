package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;
/*
Classe que implementa a interface TreeTraversal para realizar a travessia
de uma árvore binária na ordem "Pré-Ordem" (visita o nó atual, depois o nó à esquerda
e, por fim, o nó à direita) e retorna a lista resultante através do método traverse.
*/
public class PreOrderTraverse<T extends Comparable<T>> implements TreeTraversal<T> {

    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        traverseRec(tree.root(), result);
        return result;
    }

    private void traverseRec(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            result.add(node.getValue());
            traverseRec(node.getLeftChild(), result);
            traverseRec(node.getRightChild(), result);
        }
    }
}
