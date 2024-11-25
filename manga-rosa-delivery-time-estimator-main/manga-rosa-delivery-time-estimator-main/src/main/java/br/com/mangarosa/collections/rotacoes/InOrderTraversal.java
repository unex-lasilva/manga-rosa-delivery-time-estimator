package br.com.mangarosa.collections.rotacoes;

import br.com.mangarosa.collections.TreeTraversal;
import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.Tree;
import java.util.List;
import java.util.ArrayList;

public class InOrderTraversal<T extends Comparable <T>> implements  TreeTraversal<T> {

    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        inOrderMethod(tree.root(), result);
        return result;
    }
    //Metodo para fazer a travessia in order na arvore
    public void inOrderMethod(BinaryTreeNode<T> node, List<T> result) {
        if(node != null) {
            inOrderMethod(node.getLeftChild(), result); // Visita o filho a esquerda
            result.add(node.getValue()); // Adiciona o valor do nó atual
            inOrderMethod(node.getRightChild(), result); // Visita o filho da direita
        }
    }

}