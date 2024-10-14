package br.com.mangarosa.collections;

public class A3B<T extends Comparable<T>> extends A2B<T> {

    private BalancedTree<T> balancer = new BalancedTree<>();

    @Override
    public void add(T value) {
        super.add(value);
        this.root = balancer.balance(root()); // Aplica o balanceamento após adicionar
    }

    @Override
    public void remove(T value) {
        super.remove(value);
        this.root = balancer.balance(root()); // Aplica o balanceamento após remover
    }
}
