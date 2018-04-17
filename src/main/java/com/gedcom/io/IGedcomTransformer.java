package com.gedcom.io;

import com.gedcom.entity.Node;
import com.gedcom.exception.GedcomDataParserException;

/**
 * Interface to transform the text file to xml format
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public interface IGedcomTransformer {

    /**
     * transforms input file to required format
     * 
     * @param rootNode                 
     * @throws GedcomParserException
     */
    public void convert(String outputFilePath, Node rootNode) throws GedcomDataParserException;

}