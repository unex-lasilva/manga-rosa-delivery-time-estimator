package br.com.mangarosa.collections;

import java.util.List;

/**
 * Classe que representa uma árvore binária. Opera com nós genéricos do tipo Integer.
 */
public class BinaryTreeA2B implements Tree<Integer> {

    protected BinaryTreeNode<Integer> root;
    private int size;

    public BinaryTreeA2B() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Adiciona um novo valor à árvore binária.
     * Se a árvore estiver vazia, define o novo valor como raiz.
     *
     * @param value O valor a ser adicionado à árvore.
     */
    @Override
    public void add(Integer value) {
        root = addRecursive(root, value);
        size++;
    }

    /**
     * Encontra recursivamente a posição correta na árvore para inserir o novo valor.
     *
     * @param node  O nó atual sendo verificado.
     * @param value O valor a ser adicionado.
     * @return O nó atualizado após a inserção.
     */
    private BinaryTreeNode<Integer> addRecursive(BinaryTreeNode<Integer> node, Integer value) {
        if (node == null) {
            return new BinaryTreeNode<>(value);
        }

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(addRecursive(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(addRecursive(node.getRightChild(), value));
        }

        return node;
    }

    /**
     * Remove um valor da árvore, se existir.
     *
     * @param value O valor a ser removido da árvore.
     */
    @Override
    public void remove(Integer value) {
        if (contains(value)) {
            root = removeRecursive(root, value);
            size--;
        }
    }

    /**
     * Encontra recursivamente o nó a ser removido e ajusta a árvore adequadamente.
     *
     * @param node  O nó atual sendo verificado.
     * @param value O valor a ser removido.
     * @return O nó atualizado após a remoção.
     */
    private BinaryTreeNode<Integer> removeRecursive(BinaryTreeNode<Integer> node, Integer value) {
        if (node == null) {
            return null;
        }

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(removeRecursive(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(removeRecursive(node.getRightChild(), value));
        } else {
            // Caso o nó não tenha filhos
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                return null;
            }
            // Caso o nó tenha apenas o filho direito
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            }
            // Caso o nó tenha apenas o filho esquerdo
            if (node.getRightChild() == null) {
                return node.getLeftChild();
            }

            // Caso o nó tenha dois filhos, encontre o menor valor no lado direito
            BinaryTreeNode<Integer> smallestValue = findMin(node.getRightChild());
            node.setValue(smallestValue.getValue());
            node.setRightChild(removeRecursive(node.getRightChild(), smallestValue.getValue()));
        }

        return node;
    }

    /**
     * Encontra o nó com o menor valor em uma subárvore.
     *
     * @param node O nó raiz da subárvore.
     * @return O nó contendo o menor valor.
     */
    private BinaryTreeNode<Integer> findMin(BinaryTreeNode<Integer> node) {
        return node.getLeftChild() == null ? node : findMin(node.getLeftChild());
    }

    /**
     * Verifica se a árvore contém um valor específico.
     *
     * @param value O valor a ser procurado.
     * @return True se o valor for encontrado, falso caso contrário.
     */
    @Override
    public boolean contains(Integer value) {
        return containsRecursive(value);
    }

    /**
     * Verifica se um nó com o valor especificado existe na árvore.
     *
     * @param value O valor a ser procurado.
     * @return True se o valor for encontrado, falso caso contrário.
     */
    private boolean containsRecursive(Integer value) {
        return findNode(root, value) != null;
    }

    /**
     * Verifica se a árvore está vazia.
     *
     * @return True se a árvore não contiver elementos, falso caso contrário.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Verifica se o nó com o valor fornecido é uma folha (não possui filhos).
     *
     * @param value O valor do nó a ser verificado.
     * @return True se o nó for uma folha, falso caso contrário.
     */
    @Override
    public boolean isLeaf(Integer value) {
        BinaryTreeNode<Integer> node = findNode(root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }

    /**
     * Encontra o nó que contém o valor fornecido.
     *
     * @param node  O nó atual sendo verificado.
     * @param value O valor a ser procurado.
     * @return O nó contendo o valor, ou null se não encontrado.
     */
    private BinaryTreeNode<Integer> findNode(BinaryTreeNode<Integer> node, Integer value) {
        if (node == null) return null;
        if (value.equals(node.getValue())) return node;
        return value.compareTo(node.getValue()) < 0 ? findNode(node.getLeftChild(), value) : findNode(node.getRightChild(), value);
    }

    /**
     * Obtém o nó raiz da árvore.
     *
     * @return O nó raiz.
     */
    @Override
    public BinaryTreeNode<Integer> root() {
        return root;
    }

    /**
     * Obtém o tamanho da árvore.
     *
     * @return O número de elementos na árvore.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Converte a árvore para um array, percorrendo-a em ordem.
     *
     * @return Um array contendo todos os valores da árvore.
     */
    @Override
    public Integer[] toArray() {
        InOrderTraversal traversal = new InOrderTraversal();
        List<Integer> list = traversal.traverse(this);
        return list.toArray(new Integer[0]);
    }

    /**
     * Limpa a árvore, removendo todos os elementos.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }
}
