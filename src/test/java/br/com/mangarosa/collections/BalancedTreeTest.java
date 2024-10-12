package br.com.mangarosa.collections;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.Assert.*;

public class BalancedTreeTest {

    private final Tree<Integer> balancedTree;

    public BalancedTreeTest(){
        balancedTree = new BalancedTree();
    }

    @BeforeEach
    public void init(){
        balancedTree.clear();
    }

    @Test
    public void shouldAddElements(){

        assertNull(balancedTree.root());

        balancedTree.add(3);

        // checks if the root was updated
        assertNotNull(balancedTree.root());
        assertNotNull(balancedTree.root().getValue());
        assertEquals(1, balancedTree.size());
        assertEquals(Integer.valueOf(3), balancedTree.root().getValue());

        balancedTree.add(7);

        BinaryTreeNode<Integer> rightNode = balancedTree.root().getRightChild();
        assertNotNull(balancedTree.root());
        assertNotNull(balancedTree.root().getValue());
        assertEquals(Integer.valueOf(3), balancedTree.root().getValue());

        assertEquals(2, balancedTree.size());

        assertNotNull(rightNode);
        assertEquals(Integer.valueOf(7), rightNode.getValue());

        balancedTree.add(25);

        assertEquals(3, balancedTree.size());
        assertNotNull(balancedTree.root());
        BinaryTreeNode<Integer> leftNode = balancedTree.root().getLeftChild();
        rightNode = balancedTree.root().getRightChild();

        assertEquals(Integer.valueOf(7), balancedTree.root().getValue());
        assertNotNull(leftNode);
        assertNotNull(rightNode);

        assertEquals(Integer.valueOf(7), balancedTree.root().getValue());
        assertEquals(Integer.valueOf(3), leftNode.getValue());
        assertEquals(Integer.valueOf(25), rightNode.getValue());

        balancedTree.add(29);
        balancedTree.add(33);

        leftNode = balancedTree.root().getLeftChild();
        rightNode = balancedTree.root().getRightChild();

        assertEquals(Integer.valueOf(3), leftNode.getValue());
        assertEquals(Integer.valueOf(29), rightNode.getValue());
        assertEquals(Integer.valueOf(25), rightNode.getLeftChild().getValue());
        assertEquals(Integer.valueOf(33), rightNode.getRightChild().getValue());

        balancedTree.add(44);

        leftNode = balancedTree.root().getLeftChild();
        rightNode = balancedTree.root().getRightChild();

        assertEquals(Integer.valueOf(29), balancedTree.root().getValue());
        assertEquals(Integer.valueOf(7), leftNode.getValue());
        assertEquals(Integer.valueOf(33), rightNode.getValue());

        assertNotNull(leftNode.getLeftChild());
        assertNotNull(leftNode.getRightChild());
        assertEquals(Integer.valueOf(3), leftNode.getLeftChild().getValue());
        assertEquals(Integer.valueOf(25), leftNode.getRightChild().getValue());

        assertNull(rightNode.getLeftChild());
        assertNotNull(rightNode.getRightChild());
        assertEquals(Integer.valueOf(44), rightNode.getRightChild().getValue());

        balancedTree.add(52);

        rightNode = balancedTree.root().getRightChild();
        assertNotNull(rightNode);
        assertEquals(Integer.valueOf(44), rightNode.getValue());
        assertNotNull(rightNode.getLeftChild());
        assertNotNull(rightNode.getRightChild());
        assertEquals(Integer.valueOf(33), rightNode.getLeftChild().getValue());
        assertEquals(Integer.valueOf(52), rightNode.getRightChild().getValue());

        balancedTree.add(65);
        balancedTree.add(77);

        assertNotNull(rightNode.getRightChild());
        assertEquals(Integer.valueOf(65), rightNode.getRightChild().getValue());

        BinaryTreeNode<Integer> rightRight = rightNode.getRightChild();

        assertNotNull(rightRight.getLeftChild());
        assertNotNull(rightRight.getRightChild());

        assertEquals(Integer.valueOf(52), rightRight.getLeftChild().getValue());
        assertEquals(Integer.valueOf(77), rightRight.getRightChild().getValue());

        balancedTree.add(94);

        rightNode = balancedTree.root().getRightChild();

        assertNotNull(rightNode);
        assertEquals(Integer.valueOf(65), rightNode.getValue());

        assertNotNull(rightNode.getLeftChild());
        assertNotNull(rightNode.getRightChild());
        assertEquals(Integer.valueOf(44), rightNode.getLeftChild().getValue());
        assertEquals(Integer.valueOf(77), rightNode.getRightChild().getValue());

        rightRight = rightNode.getRightChild();
        assertNull(rightRight.getLeftChild());
        assertNotNull(rightNode.getRightChild());

        assertEquals(Integer.valueOf(94), rightRight.getRightChild().getValue());

        BinaryTreeNode<Integer> leftRight = rightNode.getLeftChild();
        assertNotNull(leftRight.getLeftChild());
        assertNotNull(leftRight.getRightChild());

        assertEquals(Integer.valueOf(33), leftRight.getLeftChild().getValue());
        assertEquals(Integer.valueOf(52), leftRight.getRightChild().getValue());
    }

    @Test
    public void shouldRemoveElements(){

        balancedTree.add(3);
        balancedTree.add(7);
        balancedTree.add(25);
        balancedTree.add(29);
        balancedTree.add(33);
        balancedTree.add(44);
        balancedTree.add(52);
        balancedTree.add(65);
        balancedTree.add(77);
        balancedTree.add(94);

        BinaryTreeNode<Integer> root = balancedTree.root();

        balancedTree.remove(94);
        balancedTree.remove(77);

        assertNotNull(balancedTree.root());
        BinaryTreeNode<Integer> right = balancedTree.root().getRightChild();

        assertNotNull(right);
        assertEquals(Integer.valueOf(44), right.getValue());

        assertNotNull(right.getLeftChild());
        assertNotNull(right.getRightChild());
        assertEquals(Integer.valueOf(33), right.getLeftChild().getValue());
        assertEquals(Integer.valueOf(65), right.getRightChild().getValue());

        assertNull(right.getLeftChild().getLeftChild());
        assertNull(right.getLeftChild().getRightChild());

        assertNotNull(right.getRightChild().getLeftChild());
        assertNull(right.getRightChild().getRightChild());

        balancedTree.remove(44);

        right = balancedTree.root().getRightChild();
        assertNotNull(right);

        assertEquals(Integer.valueOf(52), right.getValue());

        assertNotNull(right.getLeftChild());
        assertNotNull(right.getRightChild());
        assertEquals(Integer.valueOf(33), right.getLeftChild().getValue());
        assertEquals(Integer.valueOf(65), right.getRightChild().getValue());

        assertNull(right.getLeftChild().getLeftChild());
        assertNull(right.getLeftChild().getRightChild());
        assertNull(right.getRightChild().getLeftChild());
        assertNull(right.getRightChild().getRightChild());

        balancedTree.remove(25);
        balancedTree.remove(3);
        balancedTree.remove(7);

        assertNotNull(balancedTree.root());
        assertEquals(Integer.valueOf(52), balancedTree.root().getValue());

        assertNotNull(balancedTree.root().getLeftChild());
        assertNotNull(balancedTree.root().getRightChild());
        assertEquals(Integer.valueOf(29), balancedTree.root().getLeftChild().getValue());
        assertEquals(Integer.valueOf(65), balancedTree.root().getRightChild().getValue());

        assertNull(balancedTree.root().getLeftChild().getLeftChild());
        assertNotNull(balancedTree.root().getLeftChild().getRightChild());
        assertEquals(Integer.valueOf(33), balancedTree.root().getLeftChild().getRightChild().getValue());

        assertNull(balancedTree.root().getRightChild().getLeftChild());
        assertNull(balancedTree.root().getRightChild().getRightChild());

    }

    @Test
    public void shouldContainsElements(){

        //tree is empty
        assertNull(balancedTree.root());
        assertFalse(balancedTree.contains(29));
        balancedTree.add(29);
        assertTrue(balancedTree.contains(29));

        //add two new nodes.
        balancedTree.add(7);
        balancedTree.add(65);
        assertTrue(balancedTree.contains(7));
        assertTrue(balancedTree.contains(65));

        //insert new nodes
        balancedTree.add(25);
        balancedTree.add(44);
        balancedTree.add(94);
        balancedTree.add(3);
        balancedTree.add(52);
        balancedTree.add(77);
        balancedTree.add(33);

        //contains and not contains
        assertTrue(balancedTree.contains(44));
        assertTrue(balancedTree.contains(33));
        assertFalse(balancedTree.contains(54));
        assertFalse(balancedTree.contains(35));
        assertFalse(balancedTree.contains(23));

        //contains, remove and not contains
        assertTrue(balancedTree.contains(52));
        assertTrue(balancedTree.contains(77));

        balancedTree.remove(52);
        balancedTree.remove(77);

        assertFalse(balancedTree.contains(52));
        assertFalse(balancedTree.contains(77));

        //contains, remove, not contains - root value
        assertTrue(balancedTree.contains(29));
        balancedTree.remove(29);
        assertFalse(balancedTree.contains(29));

        //not contains, add, contains
        assertFalse(balancedTree.contains(24));
        balancedTree.add(24);
        assertTrue(balancedTree.contains(24));
    }

    @Test
    public void shouldBeEmpty(){

        //empty tree
        assertNull(balancedTree.root());
        assertTrue(balancedTree.isEmpty());

        //add an element
        balancedTree.add(29);

        //not empty tree
        assertNotNull(balancedTree.root());
        assertTrue(balancedTree.contains(29));
        assertFalse(balancedTree.isEmpty());

        //insert new elements
        balancedTree.add(7);
        balancedTree.add(65);
        assertFalse(balancedTree.isEmpty());

        //partial removal of elements
        balancedTree.remove(65);
        balancedTree.remove(7);
        assertFalse(balancedTree.isEmpty());

        //complete removal of elements
        balancedTree.remove(29);
        assertTrue(balancedTree.isEmpty());

        balancedTree.add(29);
        balancedTree.add(65);
        balancedTree.add(7);

        assertFalse(balancedTree.isEmpty());

        balancedTree.clear();

        assertTrue(balancedTree.isEmpty());
    }

    @Test
    public void shouldBeLeaf(){

        // not exists
        assertNull(balancedTree.root());

        // exists and it is the unique node
        balancedTree.add(29);
        assertTrue(balancedTree.isLeaf(29));

        // exists and it is a leaf and the root node is not a leaf
        balancedTree.add(7);
        balancedTree.add(65);
        assertFalse(balancedTree.isLeaf(29));
        assertTrue(balancedTree.isLeaf(7));
        assertTrue(balancedTree.isLeaf(65));

        //insert new nodes
        balancedTree.add(25);
        assertFalse(balancedTree.isLeaf(29));
        assertFalse(balancedTree.isLeaf(7));
        assertTrue(balancedTree.isLeaf(65));

        balancedTree.add(44);
        assertFalse(balancedTree.isLeaf(29));
        assertFalse(balancedTree.isLeaf(7));
        assertFalse(balancedTree.isLeaf(65));


        balancedTree.add(94);
        balancedTree.add(3);
        balancedTree.add(52);
        balancedTree.add(77);
        balancedTree.add(33);

        assertFalse(balancedTree.isLeaf(29));
        assertFalse(balancedTree.isLeaf(7));
        assertFalse(balancedTree.isLeaf(65));
        assertFalse(balancedTree.isLeaf(44));
        assertFalse(balancedTree.isLeaf(94));
        assertTrue(balancedTree.isLeaf(3));
        assertTrue(balancedTree.isLeaf(25));
        assertTrue(balancedTree.isLeaf(33));
        assertTrue(balancedTree.isLeaf(52));
        assertTrue(balancedTree.isLeaf(77));

    }

    @Test
    public void shouldConvertToList(){

        //empty
        assertNull(balancedTree.toList());
        balancedTree.add(29);

        assertNotNull(balancedTree.toList());
        assertEquals(Integer.valueOf(29), balancedTree.toList().get(0));

        balancedTree.add(7);
        balancedTree.add(65);
        assertNotNull(balancedTree.toList());
        assertEquals(List.of(7, 29, 65), balancedTree.toList());

        balancedTree.add(25);
        balancedTree.add(44);
        assertNotNull(balancedTree.toList());
        assertEquals(List.of(7, 25, 29, 44, 65), balancedTree.toList());

        balancedTree.add(94);
        balancedTree.add(3);
        balancedTree.add(52);
        balancedTree.add(77);
        balancedTree.add(33);

        assertNotNull(balancedTree.toList());
        assertEquals(List.of(3, 7, 25, 29, 33, 44, 52, 65, 77, 94), balancedTree.toList());

        balancedTree.remove(29);
        balancedTree.remove(7);
        balancedTree.remove(65);
        balancedTree.remove(25);
        balancedTree.remove(44);
        assertNotNull(balancedTree.toList());
        assertEquals(List.of(3, 33, 52, 77, 94), balancedTree.toList());

        balancedTree.remove(94);
        balancedTree.remove(3);
        balancedTree.remove(52);
        balancedTree.remove(77);
        balancedTree.remove(33);

        assertNull(balancedTree.toList());
    }

    @Test
    public void shouldClearTheTree(){

        assertNull(balancedTree.root());

        balancedTree.add(29);
        assertNotNull(balancedTree.root());

        balancedTree.add(7);
        balancedTree.add(65);
        balancedTree.add(25);
        balancedTree.add(44);

        assertNotNull(balancedTree.root());

        balancedTree.clear();

        assertNull(balancedTree.root());
    }
}
