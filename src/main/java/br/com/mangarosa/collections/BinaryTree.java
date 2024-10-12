package br.com.mangarosa.collections;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {
    private BinaryTreeNode<T> root;
    private int size;

    @Override
    public void add(T value) {
        root = addRecursive(root, value);
        size++;
    }

    private BinaryTreeNode<T> addRecursive(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            return new BinaryTreeNode<>(value);
        }

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(addRecursive(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(addRecursive(node.getRightChild(), value));
        } else {
            // Se o valor já existe, não faz nada
            return node;
        }

    
        updateHeight(node);
        return balance(node);
    }

    @Override
    public void remove(T value) {
        root = removeRecursive(root, value);
        if (root != null) {
            size--;
        }
    }

    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            return null; // O valor não foi encontrado
        }

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(removeRecursive(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(removeRecursive(node.getRightChild(), value));
        } else {
           
            if (node.getLeftChild() == null) {
                return node.getRightChild(); // Substitui o nó pelo filho direito
            } else if (node.getRightChild() == null) {
                return node.getLeftChild(); // Substitui o nó pelo filho esquerdo
            }

           
            node.setValue(findMin(node.getRightChild()).getValue());
            node.setRightChild(removeRecursive(node.getRightChild(), node.getValue()));
        }

      
        updateHeight(node);

       
        return balance(node);
    }

    private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild(); // Navega até o nó mais à esquerda
        }
        return node;
    }

    private int height(BinaryTreeNode<T> node) {
        return node == null ? 0 : node.getHeight();
    }

    private void updateHeight(BinaryTreeNode<T> node) {
        node.setHeight(1 + Math.max(height(node.getLeftChild()), height(node.getRightChild())));
    }

    private int balanceFactor(BinaryTreeNode<T> node) {
        return height(node.getLeftChild()) - height(node.getRightChild());
    }

    private BinaryTreeNode<T> balance(BinaryTreeNode<T> node) {
        int balance = balanceFactor(node);

     
        if (balance > 1) {
            if (balanceFactor(node.getLeftChild()) < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild())); // Rotação esquerda-direita
            }
            return rotateRight(node); // Rotação à direita
        }

     
        if (balance < -1) {
            if (balanceFactor(node.getRightChild()) > 0) {
                node.setRightChild(rotateRight(node.getRightChild())); // Rotação direita-esquerda
            }
            return rotateLeft(node); // Rotação à esquerda
        }

        return node; // Está balanceado
    }

    private BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> newRoot = node.getRightChild();
        node.setRightChild(newRoot.getLeftChild());
        newRoot.setLeftChild(node);
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    private BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> newRoot = node.getLeftChild();
        node.setLeftChild(newRoot.getRightChild());
        newRoot.setRightChild(node);
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    @Override
    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isLeaf(T value) {
        BinaryTreeNode<T> node = findNode(root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }

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

    @Override
    public BinaryTreeNode<T> root() {
        return root;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T[] toArray() {

        T[] array = (T[]) new Comparable[size];
        toArrayRecursive(root, array, new int[1]);
        return array;
    }

    private void toArrayRecursive(BinaryTreeNode<T> node, T[] array, int[] index) {
        if (node != null) {
            toArrayRecursive(node.getLeftChild(), array, index);
            array[index[0]++] = node.getValue();
            toArrayRecursive(node.getRightChild(), array, index);
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }
}
