package br.com.mangarosa;

import br.com.mangarosa.impl.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal que executa o programa de manipulação de uma árvore binária.
 * O programa permite ao usuário inserir, remover, verificar elementos e exibir a árvore
 * em várias ordens de travessia (in-order, pre-order, post-order).
 */
public class Main {

    public static void main(String[] args) {
        displayTreeImage();

        Scanner scanner = new Scanner(System.in);
        BinaryTree<Integer> tree = new BinaryTree<>();
        int option = 0;

        do {
            displayMenu();
            option = getValidOption(scanner);

            switch (option) {
                case 1:
                    addElement(scanner, tree);
                    break;

                case 2:
                    removeElement(scanner, tree);
                    break;

                case 3:
                    checkElement(scanner, tree);
                    break;

                case 4:
                    checkIfEmpty(tree);
                    break;

                case 5:
                    displayTreeInOrder(tree);
                    break;

                case 6:
                    displayTreePreOrder(tree);
                    break;

                case 7:
                    displayTreePostOrder(tree);
                    break;

                case 8:
                    clearTree(tree);
                    break;

                case 9:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (option != 9);

        scanner.close();
    }

    /**
     * Exibe uma imagem ASCII de árvore junto com uma mensagem de boas-vindas.
     */
    private static void displayTreeImage() {
        System.out.println("\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⡰⡇⠁⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠰⢾⠇⠨⡦⠀⠂⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⡀⠀⢈⣹⠜⠻⢾⠃⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠁⢠⣿⡵⠾⣻⣶⠿⠦⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⢀⠀⢀⣠⣮⣦⡶⠻⠛⢶⣄⡀⠁⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⢀⣽⠏⠁⣠⣂⣦⣈⣝⣦⣄⠀⠈⠁⠀\n"
                + "⠀⠀⠀⠀⠁⣠⣾⣵⣾⣾⠟⡙⠟⠿⣍⡉⠀⠀⠆⠀⠀\n"
                + "⠀⠰⠀⠀⠄⣠⣶⣾⣭⡶⠟⠻⣶⡰⣜⣳⣦⣄⠀⡀⠀\n"
                + "⠀⠀⠀⢀⣠⣴⣿⣋⡴⣪⠎⣄⡙⠻⠿⣯⣉⠉⠀⠀⠀\n"
                + "⠀⠂⠀⢀⣉⡭⢿⡛⣛⣵⠎⠀⠙⢾⣶⣦⣭⣷⣦⠐⠀\n"
                + "⠀⠀⢈⣙⣿⡿⠿⠟⣋⢅⡄⡄⡐⣄⢤⣉⠷⢦⣄⣀⠠\n"
                + "⠐⠾⠿⢽⣷⡶⠶⡿⣓⣭⣾⣿⢷⣬⣓⢿⠿⠿⣯⣉⣁\n"
                + "⠀⠀⠀⠉⠉⠉⠛⠛⠉⢀⣿⢿⡀⠙⠋⠓⠿⠿⠏⠉⠉\n"
                + "⠀⠀⠀⠀⠀⠀⠠⠤⠶⠾⢿⡯⠷⠶⠤⠄⠀⠀⠀\n"
        );
        System.out.println(""
                + "╔═════════════════════════════════════════════════════════╗\n"
                + "║          Bem-vindo ao Sistema de Árvore Binária         ║\n"
                + "║  Por favor, siga as instruções para manipular a árvore: ║\n"
                + "╚═════════════════════════════════════════════════════════╝\n"
        );
    }

    /**
     * Exibe o menu de opções para o usuário.
     */
    private static void displayMenu() {
        System.out.println("\n========== Menu ==========");
        System.out.println("1. Inserir um elemento");
        System.out.println("2. Remover um elemento");
        System.out.println("3. Verificar se um elemento está na árvore");
        System.out.println("4. Verificar se a árvore está vazia");
        System.out.println("5. Exibir a árvore (in-order)");
        System.out.println("6. Exibir a árvore (pre-order)");
        System.out.println("7. Exibir a árvore (post-order)");
        System.out.println("8. Limpar a árvore");
        System.out.println("9. Sair");
        System.out.println("==========================");
    }

    /**
     * Obtém uma opção válida do usuário e lida com possíveis erros de entrada.
     */
    private static int getValidOption(Scanner scanner) {
        int option = -1;
        try {
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Erro: Por favor, insira um número válido.");
            scanner.nextLine();
        }
        return option;
    }

    /**
     * Adiciona um elemento à árvore, validando a entrada do usuário.
     */
    private static void addElement(Scanner scanner, BinaryTree<Integer> tree) {
        try {
            System.out.print("Digite o valor a ser inserido: ");
            int value = scanner.nextInt();
            tree.add(value);
            System.out.println("Valor " + value + " inserido.");
        } catch (InputMismatchException e) {
            System.out.println("Erro: Valor inválido. Por favor, insira um número inteiro.");
            scanner.nextLine();
        }
    }

    /**
     * Remove um elemento da árvore, validando a entrada do usuário.
     */
    private static void removeElement(Scanner scanner, BinaryTree<Integer> tree) {
        try {
            System.out.print("Digite o valor a ser removido: ");
            int value = scanner.nextInt();
            if (tree.contains(value)) {
                tree.remove(value);
                System.out.println("Valor " + value + " removido.");
            } else {
                System.out.println("Valor não encontrado na árvore.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Valor inválido. Por favor, insira um número inteiro.");
            scanner.nextLine();
        }
    }

    /**
     * Verifica se um valor está presente na árvore.
     */
    private static void checkElement(Scanner scanner, BinaryTree<Integer> tree) {
        try {
            System.out.print("Digite o valor a ser verificado: ");
            int value = scanner.nextInt();
            if (tree.contains(value)) {
                System.out.println("O valor " + value + " está na árvore.");
            } else {
                System.out.println("O valor " + value + " não está na árvore.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Valor inválido. Por favor, insira um número inteiro.");
            scanner.nextLine();
        }
    }

    /**
     * Verifica se a árvore está vazia.
     */
    private static void checkIfEmpty(BinaryTree<Integer> tree) {
        if (tree.isEmpty()) {
            System.out.println("A árvore está vazia.");
        } else {
            System.out.println("A árvore não está vazia.");
        }
    }

    /**
     * Exibe a árvore em ordem (in-order).
     */
    private static void displayTreeInOrder(BinaryTree<Integer> tree) {
        System.out.println("Exibindo árvore (in-order):");
        InOrderTraversal<Integer> inOrderTraversal = new InOrderTraversal<>();
        List<Integer> inOrder = inOrderTraversal.traverse(tree);
        System.out.println(inOrder);
    }

    /**
     * Exibe a árvore em pré-ordem (pre-order).
     */
    private static void displayTreePreOrder(BinaryTree<Integer> tree) {
        System.out.println("Exibindo árvore (pre-order):");
        PreOrderTraversal<Integer> preOrderTraversal = new PreOrderTraversal<>();
        List<Integer> preOrder = preOrderTraversal.traverse(tree);
        System.out.println(preOrder);
    }

    /**
     * Exibe a árvore em pós-ordem (post-order).
     */
    private static void displayTreePostOrder(BinaryTree<Integer> tree) {
        System.out.println("Exibindo árvore (post-order):");
        PostOrderTraversal<Integer> postOrderTraversal = new PostOrderTraversal<>();
        List<Integer> postOrder = postOrderTraversal.traverse(tree);
        System.out.println(postOrder);
    }

    /**
     * Limpa a árvore, removendo todos os seus elementos.
     */
    private static void clearTree(BinaryTree<Integer> tree) {
        tree.clear();
        System.out.println("A árvore foi limpa.");
    }
}