package com.gedcom.util;

/**
 * The class holds all the constants used in the application.
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class GedcomConstants {
	public static final String OUTPUT_FILE_CONVERSION_ERROR = "Could not write to output file.";
	public static final String INPUT_FILE_CONVERSION_ERROR = "Failed to convert data to xml";
	public static final String INPUT_PROCESSOR_NOT_FOUND =  "Input processor is not found";
	public static final String TRANSFORMER_NOT_FOUND = "Output transformer format is not available" ;
	public static final String OUTPUT_FILE_CREATION_FAILED = "Unable to create the output file";
	public static final String TEXT_FILE_DATA_FORMAT ="Text file format is not valid.";
	public static final String INPUT_FILE_READING_ERROR ="Unable to read the input file.";
	public static final String LEVEL_INFO_NOT_FOUND =  "Level information not available.";
	public static final String TAG_OR_ID_NOT_PRESENT = "Tag or Id information is not available";
  
    public final static String XML_ID_ENTRY = "id";
    public final static String PARSER_TYPE="texttoxml";
    public final static String XML_VALUE_ENTRY = "value";
    public final static String TEXT_PROCESSOR ="textprocessor";
    public final static String OUTPUT_PROCESSOR ="outputprocessor";
    
	public static String DEFAULT_INPUT_FILE = "src/main/resources/GedcomDataInput.txt";
	public static String DEFAULT_OUTPUT_FILE = "output/GedcomParsedOutput.xml";
    public final static String ID_REGEX = "@.*@";
	public final static String TAG_REGEX = "[A-Z]{3,4}";
		
	public static String DEFAULT_INPUT_FILE_VALID_TEST = "src/test/resources/Gedcom_TestSample_Valid.txt";
	public static String DEFAULT_INPUT_FILE_INVALID_TEST = "src/test/resources/Gedcom_TestSample_Valid.txt";
    public static String DEFAULT_OUTPUT_FILE_TEST  = "src/test/resources/Gedcom_TestSampleOutput.xml";

		

		
	}

