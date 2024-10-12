package br.com.mangarosa.collections.traverses;

import java.util.ArrayList;
import java.util.List;

import br.com.mangarosa.collections.*;

public class InOrderTraversal <T extends Comparable<T>> implements TreeTraversal<T> {

    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        inOrder(tree.root(), result);
        return result;
    }

    private void inOrder(BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            // Adiciona o nó à esquerda do atual, depois o próprio nó, e por fim o nó à sua direita
            inOrder(node.getLeftChild(), result);
            result.add(node.getValue());
            inOrder(node.getRightChild(), result);
        }   
    }
    
}
