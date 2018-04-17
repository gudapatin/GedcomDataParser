package com.gedcom.parser;

import com.gedcom.entity.Node;
import com.gedcom.exception.GedcomDataParserException;

/**
 * The class helps the user .
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class GedcomTreeNode {
    private static GedcomTreeNode treenode = null;
    private Node root = null;
    private Node currentNode = null;

    private GedcomTreeNode() {
        super();
        root = new Node(-1, null, null, "1");
        currentNode = root;
    }

    public static GedcomTreeNode getTreeNode() {
        if (treenode == null) {
        	treenode = new GedcomTreeNode();
        }
        return treenode;
    }

    /**
     * method to extract node
     * @param node
     * @throws GedcomDataParserException
     */

	public void extractNode(Node node) {
        if (node.getLevel() == 0) {
            root.addChild(node);
            node.setParent(root);
        } else if (currentNode.getLevel() < node.getLevel()) {
            currentNode.addChild(node);
            node.setParent(currentNode);
        } else if (currentNode.getLevel() > node.getLevel()) {
            Node n = findNode(node.getLevel() - 1);
            n.addChild(node);
            node.setParent(n);
        } else if (currentNode.getLevel() == node.getLevel()) {
            currentNode.getParent().addChild(node);
            node.setParent(currentNode.getParent());
        }
        currentNode = node;
    }

    /**
     * Method to find the node having the given level starting from current node
     * and traversing till its root.
     * 
     * @param referenceNode
     *            - node from which search has to be started
     * @param expectedLevel
     *            - level to be searched for
     * @return
     */
    private Node findNode(int nodeLevel) {
        Node node = currentNode.getParent();
        while (node != null) {
            if (node.getLevel() <= nodeLevel) {
                break;
            } else {
                node = node.getParent();
            }
        }
        return node;
    }

    /**
     * API to retrieve the root node 
     * 
     * @return
     */
    public Node getRoot() {
        return root;
    }
}
