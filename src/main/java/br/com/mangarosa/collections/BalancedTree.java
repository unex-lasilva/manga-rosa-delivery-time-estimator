package br.com.mangarosa.collections;

public class BalancedTree<T extends Comparable<T>> extends BinaryTree<T> {

    public void add(T value) {
        setRoot(addRecursive(root(), value)); //adicionando o valor na árvore de forma recursiva
        size++;//tamanho da árvore
    }

    private BinaryTreeNode<T> addRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) return new BinaryTreeNode<>(value); //criando um novo nó se a posição estiver vazia
        return navigateTree(current, value, true); //inserindo o valor na posição correta
    }

    @Override
    public void remove(T value) {
        BinaryTreeNode<T> current = root(); //raiz da árvore
        if (current == null){ return; } //retornando se a árvore estiver vazia
        setRoot(removeRecursive(current, value)); //remove o valor e redefine a raiz
    }

    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) return null; //retornando nulo se não houver nó
        return navigateTree(current, value, false); //remover o valor na posição correta
    }

    private BinaryTreeNode<T> navigateTree(BinaryTreeNode<T> current, T value, boolean isAdd) {
        if (isAdd) {
            if (value.compareTo(current.getValue()) < 0) {
                current.setLeftChild(addRecursive(current.getLeftChild(), value)); //adiciona à esquerda
            } else if (value.compareTo(current.getValue()) > 0) {
                current.setRightChild(addRecursive(current.getRightChild(), value)); //adiciona à direita
            }
        } else {
            if (value.compareTo(current.getValue()) < 0) {
                current.setLeftChild(removeRecursive(current.getLeftChild(), value)); //remove da esquerda
            } else if (value.compareTo(current.getValue()) > 0) {
                current.setRightChild(removeRecursive(current.getRightChild(), value)); //remove da direita
            } else {

                // Caso em que o nó a ser removido foi encontrado
                if (current.getLeftChild() == null) return current.getRightChild(); //retorna filho da direita se não houver à esquerda
                if (current.getRightChild() == null) return current.getLeftChild(); //retorna filho da esquerda se não houver à direita
                current.setValue(findSmallest(current.getRightChild())); //substitui pelo menor valor da direita
                current.setRightChild(removeRecursive(current.getRightChild(), current.getValue())); //remove o menor da direita
            }
        }
        return treeBalance(current); //rebalanceia a árvore após a operação
    }

    private BinaryTreeNode<T> treeBalance(BinaryTreeNode<T> current) {
        int factor = getFactor(current); //calculando o fator de balanceamento

        if (factor > 1) {
            if (getFactor(current.getLeftChild()) < 0)
                current.setLeftChild(rollLeft(current.getLeftChild())); //rotacionando a esquerda se necessário
            return rollRight(current); //rotaciona à direita
        }
        if (factor < -1) {
            if (getFactor(current.getRightChild()) > 0)
                current.setRightChild(rollRight(current.getRightChild())); //rotaciona à direita se necessário
            return rollLeft(current); //rotaciona à esquerda
        }
        return current; //retorna o nó atual se não precisar de balanceamento
    }

    private int getFactor(BinaryTreeNode<T> current) {
        return height(current.getLeftChild()) - height(current.getRightChild()); //calcula o fator de balanceamento
    }

    private BinaryTreeNode<T> rollRight(BinaryTreeNode<T> current) {
        BinaryTreeNode<T> newRoot = current.getLeftChild(); //define a nova raiz
        current.setLeftChild(newRoot.getRightChild()); //ajusta o filho da esquerda
        newRoot.setRightChild(current); //conecta o nó atual à nova raiz
        return newRoot; //retorna a nova raiz
    }

    private BinaryTreeNode<T> rollLeft(BinaryTreeNode<T> current) {
        BinaryTreeNode<T> newRoot = current.getRightChild(); //define a nova raiz
        current.setRightChild(newRoot.getLeftChild()); //ajusta o filho da direita
        newRoot.setLeftChild(current); //conecta o nó atual à nova raiz
        return newRoot; //retorna a nova raiz
    }

    private int height(BinaryTreeNode<T> current) {
        return current == null ? 0 : Math.max(height(current.getLeftChild()), height(current.getRightChild())) + 1; //calcula a altura do nó
    }
}
