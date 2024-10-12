package br.com.mangarosa;

import br.com.mangarosa.collections.BinaryTree;
import br.com.mangarosa.collections.InOrderTraversal;
import br.com.mangarosa.collections.PreOrderTraversal;
import br.com.mangarosa.collections.PostOrderTraversal;
import br.com.mangarosa.collections.BinaryTreeNode;
import java.util.List;
import java.util.Scanner;

// classe principal que gerencia o menu e as operações da árvore binária
public class Main {
    private static BinaryTree<Integer> tree = new BinaryTree<>(); // árvore binária de inteiros
    private static Scanner scanner = new Scanner(System.in); // scanner para entrada de dados

    // metodo principal que exibe o menu e lida com as opções do usuário
    public static void main(String[] args) {
        int option;
        do {
            showMenu();
            option = scanner.nextInt();
            handleOption(option);
        } while (option != 0);
        System.out.println("Encerrando o programa.");
    }

    // exibe o menu com as opções disponíveis
    private static void showMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Adicionar elemento");
        System.out.println("2. Remover elemento");
        System.out.println("3. Verificar se contém elemento");
        System.out.println("4. Exibir In-Order");
        System.out.println("5. Exibir Pre-Order");
        System.out.println("6. Exibir Post-Order");
        System.out.println("7. Contar elementos");
        System.out.println("8. Calcular níveis da árvore");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // lida com a opção escolhida pelo usuário e executa a ação correspondente
    private static void handleOption(int option) {
        switch (option) {
            case 1:
                addElement();
                break;
            case 2:
                removeElement();
                break;
            case 3:
                containsElement();
                break;
            case 4:
                displayInOrder();
                break;
            case 5:
                displayPreOrder();
                break;
            case 6:
                displayPostOrder();
                break;
            case 7:
                countElements();
                break;
            case 8:
                calculateLevels();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    // adiciona um novo elemento à árvore
    private static void addElement() {
        System.out.print("Digite o elemento para adicionar: ");
        int value = scanner.nextInt();
        tree.add(value);
        System.out.println("Elemento " + value + " adicionado.");
    }

    // remove um elemento da árvore
    private static void removeElement() {
        System.out.print("Digite o elemento para remover: ");
        int value = scanner.nextInt();
        tree.remove(value);
        System.out.println("Elemento " + value + " removido.");
    }

    // verifica se um elemento está na árvore
    private static void containsElement() {
        System.out.print("Digite o elemento para verificar: ");
        int value = scanner.nextInt();
        if (tree.contains(value)) {
            System.out.println("O elemento " + value + " está na árvore.");
        } else {
            System.out.println("O elemento " + value + " não está na árvore.");
        }
    }

    // exibe os elementos da árvore em ordem (in-order)
    private static void displayInOrder() {
        InOrderTraversal<Integer> traversal = new InOrderTraversal<>();
        List<Integer> result = traversal.traverse(tree);
        System.out.println("In-Order: " + result);
    }

    // exibe os elementos da árvore em pré-ordem (pre-order)
    private static void displayPreOrder() {
        PreOrderTraversal<Integer> traversal = new PreOrderTraversal<>();
        List<Integer> result = traversal.traverse(tree);
        System.out.println("Pre-Order: " + result);
    }

    // exibe os elementos da árvore em pós-ordem (post-order)
    private static void displayPostOrder() {
        PostOrderTraversal<Integer> traversal = new PostOrderTraversal<>();
        List<Integer> result = traversal.traverse(tree);
        System.out.println("Post-Order: " + result);
    }

    // conta o número de elementos na árvore
    private static void countElements() {
        System.out.println("Número de elementos na árvore: " + tree.size());
    }

    // calcula e exibe o número de níveis na árvore
    private static void calculateLevels() {
        System.out.println("Número de níveis na árvore: " + getTreeHeight(tree.root()));
    }

    // calcula a altura da árvore a partir de um nó
    private static int getTreeHeight(BinaryTreeNode<Integer> node) {
        if (node == null) return 0;
        int leftHeight = getTreeHeight(node.getLeftChild());
        int rightHeight = getTreeHeight(node.getRightChild());
        return Math.max(leftHeight, rightHeight) + 1;
    }
}