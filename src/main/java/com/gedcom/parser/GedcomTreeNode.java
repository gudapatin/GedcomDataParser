package com.gedcom.parser;

import com.gedcom.entity.Node;
import com.gedcom.exception.GedcomDataParserException;

/**
 * Class responsible for creating a tree out of the data provided.
 * 
 * @author ngudapat
 *
 */


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
     * API to add node into data tree Step 1: When a node comes with level as 0,
     * a new subtree is started from root. Step 2: If the incoming node's level
     * is greater than the current node, then the new node is added as a child
     * to the current node. Step 3: If the incoming node's level is lesser than
     * the current node, then the new node is added as child to the node whose
     * level is one less than new node's level. Step 4: If the incoming and
     * current node has same level, then the new node is added as a child to the
     * parent of the current node itself.
     * 
     * currentNode indicates the last data node added to the tree.
     * 
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
