package br.com.mangarosa.collections.rotacoes;


import br.com.mangarosa.collections.TreeTraversal;
import br.com.mangarosa.collections.BinaryTreeNode;
import br.com.mangarosa.collections.Tree;
import java.util.List;
import java.util.ArrayList;

public class PosOrderTraversal <T extends Comparable <T>> implements  TreeTraversal<T> {

     @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        posOrderMethod(tree.root(), result);
        return result;
    }
    //Metodo para a travessia pos ordem na arvore
    public void posOrderMethod(BinaryTreeNode<T> node, List<T> result) {
        if(node != null) {
            posOrderMethod(node.getLeftChild(), result); // visita o filho a esquerda
            posOrderMethod(node.getRightChild(), result); // visita o filho a direita
            result.add(node.getValue()); // adiciona o valor do nó atual
        }
    }

}
