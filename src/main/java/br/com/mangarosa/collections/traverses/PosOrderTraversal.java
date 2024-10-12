package br.com.mangarosa.collections.traverses;

import java.util.ArrayList;
import java.util.List;

import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.Tree;
import br.com.mangarosa.collections.TreeTraversal;

public class PosOrderTraversal <T extends Comparable<T>> implements TreeTraversal<T> {

    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        posOrder(tree.root(), result);
        return result;
    }

    private void posOrder(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            // Adiciona o nó à esquerda do atual, depois sua direita, e por fim impreme o nó
            posOrder(node.getLeftChild(), result);
            posOrder(node.getRightChild(), result);        
            result.add(node.getValue());
        }   
    }
      
}
