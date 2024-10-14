/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mangarosa.collections;

public class BalancedTreeNode<T extends Comparable<T>> {

    private final T value;
    private BalancedTreeNode<T> left;
    private BalancedTreeNode<T> right;
    private int height;

    public BalancedTreeNode(T value) {
        this.value = value;
        this.height = 1;  // Quando o nó é criado, sua altura é 1
    }

    public T getValue() {
        return value;
    }

    public BalancedTreeNode<T> getLeftChild() {
        return left;
    }

    public void setLeftChild(BalancedTreeNode<T> left) {
        this.left = left;
    }

    public BalancedTreeNode<T> getRightChild() {
        return right;
    }

    public void setRightChild(BalancedTreeNode<T> right) {
        this.right = right;
    }

    // Método para obter a altura de um nó
    public int getHeight(BalancedTreeNode<T> node) {
        return (node == null) ? 0 : node.height;
    }

    // Método para calcular o fator de balanceamento
    public int getBalanceFactor() {
        return getHeight(left) - getHeight(right);
    }

    // Atualizar altura do nó
    public void updateHeight() {
        this.height = 1 + Math.max(getHeight(left), getHeight(right));
    }

    // Rotação à direita
    public BalancedTreeNode<T> rotateRight() {
        BalancedTreeNode<T> newRoot = left;
        BalancedTreeNode<T> temp = newRoot.right;

        // Realizar a rotação
        newRoot.right = this;
        this.left = temp;

        // Atualizar as alturas
        this.updateHeight();
        newRoot.updateHeight();

        // Retornar nova raiz
        return newRoot;
    }

    // Rotação à esquerda
    public BalancedTreeNode<T> rotateLeft() {
        BalancedTreeNode<T> newRoot = right;
        BalancedTreeNode<T> temp = newRoot.left;

        // Realizar a rotação
        newRoot.left = this;
        this.right = temp;

        // Atualizar as alturas
        this.updateHeight();
        newRoot.updateHeight();

        // Retornar nova raiz
        return newRoot;
    }

    // Método de balanceamento
    public BalancedTreeNode<T> balance() {
        // Atualizar a altura deste nó
        this.updateHeight();

        // Verificar o fator de balanceamento
        int balanceFactor = getBalanceFactor();

        // Se a árvore estiver desbalanceada para a esquerda
        if (balanceFactor > 1) {
            // Rotação simples ou dupla (esquerda-direita)
            if (getHeight(left.left) >= getHeight(left.right)) {
                return rotateRight();
            } else {
                left = left.rotateLeft();
                return rotateRight();
            }
        }

        // Se a árvore estiver desbalanceada para a direita
        if (balanceFactor < -1) {
            // Rotação simples ou dupla (direita-esquerda)
            if (getHeight(right.right) >= getHeight(right.left)) {
                return rotateLeft();
            } else {
                right = right.rotateRight();
                return rotateLeft();
            }
        }

        // Se o nó estiver balanceado, não há necessidade de mudanças
        return this;
    }
}
