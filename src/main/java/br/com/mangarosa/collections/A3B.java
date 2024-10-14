package br.com.mangarosa.collections;

public class A3B<T extends Comparable<T>> extends A2B<T> {
        

    @Override
    public void add(T value) {
        if (!contains(value)) {
            root = addRecursive(root, value);
            size++;
            System.out.println("Adicionado: " + value + ", Tamanho: " + size); // Log
        }
    }

    // Método recursivo para adicionar elementos
    @Override
    BinaryTreeNode<T> addRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) {
            return new BinaryTreeNode<>(value);
        }
        if (value.compareTo(current.getValue()) < 0) {
            current.setLeftChild(addRecursive(current.getLeftChild(), value));
        } else {
            current.setRightChild(addRecursive(current.getRightChild(), value));
        }
        return current;
    }

    @Override
    public void remove(T value) {
        if (contains(value)) {
            root = removeRecursive(root, value);
            size--;
            System.out.println("Removido: " + value + ", Tamanho: " + size); // Log
            if (root != null) {
                root = balance(root);
            }
        }
    }
  
    // Método recursivo para remover elementos
    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> current, T value) {
        if (current == null) {
            return null; // Valor não encontrado
        }

        // Descendo na árvore
        if (value.compareTo(current.getValue()) < 0) {
            current.setLeftChild(removeRecursive(current.getLeftChild(), value));
        } else if (value.compareTo(current.getValue()) > 0) {
            current.setRightChild(removeRecursive(current.getRightChild(), value));
        } else {
            // Encontrou o nó a ser removido
            if (current.getLeftChild() == null && current.getRightChild() == null) {
                return null; // Sem filhos
            } else if (current.getLeftChild() == null) {
                return current.getRightChild(); // Um filho (direito)
            } else if (current.getRightChild() == null) {
                return current.getLeftChild(); // Um filho (esquerdo)
            } else {
                // Nó com dois filhos: encontra o menor da subárvore à direita
                BinaryTreeNode<T> smallestValue = findSmallestValue(current.getRightChild());
                current.setValue(smallestValue.getValue());
                current.setRightChild(removeRecursive(current.getRightChild(), smallestValue.getValue()));
            }
        }
        return current; // Retorna o nó atualizado
    }

    private BinaryTreeNode<T> findSmallestValue(BinaryTreeNode<T> node) {
        return node.getLeftChild() == null ? node : findSmallestValue(node.getLeftChild());
    }

    // Método para balancear a árvore
    private BinaryTreeNode<T> balance(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }

        int balanceFactor = height(node.getLeftChild()) - height(node.getRightChild());

        // Rotação à direita
        if (balanceFactor > 1) {
            if (height(node.getLeftChild().getLeftChild()) >= height(node.getLeftChild().getRightChild())) {
                return rotateRight(node);
            } else {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
                return rotateRight(node);
            }
        }

        // Rotação à esquerda
        if (balanceFactor < -1) {
            if (height(node.getRightChild().getRightChild()) >= height(node.getRightChild().getLeftChild())) {
                return rotateLeft(node);
            } else {
                node.setRightChild(rotateRight(node.getRightChild()));
                return rotateLeft(node);
            }
        }

        return node; // Retorna o nó balanceado
    }

    // Métodos para rotação
    private BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> y) {
        BinaryTreeNode<T> x = y.getLeftChild();
        BinaryTreeNode<T> T2 = x.getRightChild();

        // Realiza a rotação
        x.setRightChild(y);
        y.setLeftChild(T2);

        return x; // Retorna nova raiz
    }

    private BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> x) {
        BinaryTreeNode<T> y = x.getRightChild();
        BinaryTreeNode<T> T2 = y.getLeftChild();

        // Realiza a rotação
        y.setLeftChild(x);
        x.setRightChild(T2);

        return y; // Retorna nova raiz
    }

    // Método para calcular a altura de um nó
    private int height(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1;
    }
}
