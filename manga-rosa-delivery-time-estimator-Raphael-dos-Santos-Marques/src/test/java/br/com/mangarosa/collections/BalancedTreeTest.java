package br.com.mangarosa.collections;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import static org.junit.Assert.*;

public class BalancedTreeTest {

    private final Tree<Integer> balancedTree;

    public BalancedTreeTest() {
        balancedTree = new BalancedTree();
    }

    @BeforeEach
    public void init() {
        balancedTree.clear();
    }

    @Test
    public void testAddElements() {
        assertNull(balancedTree.root());

        balancedTree.add(3);
        assertEquals(Integer.valueOf(3), balancedTree.root().getValue());
        assertEquals(1, balancedTree.size());

        balancedTree.add(7);
        assertEquals(2, balancedTree.size());
        assertEquals(Integer.valueOf(7), balancedTree.root().getRightChild().getValue());

        balancedTree.add(25);
        assertEquals(3, balancedTree.size());
        assertEquals(Integer.valueOf(25), balancedTree.root().getRightChild().getRightChild().getValue());

        balancedTree.add(29);
        balancedTree.add(33);
        assertEquals(Integer.valueOf(33), balancedTree.root().getRightChild().getRightChild().getRightChild().getValue());

        balancedTree.add(44);
        assertEquals(Integer.valueOf(44), balancedTree.root().getRightChild().getRightChild().getRightChild().getRightChild().getValue());

        balancedTree.add(52);
        balancedTree.add(65);
        balancedTree.add(77);
        balancedTree.add(94);
    }

    @Test
    public void testRemoveElements() {
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

        balancedTree.remove(94);
        balancedTree.remove(77);
        assertEquals(Integer.valueOf(65), balancedTree.root().getRightChild().getRightChild().getValue());

        balancedTree.remove(44);
        assertEquals(Integer.valueOf(52), balancedTree.root().getRightChild().getValue());

        balancedTree.remove(25);
        balancedTree.remove(3);
        balancedTree.remove(7);
        assertEquals(Integer.valueOf(52), balancedTree.root().getValue());
    }

    @Test
    public void testContainsElements() {
        assertFalse(balancedTree.contains(29));
        balancedTree.add(29);
        assertTrue(balancedTree.contains(29));

        balancedTree.add(7);
        balancedTree.add(65);
        assertTrue(balancedTree.contains(7));
        assertTrue(balancedTree.contains(65));

        assertFalse(balancedTree.contains(54));

        balancedTree.remove(29);
        assertFalse(balancedTree.contains(29));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(balancedTree.isEmpty());
        balancedTree.add(29);
        assertFalse(balancedTree.isEmpty());

        balancedTree.remove(29);
        assertTrue(balancedTree.isEmpty());
    }

    @Test
    public void testIsLeaf() {
        balancedTree.add(29);
        assertTrue(balancedTree.isLeaf(29));

        balancedTree.add(7);
        balancedTree.add(65);
        assertTrue(balancedTree.isLeaf(65));

        balancedTree.add(25);
        assertFalse(balancedTree.isLeaf(7));
    }

    @Test
    public void testConvertToList() {
        assertNull(balancedTree.toList());

        balancedTree.add(29);
        balancedTree.add(7);
        balancedTree.add(65);
        assertEquals(List.of(7, 29, 65), balancedTree.toList());

        balancedTree.add(25);
        balancedTree.add(44);
        assertEquals(List.of(7, 25, 29, 44, 65), balancedTree.toList());

        balancedTree.clear();
        assertNull(balancedTree.toList());
    }

    @Test
    public void testClearTree() {
        balancedTree.add(29);
        balancedTree.add(7);
        balancedTree.add(65);
        balancedTree.add(25);
        balancedTree.add(44);

        balancedTree.clear();
        assertNull(balancedTree.root());
    }
}
