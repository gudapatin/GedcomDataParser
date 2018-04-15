package com.gedcom.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;


/**
 * The class helps the user .
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class NodeTest {
	   @Test
	    public void testNodeCreator() {
	        System.out.println("Running NodeTestCases::testNodeCreator");
	        Node node = new Node(0, "@I0001@", "INDI", null);
	        assertEquals(node.getLevel(), 0);
	        assertEquals(node.getId(), "@I0001@");
	        assertEquals(node.getTag(), "INDI");
	        assertEquals(node.getData(), null);
	    }

	    @Test
	    public void testRootNode() {
	        System.out.println("Running NodeTestCases::testRootNode");
	        Node node = new Node(0, "@I0001@", "INDI", "TEST VALUE");

	        assertEquals(node.getLevel(), 0);
	        assertEquals(node.getId(), "@I0001@");
	        assertEquals(node.getTag(), "INDI");
	        assertEquals(node.getData(), "TEST VALUE");
	        Assert.assertTrue(node.isTerminalNode());

	    }
    /**
     * Test to verify the Node APIs for a node without any children
     */
    @Test
    public void test_NodeWithoutSibling() {
        Node node = new Node(0, "@I421@", null, "testdata");
        Assert.assertTrue(node.isTerminalNode());
        Assert.assertNull(node.getNodeList());
        Assert.assertTrue(node.isInputAvailable());
    }

    /**
     * Test to verify the Node APIs for a node with children
     */
    @Test
    public void test_NodeWithSibling() {
        Node node = new Node(0, "@I456@", null, "");
        Node child = new Node(1, "TAG", null, "child");
        node.addChild(child);

        Assert.assertFalse(node.isTerminalNode());
        Assert.assertEquals(node.getNodeList().get(0), child);
        Assert.assertFalse(node.isInputAvailable());
    }



}
