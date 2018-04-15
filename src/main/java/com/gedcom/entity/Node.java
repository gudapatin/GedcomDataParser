package com.gedcom.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * An entity class to hold data which is in gedcom format.
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class Node {


    private int level;
    private String id;
    private String tag;
    private String data;
    private Node rootNode;
    private List<Node> nodeList;

    /**
     * @param level
     * @param id
     * @param tag
     * @param data
     */
    public Node(int level, String id, String tag, String data) {
        super();
        this.level = level;
        this.id = id;
        this.tag = tag;
        this.data = data;
    }

   
     
    /**
     * @param node
     */
    public void addChild(Node node) {
        if (nodeList == null) {
            nodeList = new LinkedList<Node>();
        }
        nodeList.add(node);
    }

    
    /**
     * @return level 
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return Id
     */
    public String getId() {
    	return id;
    }

    /**
     * @return Tag
     */
    public String getTag() {
    	return tag;
    }

    /**
     * @return
     */
    public String getData() {
    	return data;
    }

    /**
     * @return
     */
    public Node getParent() {
    	return rootNode;
    }

    /**
     * @param rootNode
     */
    public void setParent(Node rootNode) {
        this.rootNode = rootNode;
    }

    
    /**
     * @return
     */
    public List<Node> getNodeList() {
        return nodeList;
    }

    
    /**
     * @return
     */
    public boolean isTerminalNode() {
        return nodeList == null;
    }

   
    /**
     * @return
     */
    public boolean isInputAvailable() {
        return data != null && !data.isEmpty();
    }
}
