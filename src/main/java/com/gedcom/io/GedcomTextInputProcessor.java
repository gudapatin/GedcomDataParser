package com.gedcom.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.gedcom.entity.Node;
import com.gedcom.exception.GedcomDataParserException;
import com.gedcom.parser.GedcomTreeNode;
import com.gedcom.util.GedcomConstants;

/**
 * The class parses and processes the given input text file 
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class GedcomTextInputProcessor implements IGedcomInputProcessor{

	 private static final Logger logger = Logger.getLogger(GedcomTextInputProcessor.class.getSimpleName());
	   
	    /* (non-Javadoc)
	     * @see com.gedcom.io.GedcomInputProcessor#parseInput(java.lang.String)
	     */
	    @Override
	    public Node parseInput(String file) throws GedcomDataParserException {
	        logger.log(Level.INFO, "Reading input gedcom data file");
	        GedcomTreeNode treeNode = GedcomTreeNode.getTreeNode();

	        try (Stream<String> inputStream = Files.lines(Paths.get(file), Charset.defaultCharset())) {
	            List<String> inputLines = inputStream
	                    .filter(inputData -> (inputData != null && !inputData.trim().isEmpty()))
	                    .collect(Collectors.toCollection(LinkedList::new));

	            for (String inputData : inputLines) {
	            	treeNode.extractNode(getInputNode(inputData));
	            }
	        } catch (IOException e) {
	            throw new GedcomDataParserException(GedcomConstants.INPUT_FILE_READING_ERROR);
	        }
	        logger.log(Level.INFO, "Reading input text data file completed.");
	        return treeNode.getRoot();
	    }

	    /**	     
	     * Converts the input text line to data node. If expected format is not present 
	     * exception is thrown
	     * 
	     * @param inputData
	     * @return getInputNode
	     * @throws GedcomParserException
	     */
	    private Node getInputNode(String inputData) throws GedcomDataParserException {
	        logger.log(Level.INFO, "Creating data node for input > " + inputData);
	        String[] inputStringArray = inputData.trim().split("\\s+", 3);
	        String id = null;
	        String tag = null;
	        String data = null;
	        int level = 0;
	        try {
	            level = Integer.parseInt(inputStringArray[0]);
	        } catch (NumberFormatException ex) {
	            throw new GedcomDataParserException(GedcomConstants.LEVEL_INFO_NOT_FOUND);
	        }
	        if (inputStringArray[1].matches(GedcomConstants.ID_REGEX)) {
	            id = inputStringArray[1];
	            if (inputStringArray.length == 3) {
	                tag = inputStringArray[2];
	            }
	        } else if (inputStringArray[1].matches(GedcomConstants.TAG_REGEX)) {
	            tag = inputStringArray[1];
	        } else {
	            throw new GedcomDataParserException(GedcomConstants.TAG_OR_ID_NOT_PRESENT);
	        }

	        if (inputStringArray.length == 3) {
	            data = inputStringArray[2];
	        }

	        Node node = new Node(level, id, tag, data);
	        return node;
	    }

	  
}
