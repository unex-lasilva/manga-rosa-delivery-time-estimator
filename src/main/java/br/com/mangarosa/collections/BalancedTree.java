package br.com.mangarosa.collections;

public class BalancedTree<T extends Comparable<T>> extends BinaryTree<T> {

    @Override
    public void add(T value) {
        setRoot(addRecursive(root(), value));
        size++;
    }

    private BinaryTreeNode<T> addRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) {
            return new BinaryTreeNode<>(value);
        }
        if (value.compareTo(current.getValue()) < 0) {
            current.setLeftChild(addRecursive(current.getLeftChild(), value));
        } else if (value.compareTo(current.getValue()) > 0) {
            current.setRightChild(addRecursive(current.getRightChild(), value));
        } else {
            return current; // Se o Valor já Existe
        }
        return treeBalance(current);//Chama o Balanceamente após a Adição do Elemento
    }

    @Override
    public void remove(T value) {
        setRoot(removeRecursive(root(), value));
    }

    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) {
            return null;
        } else if (value.compareTo(current.getValue())<0) {
            current.setLeftChild(removeRecursive(current.getLeftChild(),value));
        } else if (value.compareTo(current.getValue()) > 0) {
            current.setRightChild(removeRecursive(current.getRightChild(),value));
        } else {
            if (current.getLeftChild() == null || current.getRightChild() == null){
                current = (current.getLeftChild()!=null) ? current.getLeftChild() : current.getRightChild();
            }
            else{
                current.setValue(findSmallest(current.getRightChild()));
                current.setRightChild(removeRecursive(current.getRightChild(), current.getValue()));
            }
        }

        if (current != null){
            current = treeBalance(current);// Chama balanceamento após a remoção do Elemento
        }
        return current;
    }

    private BinaryTreeNode<T> treeBalance(BinaryTreeNode<T> current){
        int factor = getFactor(current);

        // Sempre que o fator foi Maior que 1 ou Menor que -1 , Ta desbalanceado!
        if (factor > 1){ //Fator Positivo, Desequilibrio a Esquerda.
            if (getFactor(current.getLeftChild()) < 0){ // Se for a Esquerda da Direita
                current.setLeftChild(rollLeft(current.getLeftChild())); // Roda pra  esquerda
            }
            return rollRight(current);// Roda pra Direita
        }

        if (factor < -1){ // Fator Negativo, Desequilibrio a Direita.
            if (getFactor(current.getRightChild()) > 0){ // Se for a Direita da Esquerda
                current.setRightChild(rollRight(current.getRightChild())); // Roda pra Direita
            }
            return rollLeft(current); // roda pra Esquerda
        }

        return current;
    }

    private int getFactor(BinaryTreeNode<T> current){
        int leftHeight = height(current.getLeftChild());
        int rightHeight = height(current.getRightChild());
        return leftHeight - rightHeight; // Calcula a diferença entre a altura do filho Esquerdo e direito.
    }

    private BinaryTreeNode<T> rollRight(BinaryTreeNode<T> current){
        BinaryTreeNode<T> newRoot = current.getLeftChild(); // Transforma o filho esquerdo do no 'Current' e transforma na nova raiz
        current.setLeftChild(newRoot.getRightChild());
        newRoot.setRightChild(current);//Transforma a velha raiz no filho direito dessa nova raiz
        return newRoot;
    }

    private BinaryTreeNode<T> rollLeft(BinaryTreeNode<T> current){
        BinaryTreeNode<T> newRoot = current.getRightChild();
        current.setRightChild(newRoot.getLeftChild());
        newRoot.setLeftChild(current);
        return newRoot; // Mesma coisa do anterior, só que do lado contrario. 👍
    }

    private int height(BinaryTreeNode<T> current){
        if (current == null){
            return 0;
        }
        return Math.max(height(current.getLeftChild()),height(current.getRightChild())) + 1; //Retorna a altura. O maior + 1
    }
}
