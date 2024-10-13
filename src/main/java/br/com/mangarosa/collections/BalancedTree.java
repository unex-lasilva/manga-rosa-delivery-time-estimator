package br.com.mangarosa.collections;

public class BalancedTree<T extends Comparable<T>> extends BinaryTree<T> {


    //Sobrescrita do método de adicionar e balancear
    @Override
    public void add(T value) {
        super.add(value);
        balancearTree();
    }

    //Sobrescrita do método de remover e balancear
    @Override
    public void remove(T value) {
        super.remove(value);
        balancearTree();
    }  

    //Método que é chamado para gerar o balanceamento 
    private void balancearTree(){
        attAltura(this.root());
        BinaryTreeNode<T> root = balancear(this.root());
        this.setRoot(root);
    }

    //Método recursivo que balanceia a árvore
    private BinaryTreeNode<T> balancear (BinaryTreeNode<T> node){
        if (node == null){
            return null;
        }else{
            BinaryTreeNode<T> leftNode =  node.getLeftChild();
            BinaryTreeNode<T> rightNode =  node.getRightChild();

            node.setLeftChild(balancear(leftNode));
            node.setRightChild(balancear(rightNode));

            attAltura(node);

            int balancearCalculo = altura(leftNode) - altura(rightNode);

            int leftBalance = balancearCalculo(leftNode);
            int rightBalance =  balancearCalculo(rightNode);

            // Rotação Simples Esquerda
            if (balancearCalculo < -1 && rightBalance <= 0){
                return  rotacaoEsquerda(node);
            }
            // Rotação Simples Direita
            if (balancearCalculo > 1 && leftBalance >= 0){
                return  rotacaoDireita(node);
            }
            // Rotação Dupla Esquerda
            if (balancearCalculo < -1 && rightBalance > 0){
                node.setRightChild(rotacaoDireita(node.getRightChild()));
                return rotacaoEsquerda(node);
            }
            // Rotação Dupla Direita
            if  (balancearCalculo > 1 && leftBalance < 0){
                node.setLeftChild(rotacaoEsquerda(node.getLeftChild()));
                return rotacaoDireita(node);
            } 
        }
        return node;
    }

    //Rotação simples à esquerda quando o nó está desbalanceado
    private BinaryTreeNode<T> rotacaoEsquerda(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> direita = node.getRightChild();
        BinaryTreeNode<T> esqDireita = direita.getLeftChild();

        direita.setLeftChild(node);
        node.setRightChild(esqDireita);

        int noAltura = Math.max(altura(node.getLeftChild()), altura(node.getRightChild())) + 1;
        int direitaAltura = Math.max(altura(direita.getLeftChild()), altura(direita.getRightChild())) + 1;
       
        node.setAltura(noAltura);
        direita.setAltura(direitaAltura);

        return direita;
    }

     //Rotação simples à direita quando o nó está desbalanceado
    private BinaryTreeNode<T> rotacaoDireita(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> esquerda = node.getLeftChild();
        BinaryTreeNode<T> dirEsquerda = esquerda.getRightChild();

        esquerda.setRightChild(node);
        node.setLeftChild(dirEsquerda);

        int noAltura = Math.max(altura(node.getLeftChild()), altura(node.getRightChild())) + 1;
        int esquerdaAltura = Math.max(altura(esquerda.getLeftChild()), altura(esquerda.getRightChild())) + 1;
       
        node.setAltura(noAltura);
        esquerda.setAltura(esquerdaAltura);

        return esquerda;
    }

    //Método que calcula o fator de balanceamento
    private int balancearCalculo (BinaryTreeNode<T> node){
        return node != null ? altura(node.getLeftChild()) - altura(node.getRightChild()) : 0;
    }

    //Método que retorna a altura do nó
    private int altura(BinaryTreeNode<T> node) {
        return (node != null ) ? node.getAltura(): 0;
    }

    //Método que atualiza a altura de cada nó recursivamente
    private  int attAltura(BinaryTreeNode<T> node){

        if (node == null) {
            return 0;
        }else{
            int esqAltura = attAltura(node.getLeftChild());
            int dirAltura = attAltura(node.getRightChild());
            int altura = Math.max(esqAltura, dirAltura) + 1;
            node.setAltura(altura);
            return altura;
        }

    }
    


    

}
