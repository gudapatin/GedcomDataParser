package com.gedcom.parser;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.gedcom.entity.Node;
import com.gedcom.exception.GedcomDataParserException;
import com.gedcom.util.GedcomCommonUtil;

/**
 * Concrete class for parser tree strategy
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class GedcomParserTreeStrategy implements IGedcomParserStrategy {

    private static final Logger logger = Logger.getLogger(GedcomParser.class.getSimpleName());
	/**
     * Method handling the parsing of input data and conversion to required
     * output format
     */
    public void parseDocument(String inputFilePath, String outputFilePath) {
        logger.log(Level.INFO, "Gedcom data parsing started");
       Node treeNode = null;
        try {
        	treeNode = GedcomCommonUtil.getInputProcessor().parseInput(inputFilePath);
            if (treeNode != null) {
            	GedcomCommonUtil.getTransformer().convert(outputFilePath, treeNode);
            }
        } catch (GedcomDataParserException e) {
            logger.log(Level.SEVERE, "\n \n****Incorrect input file.Please check the path and format of input file.Conversion failed.*****\n");
        }
        logger.log(Level.INFO, "Gedcom Data parsing completed.");
    }

}
