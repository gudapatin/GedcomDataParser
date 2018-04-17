package com.gedcom.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.gedcom.entity.Node;
import com.gedcom.exception.GedcomDataParserException;

/**
 * The class helps the user .
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public final class GedcomXmlUtility {
	
    private static final Logger logger = Logger.getLogger(GedcomXmlUtility.class.getSimpleName());

    
    private GedcomXmlUtility() {
    	
    }
    /**
     * Method handling the creation of file
     * 
     * @param filePath
     * @return
     * @throws GedcomDataParserException
     */
    public static File createFile(String filePath) throws GedcomDataParserException {
        logger.log(Level.INFO, "Creating output XML file.");
        File outputFile = new File(filePath);
        if (outputFile.exists()) {
            outputFile.delete();
        }
        try {
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
            outputFile.createNewFile();
        } catch (IOException e) {
            throw new GedcomDataParserException(GedcomConstants.OUTPUT_FILE_CREATION_FAILED);
        }
        return outputFile;
    }

    /**
     * Method responsibilities :- 1. Traverse the data tree starting from root
     * node covering all nodes in order. 2. Write data of each node to output
     * XML.
     * 
     * @param streamWriter
     * @param node
     *            - root node of the data tree
     */
    public static void convertToXML(XMLStreamWriter streamWriter, Node node) throws GedcomDataParserException {
        try {
            formatFields(streamWriter, node);
            List<Node> children = node.getNodeList();
            if (children != null) {
                for (Node child : children) {
                    convertToXML(streamWriter, child);
                }
            }
            streamWriter.writeEndElement();
        } catch (XMLStreamException ex) {
            throw new GedcomDataParserException(GedcomConstants.INPUT_FILE_CONVERSION_ERROR);
        }
    }

    /**
     * Method responsible for formatting the data in XML format. Formatting
     * logic is as follows. Step 1: If both ID and tag is not present, a new XML
     * tag is started with data value. This is expected for root element GEDCOM.
     * Otherwise, a new XML tag is started with the tag element value. Step 2:
     * If id is present, then write the id as an attribute entry. Step 3: If
     * leaf node, then write the data value as characters. Step 4: If not leaf
     * node and if data is present, then write the data value as a value
     * attribute.
     * 
     * @param writer
     * @param node
     * @throws XMLStreamException
     */
    public static void formatFields(XMLStreamWriter streamWriter, Node node) throws XMLStreamException {
        logger.log(Level.FINE, "Converting data -> "
                + (node.getLevel() + " - " + node.getId() + " - " + node.getTag() + " - " + node.getData()));
        if (node.getId() == null && node.getTag() == null && node.isInputAvailable()) {
            streamWriter.writeStartElement(node.getData());
        } else if (node.getTag() != null) {
            streamWriter.writeStartElement(node.getTag());
        }
        if (node.getId() != null) {
            streamWriter.writeAttribute(GedcomConstants.XML_ID_ENTRY, node.getId());
        } else if (node.isTerminalNode() && node.isInputAvailable()) {
            streamWriter.writeCharacters(node.getData());
        } else if (!node.isTerminalNode() && node.isInputAvailable()) {
            streamWriter.writeAttribute(GedcomConstants.XML_VALUE_ENTRY, node.getData());
        }
    }
    

}
