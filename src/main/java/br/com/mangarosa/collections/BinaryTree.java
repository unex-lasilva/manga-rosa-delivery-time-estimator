package br.com.mangarosa.collections;

/**
 * A classe BinaryTree é uma implementação de árvore binária balanceada. Ela estende a classe
 * BinaryTreeA2B, e seus métodos são responsáveis por adicionar, remover e manter o balanceamento da árvore
 * usando rotações para garantir o balanceamento adequado dos nós.
 */
public class BinaryTree extends BinaryTreeA2B {

    /**
     * Adiciona um valor à árvore binária e garante que a árvore permaneça balanceada após a inserção.
     *
     * @param value O valor a ser adicionado à árvore.
     */
    @Override
    public void add(Integer value) {
        super.add(value);
        root = balance(root);
    }

    /**
     * Remove um valor da árvore binária e garante que a árvore permaneça balanceada após a remoção.
     *
     * @param value O valor a ser removido da árvore.
     */
    @Override
    public void remove(Integer value) {
        super.remove(value);
        root = balance(root);
    }

    /**
     * Balanceia a árvore binária a partir de um determinado nó. Verifica o fator de balanceamento
     * e aplica rotações (simples ou duplas) conforme necessário.
     *
     * @param node O nó a partir do qual o balanceamento será feito.
     * @return O novo nó raiz após o balanceamento.
     */
    private BinaryTreeNode<Integer> balance(BinaryTreeNode<Integer> node) {
        if (node == null) return null;

        // Calcula o fator de balanceamento
        int balanceFactor = height(node.getLeftChild()) - height(node.getRightChild());

        // Verifica se a árvore está desbalanceada para a esquerda
        if (balanceFactor > 1) {
            // Caso de rotação simples à direita
            if (height(node.getLeftChild().getLeftChild()) >= height(node.getLeftChild().getRightChild())) {
                node = rotateRight(node);
            }
            // Caso de rotação dupla à direita (esquerda-direita)
            else {
                node = rotateLeftRight(node);
            }
        }

        // Verifica se a árvore está desbalanceada para a direita
        if (balanceFactor < -1) {
            // Caso de rotação simples à esquerda
            if (height(node.getRightChild().getRightChild()) >= height(node.getRightChild().getLeftChild())) {
                node = rotateLeft(node);
            }
            // Caso de rotação dupla à esquerda (direita-esquerda)
            else {
                node = rotateRightLeft(node);
            }
        }

        return node;
    }

    /**
     * Calcula a altura de um nó na árvore binária. A altura de um nó é a maior profundidade
     * entre seus filhos mais 1 (para contar o nó atual).
     *
     * @param node O nó cuja altura será calculada.
     * @return A altura do nó.
     */
    private int height(BinaryTreeNode<Integer> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }

    /**
     * Realiza uma rotação simples à esquerda. Esse tipo de rotação é utilizado para corrigir
     * o desbalanceamento quando o subárvore à direita de um nó é mais profundo.
     *
     * @param node O nó que será rotacionado.
     * @return O novo nó raiz após a rotação.
     */
    private BinaryTreeNode<Integer> rotateLeft(BinaryTreeNode<Integer> node) {
        BinaryTreeNode<Integer> newRoot = node.getRightChild();
        node.setRightChild(newRoot.getLeftChild());
        newRoot.setLeftChild(node);
        return newRoot;
    }

    /**
     * Realiza uma rotação simples à direita. Esse tipo de rotação é utilizado para corrigir
     * o desbalanceamento quando o subárvore à esquerda de um nó é mais profundo.
     *
     * @param node O nó que será rotacionado.
     * @return O novo nó raiz após a rotação.
     */
    private BinaryTreeNode<Integer> rotateRight(BinaryTreeNode<Integer> node) {
        BinaryTreeNode<Integer> newRoot = node.getLeftChild();
        node.setLeftChild(newRoot.getRightChild());
        newRoot.setRightChild(node);
        return newRoot;
    }

    /**
     * Realiza uma rotação dupla à esquerda e depois à direita (esquerda-direita). Esse tipo de rotação
     * é utilizado quando o subárvore à esquerda do nó tem uma desbalanceamento à direita.
     *
     * @param node O nó que será rotacionado.
     * @return O novo nó raiz após a rotação.
     */
    private BinaryTreeNode<Integer> rotateLeftRight(BinaryTreeNode<Integer> node) {
        node.setLeftChild(rotateLeft(node.getLeftChild()));
        return rotateRight(node);
    }

    /**
     * Realiza uma rotação dupla à direita e depois à esquerda (direita-esquerda). Esse tipo de rotação
     * é utilizado quando o subárvore à direita do nó tem uma desbalanceamento à esquerda.
     *
     * @param node O nó que será rotacionado.
     * @return O novo nó raiz após a rotação.
     */
    private BinaryTreeNode<Integer> rotateRightLeft(BinaryTreeNode<Integer> node) {
        node.setRightChild(rotateRight(node.getRightChild()));
        return rotateLeft(node);
    }
}
