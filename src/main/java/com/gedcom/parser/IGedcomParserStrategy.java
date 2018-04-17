
package com.gedcom.parser;
import java.io.File;


/**
 * Interface for the GedcomParser
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public interface IGedcomParserStrategy{
	 
	/** default method for the interface
	 * @param inputFile
	 * @return boolean
	 */
	public default boolean validateInputFile(File inputFile){
		if(inputFile.exists() && inputFile.canRead() && (inputFile.length() !=0)){
			System.out.println("Valid File :: Parsing document.");
			return true;
		}

		System.out.println("######## Please give a valid input File #########");
		return false;
	}	
	
    /**
     * @param inputFilePath
     * @param outputFilePath
     */
    public void parseDocument(String inputFilePath, String outputFilePath) ;

}