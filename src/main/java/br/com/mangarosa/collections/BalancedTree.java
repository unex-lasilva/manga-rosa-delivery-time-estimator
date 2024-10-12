package br.com.mangarosa.collections;

import br.com.mangarosa.impl.BinaryTree;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação de uma árvore binária balanceada, baseada em rotações para manter o equilíbrio
 * após inserções e remoções. Esta classe estende a funcionalidade da BinaryTree e inclui métodos
 * de balanceamento para garantir que a árvore permaneça balanceada.
 */
public class BalancedTree<T extends Comparable<T>> extends BinaryTree<T> {

    private int size = 0;

    /**
     * Adiciona um valor à árvore binária balanceada, mantendo o balanceamento após a inserção.
     */
    @Override
    public void add(T value) {
        setRoot(addRecursive(getRoot(), value));
        size++;
    }

    /**
     * Remove um valor da árvore binária balanceada, mantendo o balanceamento após a remoção.
     */
    @Override
    public void remove(T value) {
        if (contains(value)) {
            setRoot(removeRecursive(getRoot(), value));
            size--;
        }
    }

    /**
     * Retorna o número de elementos na árvore.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Método auxiliar que insere um valor na árvore e aplica o balanceamento, se necessário.
     */
    private BinaryTreeNode<T> addRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) {
            return new BinaryTreeNode<>(value);
        }

        // Insere o valor recursivamente na subárvore esquerda ou direita
        if (value.compareTo(current.getValue()) < 0) {
            current.setLeftChild(addRecursive(current.getLeftChild(), value));
        } else if (value.compareTo(current.getValue()) > 0) {
            current.setRightChild(addRecursive(current.getRightChild(), value));
        }

        return balanceTree(current);
    }

    /**
     * Método auxiliar que remove um valor da árvore e aplica o balanceamento, se necessário.
     */
    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) {
            return null;
        }

        // Encontra o valor a ser removido e executa o processo de remoção
        if (value.equals(current.getValue())) {
            // Casos de remoção (sem filhos, com um filho ou com dois filhos)
            if (current.getLeftChild() == null && current.getRightChild() == null) {
                return null;
            }
            if (current.getRightChild() == null) {
                return current.getLeftChild();
            }
            if (current.getLeftChild() == null) {
                return current.getRightChild();
            }
            // Substitui o valor do nó removido pelo menor valor da subárvore direita
            T smallestValue = findSmallestValue(current.getRightChild());
            current.setValue(smallestValue);
            current.setRightChild(removeRecursive(current.getRightChild(), smallestValue));
        } else if (value.compareTo(current.getValue()) < 0) {
            current.setLeftChild(removeRecursive(current.getLeftChild(), value));
        } else {
            current.setRightChild(removeRecursive(current.getRightChild(), value));
        }

        return balanceTree(current);
    }

    /**
     * Encontra o menor valor em uma subárvore, usado no processo de remoção.
     */
    private T findSmallestValue(BinaryTreeNode<T> root) {
        return root.getLeftChild() == null ? root.getValue() : findSmallestValue(root.getLeftChild());
    }

    /**
     * Aplica o balanceamento na árvore, verificando o fator de balanceamento e
     * realizando rotações, se necessário.
     */
    private BinaryTreeNode<T> balanceTree(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }

        // Calcula o fator de balanceamento (diferença de altura entre subárvores)
        int balanceFactor = height(node.getLeftChild()) - height(node.getRightChild());

        // Aplica a rotação à direita se o fator de balanceamento for maior que 1
        if (balanceFactor > 1) {
            if (height(node.getLeftChild().getLeftChild()) >= height(node.getLeftChild().getRightChild())) {
                node = rotateRight(node);
            } else {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
                node = rotateRight(node);
            }
        }

        // Aplica a rotação à esquerda se o fator de balanceamento for menor que -1
        if (balanceFactor < -1) {
            if (height(node.getRightChild().getRightChild()) >= height(node.getRightChild().getLeftChild())) {
                node = rotateLeft(node);
            } else {
                node.setRightChild(rotateRight(node.getRightChild()));
                node = rotateLeft(node);
            }
        }

        return node; // Retorna o nó balanceado
    }

    /**
     * Calcula a altura de um nó na árvore.
     */
    private int height(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1;
    }

    /**
     * Realiza uma rotação simples à direita para balanceamento.
     */
    private BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> y) {
        BinaryTreeNode<T> x = y.getLeftChild();
        y.setLeftChild(x.getRightChild());
        x.setRightChild(y);
        return x;
    }

    /**
     * Realiza uma rotação simples à esquerda para balanceamento.
     */
    private BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> y) {
        BinaryTreeNode<T> x = y.getRightChild();
        y.setRightChild(x.getLeftChild());
        x.setLeftChild(y);
        return x;
    }

    /**
     * Converte a árvore balanceada em uma lista, realizando uma travessia in-order.
     */
    public List<T> toList() {
        if (root() == null) {
            return null;
        }

        List<T> list = new ArrayList<>();
        toListRecursive(root(), list);
        return list;
    }

    /**
     * Método auxiliar para percorrer a árvore recursivamente e preencher a lista in-order.
     */
    private void toListRecursive(BinaryTreeNode<T> current, List<T> list) {
        if (current != null) {
            toListRecursive(current.getLeftChild(), list);
            list.add(current.getValue());
            toListRecursive(current.getRightChild(), list);
        }
    }
}
