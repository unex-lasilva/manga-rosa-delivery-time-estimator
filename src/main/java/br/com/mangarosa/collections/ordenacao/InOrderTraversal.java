package br.com.mangarosa.collections.ordenacao;

import java.util.ArrayList;
import java.util.List;

import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.Tree;
import br.com.mangarosa.collections.TreeTraversal;

public class InOrderTraversal <T extends Comparable<T>>implements TreeTraversal<T>{

   // Lista de saída da árvore em ordem
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> resultado = new ArrayList<>();
        inOrder(tree.root(), resultado);
        return resultado;
    }
    
    // Ordenação da ordem
    public void  inOrder(BinaryTreeNode<T> noCurrent, List<T> resultado) {
        if (noCurrent != null){
            inOrder(noCurrent.getLeftChild(), resultado);
            resultado.add(noCurrent.getValue());
            inOrder(noCurrent.getRightChild(), resultado);
            
        }
    }
    
}
