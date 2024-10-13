package br.com.mangarosa.collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeTest {

    private Tree<Integer> binaryTree;

    @BeforeEach
    public void setUp() {
        binaryTree = new BinaryTree();
        binaryTree.clear();
    }

    @Test
    public void shouldAddElements() {
        binaryTree.add(29);

        assertNotNull(binaryTree.root());
        assertEquals(1, binaryTree.size());
        assertEquals(Integer.valueOf(29), binaryTree.root().getValue());

        binaryTree.add(7);
        binaryTree.add(65);
        binaryTree.add(25);
        binaryTree.add(44);
        binaryTree.add(94);

        assertNotNull(binaryTree.root());
        assertEquals(6, binaryTree.size());

        BinaryTreeNode<Integer> leftNode = binaryTree.root().getLeftChild();
        BinaryTreeNode<Integer> rightNode = binaryTree.root().getRightChild();

        assertNotNull(leftNode);
        assertNotNull(rightNode);
        assertEquals(Integer.valueOf(7), leftNode.getValue());
        assertEquals(Integer.valueOf(65), rightNode.getValue());

        assertNull(leftNode.getLeftChild());
        assertNotNull(leftNode.getRightChild());
        assertEquals(Integer.valueOf(25), leftNode.getRightChild().getValue());

        assertEquals(Integer.valueOf(44), rightNode.getLeftChild().getValue());
        assertEquals(Integer.valueOf(94), rightNode.getRightChild().getValue());

        binaryTree.add(3);
        binaryTree.add(52);
        binaryTree.add(77);
        binaryTree.add(33);

        assertEquals(10, binaryTree.size());
        assertEquals(Integer.valueOf(3), leftNode.getLeftChild().getValue());

        BinaryTreeNode<Integer> leftRight = rightNode.getLeftChild();
        assertNotNull(leftRight);
        assertEquals(Integer.valueOf(33), leftRight.getLeftChild().getValue());
        assertEquals(Integer.valueOf(52), leftRight.getRightChild().getValue());

        BinaryTreeNode<Integer> rightRight = rightNode.getRightChild();
        assertEquals(Integer.valueOf(77), rightRight.getLeftChild().getValue());
    }

    @Test
    public void shouldRemoveElements() {
        addInitialElements();

        assertEquals(10, binaryTree.size());

        binaryTree.remove(3);
        binaryTree.remove(52);
        assertEquals(8, binaryTree.size());

        BinaryTreeNode<Integer> right = binaryTree.root().getRightChild();
        BinaryTreeNode<Integer> leftRight = right.getLeftChild();
        assertNull(leftRight.getRightChild());

        binaryTree.remove(44);
        assertEquals(7, binaryTree.size());

        binaryTree.remove(7);
        assertEquals(6, binaryTree.size());

        binaryTree.remove(65);
        assertEquals(5, binaryTree.size());

        binaryTree.remove(29);
        assertEquals(4, binaryTree.size());

        BinaryTreeNode<Integer> root = binaryTree.root();
        assertEquals(Integer.valueOf(33), root.getValue());
    }

    @Test
    public void shouldContainsElements() {
        assertFalse(binaryTree.contains(29));
        binaryTree.add(29);
        assertTrue(binaryTree.contains(29));

        binaryTree.add(7);
        binaryTree.add(65);
        assertTrue(binaryTree.contains(7));
        assertTrue(binaryTree.contains(65));

        binaryTree.add(25);
        binaryTree.add(44);
        binaryTree.add(94);
        binaryTree.add(3);
        binaryTree.add(52);
        binaryTree.add(77);
        binaryTree.add(33);

        assertTrue(binaryTree.contains(44));
        assertFalse(binaryTree.contains(54));

        binaryTree.remove(52);
        binaryTree.remove(77);
        assertFalse(binaryTree.contains(52));
        assertFalse(binaryTree.contains(77));
    }

    @Test
    public void shouldBeEmpty() {
        assertTrue(binaryTree.isEmpty());

        binaryTree.add(29);
        assertFalse(binaryTree.isEmpty());

        binaryTree.remove(29);
        assertTrue(binaryTree.isEmpty());

        binaryTree.add(29);
        binaryTree.add(65);
        binaryTree.add(7);

        binaryTree.clear();
        assertTrue(binaryTree.isEmpty());
    }

    @Test
    public void shouldBeLeaf() {
        binaryTree.add(29);
        assertTrue(binaryTree.isLeaf(29));

        binaryTree.add(7);
        binaryTree.add(65);
        assertFalse(binaryTree.isLeaf(29));
        assertTrue(binaryTree.isLeaf(7));
        assertTrue(binaryTree.isLeaf(65));

        binaryTree.add(25);
        assertFalse(binaryTree.isLeaf(7));
        assertTrue(binaryTree.isLeaf(25));
    }

    private void addInitialElements() {
        binaryTree.add(29);
        binaryTree.add(7);
        binaryTree.add(65);
        binaryTree.add(25);
        binaryTree.add(44);
        binaryTree.add(94);
        binaryTree.add(3);
        binaryTree.add(52);
        binaryTree.add(77);
        binaryTree.add(33);
    }
}
