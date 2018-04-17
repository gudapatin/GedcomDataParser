package com.gedcom.parser;

import java.util.HashMap;
import java.util.Map;

import com.gedcom.util.GedcomConstants;

/**
 * Factory class to build strategy  parsers .
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class GedcomParserStrategyFactory {

private static Map <String,IGedcomParserStrategy> parsers =
            new HashMap<>();
 static {
	 parsers.put(GedcomConstants.PARSER_TYPE,
                new GedcomParserTreeStrategy());
 }
 public static IGedcomParserStrategy getParserStrategy(String type) {
       return parsers.get(type);
 }
}


