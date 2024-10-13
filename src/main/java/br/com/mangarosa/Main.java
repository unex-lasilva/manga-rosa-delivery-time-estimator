package br.com.mangarosa;

import br.com.mangarosa.collections.BinarySearchTreeTraversal;
import br.com.mangarosa.collections.BinaryTree;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        BinarySearchTreeTraversal<Integer> traversal = new BinarySearchTreeTraversal<>();

        // Travessia In-Order
        List<Integer> inOrder = traversal.traverse(tree);
        System.out.println("In-Order: " + inOrder);

        // Travessia Pre-Order
        List<Integer> preOrder = traversal.preOrderTraversal(tree);
        System.out.println("Pre-Order: " + preOrder);

        // Travessia Post-Order
        List<Integer> postOrder = traversal.postOrderTraversal(tree);
        System.out.println("Post-Order: " + postOrder);
    }
}
