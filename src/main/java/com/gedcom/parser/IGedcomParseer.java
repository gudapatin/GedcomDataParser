package com.gedcom.parser;

import java.io.File;

/**
 * Interface to parse the document .
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public interface IGedcomParseer{
	
	//default method	
	public default boolean validateInputFile(File inputFile){
		if(inputFile.exists() && inputFile.canRead() && (inputFile.length() !=0)){
			System.out.println("Valid File :: Parsing document.");
			return true;
		}

		System.out.println("######## Please give a valid input File #########");
		return false;
	}
	

public void parseDocument(String inputFile, String outputFile);

}
	