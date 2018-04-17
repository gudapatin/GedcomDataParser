package com.gedcom.io;

import com.gedcom.entity.Node;
import com.gedcom.exception.GedcomDataParserException;

/**
 * An interface to define methods of process input .
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public interface IGedcomInputProcessor {    
	
   /** Parses the input file
     * @param file
     * @return Node
     * @throws GedcomParserException
     */
    Node parseInput(String file) throws GedcomDataParserException;
}
