package br.com.mangarosa.collections.ordenacao;

import java.util.ArrayList;
import java.util.List;

import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.Tree;
import br.com.mangarosa.collections.TreeTraversal;

public class PosOrderTraversal <T extends Comparable<T>>implements TreeTraversal<T>{

    // Lista de saída da árvore em pós ordem
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> resultado = new ArrayList<>();
        posOrdem(tree.root(), resultado);
        return resultado;
    }
    
    // Ordenação da pós ordem
    public void  posOrdem(BinaryTreeNode<T> noCurrent, List<T> resultado) {
        if (noCurrent != null){
            posOrdem(noCurrent.getLeftChild(), resultado);
            posOrdem(noCurrent.getRightChild(), resultado);
            resultado.add(noCurrent.getValue());
        }
    }
    
}
