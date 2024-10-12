package br.com.mangarosa;

import br.com.mangarosa.collections.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    //Menu interativo para testar as funcionalidades do sistema
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BalancedTree tree = new BalancedTree();
        int option;

        do {
            System.out.println("========== Menu ==========");
            System.out.println("1. Inserir um valor");
            System.out.println("2. Remover um valor");
            System.out.println("3. Verificar se um valor está na árvore");
            System.out.println("4. Verificar se a árvore está vazia");
            System.out.println("5. Exibir a árvore (in-order)");
            System.out.println("6. Exibir a árvore (pre-order)");
            System.out.println("7. Exibir a árvore (post-order)");
            System.out.println("8. Limpar a árvore");
            System.out.println("9. Sair");
            System.out.println("==========================");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Digite o valor a ser inserido: ");
                    int valueToAdd = scanner.nextInt();
                    tree.add(valueToAdd);
                    System.out.println("Valor " + valueToAdd + " inserido.");
                    break;

                case 2:
                    System.out.print("Digite o valor a ser removido: ");
                    int valueToRemove = scanner.nextInt();
                    if (tree.contains(valueToRemove)) {
                        tree.remove(valueToRemove);
                        System.out.println("Valor " + valueToRemove + " removido.");
                    } else {
                        System.out.println("Valor não encontrado na árvore.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o valor a ser verificado: ");
                    int valueToCheck = scanner.nextInt();
                    if (tree.contains(valueToCheck)) {
                        System.out.println("O valor " + valueToCheck + " está na árvore.");
                    } else {
                        System.out.println("O valor " + valueToCheck + " não está na árvore.");
                    }
                    break;

                case 4:
                    if (tree.isEmpty()) {
                        System.out.println("A árvore está vazia.");
                    } else {
                        System.out.println("A árvore não está vazia.");
                    }
                    break;

                case 5:
                    System.out.println("Exibindo árvore (in-order):");
                    InOrderTraversal inOrderTraversal = new InOrderTraversal();
                    List<Integer> inOrder = inOrderTraversal.traverse(tree);
                    System.out.println(inOrder);
                    break;

                case 6:
                    System.out.println("Exibindo árvore (pre-order):");
                    PreOrderTraversal preOrderTraversal = new PreOrderTraversal();
                    List<Integer> preOrder = preOrderTraversal.traverse(tree);
                    System.out.println(preOrder);
                    break;

                case 7:
                    System.out.println("Exibindo árvore (post-order):");
                    PostOrderTraversal postOrderTraversal = new PostOrderTraversal();
                    List<Integer> postOrder = postOrderTraversal.traverse(tree);
                    System.out.println(postOrder);
                    break;

                case 8:
                    tree.clear();
                    System.out.println("A árvore foi limpa.");
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
}
