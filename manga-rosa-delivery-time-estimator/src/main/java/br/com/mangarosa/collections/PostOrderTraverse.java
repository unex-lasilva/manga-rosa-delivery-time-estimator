package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;
/*
Classe que implementa a interface TreeTraversal para realizar a travessia
de uma árvore binária na ordem "Pós-Ordem" (visita o nó à esquerda, depois o nó à direita
e, por fim, o nó atual) e retorna a lista resultante através do método traverse.
*/
public class PostOrderTraverse<T extends Comparable<T>> implements TreeTraversal<T> {

    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        traverseRec(tree.root(), result);
        return result;
    }

    private void traverseRec(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            traverseRec(node.getLeftChild(), result);
            traverseRec(node.getRightChild(), result);
            result.add(node.getValue());
        }
    }
}
