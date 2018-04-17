package com.gedcom.parser;

/**
 * The class parses the document .
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class GedcomParser{
		
	
		/**
		 * @param inputFile
		 * @param outputFile
		 * @param parseFormat
		 */
		public void parse(String inputFile, String outputFile,String parseFormat) {
		
			IGedcomParserStrategy gedcomParserTreeStrategy = GedcomParserStrategyFactory.getParserStrategy(parseFormat);

	        if (gedcomParserTreeStrategy != null) {
	             gedcomParserTreeStrategy.parseDocument(inputFile, outputFile);
	        } 
	    }
	
	

}

