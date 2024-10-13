package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

/*
Classe que implementa a interface TreeTraversal para criar uma lista com todos os valores da árvore
na ordem "PostOrder" (esquerda, direita, eu) e retornar ela pelo método traverse
 */

public class PostOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T>
{
    @Override
    public List<T> traverse(Tree<T> tree)
    {
        List<T> result = new ArrayList<>();
        traverseRec(tree.root(), result);
        return result;
    }

    private void traverseRec(BinaryTreeNode<T> node, List<T> result)
    {
        if (node != null)
        {
            traverseRec(node.getLeftChild(), result);
            traverseRec(node.getRightChild(), result);
            result.add(node.getValue());
        }
    }
}
