package br.com.mangarosa.collections.rotacoes;

import br.com.mangarosa.collections.TreeTraversal;
import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.Tree;
import java.util.List;
import java.util.ArrayList;


public class PreOrderTraversal <T extends Comparable <T>> implements TreeTraversal<T> {

    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        preOrderMethod(tree.root(), result);
        return result;
    }

    // Metodo para a travessia pre ordem na arvoe
    public void preOrderMethod(BinaryTreeNode<T> node, List<T> result) {
        if(node != null) {
            result.add(node.getValue()); // Adiciona  o valor do nó atual
            preOrderMethod(node.getLeftChild(), result); // Visita o filho a esquerda
            preOrderMethod(node.getRightChild(), result); // Visita o filho a direita
        }
    }

}
