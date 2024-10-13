package br.com.mangarosa.collections.ordenacao;

import java.util.ArrayList;
import java.util.List;


import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.Tree;
import br.com.mangarosa.collections.TreeTraversal;

public class PreOrderTraversal <T extends Comparable<T>>implements TreeTraversal<T> {

    // Lista de saída da árvore em pré ordem
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> resultado = new ArrayList<>();
        preOrdem(tree.root(), resultado);
        return resultado;
    }
    
    // Ordenação da pré ordem
    public void  preOrdem(BinaryTreeNode<T> noCurrent, List<T> resultado) {
        if (noCurrent != null){
            resultado.add(noCurrent.getValue());
            preOrdem(noCurrent.getLeftChild(), resultado);
            preOrdem(noCurrent.getRightChild(), resultado);
        }
    }
}