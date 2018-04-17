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
		 
         System.out.println("Usage: java GEDCOMToXMLApp <GEDCOMInputFilename.txt> <XMLOutputFilename.xml>");
	 
		 if(args.length == 2){
			 inputTextFile = args[0];
			 outputXMLFile = args[1];
			}
			else if(args.length == 1){		
				inputTextFile = args[0];
				logger.info(String.format("Input TXT File %s is parsed %n the output file default location is %s",
						 getAbsoluteFilePath(inputTextFile), getAbsoluteFilePath(outputXMLFile)));
			}
			else {
				 File file = new File(inputTextFile);
				 System.out.println(" file.getAbsolutePath()"+ file.getAbsolutePath());
				 
			}
		 
		 GedcomParser gsf = new GedcomParser();
		 gsf.parse(inputTextFile, outputXMLFile,GedcomConstants.PARSER_TYPE);
		 System.out.println(String.format("Input TXT File %s is parsed %n the output XML  created is %s",
		 getAbsoluteFilePath(inputTextFile), getAbsoluteFilePath(outputXMLFile)));
	        

	 }	 
	 public static String getAbsoluteFilePath(String inputFile) {
		 File file = new File(inputFile);		 
		 return file.getAbsolutePath();
		 
		 
	 }

	      
}
