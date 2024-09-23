package br.com.mangarosa.collections;

public interface Tree <T extends Comparable<T>>{

    void add(T value);

    void remove(T value);

    boolean contains(T value);

    boolean isEmpty();

    boolean isLeaf(T value);

    BinaryTreeNode<T> root();

    int size();

    T[] toArray();

    void clear();
}
