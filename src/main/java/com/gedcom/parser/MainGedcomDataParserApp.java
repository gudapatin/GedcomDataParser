package com.gedcom.parser;

import java.io.File;
import java.util.logging.Logger;

import com.gedcom.util.GedcomConstants;



/**
 * The class helps the user .
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class MainGedcomDataParserApp {
    private static final Logger logger = Logger.getLogger(MainGedcomDataParserApp.class.getSimpleName());

	
	private static  String inputTextFile = GedcomConstants.DEFAULT_INPUT_FILE;
	private static  String outputXMLFile = GedcomConstants.DEFAULT_OUTPUT_FILE;
	
	 public static void main(String[] args) {
		 
		 if(args.length == 2){
			 inputTextFile = args[0];
			 outputXMLFile = args[1];
			 logger.info(String.format("Input TXT File: %s  %n Output File: %s",
					 getAbsoluteFilePath(inputTextFile), getAbsoluteFilePath(outputXMLFile)));
			}
			else if(args.length == 1){		
				inputTextFile = args[0];
				logger.info(String.format("Input TXT File %s is parsed %n the output file default location is %s",
						 getAbsoluteFilePath(inputTextFile), getAbsoluteFilePath(outputXMLFile)));
			}
			else {
				 File file = new File(inputTextFile);
				 logger.info(String.format("Input TXT File: %s  %n Output File: %s",
						 getAbsoluteFilePath(inputTextFile), getAbsoluteFilePath(outputXMLFile))); 
			}
	 
		 GedcomParser gsf = new GedcomParser();
		 gsf.parse(inputTextFile, outputXMLFile,GedcomConstants.PARSER_TYPE);
	
	        

	 }	 
	 public static String getAbsoluteFilePath(String inputFile) {
		 File file = new File(inputFile);		 
		 return file.getAbsolutePath();
		 
		 
	 }

	      
}
