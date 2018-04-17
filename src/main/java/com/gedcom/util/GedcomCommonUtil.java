package com.gedcom.util;

import com.gedcom.exception.GedcomDataParserException;
import com.gedcom.io.IGedcomInputProcessor;
import com.gedcom.io.GedcomInputProcessorFactory;
import com.gedcom.io.IGedcomTransformer;
import com.gedcom.io.GedcomTransformerFactory;


/**
 * A utility class for the input processor and transformer .
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class GedcomCommonUtil {
	/**
     * Method to get the reader
     * 
     * @return
     * @throws GedcomDataParserException */
     
    public static IGedcomInputProcessor getInputProcessor() throws GedcomDataParserException {
    	
    	IGedcomInputProcessor inputProcessor = GedcomInputProcessorFactory.getInputProcessor(GedcomConstants.TEXT_PROCESSOR);               
        if (inputProcessor == null) {
            throw new GedcomDataParserException(GedcomConstants.INPUT_PROCESSOR_NOT_FOUND);
        }
        return inputProcessor;
    }

    /**
     * Method to get the writer
     * 
     * @return
     * @throws GedcomDataParserException
     */
    public static IGedcomTransformer getTransformer() throws GedcomDataParserException {
    	IGedcomTransformer xmlWriter = GedcomTransformerFactory.getTransformer(GedcomConstants.OUTPUT_PROCESSOR);               
        if (xmlWriter == null) {
            throw new GedcomDataParserException(GedcomConstants.TRANSFORMER_NOT_FOUND);
        }
        return xmlWriter;
    }

}

