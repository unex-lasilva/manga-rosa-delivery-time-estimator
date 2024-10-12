package br.com.mangarosa.collections.traverses;

import java.util.ArrayList;
import java.util.List;

import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.Tree;
import br.com.mangarosa.collections.TreeTraversal;

public class PreOrderTraversal <T extends Comparable<T>> implements TreeTraversal<T> {

    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        preOrder(tree.root(), result);
        return result;
    }

    private void preOrder(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            // Adiciona o nó atual, depois visita a sua esquerda e por último a direita
            result.add(node.getValue());
            preOrder(node.getLeftChild(), result);
            preOrder(node.getRightChild(), result);        
        }   
    }
    
}
