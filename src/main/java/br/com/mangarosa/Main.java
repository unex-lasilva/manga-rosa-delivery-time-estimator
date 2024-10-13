package br.com.mangarosa;

import br.com.mangarosa.impl.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Programa para manipular uma árvore binária.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree<Integer> tree = new BinaryTree<>();
        int option;

        do {
            displayMenu();
            option = getValidOption(scanner);

            switch (option) {
                case 1 -> addElement(scanner, tree);   // Adicionar elemento
                case 2 -> removeElement(scanner, tree); // Remover elemento
                case 3 -> checkElement(scanner, tree);  // Verificar elemento
                case 4 -> checkIfEmpty(tree);           // Verificar se está vazia
                case 5 -> displayTreeInOrder(tree);      // Exibir em ordem (in-order)
                case 6 -> displayTreePreOrder(tree);     // Exibir pré-ordem (pre-order)
                case 7 -> displayTreePostOrder(tree);    // Exibir pós-ordem (post-order)
                case 8 -> clearTree(tree);               // Limpar árvore
                case 9 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 9);

        scanner.close();
    }

    private static void displayMenu() {
        // Exibe o menu de opções
        System.out.println("\n========== Menu ==========");
        System.out.println("1. Inserir elemento");
        System.out.println("2. Remover elemento");
        System.out.println("3. Verificar elemento");
        System.out.println("4. Verificar se vazia");
        System.out.println("5. Exibir (in-order)");
        System.out.println("6. Exibir (pre-order)");
        System.out.println("7. Exibir (post-order)");
        System.out.println("8. Limpar árvore");
        System.out.println("9. Sair");
        System.out.println("==========================");
    }

    private static int getValidOption(Scanner scanner) {
        // Lê a opção do usuário e trata erros
        int option = -1;
        try {
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Erro: insira um número válido.");
            scanner.nextLine();
        }
        return option;
    }

    private static void addElement(Scanner scanner, BinaryTree<Integer> tree) {
        // Adiciona um valor à árvore
        try {
            System.out.print("Valor a inserir: ");
            int value = scanner.nextInt();
            tree.add(value);
            System.out.println("Valor " + value + " inserido.");
        } catch (InputMismatchException e) {
            System.out.println("Erro: insira um número inteiro.");
            scanner.nextLine();
        }
    }

    private static void removeElement(Scanner scanner, BinaryTree<Integer> tree) {
        // Remove um valor da árvore
        try {
            System.out.print("Valor a remover: ");
            int value = scanner.nextInt();
            if (tree.contains(value)) {
                tree.remove(value);
                System.out.println("Valor " + value + " removido.");
            } else {
                System.out.println("Valor não encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: insira um número inteiro.");
            scanner.nextLine();
        }
    }

    private static void checkElement(Scanner scanner, BinaryTree<Integer> tree) {
        // Verifica se um valor está na árvore
        try {
            System.out.print("Valor a verificar: ");
            int value = scanner.nextInt();
            System.out.println(tree.contains(value) ? "Valor encontrado." : "Valor não encontrado.");
        } catch (InputMismatchException e) {
            System.out.println("Erro: insira um número inteiro.");
            scanner.nextLine();
        }
    }

    private static void checkIfEmpty(BinaryTree<Integer> tree) {
        // Verifica se a árvore está vazia
        System.out.println(tree.isEmpty() ? "A árvore está vazia." : "A árvore não está vazia.");
    }

    private static void displayTreeInOrder(BinaryTree<Integer> tree) {
        // Exibe a árvore em ordem (in-order)
        InOrderTraversal<Integer> traversal = new InOrderTraversal<>();
        System.out.println("In-order: " + traversal.traverse(tree));
    }

    private static void displayTreePreOrder(BinaryTree<Integer> tree) {
        // Exibe a árvore em pré-ordem (pre-order)
        PreOrderTraversal<Integer> traversal = new PreOrderTraversal<>();
        System.out.println("Pre-order: " + traversal.traverse(tree));
    }

    private static void displayTreePostOrder(BinaryTree<Integer> tree) {
        // Exibe a árvore em pós-ordem (post-order)
        PostOrderTraversal<Integer> traversal = new PostOrderTraversal<>();
        System.out.println("Post-order: " + traversal.traverse(tree));
    }

    private static void clearTree(BinaryTree<Integer> tree) {
        // Limpa a árvore
        tree.clear();
        System.out.println("Árvore limpa.");
    }
}
