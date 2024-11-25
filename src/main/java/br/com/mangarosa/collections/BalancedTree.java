package br.com.mangarosa.collections;

public class BalancedTree<T extends Comparable<T>> extends BinaryTree<T> {

    @Override
    public void add(T value) {
       super.add(value);
       balanceiaTree();
    }

    @Override
    public void remove(T value) {
        super.remove(value);
        balanceiaTree();
    }

    //Balanceia a arvore
    private void balanceiaTree() {
        updateAltura(this.root());
        BinaryTreeNode<T> node = balance(this.root());
        this.setRoot(node);

    }

    //Metodo recursivo para balancear a arvore
    private BinaryTreeNode<T> balance(BinaryTreeNode<T> node) {
            if(node == null){
                return null;
        } else {
            BinaryTreeNode<T> leftNode = node.getLeftChild();
            BinaryTreeNode<T> rightNode = node.getRightChild();

            node.setLeftChild(balance(leftNode));
            node.setRightChild(balance(rightNode));
            updateAltura(node);

            int FatorBalance = alt(leftNode) - alt(rightNode);

            int leftBalance = FatorBalance(leftNode);
            int rightBalance = FatorBalance(rightNode);

            //Rotação simples a esq
            if(FatorBalance < -1 &&  rightBalance <= 0){
                return rotacionaEsq(node);
            }
            //Rotação simples a dir
            if(FatorBalance > 1 && leftBalance >= 0){
                return rotacionaDir(node);
            }
            //Rotação dupla a esq
            if(FatorBalance < -1  && rightBalance > 0){
                node.setRightChild(rotacionaDir(node.getRightChild()));
                    return rotacionaEsq(node);
            }
            //Rotação dupla a dir
            if(FatorBalance > 1 && leftBalance < 0) {
                node.setLeftChild(rotacionaDir(node.getLeftChild()));
                    return rotacionaDir(node);
            }
        }
        return node;
    }


    //Metodo de rotação a direita
    private BinaryTreeNode<T> rotacionaDir(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> left = node.getLeftChild();
        BinaryTreeNode<T> rightLeft = left.getRightChild();

        left.setRightChild(node);
        node.setLeftChild(rightLeft);

        int nodeHeight = Math.max(alt(node.getLeftChild()), alt(node.getRightChild())) + 1;
        int leftHeight = Math.max(alt(left.getLeftChild()), alt(left.getRightChild())) + 1;

        node.setHeight(nodeHeight);
        left.setHeight(leftHeight);

        return left;
    }

    //Metodo de rotação a esquerda 
    private BinaryTreeNode<T>  rotacionaEsq(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> right = node.getRightChild();
        BinaryTreeNode<T> leftRight = right.getLeftChild();

        right.setLeftChild(node);
        node.setRightChild(leftRight);

        int nodeHeight = Math.max(alt(node.getLeftChild()), alt(node.getRightChild())) + 1;
        int rightHeight = Math.max(alt(right.getLeftChild()), alt(right.getRightChild())) + 1;

        node.setHeight(nodeHeight);
        right.setHeight(rightHeight);

        return right;
    }

    //faz o calculo do fator de balanceamento

    private int FatorBalance(BinaryTreeNode<T> node) {
        return node != null ? alt(node.getLeftChild()) - alt(node.getRightChild()) : 0;
    }

    //Retorna a altura do nó
    private int alt(BinaryTreeNode<T> node) {
        return (node != null) ?  node.getHeight() : 0;

    }

    //Atualiza a altura do nó
    private int updateAltura(BinaryTreeNode<T> node) {
        
        if(node == null){
            return 0;
        } else {
            int leftHeight = updateAltura(node.getLeftChild());
            int rightHeight = updateAltura(node.getRightChild());
            int alt = Math.max(leftHeight, rightHeight) + 1;
            node.setHeight(alt);
            return alt;

        }
    }


}