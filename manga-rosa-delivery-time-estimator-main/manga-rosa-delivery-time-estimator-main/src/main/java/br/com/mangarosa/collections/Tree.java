package br.com.mangarosa.collections;

import java.util.List;

public interface Tree <T extends Comparable<T>>{

    void add(T value);

    void remove(T value);

    boolean contains(T value);

    boolean isEmpty();

    boolean isLeaf(T value);

    BinaryTreeNode<T> root();

    int size();

    List<T> toList();

    void clear();
}
