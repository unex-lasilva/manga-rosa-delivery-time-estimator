package br.com.mangarosa.collections;

import java.util.List;

public interface TreeTraversal <T extends Comparable<T>> {
    List<T> traverse(Tree<T> tree);
}
