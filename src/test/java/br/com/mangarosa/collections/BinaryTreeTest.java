package br.com.mangarosa.collections;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class BinaryTreeTest {

    private final Tree<Integer> binaryTree;

    public BinaryTreeTest(){
        this.binaryTree = new BinaryTree<>();
    }

    @BeforeEach
    public void init(){
        this.binaryTree.clear();
    }

    @Test
    public void shouldAddElements(){

        this.binaryTree.add(29);

        // checks if the root was updated
        assertNotNull(this.binaryTree.root());
        assertNotNull(this.binaryTree.root().getValue());
        assertEquals(1, this.binaryTree.size());
        assertEquals(Integer.valueOf(29), this.binaryTree.root().getValue());

        this.binaryTree.add(7);
        this.binaryTree.add(65);
        this.binaryTree.add(25);
        this.binaryTree.add(44);
        this.binaryTree.add(94);

        //Check if the root node exists and if the size of the tree has changed
        assertNotNull(this.binaryTree.root());
        assertNotNull(this.binaryTree.root().getValue());
        assertEquals(6, this.binaryTree.size());

        //Check if the left and right children are not null and if their value are correct
        BinaryTreeNode<Integer> leftNode = this.binaryTree.root().getLeftChild();
        BinaryTreeNode<Integer> rightNode = this.binaryTree.root().getRightChild();

        assertNotNull(leftNode);
        assertNotNull(rightNode);

        assertEquals(Integer.valueOf(7), leftNode.getValue());
        assertEquals(Integer.valueOf(65), rightNode.getValue());

        assertNull(leftNode.getLeftChild());
        assertNotNull(leftNode.getRightChild());
        assertNotNull(rightNode.getLeftChild());
        assertNotNull(rightNode.getRightChild());

        assertEquals(Integer.valueOf(25), leftNode.getRightChild().getValue());
        assertEquals(Integer.valueOf(44), rightNode.getLeftChild().getValue());
        assertEquals(Integer.valueOf(94), rightNode.getRightChild().getValue());

        //Check if the nodes are leaves
        assertNull(leftNode.getRightChild().getLeftChild());
        assertNull(leftNode.getRightChild().getRightChild());
        assertNull(rightNode.getLeftChild().getLeftChild());
        assertNull(rightNode.getLeftChild().getRightChild());
        assertNull(rightNode.getRightChild().getLeftChild());
        assertNull(rightNode.getRightChild().getRightChild());

        this.binaryTree.add(3);
        this.binaryTree.add(52);
        this.binaryTree.add(77);
        this.binaryTree.add(33);

        assertEquals(10, this.binaryTree.size());

        // left of the left of the root
        assertNotNull(leftNode.getLeftChild());
        assertEquals(Integer.valueOf(3), leftNode.getLeftChild().getValue());

        //check the left of the right of the root
        BinaryTreeNode<Integer> leftRight = rightNode.getLeftChild();

        //check if it is not null and it is not a leaf
        assertNotNull(leftRight);
        assertNotNull(leftRight.getLeftChild());
        assertNotNull(leftRight.getRightChild());

        //check on its children
        assertEquals(Integer.valueOf(33), leftRight.getLeftChild().getValue());
        assertEquals(Integer.valueOf(52), leftRight.getRightChild().getValue());

        //check if they are leaves
        assertNull(leftRight.getLeftChild().getLeftChild());
        assertNull(leftRight.getLeftChild().getRightChild());
        assertNull(leftRight.getRightChild().getLeftChild());
        assertNull(leftRight.getRightChild().getRightChild());


        //check the right of the right of the root
        BinaryTreeNode<Integer> rightRight = rightNode.getRightChild();

        //check if it is not null and it is not a leaf
        assertNotNull(rightRight);
        assertNotNull(rightRight.getLeftChild());
        assertNull(rightRight.getRightChild());

        //check on its children
        assertEquals(Integer.valueOf(77), rightRight.getLeftChild().getValue());

        //check if they are leaves
        assertNull(rightRight.getLeftChild().getLeftChild());
        assertNull(rightRight.getLeftChild().getRightChild());
    }

    @Test
    public void shouldRemoveElements(){

    }
}
