package br.com.mangarosa;

import br.com.mangarosa.collections.*;
import br.com.mangarosa.model.Rule;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final BinaryTree<Rule> tree = new BinaryTree<>();
    private static final TreeTraversal<Rule> preOrderTraversal = new PreOrderTraversal<>();
    private static final TreeTraversal<Rule> inOrderTraversal = new InOrderTraversal<>();
    private static final TreeTraversal<Rule> postOrderTraversal = new PostOrderTraversal<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n----- Menu -----");
            System.out.println("1. Adicionar Regra");
            System.out.println("2. Remover Regra");
            System.out.println("3. Verificar se contém Regra");
            System.out.println("4. Verificar se a árvore está vazia");
            System.out.println("5. Verificar se um nó é folha");
            System.out.println("6. Percorrer Pré-Ordem");
            System.out.println("7. Percorrer Em-Ordem");
            System.out.println("8. Percorrer Pós-Ordem");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> addRule(scanner);
                case 2 -> removeRule(scanner);
                case 3 -> containsRule(scanner);
                case 4 -> checkIfEmpty();
                case 5 -> checkIfLeaf(scanner);
                case 6 -> traverseTree(preOrderTraversal);
                case 7 -> traverseTree(inOrderTraversal);
                case 8 -> traverseTree(postOrderTraversal);
                case 9 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 9);
    }

    private static void addRule(Scanner scanner) {
        System.out.print("Digite o ID da Regra para adicionar: ");
        int ruleId = scanner.nextInt();
        tree.add(new Rule(ruleId));
        System.out.println("Regra adicionada.");
    }

    private static void removeRule(Scanner scanner) {
        System.out.print("Digite o ID da Regra para remover: ");
        int ruleId = scanner.nextInt();
        tree.remove(new Rule(ruleId));
        System.out.println("Regra removida.");
    }

    private static void containsRule(Scanner scanner) {
        System.out.print("Digite o ID da Regra para verificar: ");
        int ruleId = scanner.nextInt();
        boolean contains = tree.contains(new Rule(ruleId));
        System.out.println("A árvore " + (contains ? "contém" : "não contém") + " a Regra.");
    }

    private static void checkIfEmpty() {
        boolean isEmpty = tree.isEmpty();
        System.out.println("A árvore " + (isEmpty ? " está vazia" : "não está vazia") + ".");
    }

    private static void checkIfLeaf(Scanner scanner) {
        System.out.print("Digite o ID da Regra para verificar se é folha: ");
        int ruleId = scanner.nextInt();
        boolean isLeaf = tree.isLeaf(new Rule(ruleId));
        System.out.println("O nó " + (isLeaf ? "é" : "não é") + " uma folha.");
    }

    private static void traverseTree(TreeTraversal<Rule> traversal) {
        List<Rule> result = traversal.traverse(tree);
        System.out.println("Resultado da travessia: " + result);
    }
}
