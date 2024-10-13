package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação de uma Árvore Binária de Busca genérica que mantém seus elementos ordenados.
 * Esta árvore é auto-balanceada utilizando rotações para garantir operações eficientes.
 *
 * @param <T> Tipo dos elementos armazenados na árvore, que devem ser comparáveis.
 */
public class BinaryTree<T extends Comparable<T>> implements Tree<T> {
    /**
     * Nó raiz da árvore binária.
     */
    private BinaryTreeNode<T> root;

    /**
     * Número de elementos na árvore.
     */
    private int size;

    /**
     * Adiciona um valor à árvore.
     *
     * @param value Valor a ser adicionado.
     */
    @Override
    public void add(T value) {
        root = addRecursive(root, value);
        size++;
    }

    /**
     * Método recursivo para adicionar um valor na posição correta da árvore.
     *
     * @param node  Nó atual na recursão.
     * @param value Valor a ser adicionado.
     * @return Nó atualizado após a inserção.
     */
    private BinaryTreeNode<T> addRecursive(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            return new BinaryTreeNode<>(value);
        }

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(addRecursive(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(addRecursive(node.getRightChild(), value));
        } else {
            // Valor duplicado, não adiciona
            return node;
        }

        updateHeight(node);
        return balance(node);
    }

    /**
     * Remove um valor da árvore.
     *
     * @param value Valor a ser removido.
     */
    @Override
    public void remove(T value) {
        boolean[] removed = new boolean[1];
        root = removeRecursive(root, value, removed);
        if (removed[0]) {
            size--;
        }
    }

    /**
     * Método recursivo para remover um valor da árvore.
     *
     * @param node    Nó atual na recursão.
     * @param value   Valor a ser removido.
     * @param removed Array de booleanos para indicar se o valor foi removido.
     * @return Nó atualizado após a remoção.
     */
    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> node, T value, boolean[] removed) {
        if (node == null) {
            return null;
        }

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(removeRecursive(node.getLeftChild(), value, removed));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(removeRecursive(node.getRightChild(), value, removed));
        } else {
            removed[0] = true;

            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }

            // Nó com dois filhos: obtém o valor mínimo do subárvore direita
            node.setValue(findMin(node.getRightChild()).getValue());
            node.setRightChild(removeRecursive(node.getRightChild(), node.getValue(), removed));
        }

        updateHeight(node);
        return balance(node);
    }

    /**
     * Encontra o nó com o valor mínimo a partir de um nó específico.
     *
     * @param node Nó inicial para busca.
     * @return Nó com o valor mínimo encontrado.
     */
    private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }

    /**
     * Obtém a altura de um nó.
     *
     * @param node Nó para obter a altura.
     * @return Altura do nó ou 0 se o nó for nulo.
     */
    private int height(BinaryTreeNode<T> node) {
        return node == null ? 0 : node.getHeight();
    }

    /**
     * Atualiza a altura de um nó baseado nas alturas de seus filhos.
     *
     * @param node Nó a ter a altura atualizada.
     */
    private void updateHeight(BinaryTreeNode<T> node) {
        node.setHeight(1 + Math.max(height(node.getLeftChild()), height(node.getRightChild())));
    }

    /**
     * Calcula o fator de balanceamento de um nó.
     *
     * @param node Nó para calcular o fator de balanceamento.
     * @return Fator de balanceamento (altura do filho esquerdo - altura do filho direito).
     */
    private int balanceFactor(BinaryTreeNode<T> node) {
        return height(node.getLeftChild()) - height(node.getRightChild());
    }

    /**
     * Balanceia a árvore a partir de um nó específico.
     *
     * @param node Nó a ser balanceado.
     * @return Novo nó raiz após balanceamento.
     */
    private BinaryTreeNode<T> balance(BinaryTreeNode<T> node) {
        int balance = balanceFactor(node);

        // Caso 1: Desbalanceamento à esquerda
        if (balance > 1) {
            if (balanceFactor(node.getLeftChild()) < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            }
            return rotateRight(node);
        }

        // Caso 2: Desbalanceamento à direita
        if (balance < -1) {
            if (balanceFactor(node.getRightChild()) > 0) {
                node.setRightChild(rotateRight(node.getRightChild()));
            }
            return rotateLeft(node);
        }

        // Nenhum desbalanceamento
        return node;
    }

    /**
     * Realiza uma rotação à esquerda em um nó.
     *
     * @param node Nó a ser rotacionado.
     * @return Novo nó raiz após a rotação.
     */
    private BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> newRoot = node.getRightChild();
        node.setRightChild(newRoot.getLeftChild());
        newRoot.setLeftChild(node);
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    /**
     * Realiza uma rotação à direita em um nó.
     *
     * @param node Nó a ser rotacionado.
     * @return Novo nó raiz após a rotação.
     */
    private BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> newRoot = node.getLeftChild();
        node.setLeftChild(newRoot.getRightChild());
        newRoot.setRightChild(node);
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    /**
     * Verifica se a árvore contém um determinado valor.
     *
     * @param value Valor a ser verificado.
     * @return {@code true} se o valor estiver presente, caso contrário {@code false}.
     */
    @Override
    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    /**
     * Método recursivo para verificar a presença de um valor na árvore.
     *
     * @param node  Nó atual na recursão.
     * @param value Valor a ser verificado.
     * @return {@code true} se o valor for encontrado, {@code false} caso contrário.
     */
    private boolean containsRecursive(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            return false;
        }

        if (value.compareTo(node.getValue()) < 0) {
            return containsRecursive(node.getLeftChild(), value);
        } else if (value.compareTo(node.getValue()) > 0) {
            return containsRecursive(node.getRightChild(), value);
        } else {
            return true;
        }
    }

    /**
     * Verifica se a árvore está vazia.
     *
     * @return {@code true} se a árvore estiver vazia, caso contrário {@code false}.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Verifica se um determinado valor é uma folha (nó sem filhos) na árvore.
     *
     * @param value Valor a ser verificado.
     * @return {@code true} se o valor for uma folha, {@code false} caso contrário.
     */
    @Override
    public boolean isLeaf(T value) {
        BinaryTreeNode<T> node = findNode(root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }

    /**
     * Encontra o nó correspondente a um determinado valor.
     *
     * @param node  Nó atual na recursão.
     * @param value Valor a ser encontrado.
     * @return Nó correspondente ao valor ou {@code null} se não encontrado.
     */
    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            return null;
        }
        if (value.compareTo(node.getValue()) < 0) {
            return findNode(node.getLeftChild(), value);
        } else if (value.compareTo(node.getValue()) > 0) {
            return findNode(node.getRightChild(), value);
        } else {
            return node;
        }
    }

    /**
     * Retorna o nó raiz da árvore.
     *
     * @return Nó raiz.
     */
    @Override
    public BinaryTreeNode<T> root() {
        return root;
    }

    /**
     * Retorna o número de elementos na árvore.
     *
     * @return Tamanho da árvore.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Converte a árvore em um array.
     *
     * @return Array contendo todos os elementos da árvore em ordem.
     * @throws UnsupportedOperationException Se o método não estiver implementado.
     */
    @Override
    public T[] toArray() {
        throw new UnsupportedOperationException("Método toArray() não implementado.");
    }

    /**
     * Converte a árvore em uma lista.
     *
     * @return Lista contendo todos os elementos da árvore em ordem.
     */
    @Override
    public List<T> toList() {
        if (root == null ){
            return null;
        }
        if (isEmpty()) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>(size);
        toListRecursive(root, list);
        return list;
    }

    /**
     * Método recursivo para percorrer a árvore em ordem e adicionar os elementos a uma lista.
     *
     * @param node Nó atual na recursão.
     * @param list Lista a ser populada com os elementos da árvore.
     */
    private void toListRecursive(BinaryTreeNode<T> node, List<T> list) {
        if (node != null) {
            toListRecursive(node.getLeftChild(), list);
            list.add(node.getValue());
            toListRecursive(node.getRightChild(), list);
        }
    }

    /**
     * Remove todos os elementos da árvore, deixando-a vazia.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }
}
