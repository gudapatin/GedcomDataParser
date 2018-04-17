package com.gedcom.entity;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import com.gedcom.parser.GedcomTreeNode;

/**
 * The class helps the user .
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class GedcomTreeNodeTest {
    private static GedcomTreeNode treenode = null;

    @Before
    public void init() {
        treenode = GedcomTreeNode.getTreeNode();
    }

    @After
    public void deinit() {
        Whitebox.setInternalState(GedcomTreeNode.class, "treenode", (GedcomTreeNode) null);
        treenode = null;
    }

    @Test
    public void test_extractNodeWithoutChildren() {
        List<Node> expectedNodeList = new LinkedList<>();
        Node node = new Node(0, "@1234@", null, "data");
        expectedNodeList.add(treenode.getRoot());
        expectedNodeList.add(node);

        treenode.extractNode(node);

        // verification
        List<Node> actualNodeList = new LinkedList<>();
        retrieveNodesInOrder(actualNodeList, treenode.getRoot());
        Assert.assertEquals(actualNodeList.size(), expectedNodeList.size());
        Assert.assertTrue(actualNodeList.containsAll(expectedNodeList));
    }

    /**
     * Test to verify the tree data creation when single child comes for a node
     */
    @Test
    public void test_extractNodeWithSingleChild() {
        List<Node> expectedNodeList = new LinkedList<>();
        Node node1 = new Node(0, "@1234@", null, "parent");
        Node node2 = new Node(1, "@5678@", null, "child");
        expectedNodeList.add(treenode.getRoot());
        expectedNodeList.add(node1);
        expectedNodeList.add(node2);

        treenode.extractNode(node1);
        treenode.extractNode(node2);

        // verification
        List<Node> actualNodeList = new LinkedList<>();
        retrieveNodesInOrder(actualNodeList, treenode.getRoot());
        Assert.assertEquals(actualNodeList.size(), expectedNodeList.size());
        Assert.assertTrue(actualNodeList.containsAll(expectedNodeList));
    }

    /**
     * Test to verify the tree data creation when more than one children comes
     * for a node
     */
    @Test
    public void test_extractNodeWithMultipleChildren() {
        List<Node> expectedNodeList = new LinkedList<>();
        Node node1 = new Node(0, "@1234@", null, "parent");
        Node node2 = new Node(1, "@5678@", null, "first child");
        Node node3 = new Node(1, "@910@", null, "second child");
        Node node4 = new Node(2, "@112@", null, "third child");
        expectedNodeList.add(treenode.getRoot());
        expectedNodeList.add(node1);
        expectedNodeList.add(node2);
        expectedNodeList.add(node3);
        expectedNodeList.add(node4);

        treenode.extractNode(node1);
        treenode.extractNode(node2);
        treenode.extractNode(node3);
        treenode.extractNode(node4);

        // verification
        List<Node> actualNodeList = new LinkedList<>();
        retrieveNodesInOrder(actualNodeList, treenode.getRoot());
        Assert.assertEquals(actualNodeList.size(), expectedNodeList.size());
        Assert.assertTrue(actualNodeList.containsAll(expectedNodeList));
    }

    /**
     * Test to verify the tree data creation when more than one hierarchy exists
     * in the Gedcom data with multiple children in each for a node
     */
    @Test
    public void test_extractNodeWithMultipleHierarchies() {
        List<Node> expectedNodeList = new LinkedList<>();
        Node node1 = new Node(0, "@1234@", null, "first hierarchy");
        Node node2 = new Node(1, "@5678@", null, "first child");
        Node node3 = new Node(2, "@910@", null, "second child");
        Node node4 = new Node(0, "@12@", null, "second hierarchy");
        Node node5 = new Node(1, "@13@", null, "first child");
        Node node6 = new Node(2, "@14@", null, "second child");
        expectedNodeList.add(treenode.getRoot());
        expectedNodeList.add(node1);
        expectedNodeList.add(node2);
        expectedNodeList.add(node3);
        expectedNodeList.add(node4);
        expectedNodeList.add(node5);
        expectedNodeList.add(node6);

        treenode.extractNode(node1);
        treenode.extractNode(node2);
        treenode.extractNode(node3);
        treenode.extractNode(node4);
        treenode.extractNode(node5);
        treenode.extractNode(node6);

        // verification
        List<Node> actualNodeList = new LinkedList<>();
        retrieveNodesInOrder(actualNodeList, treenode.getRoot());
        Assert.assertEquals(actualNodeList.size(), expectedNodeList.size());
        Assert.assertTrue(actualNodeList.containsAll(expectedNodeList));
    }

    /**
     * Method to retrieve nodes in order for verification
     * 
     * @param node
     * @return
     */
    private List<Node> retrieveNodesInOrder(List<Node> actualNodeList, Node node) {
        actualNodeList.add(node);
        List<Node> children = node.getNodeList();
        if (children != null) {
            for (Node child : children) {
                retrieveNodesInOrder(actualNodeList, child);
            }
        }
        return actualNodeList;
    }

}
