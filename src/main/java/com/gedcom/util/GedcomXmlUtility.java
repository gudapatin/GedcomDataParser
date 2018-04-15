package com.gedcom.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gedcom.exception.GedcomDataParserException;


/**
 * Utility class used use for conversions and formats of XML .
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
        //TODO
        return new File ("TO DO");
    }



  
}
