package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

/*
Esta classe implementa a interface TreeTraversal, gerando uma lista contendo todos os elementos da árvore
na sequência "InOrder" (esquerda, raiz, direita), e retornando essa lista pelo método traverse.
*/

public class InOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T>
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
        // Método recursivo que percorre a árvore na ordem "InOrder"
        if (node != null)
        {
            traverseRec(node.getLeftChild(), result);  // Visita a subárvore esquerda
            result.add(node.getValue());  // Adiciona o valor do nó atual
            traverseRec(node.getRightChild(), result); // Visita a subárvore direita
        }
    }

    @Override
    public List<T> ordenacao(Tree<T> tree) {
        // Método não implementado para ordenação
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
