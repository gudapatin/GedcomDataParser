package com.gedcom.io;

import java.util.HashMap;
import java.util.Map;

import com.gedcom.util.GedcomConstants;


/**
 * The factory class creates the gedcom transformers .
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class GedcomTransformerFactory {

 
private static Map <String,IGedcomTransformer> transformers = new HashMap<>();
static {
transformers.put(GedcomConstants.OUTPUT_PROCESSOR,
    new GedcomXMLTransformer());
}

/**
 * @param outputFormat
 * @return GedcomTransformer
 */
public static IGedcomTransformer getTransformer(String outputFormat) {
return transformers.get(outputFormat);
}

  
}
